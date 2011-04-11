package com.combanc.itsm.util;

import java.util.ArrayList;

public class StringFormat {
	private String formatStr;

	private ArrayList params = new ArrayList();

	public StringFormat(String str) {
		this.formatStr = str;
	}

	public void add(String v) {
		/* ${_ZVING_LICENSE_CODE_} */

		params.add(v);
	}

	public void add(long v) {
		add(String.valueOf(v));
	}

	public void add(int v) {
		add(String.valueOf(v));
	}

	public void add(float v) {
		add(String.valueOf(v));
	}

	public void add(double v) {
		add(String.valueOf(v));
	}

	public void add(Object v) {
		add(String.valueOf(v));
	}

	public String toString() {
		String[] arr = StringUtil.splitEx(formatStr, "?");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length - 1; i++) {
			sb.append(arr[i]);
			sb.append(params.get(i));
		}
		sb.append(arr[arr.length - 1]);
		return sb.toString();
	}

	public static void main(String[] args) {
		StringFormat sf = new StringFormat("c ? b ? a ");
		sf.add(1);
		sf.add(2);
		sf.add(3);
		System.out.println(sf);
	}
}
