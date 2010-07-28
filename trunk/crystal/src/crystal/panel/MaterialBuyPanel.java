package crystal.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.common.ExcelUtil;
import crystal.common.TableUtil;
import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.MaterialCategory;
import crystal.service.MaterialBuyService;
import crystal.service.MaterialCategoryService;

public class MaterialBuyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	
	// ���ݿ����
	MaterialBuyService materialBuyService = null;
	MaterialBuy materialBuy = null;
	List<MaterialBuy> materialBuyList = new ArrayList<MaterialBuy>();
	MaterialCategoryService materialCategoryService = null;
	List<MaterialCategory> materialCategoryList = new ArrayList<MaterialCategory>();

	// 1��UI-��ť���
	JPanel jPanelButton = new JPanel();
	JButton jButtonAdd = new JButton("���������");
	JButton jButtonDel = new JButton("ɾ��");
	JButton jButtonCompute = new JButton("���������������");
	JLabel jLabelTotal = new JLabel();
	int totalCount = 0;
	double totalPrice = 0;
	
	// 2��UI-JTable���
	JPanel jPanelTable = new JPanel();
	DefaultTableModel tableModel = null;
	JTable jTable = null;
	JScrollPane jScrollPane = new JScrollPane();
	String[] columnNames = { "ѡ��", "���", "��������", "���ϵ���", "������", "�����ֵ", "����ʱ��",
			"��ע" };
	int[] columnLen = { 10, 25, 15, 20, 15, 10, 20, 10};

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuBuy = new JMenuItem("����˲���");
	JMenuItem jMenuDel = new JMenuItem("ɾ����ǰ��¼(D)");
	JMenuItem jMenuExport = new JMenuItem("������ͼ��Excel�ļ�");

	public MaterialBuyPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "MaterialPanel,��ʼ������" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		materialBuyService = (MaterialBuyService) AppContextUtil.getInstance()
				.getBean("materialBuyService");
		materialBuyList = materialBuyService.findAll();
		materialCategoryService = (MaterialCategoryService) AppContextUtil.getInstance()
				.getBean("materialCategoryService");
		materialCategoryList = materialCategoryService.findAll();
		
		jTableInit();
		
		jMenuBuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuBuy_actionPerformed(e);
			}
		});
		jMenuDel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuDel_actionPerformed(e);
			}
		});
		jMenuExport.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuExport_actionPerformed(e);
			}
		});
