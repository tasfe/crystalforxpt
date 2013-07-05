package com.netblizzard.decode;

public class UnicodeToChs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = 
			//"error\n" +
				"license.err.init.FileNotFoundException = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(001)\n"
				+ "license.err.init.IOException = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(002)\n"
				+ "license.err.vf.FileNotFound = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(003)\n"
				+ "license.err.vf.SystemTimeError = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(004)\n"
				+ "license.err.pl.FileFormatError = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(005)\n"
				+ "license.err.pls.FileIllegality = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(006)\n"
				+ "license.err.getOp.NoPermission = \\u5f53\\u524dlicense\\u6ca1\\u6709\\u6388\\u6743\\u5bf9\\u8be5\\u6a21\\u5757\\u7684\\u4f7f\\u7528\\u6743\\u9650\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u7684\\u6388\\u6743\\u4fe1\\u606f\\u3002(007)\n"
				+ "license.err.vm.MachineMacError = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(008)\n"
				+ "license.err.vm.MachineCodeError = \\u4ea7\\u54c1\\u6388\\u6743\\u4fe1\\u606f\\u6821\\u9a8c\\u5931\\u8d25\\uff0c\\u8bf7\\u68c0\\u67e5License\\u6587\\u4ef6\\u662f\\u5426\\u6b63\\u786e\\u3002(009)\n"
				+ "license.err.demo.used = Demo\\u6587\\u4ef6\\u683c\\u5f0f\\u9519\\u8bef\\uff01\n"
				+ "license.err.demo.validate.file = Demo\\u6821\\u9a8c\\u9519\\u8bef\\uff01\n"
				+ "license.err.validate.module.TimeOutError = License\\u5df2\\u8fc7\\u671f\\uff0c\\u8bf7\\u8054\\u7cfb\\u5317\\u4eac\\u661f\\u7f51\\u9510\\u6377\\u7f51\\u7edc\\u6280\\u672f\\u6709\\u9650\\u516c\\u53f8\\u83b7\\u53d6\\u5ef6\\u671f\\u6216\\u8d2d\\u4e70\\u65b0\\u7684License\\u3002http://www.ruijie.com.cn (010)\n"
				+ "license.err.validate.SystemTimeError = \\u7528\\u6237\\u6216\\u67d0\\u4e9b\\u7a0b\\u5e8f\\u4fee\\u6539\\u8fc7\\u7cfb\\u7edf\\u65f6\\u95f4\\uff01\n"
				+ "license.err.validate.RemainNullError = License\\u5df2\\u8fc7\\u671f\\uff0c\\u8bf7\\u8054\\u7cfb\\u5317\\u4eac\\u661f\\u7f51\\u9510\\u6377\\u7f51\\u7edc\\u6280\\u672f\\u6709\\u9650\\u516c\\u53f8\\u83b7\\u53d6\\u5ef6\\u671f\\u6216\\u8d2d\\u4e70\\u65b0\\u7684License\\u3002http://www.ruijie.com.cn (011)\n"
				//+ "prompt at creat cert file \n"
				+ "license.frame.title = \\u83b7\\u53d6License\\u5e2e\\u52a9\n"
				+ "license.create.prompt.note = \\u5982\\u679c\\u60a8\\u9700\\u8981\\u4f7f\\u7528Focus\\uff0c\\u60a8\\u9700\\u8981\\u901a\\u8fc7\\u4ee5\\u4e0b\\u65b9\\u5f0f\\u6ce8\\u518c\\uff0c\\u8d2d\\u4e70\\u5b83\\u7684\\u8bb8\\u53ef\\u3002\n"
				+ "license.create.prompt.one = \\u751f\\u6210\\u7684\\u8bc1\\u4e66\\u9a8c\\u8bc1\\u6587\\u4ef6\\u540d\\u4e3acertificate.txt\\uff0c\\u5b58\\u653e\\u4e8e\\u201c\n"
				+ "license.create.prompt.two = \\u8bf7\\u4f7f\\u7528certificate.txt\\uff0c\\u5e76\\u8054\\u7cfb\\u5317\\u4eac\\u661f\\u7f51\\u9510\\u6377\\u7f51\\u7edc\\u6280\\u672f\\u6709\\u9650\\u516c\\u53f8\\u83b7\\u53d6License\\u3002\n"
				+ "license.create.prompt.three = \\u6536\\u5230\\u786e\\u8ba4\\u90ae\\u4ef6\\u540e\\uff0c\\u60a8\\u9700\\u8981\\u5c06\\u6536\\u5230\\u7684\\u6587\\u4ef6(license.lc)\\u590d\\u5236\\u5230\\u201c\n"
				+ "license.create.prompt.four = \\u76f4\\u63a5\\u8fd0\\u884c\\u201cFocus\\u201d\\uff0c\\u5373\\u53ef\\u6b63\\u5e38\\u4f7f\\u7528\\u3002\n"
				+ "license.create.prompt.end = \\u201d\\u6587\\u4ef6\\u5939\\u4e2d\\u3002";
		String[] strs = str.split("\n");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(uniToChs(strs[i]));
		}
	}

	private static String uniToChs(String src) {
		StringBuilder result = new StringBuilder();
		StringBuilder consult = new StringBuilder();
		consult.append(src);

		while(true) {
			int location = consult.indexOf("\\u");
			if(location == -1)
				break;
			result.append(loadConvert(consult.substring(location, location + 6)));
			consult.delete(location, location + 6);
		}
		return result.toString();
	}

	private static String loadConvert(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					outBuffer.append("\\" + aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static String chsToUni(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			String temp = "";
			int strInt = str.charAt(i);
			if (strInt > 127) {
				temp += "\\u" + Integer.toHexString(strInt);
			} else {
				temp = String.valueOf(str.charAt(i));
			}

			result += temp;
		}
		return result;
	}
}
