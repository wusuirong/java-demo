package com.sherwin.question.volatilekey;


public class VolatileObjectTest2 {
	/**
	 * 鎴愬憳鍙橀噺boolValue浣跨敤volatile鍜屼笉浣跨敤volatile浼氭湁鏄庢樉鍖哄埆镄勩€?链▼搴忛渶瑕佸璇曞嚑娆★紝灏辫兘鐭ラ亾涓よ€呬箣闂寸殑鍖哄埆镄勩€?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final VolatileObjectTest2 volObj = new VolatileObjectTest2();
		Thread t1 = new Thread() {
			public void run() {
				System.out.println("t1 start");
				for (;;) {
					volObj.waitToExit();
				}
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				System.out.println("t2 start");
				for (;;) {
					volObj.swap();
				}
			}
		};
		t2.start();
	}

	boolean boolValue = true;// 锷犱笂volatile 淇グ镄勬槸镞跺€欙紝绋嫔簭浼氩緢蹇€€鍑猴紝锲犱负volatile
						// 淇濊瘉鍚勪釜绾跨▼宸ヤ綔鍐呭瓨镄勫彉閲忓€煎拰涓诲瓨涓€镊淬€傛墍浠oolValue == !boolValue灏辨垚涓轰简鍙兘銆?

	public void waitToExit() {
		if (boolValue == false)
			System.exit(0);// 闱炲师瀛愭搷浣滐紝鐞呜涓婂簲璇ュ緢蹇细琚墦鏂€傚疄闄呬笉鏄紝锲犱负姝ゆ椂镄刡oolValue鍦ㄧ嚎绋嬭嚜宸卞唴閮ㄧ殑宸ヤ綔鍐呭瓨镄勬嫹璐濓紝锲犱负瀹冧笉浼氩己鍒跺拰涓诲瓨鍖哄烟鍚屾锛岀嚎绋?淇敼浜哹oolValue寰埚皯链夋満浼氢紶阃掑埌绾跨▼涓€镄勫伐浣滃唴瀛树腑銆傛墍浠ラ€犳垚浜嗗亣镄勨€滃师瀛愮幇璞♀€濄€?
	}

	public void swap() {// 涓嶆柇鍙嶅淇敼boolValue锛屼互链熸墦鏂嚎绋媡1.
		boolValue = false;
	}
}