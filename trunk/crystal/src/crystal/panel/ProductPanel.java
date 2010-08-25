package crystal.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Method;
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

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import crystal.MainFrame;
import crystal.Tools;
import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.common.ExcelUtil;
import crystal.common.InputVerify;
import crystal.common.TableUtil;
import crystal.hibernate.po.Material;
import crystal.hibernate.po.Product;
import crystal.hibernate.po.ProductCategory;
import crystal.hibernate.po.ProductSell;
import crystal.service.MaterialService;
import crystal.service.ProductCategoryService;
import crystal.service.ProductSellService;
import crystal.service.ProductService;

public class ProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	int makeCount = 1;
	// ���ݿ����
	MaterialService materialService = null;
	List<Material> materialList = new ArrayList<Material>();
	ProductService productService = null;
	Product product = null;
	List<Product> productList = new ArrayList<Product>();
	ProductCategoryService productCategoryService = null;
	List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
	ProductSellService productSellService = null;
	
	// �洢������Ʒ���жϳɹ�����Ҫ�ۼ����ϵ�id
	List<Integer> verifyList = new ArrayList<Integer>();
	// ��Ҫ�ۼ����ϵ���Ŀ��������Ӧ�ú�verifyList�������
	List<Integer> countList = new ArrayList<Integer>();
	
	// 1��UI-��ť���
	JPanel jPanelButton = new JPanel();
	JButton jButtonAdd = new JButton("���������");
	JButton jButtonDel = new JButton("ɾ��");
	JButton jButtonCompute = new JButton("����ʣ������");
	JLabel jLabelTotal = new JLabel();
	int totalCount = 0;
	double totalMaterialPrice = 0;
	double totalPrice = 0;
	
	// 2��UI-JTable���
	JPanel jPanelTable = new JPanel();
	DefaultTableModel tableModel = null;
	JTable jTable = null;
	JScrollPane jScrollPane = new JScrollPane();
	String[] columnNames = { "ѡ��", "���", "����", "���ϳɱ���", "����", "�����", "�ܼ�ֵ", "��ɫ",
			"��������", "����", "��ע" };
	int[] columnLen = { 15, 15, 15, 20, 15, 10, 15, 10, 10, 15, 15};

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuModify = new JMenuItem("�޸������������");
	JMenuItem jMenuMake = new JMenuItem("��������Ʒ");
	JMenuItem jMenuSell = new JMenuItem("���۴���Ʒ");
	JMenuItem jMenuDel = new JMenuItem("ɾ����ǰ��¼(D)");
	JMenuItem jMenuExport = new JMenuItem("������ͼ��Excel�ļ�");
	JMenuItem jMenuSplit = new JMenuItem("��ִ���ƷΪ����");

	public ProductPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "ProductPanel,��ʼ������" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		materialService = (MaterialService) AppContextUtil.getInstance()
				.getBean("materialService");
		productService = (ProductService) AppContextUtil.getInstance()
				.getBean("productService");
		productSellService = (ProductSellService) AppContextUtil.getInstance()
				.getBean("productSellService");
		productCategoryService = (ProductCategoryService) AppContextUtil
				.getInstance().getBean("productCategoryService");
		
		materialList = materialService.findAll();
		productList = productService.findAll();
		productCategoryList = productCategoryService.findAll();
		
		jTableInit();
		
		jMenuModify.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuModify_actionPerformed(e);
			}
		});
		jMenuMake.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuMake_actionPerformed(e);
			}
		});
		
		jMenuSell.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSell_actionPerformed(e);
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
		
		jMenuSplit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSplit_actionPerformed(e);
			}
		});
		jPopupMenu.add(jMenuModify);
		jPopupMenu.add(jMenuMake);
		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuSell);
		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuDel);
		jPopupMenu.add(jMenuExport);
		jPopupMenu.add(jMenuSplit);

		jPopupMenu.setInvoker(jTable);
		
		jButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAdd_actionPerformed(e);
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
	
	void jTableInit() {
		Object[][] tabledata = {};
		tableModel = new DefaultTableModel(tabledata, columnNames) {
			public boolean isCellEditable(int row, int column) {
//				String[] columnNames = { "ѡ��", "���", "����", "���ϳɱ���", "����", "�����", "�ܼ�ֵ", "��ɫ",
//						"��������", "����", "��ע" };
				if (column == 0 || column == 1 || column == 3 || column == 4
						//|| column == 5
						|| column == 6 || column == 8)
					return false;
				return true;
			}
		};
		jTable = new JTable(tableModel);
		int index = 1;
		for (Product p : productList) {
			if (p.getName().indexOf("-") < 0) {
				p.setName(p.getName() + "-" + p.getColor());
				productService.update(p);
			}
			Object[] object = { index, index, p.getName(),
					formatter.format(p.getMaterialPrice()),
					formatter.format(p.getPrice()), p.getCount(),
					formatter.format(p.getPrice() * p.getCount()),
					p.getColor(), p.getCountMake(),
					p.getProductCategory().getName(), p.getNote1() };
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
		for(ProductCategory pc : productCategoryList) {
			jComboBoxCategory.addItem(pc);
		}
		DefaultCellEditor categoryEditor = new DefaultCellEditor(jComboBoxCategory);
		categoryEditor.setClickCountToStart(2);
		jTable.getColumnModel().getColumn(9).setCellEditor(categoryEditor);

		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int selectRow = jTable.getSelectedRow();
					if (selectRow != -1) {
						int modelSeqno = jTable.convertRowIndexToModel(selectRow);
						product = (Product) productList.get((Integer) tableModel
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
					product = (Product) productList.get((Integer) tableModel
							.getValueAt(row, 0) - 1);
					String tValue = tableModel.getValueAt(row, column).toString();
					int cNum = 0;

//		String[] columnNames = { "ѡ��", "���", "����", "���ϳɱ���", "����", "�����", "�ܼ�ֵ", "��ɫ",
//				"��������", "����", "��ע" };
			// ����
					// ����
					if (column == 2) {
						if (tValue != null
								&& !tValue.equals(product.getName())) {
							product.setName(tValue);
							cNum++;
						}
					}
					if (column == 4) {
						if (tValue != null
								&& !tValue.equals(formatter.format(product.getPrice()))) {
							double d = 0;
							try {
								d = Double.parseDouble(tValue);
							} catch (NumberFormatException e1) {
								d = -1;
							}
							if (d >= 0) {
								product.setPrice(d);
								tableModel.setValueAt(formatter.format(d
										* product.getCount()), row, 6);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// �����
					if (column == 5) {
						if (tValue != null
								&& !tValue.equals(product.getCount()== null ? ""
										: product.getCount().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "���ݸ�ʽ����ȷ������洢");
								i = -1;
							}
							if (i >= 0) {
								product.setCount(i);
								tableModel.setValueAt(formatter.format(i
										* product.getPrice()), row, 6);
								tableModel.fireTableDataChanged();
								cNum++;
							}
						}
					}
					// ��ɫ
					if (column == 7) {
						if (tValue != null
								&& !tValue.equals(product.getColor())) {
							product.setColor(tValue);
							cNum++;
						}
					}
					// ��������
					if (column == 8) {
						if (tValue != null
								&& !tValue.equals(product.getCountMake() == null ? ""
										: product.getCountMake().toString())) {
							int i = 0;
							try {
								i = Integer.parseInt(tValue);
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(jTable, "���ݸ�ʽ����ȷ������洢");								
								i = -1;
							}
							if (i >= 0) {
								product.setCountMake(i);
								cNum++;
							}
						}
					}
					// ����
					if (column == 9) {
						ProductCategory pc = (ProductCategory)tableModel.getValueAt(row, column);
						Integer id = pc.getId();
						if (id != null
								&& !id.equals(product.getProductCategory().getId())) {
							product.setProductCategory(productCategoryService.findById(id));
							cNum++;
						}
					}
					// ��ע
					if (column == 10) {
						if (tValue != null
								&& !tValue.equals(product.getNote1())) {
							product.setNote1(tValue);
							cNum++;
						}
					}
		
					if (cNum != 0) {
						productService.update(product);
					}
				}
			}
		});
		
		jTable.setRowHeight(20);
		TableUtil.makeFace(jTable);
		TableUtil.setColumnWidth(jTable, new int[] {30, 30, 120, 80});
		TableUtil.setHiddenColumn(jTable, new int[] { 0 });
	}

	public void addRow(Product p) {
		productList.add(p);
		int rowNum = productList.size();
		Object[] row = { rowNum, rowNum, p.getName(),
				formatter.format(p.getMaterialPrice()),
				formatter.format(p.getPrice()), p.getCount(),
				formatter.format(p.getPrice() * p.getCount()), p.getColor(),
				p.getCountMake(), p.getProductCategory().getName(),
				p.getNote1() };
		tableModel.addRow(row);
		tableModel.fireTableDataChanged();
		if (jTable.getRowCount() > 0) {
			jScrollPane.getVerticalScrollBar().setValue(
					jScrollPane.getVerticalScrollBar().getHeight());
			jTable.setRowSelectionInterval(rowNum - 1, rowNum - 1);
		}
	}
	
	// ����Ƿ�����������Ʒ����ProductAdd���ã�ֱ���޸ı��ʱ���ж�
	public boolean hasProduct(String name) {
		for(Product p : productList) {
			if(name.equals(p.getName()))
				return true;
		}
		return false;
	}
	
	protected void jButtonAdd_actionPerformed(ActionEvent e) {
		ProductAdd dlg = new ProductAdd(Constants.ITEM_ADD);
		dlg.setParent(this);
		Tools.dispWin(dlg);
	}
	
	protected void jButtonCompute_actionPerformed(ActionEvent e) {
		computerTotal();
	}
	
	void jTable_mouseClicked(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			jPopupMenu.show(jTable, e.getX(), e.getY());
		}
	}
	
	// 
	void jMenuModify_actionPerformed(ActionEvent e) {
		ProductAdd dlg = new ProductAdd(Constants.ITEM_MODIFY);
		dlg.setParent(this);
		dlg.setProduct(product);
		dlg.initProduct();
		Tools.dispWin(dlg);
	}
	
	// 
	void jMenuMake_actionPerformed(ActionEvent e) {
		if(!isMaterial()) {
			JOptionPane.showMessageDialog(this, "�ǲ�������Ʒ�޷�����");
			return;
		}
		String strCount = JOptionPane.showInputDialog("������Ҫ��������", "1");
		int iCount = InputVerify.verifyInt(strCount);
		if(iCount < 0)
			return;
		if (ProductTool.make(product, verifyList, countList, iCount, materialService, productService, materialList)) {
			int rowTable = jTable.getSelectedRow();
			if (rowTable != -1) {
				int row = jTable.convertRowIndexToModel(rowTable);
				tableModel.setValueAt(product.getCount(), row, 5);
				tableModel.setValueAt(product.getCountMake(), row, 8);
				tableModel.fireTableDataChanged();
			}
		}
	}
	void jMenuSplit_actionPerformed(ActionEvent e) {
		if(!isMaterial()) {
			JOptionPane.showMessageDialog(this, "�ǲ�������Ʒ�޷����");
			return;
		}
		String strCount = JOptionPane.showInputDialog("������������", "1");
		int iCount = InputVerify.verifyInt(strCount);
		if(iCount < 0)
			return;
		if(iCount > product.getCount()) {
			JOptionPane.showMessageDialog(this, "�����Ŀ��Ӧ���ڿ����Ŀ");
			return;
		}
		if(ProductTool.split(product, verifyList, countList, iCount, materialService, productService, materialList)) {
			int rowTable = jTable.getSelectedRow();
			if (rowTable != -1) {
				int row = jTable.convertRowIndexToModel(rowTable);
				tableModel.setValueAt(product.getCount(), row, 5);
				tableModel.setValueAt(product.getCountMake(), row, 8);
				tableModel.fireTableDataChanged();
			}
		}
	}
	
	private boolean isMaterial() {
		return !product.getProductCategory().getId().equals(Constants.SEND_FEE);
	}

	// 
	void jMenuSell_actionPerformed(ActionEvent e) {
		SellDlg dlg = new SellDlg();
		Tools.dispWin(dlg);
	}


	// ɾ����¼
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��ѡ�м�¼��", "ɾ����¼",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		productList.remove(product);
		productService.delete(product);
		reloadTable();
	}

	// ������ͼ���ı��ļ�
	void jMenuExport_actionPerformed(ActionEvent e) {
		List tableData = tableModel.getDataVector();
		ExcelUtil.exportExcel(this, "��Ʒ��.xls", "��Ʒ��", columnNames,
				columnLen, tableData);
	}
	
	private void computerTotal() {
		for (Product p : productList) {
			if(p.getProductCategory().getId().equals(Constants.SEND_FEE))
				continue;
			totalCount += p.getCount();
			totalMaterialPrice += p.getMaterialPrice() * p.getCount();
			totalPrice += p.getPrice() * p.getCount();
		}
		jLabelTotal.setText("ʣ���ܿ������" + totalCount + "���������ܳɱ��ۣ�"
				+ formatter.format(totalMaterialPrice) + "Ԫ�� ��Ʒ�ܼ�ֵ��"
				+ formatter.format(totalPrice) + "Ԫ�� ������"
				+ formatter.format(totalPrice - totalMaterialPrice) + "Ԫ");
		totalCount = 0;
		totalMaterialPrice = 0;
		totalPrice = 0;
	}

	public void reloadTable() {
		tableModel.getDataVector().removeAllElements();
		int index = 1;
		for (Product p : productList) {
			Object[] object = { index, index, p.getName(),
					formatter.format(p.getMaterialPrice()),
					formatter.format(p.getPrice()), p.getCount(),
					formatter.format(p.getPrice() * p.getCount()),
					p.getColor(), p.getCountMake(),
					p.getProductCategory().getName(), p.getNote1() };
			tableModel.addRow(object);
			index++;
		}
		tableModel.fireTableDataChanged();
	}
	
	class SellDlg extends JDialog {
		JPanel contentPane;
		JPanel jPanelInfo = new JPanel();
		JPanel jPanelButton = new JPanel();
		
		JLabel jLabelInfo = new JLabel();
		
		JLabel jLabelPrice = new JLabel("�����������Ʒ�ļ۸�(*)");
		JTextField jTextFieldPrice= new JTextField("");
		
		JLabel jLabelCount = new JLabel("�������������(*)");
		JTextField jTextFieldCount = new JTextField("1");

		JButton jButtonOk = new JButton("ȷ��");
		JButton jButtonExit = new JButton("ȡ��");

		public SellDlg() {
			super();
			try {
				init();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "SellDlg,��ʼ������:" + ex);
			}
		}

		private void init() {
			jLabelInfo.setText("��ȷ���ԡ�" + product.getPrice() + "��Ԫ�ĵ��۳�����Ʒ��"
					+ product.getName() + "��");
			jTextFieldPrice.setText(formatter.format(product.getPrice()));
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
			jPanelInfo.add(jLabelInfo, new XYConstraints(5, 10, 290, 20));
			jPanelInfo.add(jLabelPrice, new XYConstraints(5, 60, 160, 20));
			jPanelInfo.add(jTextFieldPrice, new XYConstraints(170, 60, 50, 20));
			jPanelInfo.add(jLabelCount, new XYConstraints(5, 85, 160, 20));
			jPanelInfo.add(jTextFieldCount, new XYConstraints(170, 85, 50, 20));
			
			contentPane = (JPanel) this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(jPanelInfo, BorderLayout.CENTER);
			contentPane.add(jPanelButton, BorderLayout.SOUTH);

			this.setTitle("������������");
			this.setIconImage(MainFrame.imageLogo.getImage());
			this.setSize(400, 200);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		protected void jButtonOk_actionPerformed(ActionEvent e) {
			if (verifyInput()) {
				double price = 0;
				int count = 0;
				try {
					price = Double.parseDouble(jTextFieldPrice.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "���ۼ۸��ʽ����ȷ");
					return;
				}
				try {
					count = Integer.parseInt(jTextFieldCount.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "��������������������");
					return;
				}
				if (count <= 0) {
					JOptionPane.showMessageDialog(this, "��������������������");
					return;
				}
				if (!product.getProductCategory().getId().equals(
						Constants.SEND_FEE)
						&& count > product.getCount()) {
					JOptionPane.showMessageDialog(this, "�����������ڿ����������");
					return;
				}
				// ����ԭ����ʱ���¿��������������������ܼ�
				// �����˷�ʱֻ������������
				int rowTable = jTable.getSelectedRow();
				if (rowTable != -1) {
					int row = jTable.convertRowIndexToModel(rowTable);
					if(!product.getProductCategory().getId().equals(Constants.SEND_FEE) ) {
						int oldCount = product.getCount();
						product.setCount(product.getCount() - count);
						product.setCountMake(product.getCountMake() == null ? oldCount
								- count : product.getCountMake() - count);
						tableModel.setValueAt(product.getCount(), row, 5);
						tableModel.setValueAt(formatter.format(product.getPrice()
								* product.getCount()), row, 6);
						tableModel.setValueAt(product.getCountMake(), row, 8);
						productService.update(product);
						tableModel.fireTableDataChanged();
					}
					ProductSell ps = new ProductSell();
					if(count > 0) {
						ps.setProductName(product.getName());
						ps.setProductPrice(price);
						ps.setCount(count);
						ps.setTotalPrice(product.getPrice() * count);
						ps.setProduct(product);
						ps.setTime(new Timestamp(System.currentTimeMillis()));
						productSellService.save(ps);
					}
				}
				this.dispose();
			}
		}

		protected void jButtonExit_actionPerformed(ActionEvent e) {
			this.dispose();
		}

		private boolean verifyInput() {
			String strPrice = jTextFieldPrice.getText();
			if (strPrice == null || strPrice.equals("")) {
				JOptionPane.showMessageDialog(this, "���ۼ۸���Ϊ�գ�");
				return false;
			}
			String strCount = jTextFieldCount.getText();
			if (strCount == null || strCount.equals("")) {
				JOptionPane.showMessageDialog(this, "������Ŀ����Ϊ�գ�");
				return false;
			}
			return true;
		}
		
	}
}

