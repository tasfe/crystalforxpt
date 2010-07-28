package crystal;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;

import crystal.common.TableUtil;
import crystal.common.data.JObject;
import crystal.hibernate.po.Material;

public class SnmpTable extends JPanel {

	private static final long serialVersionUID = 1L;
	String mode;
	Integer subnetId;
	// 设备查询线程指针：
	Thread thread = null;
	// 设备查询条件、过滤模式和过滤标志
	String selectedItem = "全部";
	boolean dataChanged = false;
	String ignoreIp = "";
	String ignoreSubnet = "";
	
	Material material = null;
	List<Material> materialList = new ArrayList<Material>();

	JPanel jp = this;

	// 主机端口选择表
	TextDataFile portDataFile = new TextDataFile();
	TableDataSet portDataSet = new TableDataSet();

	JScrollPane jScrollPane = new JScrollPane();

	DefaultTableModel dtm = null;
	JTable jt = null;

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuDel = new JMenuItem("删除当前记录(D)");
	JMenuItem jMenuExport = new JMenuItem("导出视图到Excel文件");

	public SnmpTable(String md, Integer subnetId) {
		mode = md;
		this.subnetId = subnetId;
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "SnmpTable,初始化错误：" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		Object[][] tabledata = {};
		String[] tableTitles = { "序号", "IP", "读Community", "写Community", "描述",
				"名称", "类型", "厂商", "备注1", "备注2" };
		dtm = new DefaultTableModel(tabledata, tableTitles) {
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1)
					return false;
				return true;
			}
		};
		jt = new JTable(dtm);
		int index = 1;
		for (Material m : materialList) {
			Object[] object = { index };
			dtm.addRow(object);
			index++;
		}
		if (MainFrame.dispComm) {
			TableUtil.unsetHiddenColumn(jt, new int[] { 2, 3 }, new int[] {
					100, 100 });
		} else {
			TableUtil.setHiddenColumn(jt, new int[] { 2, 3 });
		}
		TableRowSorter sorter = new TableRowSorter(dtm);
		jt.setRowSorter(sorter);
		Comparator numComparator = new Comparator() {
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
		sorter.setComparator(0, numComparator);
		Comparator ipComparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				String ip1 = o1.toString();
				String ip2 = o2.toString();
				long ip1Value = Tools.getIpLong(ip1);
				long ip2Value = Tools.getIpLong(ip2);
				return (int) (ip1Value - ip2Value);
			}
		};
		sorter.setComparator(1, ipComparator);
		jt.setRowHeight(20);
		TableColumn firsetColumn = jt.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(50);
		firsetColumn.setMaxWidth(80);
		firsetColumn.setMinWidth(40);
		TableUtil.makeFace(jt);
		TableColumn secondColumn = jt.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(120);
		secondColumn.setMaxWidth(200);
		secondColumn.setMinWidth(60);
		TableColumn sixColumn = jt.getColumnModel().getColumn(6);
		sixColumn.setPreferredWidth(80);
		sixColumn.setMaxWidth(200);
		sixColumn.setMinWidth(60);
		JComboBox ct = new JComboBox();

		JPasswordField passwordEditor = new JPasswordField();
		jt.getColumn("读Community").setCellEditor(
				new DefaultCellEditor(passwordEditor));
		jt.getColumn("写Community").setCellEditor(
				new DefaultCellEditor(passwordEditor));
		DefaultCellEditor cellEditorType = new DefaultCellEditor(ct);
		cellEditorType.setClickCountToStart(2);
		jt.getColumnModel().getColumn(6).setCellEditor(cellEditorType);
		JComboBox c = new JComboBox();
		c.addItem(new JObject(-1, ""));

		DefaultCellEditor cellEditorVendor = new DefaultCellEditor(c);
		cellEditorVendor.setClickCountToStart(2);
		jt.getColumnModel().getColumn(7).setCellEditor(cellEditorVendor);
		JScrollPane jsp = new JScrollPane(jt);
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.setVisible(true);
		if (jt.getRowCount() > 0) {
			jsp.getVerticalScrollBar().setValue(0);
			jt.setRowSelectionInterval(0, 0);
		}
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int selectRow = jt.getSelectedRow();
					if (selectRow != -1) {
						int modelSeqno = jt.convertRowIndexToModel(selectRow);
						material = (Material) materialList.get((Integer) dtm
								.getValueAt(modelSeqno, 0) - 1);
						jdbTable_mouseClicked(e);
					}
				}
			}
		});
		jt.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				Point point = e.getPoint();
				int x = jt.rowAtPoint(point);
				int y = jt.columnAtPoint(point);
				Object tip = jt.getValueAt(x, y);
				if (y == 2 || y == 3) {
					jt.setToolTipText("");
					return;
				}
				if (tip != null) {
					jt.setToolTipText(tip.toString());
				}
			}
		});

		dtm.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column != -1) {
					material = (Material) materialList.get((Integer) dtm
							.getValueAt(row, 0) - 1);
					String tValue = dtm.getValueAt(row, column).toString();
					int cNum = 0;

					if (column == 3) {
						// if (tValue != null &&
						// !tValue.equals(device.getWriteCommunity())) {
						// device.setWriteCommunity(tValue);
						// cNum++;
						// }
					}

					if (cNum != 0) {
						// deviceService.update(device);
						// device = deviceService.load(device.getId());
					}
				}
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
		jPopupMenu.add(jMenuDel);
		jPopupMenu.add(jMenuExport);

		jPopupMenu.setInvoker(jt);
	}

	void query(String qry) {
	}


	void addRecord(Integer netType) {
		
	}

	public void reloadTable() {
//		dtm.getDataVector().removeAllElements();
//		dtm.fireTableDataChanged();
//		
//		dtm.fireTableDataChanged();
	}

	// ------------------------------删除设备记录
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "确认删除选中记录？", "删除记录",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;

//		deviceList.remove(device);
//		deviceService.delete(device);
//		reLoadView();
//
//		// 删除关联的表：设备接口表、主机Cache表
//		deviceInterfaceService = (DeviceInterfaceService) AppContextUtil
//				.getInstance().getBean("deviceInterfaceService");
//		deviceInterfaceService.deleteBySubnetIp(device.getSubnet()
//				.getSubnetId(), device.getIp());
//		// 删除关联的表：网段表
//		segmentService = (SegmentService) AppContextUtil.getInstance().getBean(
//				"segmentService");
//		segmentService.deleteBySubnetDevice(device.getSubnet().getSubnetId(),
//				device.getIp());
//		;
	}

	// 导出视图到文本文件
	void jMenuExport_actionPerformed(ActionEvent e) {
//		String[] columnName = { "IP", "读Community", "写Community", "描述", "名称",
//				"类型", "厂商", "备注1", "备注2" };
//		int[] columnLen = { 15, 15, 15, 20, 15, 10, 15, 10, 10 };
//
//		String[] columnName2 = { "IP", "读Community", "描述", "名称", "备注1", "备注2",
//				"备注3", "备注4" };
//		int[] columnLen2 = { 15, 15, 20, 15, 10, 10, 10, 10 };
//
//		List tableData = dtm.getDataVector();
//		if (mode.equals("网络设备")) {
//			excelUtil.exportExcel(this, mode + "表.xls", mode + "表", columnName,
//					columnLen, tableData);
//		} else if (mode.equals("SNMP主机")) {
//			excelUtil.exportExcel(this, mode + "表.xls", mode + "表",
//					columnName2, columnLen2, tableData);
//		}
	}

	void jdbTable_mouseClicked(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			jPopupMenu.show(jt, e.getX(), e.getY());
		}
	}
}
