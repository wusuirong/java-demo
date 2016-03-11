package org.sherwin.designpattern;

public class StateMachineImpl implements StateMachine {

	int state = NO_QUARTER;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void insertQuarter() {
		if (state == NO_QUARTER) {
			state = HAS_QUARTER;
			System.out.println("you insert a quarter");
		} else if (state == HAS_QUARTER) {
			System.out.println("already has a quarter");
		} else if (state == SOLD) {
			System.out.println("wait,we are given you a gumball");
		} else if (state == SOLDOUT) {
			System.out.println("sold out");
		}
	}

	public void pushHandler() {
		if (state == NO_QUARTER) {
			System.out.println("please insert a quarter");
		} else if (state == HAS_QUARTER) {
			System.out.println("you push the button,i will give you a gumball");
			dispence();
		} else if (state == SOLD) {
			System.out.println("wait,we are given you a gumball");
		} else if (state == SOLDOUT) {
			System.out.println("sold out");
		}
	}

	public void ejectQuarter() {
		if (state == NO_QUARTER) {
			state = HAS_QUARTER;
			System.out.println("insert a quarter");
		} else if (state == HAS_QUARTER) {
			System.out.println("already has a quarter");
		} else if (state == SOLD) {
			System.out.println("wait,we are given you a gumball");
		} else if (state == SOLDOUT) {
			System.out.println("sold out");
		}
	}
	
	protected void dispence() {
		if (state == NO_QUARTER) {
			state = HAS_QUARTER;
			System.out.println("insert a quarter");
		} else if (state == HAS_QUARTER) {
			System.out.println("already has a quarter");
		} else if (state == SOLD) {
			System.out.println("wait,we are given you a gumball");
		} else if (state == SOLDOUT) {
			System.out.println("sold out");
		}
	}

}
