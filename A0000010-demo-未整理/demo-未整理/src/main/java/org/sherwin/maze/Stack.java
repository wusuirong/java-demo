package org.sherwin.maze;

import java.util.ArrayList;
import java.util.List;

public class Stack {

	List<Record> list;
	
	public Stack() {
		list = new ArrayList<Record>();
	}
	
	public void push(Record record) {
		list.add(record);
	}
	
	public Record pop() {
		if (0 < list.size()) {
			return list.remove(list.size() - 1);
		} else {
			return null;
		}
	}
}
