package crystal.common;

import javax.swing.JOptionPane;

/**
 * @author memo
 *
 */
public class InputVerify {
	
	/**
	 * �Դ����str�Ƿ�Ϊ���������ж�
	 * @param str
	 * @return ���ؽ������������֣��������󷵻�-1
	 */
	public static int verifyInt(String str) {
		int input = -1;
		if(str == null || "".equals(str)) {
			JOptionPane.showMessageDialog(null, "��������Ŀ����Ϊ�գ�");
			return -1;
		}
		try {
			input = Integer.valueOf(str);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "��������Ŀ��ʽ���ԣ�");
			return -1;
		}
		if(input <= 0) {
			JOptionPane.showMessageDialog(null, "��������Ŀ�������0��");
			return -1;
		}
		return input;
	}
}
