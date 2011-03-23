package cdu.computer.hxl.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * ��װ��˵��йص�һЩ����
 * 
 * @author hxl
 * @date 2011-03-17
 * 
 */
public class MenuFactory {
	public static JMenu createMenu(String menuName, String[] items) {
		JMenu menu = new JMenu(menuName);
		for (int i = 0; i < items.length; i++) {
			menu.add(new JMenuItem(items[i]));
		}
		return menu;
	}
}