//		jPopupMenu.add(jMenuBuy);
//		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuDel);
//		jPopupMenu.add(jMenuExport);

		jPopupMenu.setInvoker(jTable);
		
		jButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jButtonCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCompute_actionPerformed(e);
			}
		});
		
		// UI-����
		jPanelButton.add(jButtonAdd);
		// jPanelButton.add(jButtonDel);
		jPanelButton.add(jButtonCompute);
		computerTotal();
		jLabelTotal.setForeground(Color.RED);
		jPanelButton.add(jLabelTotal);
		
		jScrollPane.getViewport().add(jTable);
		
		this.setLayout(new BorderLayout());
		this.add(jPanelButton, BorderLayout.NORTH);
		this.add(jScrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		if (jTable.getRowCount() > 0) {
			jScrollPane.getVerticalScrollBar().setValue(0);
			jTable.setRowSelectionInterval(0, 0);
		}
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	void jTableInit() {
		Object[][] tabledata = {};
		tableModel = new DefaultTableModel(tabledata, columnNames) {
			public boolean isCellEditable(int row, int column) {
//				String[] columnNames = { "ѡ��", "���", "��������", "���ϵ���", "������", "�����ֵ", "����ʱ��",
//				"��ע" };
				if (column == 0 || column == 1 || column == 2 || column == 3
						|| column == 5 || column == 6 || column == 1)
					return false;
				return true;
			}
		};
		jTable = new JTable(tableModel);
		int index = 1;
		for (MaterialBuy  mb : materialBuyList) {
			Object[] object = { index, index, mb.getMaterialName(),
					formatter.format(mb.getMaterialPrice()), mb.getCount(),
					formatter.format(mb.getMaterialPrice() * mb.getCount()),
					mb.getTime(), mb.getNote1() };
			tableModel.addRow(object);
			index++;
		}
		TableRowSorter sorter = new TableRowSorter(tableModel);
		jTable.setRowSorter(sorter);
		Comparator intComparator = new Comparator() {
			public int compare(Object arg0, Object arg1) {
				try {
					int a = Integer.parseInt(arg0.toString());
					int b = Integer.parseInt(arg1.toString());
					return a - b;
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		};
		sorter.setComparator(0, intComparator);
		sorter.setComparator(1, intComparator);
		sorter.setComparator(4, intComparator);
		sorter.setComparator(7, intComparator);
		Comparator doubleComparator = new Comparator() {
			public int compare(Object arg0, Object arg1) {
				try {
					double a = Double.parseDouble(arg0.toString());
					double b = Double.parseDouble(arg1.toString());
					return a - b > 0 ? 1 : 0;
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		};
		sorter.setComparator(3, doubleComparator);
		sorter.setComparator(5, doubleComparator);
		
		JComboBox jComboBoxCategory = new JComboBox();
		for(MaterialCategory mc : materialCategoryList) {
			jComboBoxCategory.addItem(mc);
		}

		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int selectRow = jTable.getSelectedRow();
					if (selectRow != -1) {
						int modelSeqno = jTable.convertRowIndexToModel(selectRow);
						materialBuy = (MaterialBuy) materialBuyList.get((Integer) tableModel
								.getValueAt(modelSeqno, 0) - 1);
						jTable_mouseClicked(e);
					}
				}
			}
		});
		jTable.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}
			public void mouseMoved(MouseEvent e) {
				Point point = e.getPoint();
				int x = jTable.rowAtPoint(point);
				int y = jTable.columnAtPoint(point);
				Object tip = jTable.getValueAt(x, y);
				if (y == 2 || y == 3) {
					jTable.setToolTipText("");
					return;
				}
				if (tip != null) {
					jTable.setToolTipText(tip.toString());
				}
			}
		});

		tableModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column != -1) {
					materialBuy = (MaterialBuy) materialBuyList.get((Integer) tableModel
							.getValueAt(row, 0) - 1);
					String tValue = tableModel.getValueAt(row, column).toString();
					int cNum = 0;
//					String[] columnNames = { "ѡ��", "���", "��������", "���ϵ���", "������", "�����ֵ", "����ʱ��",
//					"��ע" };
					// ������
					if (column == 4) {
						if (tValue != null
								&& !tValue.equals(materialBuy.getCount()== null ? ""
										: materialBuy.getCount().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "���ݸ�ʽ����ȷ������洢");
								i = -1;
							}
							if (i >= 0) {
								materialBuy.setCount(i);
								tableModel.setValueAt(formatter.format(i
										* materialBuy.getMaterialPrice()), row, 5);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// ��ע
					if (column == 7) {
						if (tValue != null
								&& !tValue.equals(materialBuy.getNote1())) {
							materialBuy.setNote1(tValue);
							cNum++;
						}
					}

					if (cNum != 0) {
						materialBuyService.update(materialBuy);
					}
				}
			}
		});
		
		jTable.setRowHeight(20);
		TableUtil.makeFace(jTable);
		TableUtil.setColumnWidth(jTable, new int[] {30, 30, 80, 80});
		TableUtil.setHiddenColumn(jTable, new int[] { 0 });
	}

	protected void jButtonCompute_actionPerformed(ActionEvent e) {
		computerTotal();
	}
	
	void jTable_mouseClicked(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			jPopupMenu.show(jTable, e.getX(), e.getY());
		}
	}
	
	// �������
	void jMenuBuy_actionPerformed(ActionEvent e) {
	}

	// ɾ����¼
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��ѡ�м�¼��", "ɾ����¼",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		materialBuyList.remove(materialBuy);
		materialBuyService.delete(materialBuy);
		reloadTable();
	}

	// ������ͼ���ı��ļ�
	@SuppressWarnings("unchecked")
	void jMenuExport_actionPerformed(ActionEvent e) {
		List tableData = tableModel.getDataVector();
		ExcelUtil.exportExcel(this, "��Ʒ���ϱ�.xls", "��Ʒ���ϱ�", columnNames,
				columnLen, tableData);
	}
	
	private void computerTotal() {
		for (MaterialBuy mb : materialBuyList) {
			totalCount += mb.getCount();
			totalPrice += mb.getMaterialPrice() * mb.getCount();
		}
		jLabelTotal.setText("�������������" + totalCount + "�����ܼ�ֵ��"
				+ formatter.format(totalPrice) + "Ԫ");
		totalCount = 0;
		totalPrice = 0;
	}

	public void reloadTable() {
		tableModel.getDataVector().removeAllElements();
		int index = 1;
		for (MaterialBuy mb : materialBuyList) {
			Object[] object = { index, index, mb.getMaterialName(),
					formatter.format(mb.getMaterialPrice()), mb.getCount(),
					formatter.format(mb.getMaterialPrice() * mb.getCount()),
					mb.getTime(), mb.getNote1() };
			tableModel.addRow(object);
			index++;
		}
		tableModel.fireTableDataChanged();
	}
}
