package cdu.computer.hxl.util;

import java.util.Calendar;

public class Accessor {
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param s
	 * @return true or false
	 */
	public static boolean isNULL(String s) {
		return s == null ? true : false;
	}

	/**
	 * ���ʱ���Ƿ���һ����Ч��ʱ��
	 * 
	 * @param time
	 * @return true or false
	 */
	public static boolean isLegalTime(String time) {

		String[] t = time.trim().split("-");
		if (t.length != 3)
			return false;

		int y = Integer.parseInt(t[0]);
		int m = Integer.parseInt(t[1]);
		int d = Integer.parseInt(t[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, y);
		calendar.set(Calendar.MONTH, m - 1);
		calendar.set(Calendar.DATE, d);

		if (y != calendar.get(Calendar.YEAR))
			return false;
		else if ((m - 1) != calendar.get(Calendar.MONTH))
			return false;
		else if (d != calendar.get(Calendar.DATE))
			return false;

		return true;
	}

	/**
	 * �ж��Ƿ�Ϊһ���Ϸ�����
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num) {
		try {
			Double.parseDouble(num);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// public static void main(String[] args) {
	// System.out.println(Accessor.isLegalTime("2011-5-32"));
	// }
}
