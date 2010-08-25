package crystal.common;

import javax.swing.JOptionPane;

/**
 * @author memo
 *
 */
public class InputVerify {
	
	/**
	 * 对传入的str是否为正整数做判断
	 * @param str
	 * @return 返回解析出来的数字，解析错误返回-1
	 */
	public static int verifyInt(String str) {
		int input = -1;
		if(str == null || "".equals(str)) {
			JOptionPane.showMessageDialog(null, "所输入数目不能为空！");
			return -1;
		}
		try {
			input = Integer.valueOf(str);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "所输入数目格式不对！");
			return -1;
		}
		if(input <= 0) {
			JOptionPane.showMessageDialog(null, "所输入数目必须大于0！");
			return -1;
		}
		return input;
	}
}
