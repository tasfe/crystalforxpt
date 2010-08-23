package crystal.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import crystal.MainFrame;
import crystal.Tools;
import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.common.ExcelUtil;
import crystal.common.TableUtil;
import crystal.hibernate.po.Material;
import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.MaterialCategory;
import crystal.service.MaterialBuyService;
import crystal.service.MaterialCategoryService;
import crystal.service.MaterialService;

public class MaterialPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	
	// 数据库相关
	MaterialService materialService = null;
	Material material = null;
	List<Material> materialList = new ArrayList<Material>();
	MaterialCategoryService materialCategoryService = null;
	List<MaterialCategory> materialCategoryList = new ArrayList<MaterialCategory>();
	MaterialBuyService materialBuyService = null;
	
	// 1、UI-按钮面板
	JPanel jPanelButton = new JPanel();
	JButton jButtonAdd = new JButton("添加新种类");
	JButton jButtonDel = new JButton("删除");
	JButton jButtonDisp = new JButton("显示全部材料");
	boolean dispAll = false;
	JButton jButtonCompute = new JButton("计算剩余库存量");
	JLabel jLabelTotal = new JLabel();
	int totalCount = 0;
	double totalPrice = 0;
	
	// 2、UI-JTable面板
	JPanel jPanelTable = new JPanel();
	DefaultTableModel tableModel = null;
	JTable jTable = null;
	JScrollPane jScrollPane = new JScrollPane();
	String[] columnNames = { "选择", "序号", "名称", "单价", "库存数", "总价值", "编号",
			"编号附注", "颜色", "批次", "曾购买数", "分类", "备注" };
	int[] columnLen = { 15, 15, 15, 20, 15, 10, 15, 10, 10, 15, 15, 15, 15};

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuBuy = new JMenuItem("购买此材料");
	JMenuItem jMenuDel = new JMenuItem("删除当前记录(D)");
	JMenuItem jMenuExport = new JMenuItem("导出视图到Excel文件");
	JMenuItem jMenuCopy = new JMenuItem("复制此种类");

	public MaterialPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "MaterialPanel,初始化错误：" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		materialService = (MaterialService) AppContextUtil.getInstance()
				.getBean("materialService");
		materialBuyService = (MaterialBuyService) AppContextUtil.getInstance()
				.getBean("materialBuyService");
		// materialList = materialService.findAll();
		materialList = materialService.findAllByCount(0);
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
		jMenuCopy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuCopy_actionPerformed(e);
			}
		});
		
		jPopupMenu.add(jMenuBuy);
		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuDel);
		jPopupMenu.add(jMenuExport);
		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuCopy);

		jPopupMenu.setInvoker(jTable);
		
		jButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAdd_actionPerformed(e);
			}
		});
		
		jButtonDisp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonDisp_actionPerformed(e);
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
		jPanelButton.add(jButtonDisp);
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
//				String[] columnNames = { "选择", "序号", "名称", "单价", "库存数", "总价值", "编号",
//				"编号附注", "颜色", "批次", "曾购买数", "分类", "备注" };
				if (column == 0 || column == 1 
//						|| column == 2 || column == 3
//						|| column == 4 || column == 5 || column == 10
						)
					return false;
				return true;
			}
		};
		jTable = new JTable(tableModel);
		int index = 1;
		for (Material m : materialList) {
			Object[] object = { index, index, m.getName(),
					formatter.format(m.getPrice()), m.getCount(),
					formatter.format(m.getPrice() * m.getCount()), m.getType(),
					m.getTypeNumber(), m.getColor(), m.getBatchNum(),
					m.getCountBuy(), m.getMaterialCategory().getName(),
					m.getNote1() };
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
		sorter.setComparator(9, intComparator);
		sorter.setComparator(10, intComparator);
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
		DefaultCellEditor categoryEditor = new DefaultCellEditor(jComboBoxCategory);
		categoryEditor.setClickCountToStart(2);
		jTable.getColumnModel().getColumn(11).setCellEditor(categoryEditor);

		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int selectRow = jTable.getSelectedRow();
					if (selectRow != -1) {
						int modelSeqno = jTable.convertRowIndexToModel(selectRow);
						material = (Material) materialList.get((Integer) tableModel
								.getValueAt(modelSeqno, 0) - 1);
						jTable_mouseClicked(e);
					}
				}
			}
		});
