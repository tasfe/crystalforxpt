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
	// �豸��ѯ�߳�ָ�룺
	Thread thread = null;
	// �豸��ѯ����������ģʽ�͹��˱�־
	String selectedItem = "ȫ��";
	boolean dataChanged = false;
	String ignoreIp = "";
	String ignoreSubnet = "";
	
	Material material = null;
	List<Material> materialList = new ArrayList<Material>();

	JPanel jp = this;

	// �����˿�ѡ���
	TextDataFile portDataFile = new TextDataFile();
	TableDataSet portDataSet = new TableDataSet();

	JScrollPane jScrollPane = new JScrollPane();

	DefaultTableModel dtm = null;
	JTable jt = null;

	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenuItem jMenuDel = new JMenuItem("ɾ����ǰ��¼(D)");
	JMenuItem jMenuExport = new JMenuItem("������ͼ��Excel�ļ�");

	public SnmpTable(String md, Integer subnetId) {
		mode = md;
		this.subnetId = subnetId;
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "SnmpTable,��ʼ������" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		Object[][] tabledata = {};
		String[] tableTitles = { "���", "IP", "��Community", "дCommunity", "����",
				"����", "����", "����", "��ע1", "��ע2" };
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
		jt.getColumn("��Community").setCellEditor(
				new DefaultCellEditor(passwordEditor));
		jt.getColumn("дCommunity").setCellEditor(
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

	// ------------------------------ɾ���豸��¼
	void jMenuDel_actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��ѡ�м�¼��", "ɾ����¼",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;

//		deviceList.remove(device);
//		deviceService.delete(device);
//		reLoadView();
//
//		// ɾ�������ı��豸�ӿڱ�����Cache��
//		deviceInterfaceService = (DeviceInterfaceService) AppContextUtil
//				.getInstance().getBean("deviceInterfaceService");
//		deviceInterfaceService.deleteBySubnetIp(device.getSubnet()
//				.getSubnetId(), device.getIp());
//		// ɾ�������ı����α�
//		segmentService = (SegmentService) AppContextUtil.getInstance().getBean(
//				"segmentService");
//		segmentService.deleteBySubnetDevice(device.getSubnet().getSubnetId(),
//				device.getIp());
//		;
	}

	// ������ͼ���ı��ļ�
	void jMenuExport_actionPerformed(ActionEvent e) {
//		String[] columnName = { "IP", "��Community", "дCommunity", "����", "����",
//				"����", "����", "��ע1", "��ע2" };
//		int[] columnLen = { 15, 15, 15, 20, 15, 10, 15, 10, 10 };
//
//		String[] columnName2 = { "IP", "��Community", "����", "����", "��ע1", "��ע2",
//				"��ע3", "��ע4" };
//		int[] columnLen2 = { 15, 15, 20, 15, 10, 10, 10, 10 };
//
//		List tableData = dtm.getDataVector();
//		if (mode.equals("�����豸")) {
//			excelUtil.exportExcel(this, mode + "��.xls", mode + "��", columnName,
//					columnLen, tableData);
//		} else if (mode.equals("SNMP����")) {
//			excelUtil.exportExcel(this, mode + "��.xls", mode + "��",
//					columnName2, columnLen2, tableData);
//		}
	}

	void jdbTable_mouseClicked(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			jPopupMenu.show(jt, e.getX(), e.getY());
		}
	}
}
