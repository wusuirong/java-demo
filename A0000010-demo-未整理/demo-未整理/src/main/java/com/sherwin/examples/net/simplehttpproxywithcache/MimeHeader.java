package com.sherwin.examples.net.simplehttpproxywithcache;
import java.util.*;

/**
 * MIME头是类似这样的格式
 * Content-Type: text/html
 * Content-length: 100
 * @author Administrator
 *
 */
public class MimeHeader extends Hashtable {
	void parse(String data) {
		StringTokenizer st = new StringTokenizer(data, "\r\n");

		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			int colon = s.indexOf(':');
			String key = s.substring(0, colon);
			String val = s.substring(colon + 2); // skip ": "
			put(key, val);
		}
	}

	MimeHeader() {
	}

	MimeHeader(String d) {
		parse(d);
	}

	public String toString() {
		String ret = "";
		Enumeration e = keys();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String val = (String) get(key);
			ret += key + ": " + val + "\r\n";
		}
		return ret;
	}

	// This simple function converts a mime string from
	// any variant of capitalization to a canonical form.
	// For example: CONTENT-TYPE or content-type to Content-Type,
	// or Content-length or CoNTeNT-LENgth to Content-Length.
	private String fix(String ms) {
		char chars[] = ms.toLowerCase().toCharArray();
		boolean upcaseNext = true;

		for (int i = 0; i < chars.length - 1; i++) {
			char ch = chars[i];
			if (upcaseNext && 'a' <= ch && ch <= 'z') {
				chars[i] = (char) (ch - ('a' - 'A'));
			}
			upcaseNext = ch == '-';
		}
		return new String(chars);
	}

	public String get(String key) {
		return (String) super.get(fix(key));
	}

	public void put(String key, String val) {
		super.put(fix(key), val);
	}
}