/*		
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
*/
		tableModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column != -1) {
					material = (Material) materialList.get((Integer) tableModel
							.getValueAt(row, 0) - 1);
					String tValue = tableModel.getValueAt(row, column).toString();
					int cNum = 0;
//					String[] columnNames = { "选择", "序号", "名称", "单价", "库存数", "总价值", "编号",
//							"编号附注", "颜色", "批次", "曾购买数", "分类", "备注" };
					// 名称
					if (column == 2) {
						if (tValue != null
								&& !tValue.equals(material.getName())) {
							material.setName(tValue);
							cNum++;
						}
					}
					// 单价
					if (column == 3) {
						if (tValue != null
								&& !tValue.equals(formatter.format(material.getPrice()))) {
							double d = 0;
							try {
								d = Double.parseDouble(tValue);
							} catch (NumberFormatException e1) {
								d = -1;
							}
							if (d >= 0) {
								material.setPrice(d);
								tableModel.setValueAt(formatter.format(d
										* material.getCount()), row, 5);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// 库存数
					if (column == 4) {
						if (tValue != null
								&& !tValue.equals(material.getCount()== null ? ""
										: material.getCount().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "数据格式不正确，不会存储");
								i = -1;
							}
							if (i >= 0) {
								material.setCount(i);
								tableModel.setValueAt(formatter.format(i
										* material.getPrice()), row, 5);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// 编号
					if (column == 6) {
						if (tValue != null
								&& !tValue
										.equals(material.getType() == null ? ""
												: material.getType().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "数据格式不正确，不会存储");
								i = -1;
							}
							if (i >= 0) {
								material.setType(i);
								cNum++;
							}
						}
					}
					// 编号附注
					if (column == 7) {
						if (tValue != null
								&& !tValue.equals(material.getTypeNumber())) {
							material.setTypeNumber(tValue);
							cNum++;
						}
					}
					// 颜色
					if (column == 8) {
						if (tValue != null
								&& !tValue.equals(material.getColor())) {
							material.setColor(tValue);
							cNum++;
						}
					}
					// 批次
					if (column == 9) {
						if (tValue != null
								&& !tValue.equals(material.getBatchNum() == null ? ""
										: material.getBatchNum().toString())) {
							long l = 0;
							try {
								l = Long.parseLong(tValue);
							} catch (NumberFormatException e1) {
								l = -1;
							}
							if (l >= 0) {
								material.setBatchNum(l);
								cNum++;
							}
						}
					}
					// 曾购买数
					if (column == 10) {
						if (tValue != null
								&& !tValue.equals(material.getCountBuy() == null ? ""
										: material.getCountBuy().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "数据格式不正确，不会存储");								
								i = -1;
							}
							if (i >= 0) {
								material.setCountBuy(i);
								cNum++;
							}
						}
					}
					// 分类
					if (column == 11) {
						MaterialCategory mc = (MaterialCategory)tableModel.getValueAt(row, column);
						Integer id = mc.getId();
						if (id != null
								&& !id.equals(material.getMaterialCategory().getId())) {
							material.setMaterialCategory(materialCategoryService.findById(id));
							cNum++;
						}
					}
					// 备注
					if (column == 12) {
						if (tValue != null
								&& !tValue.equals(material.getNote1())) {
							material.setNote1(tValue);
							cNum++;
						}
					}

					if (cNum != 0) {
						materialService.update(material);
					}
				}
			}
		});
		
		jTable.setRowHeight(20);
		TableUtil.makeFace(jTable);
		TableUtil.setColumnWidth(jTable, new int[] {30, 30, 180, 80});
		TableUtil.setHiddenColumn(jTable, new int[] { 0, 9 });
	}

	// 添加一行记录，被MaterialAdd调用
	public void addRow(Material m) {
		materialList.add(m);
		int rowNum = materialList.size();
		Object[] row = { rowNum, rowNum, m.getName(),
				formatter.format(m.getPrice()), m.getCount(),
				formatter.format(m.getPrice() * m.getCount()), m.getType(),
				m.getTypeNumber(), m.getColor(), m.getBatchNum(),
				m.getCountBuy(), m.getMaterialCategory().getName(),
				m.getNote1() };
		tableModel.addRow(row);
		tableModel.fireTableDataChanged();
		if (jTable.getRowCount() > 0) {
			jScrollPane.getVerticalScrollBar().setValue(
					jScrollPane.getVerticalScrollBar().getHeight());
			jTable.setRowSelectionInterval(rowNum - 1, rowNum - 1);
		}
	}
	
	// 检查是否有重名的材料，被MaterialAdd调用，直接修改表格时不判断
	public boolean hasMaterial(String name) {
		for(Material m : materialList) {
			if(name.equals(m.getName()))
				return true;
		}
		return false;
	}
	
	protected void jButtonAdd_actionPerformed(ActionEvent e) {
		MaterialAdd dlg = new MaterialAdd();
		dlg.setParent(this);
		Tools.dispWin(dlg);
	}
	
	protected void jButtonDisp_actionPerformed(ActionEvent e) {
		dispAll = !dispAll;
		materialList = dispAll ? materialService.findAll() : materialService.findAllByCount(0);
		jButtonDisp.setText(dispAll ? "显示库存量非0的材料" : "显示全部材料");
		reloadTable();
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
		BuyDlg dlg = new BuyDlg();
		Tools.dispWin(dlg);
	}

	// 删除记录
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "确认删除选中记录？", "删除记录",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		materialList.remove(material);
		materialService.delete(material);
		reloadTable();
	}

	// 导出视图到文本文件
	@SuppressWarnings("unchecked")
	void jMenuExport_actionPerformed(ActionEvent e) {
		List tableData = tableModel.getDataVector();
		ExcelUtil.exportExcel(this, "饰品材料表.xls", "饰品材料表", columnNames,
				columnLen, tableData);
	}
	// 复制选中行记录，方便直接修改颜色
	void jMenuCopy_actionPerformed(ActionEvent e) {
		// 复制对象
/*		Material mcopy = new Material();
		mcopy.setName(material.getName());
		mcopy.setPrice(material.getPrice());
		mcopy.setCount(material.getCount());
		mcopy.setType(material.getType());
		mcopy.setTypeNumber(material.getTypeNumber());
		mcopy.setMaterialCategory(material.getMaterialCategory());
		mcopy.setColor(material.getColor());
		mcopy.setCountBuy(material.getCountBuy());
		mcopy.setNote1(material.getNote1());
		materialList.add(mcopy);
		materialService.save(mcopy);
		// 更新JTable
		int rowNum = materialList.size();
		Object[] row = { rowNum, rowNum, material.getName(),
				formatter.format(material.getPrice()), material.getCount(),
				formatter.format(material.getPrice() * material.getCount()), material.getType(),
				material.getTypeNumber(), material.getColor(), material.getBatchNum(),
				material.getCountBuy(), material.getMaterialCategory().getName(),
				material.getNote1() };
		tableModel.addRow(row);
		tableModel.fireTableDataChanged();
		if (jTable.getRowCount() > 0) {
			jScrollPane.getVerticalScrollBar().setValue(
					jScrollPane.getVerticalScrollBar().getHeight());
			jTable.setRowSelectionInterval(rowNum - 1, rowNum - 1);
		}
*/
		// 2010-07-23 逻辑改为打开一个添加材料窗口，并把要复制的材料传入
		MaterialAdd dlg = new MaterialAdd();
		dlg.setParent(this);
		dlg.copyMaterial(material);
		Tools.dispWin(dlg);
	}
	
	private void computerTotal() {
		for (Material m : materialList) {
			totalCount += m.getCount();
			totalPrice += m.getPrice() * m.getCount();
		}
		jLabelTotal.setText("剩余总库存数：" + totalCount + "件，总价值："
				+ formatter.format(totalPrice) + "元");
		totalCount = 0;
		totalPrice = 0;
	}

	public void reloadTable() {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		int index = 1;
		for (Material m : materialList) {
			Object[] object = { index, index, m.getName(),
					formatter.format(m.getPrice()), m.getCount(),
					formatter.format(m.getPrice() * m.getCount()), m.getType(),
					m.getTypeNumber(), m.getColor(), m.getBatchNum(),
					m.getCountBuy(), m.getMaterialCategory().getName(),
					m.getNote1() };
			tableModel.addRow(object);
			index++;
		}
		tableModel.fireTableDataChanged();
	}
	
	class BuyDlg extends JDialog {
		private static final long serialVersionUID = 1L;
		
		JPanel contentPane;
		JPanel jPanelInfo = new JPanel();
		JPanel jPanelButton = new JPanel();
		
		JLabel jLabelInfo = new JLabel();
		JLabel jLabelInfo1 = new JLabel();
		
		JLabel jLabelCount = new JLabel("请输入购买数量(*)");
		JTextField jTextFieldCount = new JTextField("1");

		JButton jButtonOk = new JButton("确定");
		JButton jButtonExit = new JButton("取消");

		public BuyDlg() {
			super();
			try {
				init();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "BuyDlg,初始化错误:" + ex);
			}
		}

		private void init() {
			jLabelInfo.setText("请确认以【" + material.getPrice() + "】元的单价购买材料【"
					+ material.getName() + "】");
			jLabelInfo1.setText("如单价已改变，请新建材料种类！");
			jLabelInfo1.setForeground(Color.RED);
			// jPanelButton
			jButtonOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButtonOk_actionPerformed(e);
				}
			});
			jButtonExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButtonExit_actionPerformed(e);
				}
			});
			jPanelButton.setBorder(BorderFactory.createEtchedBorder());
			jPanelButton.add(jButtonOk);
			jPanelButton.add(jButtonExit);

			jPanelInfo.setLayout(new XYLayout());
			jPanelInfo.add(jLabelInfo, new XYConstraints(5, 10, 250, 20));
			jPanelInfo.add(jLabelInfo1, new XYConstraints(35, 35, 250, 20));
			jPanelInfo.add(jLabelCount, new XYConstraints(5, 60, 120,
					20));
			jPanelInfo.add(jTextFieldCount, new XYConstraints(130, 60,
					50, 20));
			
			contentPane = (JPanel) this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(jPanelInfo, BorderLayout.CENTER);
			contentPane.add(jPanelButton, BorderLayout.SOUTH);

			this.setTitle("添加新种类材料");
			this.setIconImage(MainFrame.imageLogo.getImage());
			this.setSize(260, 200);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		protected void jButtonOk_actionPerformed(ActionEvent e) {
			if (verifyInput()) {
				int count = 0;
				if (!"".equals(jTextFieldCount.getText())) {
					try {
						count = Integer.parseInt(jTextFieldCount.getText());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "购买数目请输入正整数");
						return;
					}
					if (count <= 0) {
						JOptionPane.showMessageDialog(this, "购买数目请输入正整数");
						return;
					}
				}
				// 购买原材料时更新库存量、曾购买量、库存总价
				// 购买运费时只更新曾购买量
				int rowTable = jTable.getSelectedRow();
				if (rowTable != -1) {
					int row = jTable.convertRowIndexToModel(rowTable);
					if (material.getMaterialCategory().getName().equals("原材料")) {
						int oldCount = material.getCount();
						material.setCount(material.getCount() + count);
						material.setCountBuy(material.getCountBuy() == null ? oldCount + count
								: material.getCountBuy() + count);
						tableModel.setValueAt(material.getCount(), row, 4);
						tableModel.setValueAt(formatter.format(material.getPrice()
								* material.getCount()), row, 5);
						tableModel.setValueAt(material.getCountBuy(), row, 10);
					} else if (material.getMaterialCategory().getName()
							.equals("运费")) {
						material.setCountBuy(material.getCountBuy() + count);
						tableModel.setValueAt(material.getCountBuy(), row, 10);
					}
					materialService.update(material);
					tableModel.fireTableDataChanged();
					MaterialBuy mb = new MaterialBuy();
					if(count > 0) {
						mb.setMaterialName(material.getName());
						mb.setMaterialPrice(material.getPrice());
						mb.setCount(count);
						mb.setTotalPrice(material.getPrice() * count);
						mb.setMaterial(material);
						mb.setTime(new Timestamp(System.currentTimeMillis()));
						materialBuyService.save(mb);
					}
				}
				this.dispose();
			}
		}

		protected void jButtonExit_actionPerformed(ActionEvent e) {
			this.dispose();
		}

		private boolean verifyInput() {
			String strCount = jTextFieldCount.getText();
			if (strCount == null || strCount.equals("")) {
				JOptionPane.showMessageDialog(this, "购买数目不能为空！");
				return false;
			}
			return true;
		}
		
	}
}
