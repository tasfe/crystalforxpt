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
import crystal.hibernate.po.ProductSell;
import crystal.hibernate.po.MaterialCategory;
import crystal.service.ProductSellService;
import crystal.service.MaterialCategoryService;

public class ProductSellPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	
	// 数据库相关
	ProductSellService productSellService = null;
	ProductSell productSell = null;
	List<ProductSell> productSellList = new ArrayList<ProductSell>();
	MaterialCategoryService materialCategoryService = null;
	List<MaterialCategory> materialCategoryList = new ArrayList<MaterialCategory>();

	// 1、UI-按钮面板
	JPanel jPanelButton = new JPanel();
	JButton jButtonAdd = new JButton("添加新种类");
	JButton jButtonDel = new JButton("删除");
	JButton jButtonCompute = new JButton("计算售出商品总量");
	JLabel jLabelTotal = new JLabel();
	int totalCount = 0;
	double totalPrice = 0;
	
	// 2、UI-JTable面板
	JPanel jPanelTable = new JPanel();
	DefaultTableModel tableModel = null;
	JTable jTable = null;
	JScrollPane jScrollPane = new JScrollPane();
	String[] columnNames = { "选择", "序号", "商品名称", "商品单价", "出售数", "出售价值", "出售时间",
			"备注" };
	int[] columnLen = { 15, 15, 15, 20, 15, 10, 20, 10};

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuBuy = new JMenuItem("出售此材料");
	JMenuItem jMenuDel = new JMenuItem("删除当前记录(D)");
	JMenuItem jMenuExport = new JMenuItem("导出视图到Excel文件");

	public ProductSellPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "MaterialPanel,初始化错误：" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		productSellService = (ProductSellService) AppContextUtil.getInstance()
				.getBean("productSellService");
		productSellList = productSellService.findAll();
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
		
		// UI-布局
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
//				String[] columnNames = { "选择", "序号", "材料名称", "材料单价", "购买数", "购买价值", "购买时间",
//				"备注" };
				if (column == 0 || column == 1 || column == 2 || column == 3
						|| column == 5 || column == 6 || column == 1)
					return false;
				return true;
			}
		};
		jTable = new JTable(tableModel);
		int index = 1;
		for (ProductSell  ps : productSellList) {
			Object[] object = { index, index, ps.getProductName(),
					formatter.format(ps.getProductPrice()), ps.getCount(),
					formatter.format(ps.getProductPrice() * ps.getCount()),
					ps.getTime(), ps.getNote1() };
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
						productSell = (ProductSell) productSellList.get((Integer) tableModel
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
					productSell = (ProductSell) productSellList.get((Integer) tableModel
							.getValueAt(row, 0) - 1);
					String tValue = tableModel.getValueAt(row, column).toString();
					int cNum = 0;
//					String[] columnNames = { "选择", "序号", "材料名称", "材料单价", "购买数", "购买价值", "购买时间",
//					"备注" };
					// 购买数
					if (column == 4) {
						if (tValue != null
								&& !tValue.equals(productSell.getCount()== null ? ""
										: productSell.getCount().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "数据格式不正确，不会存储");
								i = -1;
							}
							if (i >= 0) {
								productSell.setCount(i);
								tableModel.setValueAt(formatter.format(i
										* productSell.getProductPrice()), row, 5);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// 备注
					if (column == 7) {
						if (tValue != null
								&& !tValue.equals(productSell.getNote1())) {
							productSell.setNote1(tValue);
							cNum++;
						}
					}

					if (cNum != 0) {
						productSellService.update(productSell);
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
	
	// 购买材料
	void jMenuBuy_actionPerformed(ActionEvent e) {
	}

	// 删除记录
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "确认删除选中记录？", "删除记录",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		productSellList.remove(productSell);
		productSellService.delete(productSell);
		reloadTable();
	}

	// 导出视图到文本文件
	@SuppressWarnings("unchecked")
	void jMenuExport_actionPerformed(ActionEvent e) {
		List tableData = tableModel.getDataVector();
		ExcelUtil.exportExcel(this, "饰品材料表.xls", "饰品材料表", columnNames,
				columnLen, tableData);
	}
	
	private void computerTotal() {
		for (ProductSell ps : productSellList) {
			if(!ps.getProductName().contains("运费"))
				totalCount += ps.getCount();
			totalPrice += ps.getProductPrice() * ps.getCount();
		}
		jLabelTotal.setText("售出商品总量：" + totalCount + "件，总价值（已去除运费）："
				+ formatter.format(totalPrice) + "元");
		totalCount = 0;
		totalPrice = 0;
	}

	public void reloadTable() {
		tableModel.getDataVector().removeAllElements();
		int index = 1;
		for (ProductSell ps : productSellList) {
			Object[] object = { index, index, ps.getProductName(),
					formatter.format(ps.getProductPrice()), ps.getCount(),
					formatter.format(ps.getProductPrice() * ps.getCount()),
					ps.getTime(), ps.getNote1() };
			tableModel.addRow(object);
			index++;
		}
		tableModel.fireTableDataChanged();
	}
}
