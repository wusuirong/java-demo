类的构造顺序
总体思路就是先执行父类的静态初始化，再执行本类的静态初始化，然后执行父类的初始化，再执行本类的初始化


原文
作者：臧圩人（zangweiren） 
网址：http://zangweiren.javaeye.com/ 

>>>转载请注明出处！<<< 

大家在去参加面试的时候，经常会遇到这样的考题：给你两个类的代码，它们之间是继承的关系，每个类里只有构造器方法和一些变量，构造器里可能还有一段代码对变量值进行了某种运算，另外还有一些将变量值输出到控制台的代码，然后让我们判断输出的结果。这实际上是在考查我们对于继承情况下类的初始化顺序的了解。 

我们大家都知道，对于静态变量、静态初始化块、变量、初始化块、构造器，它们的初始化顺序依次是（静态变量、静态初始化块）>（变量、初始化块）>构造器。我们也可以通过下面的测试代码来验证这一点： 
Java代码 
public class InitialOrderTest {   
  
    // 静态变量   
    public static String staticField = "静态变量";   
    // 变量   
    public String field = "变量";   
  
    // 静态初始化块   
    static {   
        System.out.println(staticField);   
        System.out.println("静态初始化块");   
    }   
  
    // 初始化块   
    {   
        System.out.println(field);   
        System.out.println("初始化块");   
    }   
  
    // 构造器   
    public InitialOrderTest() {   
        System.out.println("构造器");   
    }   
  
    public static void main(String[] args) {   
        new InitialOrderTest();   
    }   
}  

运行以上代码，我们会得到如下的输出结果： 

1、静态变量 
2、静态初始化块 
3、变量 
4、初始化块 
5、构造器 

这与上文中说的完全符合。那么对于继承情况下又会怎样呢？我们仍然以一段测试代码来获取最终结果： 
Java代码 
class Parent {   
    // 静态变量   
    public static String p_StaticField = "父类--静态变量";   
    // 变量   
    public String p_Field = "父类--变量";   
  
    // 静态初始化块   
    static {   
        System.out.println(p_StaticField);   
        System.out.println("父类--静态初始化块");   
    }   
  
    // 初始化块   
    {   
        System.out.println(p_Field);   
        System.out.println("父类--初始化块");   
    }   
  
    // 构造器   
    public Parent() {   
        System.out.println("父类--构造器");   
    }   
}   
  
public class SubClass extends Parent {   
    // 静态变量   
    public static String s_StaticField = "子类--静态变量";   
    // 变量   
    public String s_Field = "子类--变量";   
    // 静态初始化块   
    static {   
        System.out.println(s_StaticField);   
        System.out.println("子类--静态初始化块");   
    }   
    // 初始化块   
    {   
        System.out.println(s_Field);   
        System.out.println("子类--初始化块");   
    }   
  
    // 构造器   
    public SubClass() {   
        System.out.println("子类--构造器");   
    }   
  
    // 程序入口   
    public static void main(String[] args) {   
        new SubClass();   
    }   
}  

运行一下上面的代码，结果马上呈现在我们的眼前： 

1、父类--静态变量 
2、父类--静态初始化块 
3、子类--静态变量 
4、子类--静态初始化块 
5、父类--变量 
6、父类--初始化块 
7、父类--构造器 
8、子类--变量 
9、子类--初始化块 
10、子类--构造器 

现在，结果已经不言自明了。大家可能会注意到一点，那就是，并不是父类完全初始化完毕后才进行子类的初始化，实际上子类的静态变量和静态初始化块的初始化是在父类的变量、初始化块和构造器初始化之前就完成了。 

那么对于静态变量和静态初始化块之间、变量和初始化块之间的先后顺序又是怎样呢？是否静态变量总是先于静态初始化块，变量总是先于初始化块就被初始化了呢？实际上这取决于它们在类中出现的先后顺序。我们以静态变量和静态初始化块为例来进行说明。 

同样，我们还是写一个类来进行测试： 
Java代码 
public class TestOrder {   
    // 静态变量   
    public static TestA a = new TestA();   
       
    // 静态初始化块   
    static {   
        System.out.println("静态初始化块");   
    }   
       
    // 静态变量   
    public static TestB b = new TestB();   
  
    public static void main(String[] args) {   
        new TestOrder();   
    }   
}   
  
class TestA {   
    public TestA() {   
        System.out.println("Test--A");   
    }   
}   
  
class TestB {   
    public TestB() {   
        System.out.println("Test--B");   
    }   
}  

运行上面的代码，会得到如下的结果： 

1、Test--A 
2、静态初始化块 
3、Test--B 

大家可以随意改变变量a、变量b以及静态初始化块的前后位置，就会发现输出结果随着它们在类中出现的前后顺序而改变，这就说明静态变量和静态初始化块是依照他们在类中的定义顺序进行初始化的。同样，变量和初始化块也遵循这个规律。 

了解了继承情况下类的初始化顺序之后，如何判断最终输出结果就迎刃而解了。