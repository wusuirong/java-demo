package org.danny.demo.datastructure.line;

public class ElementPrinter implements ElementVisitor {

	public void visit(Object o) {
		System.out.println(o.toString());
	}
}
