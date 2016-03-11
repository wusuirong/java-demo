package com.sherwin.examples.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.Vector;

public class ShowComponent {

	public static void main(String[] args) {
		args = new String[]{"java.awt.Label", "java.awt.Button", "java.awt.Checkbox", 
				"java.awt.Choice", "java.awt.List", "java.awt.Scrollbar",
				"java.awt.TextField", "java.awt.TextArea"};
		//取得要显示的组件
		Vector components = getComponentsFromArgs(args);

		//创建主frame
		JFrame frame = new JFrame("ShowComponent");

		//处理窗口关闭事件，动作为退出系统
		frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

		//在主frame上创建一个菜单栏
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		//在菜单栏上创建一个选择外观主题的菜单选项
		JMenu plafmenu = createPlafMenu(frame);
		menubar.add(plafmenu);

		//创建一个tab容器
		JTabbedPane pane = new JTabbedPane();

		//把各个组件分别加入tab容器的每个tab
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component) components.elementAt(i);
			String classname = c.getClass().getName();
			String tabname = classname.substring(classname.lastIndexOf('.') + 1);
			pane.addTab(tabname, c);
		}

		//把tab容器加入主frame
		frame.getContentPane().add(pane);

		//自动调整主frame大小并显示
		frame.pack();
		frame.setVisible(true);

		//图形代码运行在独立的线程里，所以这里main方法结束了不影响界面运行
	}

	public static JMenu createPlafMenu(final JFrame frame) {
		//创建菜单
		JMenu plafmenu = new JMenu("Look and Feel");

		//创建一个单选组
		ButtonGroup radiogroup = new ButtonGroup();

		//取得系统能使用的外观主题列表
		UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();

		//遍历外观主题列表
		for (int i = 0; i < plafs.length; i++) {
			String plafName = plafs[i].getName();
			final String plafClassName = plafs[i].getClassName();

			//创建单选样式的菜单
			JMenuItem item = plafmenu.add(new JRadioButtonMenuItem(plafName));

			//设置点击事件
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//设置外观
						UIManager.setLookAndFeel(plafClassName);
						//刷新整个界面外观
						SwingUtilities.updateComponentTreeUI(frame);
						//自动调整主frame大小
						frame.pack();
					} catch (Exception ex) {
						System.err.println(ex);
					}
				}
			});

			//把菜单项加到单选组，限制它们只能单选
			radiogroup.add(item);
		}
		return plafmenu;
	}

	public static Vector getComponentsFromArgs(String[] args) {
		Vector components = new Vector(); // List of components to return
		Component component = null; // The current component
		PropertyDescriptor[] properties = null; // Properties of the component
		Object[] methodArgs = new Object[1]; // We'll use this below

		nextarg: // This is a labeled loop
		for (int i = 0; i < args.length; i++) { // Loop through all arguments
			// If the argument does not contain an equal sign, then it is
			// a component class name. Otherwise it is a property setting
			int equalsPos = args[i].indexOf('=');
			if (equalsPos == -1) { // Its the name of a component
				try {
					// Load the named component class
					Class componentClass = Class.forName(args[i]);
					// Instantiate it to create the component instance
					component = (Component) componentClass.newInstance();
					// Use JavaBeans to introspect the component
					// And get the list of properties it supports
					BeanInfo componentBeanInfo = Introspector.getBeanInfo(componentClass);
					properties = componentBeanInfo.getPropertyDescriptors();
				} catch (Exception e) {
					// If any step failed, print an error and exit
					System.out.println("Can't load, instantiate, " + "or introspect: " + args[i]);
					System.exit(1);
				}

				// If we succeeded, store the component in the vector
				components.addElement(component);
			} else { // The arg is a name=value property specification
				String name = args[i].substring(0, equalsPos); // property name
				String value = args[i].substring(equalsPos + 1); // property
				// value

				// If we don't have a component to set this property on, skip!
				if (component == null)
					continue nextarg;

				// Now look through the properties descriptors for this
				// component to find one with the same name.
				for (int p = 0; p < properties.length; p++) {
					if (properties[p].getName().equals(name)) {
						// Okay, we found a property of the right name.
						// Now get its type, and the setter method
						Class type = properties[p].getPropertyType();
						Method setter = properties[p].getWriteMethod();

						// Check if property is read-only!
						if (setter == null) {
							System.err.println("Property " + name + " is read-only");
							continue nextarg; // continue with next argument
						}

						// Try to convert the property value to the right type
						// We support a small set of common property types here
						// Store the converted value in an Object[] so it can
						// be easily passed when we invoke the property setter
						try {
							if (type == String.class) { // no conversion needed
								methodArgs[0] = value;
							} else if (type == int.class) { // String to int
								methodArgs[0] = Integer.valueOf(value);
							} else if (type == boolean.class) { // to boolean
								methodArgs[0] = Boolean.valueOf(value);
							} else if (type == Color.class) { // to Color
								methodArgs[0] = Color.decode(value);
							} else if (type == Font.class) { // String to
								// Font
								methodArgs[0] = Font.decode(value);
							} else {
								// If we can't convert, ignore the property
								System.err.println("Property " + name + " is of unsupported type " + type.getName());
								continue nextarg;
							}
						} catch (Exception e) {
							// If conversion failed, continue with the next arg
							System.err.println("Can't convert  '" + value + "' to type " + type.getName() + " for property " + name);
							continue nextarg;
						}

						// Finally, use reflection to invoke the property
						// setter method of the component we created, and pass
						// in the converted property value.
						try {
							setter.invoke(component, methodArgs);
						} catch (Exception e) {
							System.err.println("Can't set property: " + name);
						}

						// Now go on to next command-line arg
						continue nextarg;
					}
				}

				// If we get here, we didn't find the named property
				System.err.println("Warning: No such property: " + name);
			}
		}

		return components;
	}
}
