package crystal.common;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TableUtil {
	public TableUtil() {
	}

	// ����JTable���п��
	public static void setColumnWidth(JTable jTable, int[] width) {
		TableColumn column = null;
		if (jTable.getColumnCount() != width.length) {
			System.out.println("columnCount does not equal coumnWidth.length  "
					+ jTable.getColumnCount() + "  !=  " + width.length);
			// return;
		}
		for (int i = 0; i < jTable.getColumnCount(); i++) {
			// �п����鲻����ʣ����в����ÿ��
			if (i >= width.length)
				return;

			// -1 �������п�ϵͳ�Զ�����
			if (width[i] < 0)
				continue;
			else {
				column = jTable.getColumnModel().getColumn(i);
				// 0 ����ʾ����
				if (width[i] == 0) {
					TableColumn hcolumn = jTable.getTableHeader()
							.getColumnModel().getColumn(i);
					hcolumn.setPreferredWidth(0);
					hcolumn.setMaxWidth(0);
					hcolumn.setMinWidth(0);
					column.setPreferredWidth(0);
					column.setMaxWidth(0);
					column.setMinWidth(0);
				} else {
					column.setPreferredWidth(width[i]);
					// column.setMaxWidth(2 * width[i]);
					// column.setMinWidth(30);
				}
			}
		}
	}

	// ����ָ����
	public static void setHiddenColumn(JTable jTable, int[] hiddenColumn) {
		TableColumn column = null;
		TableColumn hcolumn = null;
		if (hiddenColumn.length == 0)
			return;
		for (int i = 0; i < hiddenColumn.length; i++) {
			column = jTable.getColumnModel().getColumn(hiddenColumn[i]);
			hcolumn = jTable.getTableHeader().getColumnModel().getColumn(
					hiddenColumn[i]);
			hcolumn.setPreferredWidth(0);
			hcolumn.setMaxWidth(0);
			hcolumn.setMinWidth(0);
			column.setPreferredWidth(0);
			column.setMaxWidth(0);
			column.setMinWidth(0);
		}
	}

	// �ָ�������
	public static void unsetHiddenColumn(JTable jTable, int[] hiddenColumn,
			int[] width) {
		TableColumn column = null;
		if (hiddenColumn.length != width.length) {
			System.out.println("columnCount does not equal coumnWidth.length  "
					+ hiddenColumn.length + "  !=  " + width.length);
		}
		for (int i = 0; i < hiddenColumn.length; i++) {
			// �п����鲻����ʣ����в����ÿ��
			if (i >= width.length)
				return;

			// -1 �������п�ϵͳ�Զ�����
			if (width[i] < 0)
				continue;
			else {
				column = jTable.getColumnModel().getColumn(hiddenColumn[i]);
				// 0 ����ʾ����
				if (width[i] == 0) {
					TableColumn hcolumn = jTable.getTableHeader()
							.getColumnModel().getColumn(hiddenColumn[i]);
					hcolumn.setPreferredWidth(0);
					hcolumn.setMaxWidth(0);
					hcolumn.setMinWidth(0);
					column.setPreferredWidth(0);
					column.setMaxWidth(0);
					column.setMinWidth(0);
				} else {
					TableColumn hcolumn = jTable.getTableHeader()
							.getColumnModel().getColumn(hiddenColumn[i]);
					hcolumn.setPreferredWidth(width[i]);
					hcolumn.setMaxWidth(width[i] * 3);
					hcolumn.setMinWidth(width[i] / 2);
					column.setPreferredWidth(width[i]);
					column.setMaxWidth(width[i] * 3);
					column.setMinWidth(width[i] / 2);
					// column.setPreferredWidth(width[i]);
				}
			}
		}
	}

	// ������Ӻ�ɾ��JTable����֮��ˢ�µ�0�е������
	public static void refreshRowHeader(JTable jTable) {
		TableModel model = jTable.getModel();
		int rowCount = jTable.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			model.setValueAt(i + 1, i, 0);
		}
		// jTable.invalidate();
	}

	public static void makeFace(JTable table) {

		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
//					if (value instanceof Integer) {
//						setVerticalAlignment(SwingConstants.CENTER);
//						setHorizontalAlignment(SwingConstants.CENTER);
//						setVerticalTextPosition(SwingConstants.CENTER);
//						setHorizontalTextPosition(SwingConstants.CENTER);
//					} else {
//						setVerticalAlignment(SwingConstants.CENTER);
//						setHorizontalAlignment(SwingConstants.LEADING);
//						setVerticalTextPosition(SwingConstants.CENTER);
//						setHorizontalTextPosition(SwingConstants.CENTER);
//					}

					if (row % 2 == 0)
						setBackground(Color.white);
					else if (row % 2 == 1)
						setBackground(new Color(206, 231, 255));

//					if (table.getColumnName(column).equals("��Community")
//							|| table.getColumnName(column).equals("дCommunity")) {
//						String password = "";
//						if (value == null || value.equals("")) {
//							// /value = password;
//							return super
//									.getTableCellRendererComponent(table,
//											password, isSelected, hasFocus,
//											row, column);
//						} else {
//							int wordLong = value.toString().length();
//							for (int i = 0; i < wordLong; i++)
//								password += "*";
//							// value = password;
//							return super
//									.getTableCellRendererComponent(table,
//											password, isSelected, hasFocus,
//											row, column);
//						}
//					}

					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
