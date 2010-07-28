package crystal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialPlot;

import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.borland.jbcl.layout.VerticalFlowLayout;
import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import crystal.common.Constants;
import crystal.common.data.JObject;
import crystal.panel.MaterialBuyPanel;
import crystal.panel.MaterialPanel;
import crystal.panel.ProductPanel;
import crystal.panel.ProductSellPanel;

/**
 * <p>
 * Title:BaseLine Database Query and Modify.
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class QuerySelect extends JPanel {
	private static final long serialVersionUID = 1L;
	
	String selectNet, selectType; // ѡ�������������
	Integer selectId;
	String lastSelectNet = "";
	TreePath currentPath;

	CardLayout cardLayout = new CardLayout();
	JPanel tablePanel = new JPanel();
	// ͳ�����
	JPanel statPanel = new JPanel();
	JLabel typeLabel = new JLabel();
	JLabel devNumLabel = new JLabel();
	JLabel pcNumLabel = new JLabel();
	JLabel segNumLabel = new JLabel();
	JLabel hostNumLabel = new JLabel();
	// ���з����豸���������ͳ��
	JLabel devAllLabel = new JLabel();
	JLabel pcAllLabel = new JLabel();
	JLabel hostAllLabel = new JLabel();
	TextDataFile deviceIfDF = new TextDataFile();
	TableDataSet deviceIfDS = new TableDataSet();

	JPanel rootAllPanel = new JPanel();
	JPanel numPanel = new JPanel();
	// ���ϱ�
	JTabbedPane rootPanel = new JTabbedPane();
	// �������
	JPanel computerPanel = new JPanel();

	// CardLayout��壺
	JPanel jPanelMaterial = new JPanel();
	MaterialPanel materialPanel = new MaterialPanel();
	JPanel jPanelProduct = new JPanel();
	ProductPanel productPanel = new ProductPanel();
	JPanel jPanelMaterialBuy = new JPanel();
	MaterialBuyPanel materialBuyPanel = new MaterialBuyPanel();
	JPanel jPanelProductSell = new JPanel();
	ProductSellPanel productSellPanel = new ProductSellPanel();
	
	// ������
	JPanel jPanelSubnet = new JPanel();
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(new JObject(0,"��Ŀ¼"));
	JTree jTree = new JTree(root);
	// JScrollPane treeView = new JScrollPane(jTree);
	JScrollPane treeView = new JScrollPane();
	DefaultTreeCellRenderer treeRenderer = new DefaultTreeCellRenderer();

	// �����˵�
	JPopupMenu jPopupMenu = new JPopupMenu();
	JMenu jMenuSetup = new JMenu("����");
	JMenuItem jMenuSnmpQuery = new JMenuItem("����");
	JMenuItem jMenuLinkDisc = new JMenuItem("���˷���");
	JMenuItem jMenuDevLink = new JMenuItem("�����˿�");
	JMenuItem jMenuTopNew = new JMenuItem("����������ͼ");
	JMenuItem jMenuSwitchLayout = new JMenuItem("����ͼ����");
	JMenuItem jMenuTopEdit = new JMenuItem("����ͼ����");
	JMenuItem jMenuSegment = new JMenuItem("����");
	JMenuItem jMenuAdd = new JMenuItem("���");
	JMenuItem jMenuRename = new JMenuItem("����");
	JMenuItem jMenuDel = new JMenuItem("ɾ��");

	JMenuItem jMenuStop = new JMenuItem("ֹͣ");

	JMenuItem jMenuTopDyn = new JMenuItem("����ͼ");
	JMenuItem jMenuDevStat = new JMenuItem("һ����");
	JMenuItem jMenuHostStat = new JMenuItem("һ����");
	JMenuItem jMenuSegBand = new JMenuItem("�����");

	// addCode 2009-04-13
	JSplitPane jSplitPaneConfig = new JSplitPane();

	// Panel 2
	JPanel jPanelLogQuery = new JPanel(); // GroupPanel�еİ�ť��ǩ����
	JScrollPane jScrollPane2 = new JScrollPane();
	JLabel jLabelLog = new JLabel();
	JButton jButtonComputer = new JButton();
	JButton jButtonArp = new JButton();
	JPanel jPanelLog = new JPanel(); // cardLayout �в�ѯ��־�������ʾ���

	// ������־ �� ARP��־ ���ڵ��ļ��б�
	JPanel jPanelLogList = new JPanel();
	JSplitPane jSplitPane = new JSplitPane();
	JPanel jPanelLeft = new JPanel();
	JPanel jPanelRight = new JPanel();
	JLabel jLabelLeft = new JLabel();
	JLabel jLabelRight = new JLabel();
	JTextArea jTextAreaComputerLog = new JTextArea();
	JScrollPane jScrollPaneComputerLog = new JScrollPane();

	JTextArea jTextAreaArpLog = new JTextArea();
	JScrollPane jScrollPaneArpLog = new JScrollPane();

	// Panel 3 keyDevice
	JPanel jPanelDeviceStatusConfig = new JPanel();
	JScrollPane jScrollPane3 = new JScrollPane();

	JLabel jLabelSubnet = new JLabel();
	JComboBox jComboBoxSubnet = new JComboBox();
	JLabel jLabelIp = new JLabel();
	JComboBox jComboBoxIp = new JComboBox();
	JButton jButtonAddDevice = new JButton();
	JButton jButtonDelDevice = new JButton();

	JPanel jPanelDeviceStatus = new JPanel();
	JPanel jPanelInterfaceStatus = new JPanel();
	JCalendar calendar = new JCalendar();
	JDateChooser dateChooser = new JDateChooser();
	JPanel jPanelInterfaceStatusConfig = new JPanel();
	JScrollPane jScrollPane4 = new JScrollPane();
	JLabel jLabelIfSubnet = new JLabel();
	JLabel jLabelIfIp = new JLabel();
	JLabel jLabelIfIndex = new JLabel();
	JLabel jLabelIfDesc = new JLabel();
	JComboBox jComboBoxIfSubnet = new JComboBox();
	JComboBox jComboBoxIfIp = new JComboBox();
	JComboBox jComboBoxIfIndex = new JComboBox();
	JTextArea jTextAreaIfDesc = new JTextArea();

	JButton jButtonAddIf = new JButton();
	JButton jButtonDelIf = new JButton();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");

	public QuerySelect() {
		try {
			jbInit();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "QuerySelect,��ʼ������" + ex);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	void jbInit() throws Exception {
		jMenuSetup.setIcon(MainFrame.imageConfig);
		jMenuSetup.setHorizontalTextPosition(SwingConstants.LEFT);

		jMenuAdd.setIcon(MainFrame.imageAddOne);
		jMenuAdd.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuAdd_actionPerformed(e);
			}
		});

		jMenuSnmpQuery.setIcon(MainFrame.imageSearch);
		jMenuSnmpQuery.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSnmpQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSnmpQuery_actionPerformed(e);
			}
		});

		jMenuLinkDisc.setIcon(MainFrame.imageTopDisc);
		jMenuLinkDisc.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuLinkDisc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuLinkDisc_actionPerformed(e);
			}
		});
		if (MainFrame.demoMode)
			jMenuLinkDisc.setEnabled(false);

		jMenuTopNew.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuTopNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuTopNew_actionPerformed(e);
			}
		});

		jMenuSwitchLayout.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSwitchLayout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSwitchLayout_actionPerformed(e);
			}
		});
		
		jMenuTopEdit.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuTopEdit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuTopEdit_actionPerformed(e);
			}
		});

		jMenuSegment.setIcon(MainFrame.imageSearch);
		jMenuSegment.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSegment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSegment_actionPerformed(e);
			}
		});

		jMenuStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuStop_actionPerformed(e);
			}
		});

		jMenuTopDyn.setIcon(new ImageIcon(crystal.MainFrame.iconPath
				+ "blockdevice.png"));
		jMenuTopDyn.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuTopDyn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuTopDyn_actionPerformed(e);
			}
		});

		jMenuDevStat.setIcon(MainFrame.imageStatus);
		jMenuDevStat.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuDevStat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuDevStat_actionPerformed(e);
			}
		});

		jMenuHostStat.setIcon(MainFrame.imageStatus);
		jMenuHostStat.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuHostStat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuHostStat_actionPerformed(e);
			}
		});

		jMenuSegBand.setIcon(MainFrame.imageBand);
		jMenuSegBand.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSegBand.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSegBand_actionPerformed(e);
			}
		});

		jMenuDevLink.setIcon(MainFrame.imageLinkport);
		jMenuDevLink.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuDevLink.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuDevLink_actionPerformed(e);
			}
		});
		jMenuRename.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuRename_actionPerformed(e);
			}
		});
		jMenuDel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuDel_actionPerformed(e);
			}
		});

		jPopupMenu.add(jMenuTopDyn);
		jPopupMenu.add(jMenuDevStat);
		jPopupMenu.add(jMenuHostStat);
		jPopupMenu.add(jMenuSegBand);
		jPopupMenu.add(jMenuTopEdit);
		jPopupMenu.add(jMenuSwitchLayout);
		jPopupMenu.add(jMenuSetup);
		jPopupMenu.add(jMenuStop);
		jMenuSetup.add(jMenuSnmpQuery);
		jMenuSetup.add(jMenuAdd);
		jMenuSetup.add(jMenuLinkDisc);
		jMenuSetup.add(jMenuTopNew);
		jMenuSetup.add(jMenuDevLink);
		jMenuSetup.add(jMenuSegment);
		jMenuSetup.add(jMenuRename);
		jMenuSetup.add(jMenuDel);


		jPopupMenu.setInvoker(jTree);

		jSplitPaneConfig.setPreferredSize(new Dimension(160, 740));
		jPanelSubnet.setBorder(BorderFactory.createEtchedBorder());
		jPanelSubnet.setPreferredSize(new Dimension(160, 340));
		tablePanel.setBorder(BorderFactory.createEtchedBorder());
		tablePanel.setLayout(cardLayout);
		jPanelSubnet.setLayout(new BorderLayout());

		treeView.getViewport().add(jTree);
		// ��������������
		// createTreeNodes();
		treeRenderer.setOpenIcon(MainFrame.imageSegFlux);
		treeRenderer.setClosedIcon(MainFrame.imageExit);
		treeRenderer.setLeafIcon(MainFrame.imageSubnet);
		jTree.setCellRenderer(treeRenderer);
		jTree.setEditable(false);
		jTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree.setShowsRootHandles(false); // �������۵�
		jTree
				.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
					public void valueChanged(TreeSelectionEvent e) {
						jTree_valueChanged(e);
					}
				});
		jTree.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jTree_mouseClicked(e);
			}
		});

		// addCode 2009-04-13
		jPanelLog.setLayout(new BorderLayout());
		DialPlot dial = new DialPlot();

		File logDir = new File("./log");
		String logList[] = logDir.list();

		for (int i = 0; i < logList.length; i++) {
			if (logList[i].endsWith("-arp.txt"))
				jTextAreaArpLog.append(logList[i] + "\n");
			else if (logList[i].endsWith(".txt")
					&& !(logList[i].endsWith("-data.txt"))
					&& !(logList[i].endsWith("-hour.txt"))
					&& !(logList[i].endsWith("error.txt"))
					&& !(logList[i].endsWith("log.txt")))
				jTextAreaComputerLog.append(logList[i] + "\n");
		}
		jTextAreaComputerLog.setEditable(false);
		jTextAreaArpLog.setEditable(false);
		jSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.setDividerLocation(400);
		jPanelLogList.setLayout(new BorderLayout());
		jPanelLogList.add(jSplitPane, BorderLayout.CENTER);
		jLabelLeft.setText("���ڵĽ�����־�б�");
		jPanelLeft.setLayout(new BorderLayout());
		jPanelLeft.add(jLabelLeft, BorderLayout.NORTH);
		jPanelLeft.add(jScrollPaneComputerLog, BorderLayout.CENTER);
		jLabelRight.setText("���ڵ�ARP��־�б�");
		jPanelRight.setLayout(new BorderLayout());
		jPanelRight.add(jLabelRight, BorderLayout.NORTH);
		jPanelRight.add(jScrollPaneArpLog, BorderLayout.CENTER);

		jSplitPane.add(jPanelLeft, JSplitPane.LEFT);
		jSplitPane.add(jPanelRight, JSplitPane.RIGHT);

		jScrollPaneComputerLog.getViewport().add(jTextAreaComputerLog);
		jScrollPaneArpLog.getViewport().add(jTextAreaArpLog);

		JFreeChart dialChart = new JFreeChart(dial);
		ChartPanel chartPanel = new ChartPanel(dialChart, false);
		chartPanel.setPreferredSize(new Dimension(100, 70));
		// arpLogPanel.add(chartPanel, BorderLayout.NORTH);

		this.setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.CENTER);
		add(jPanelSubnet, BorderLayout.WEST);
		
		jPanelSubnet.add(treeView, BorderLayout.CENTER);
		// ���з����豸���������ͳ��
		tablePanel.add(rootAllPanel, "rootPanel");
		numPanel.setLayout(new XYLayout());
		numPanel.add(devAllLabel, new XYConstraints(0, 0, 130, 20));
		numPanel.add(pcAllLabel, new XYConstraints(140, 0, 150, 20));
		numPanel.add(hostAllLabel, new XYConstraints(280, 0, 150, 20));
		// ����û�н���ˢ����䣬��Ȼ�������ã����κ�ʱ�򶼲�����ʾnumPanel���
		numPanel.setVisible(true); // �����κ���ݣ�����ʾ
		rootAllPanel.setLayout(new BorderLayout());
		rootAllPanel.add(numPanel, BorderLayout.NORTH);
		rootAllPanel.add(rootPanel, BorderLayout.CENTER);
		
		statPanel.setLayout(new VerticalFlowLayout());
		statPanel.setBorder(BorderFactory.createTitledBorder("����ͳ��"));
		statPanel.setBackground(Color.white);
		typeLabel.setIcon(MainFrame.imageType);
		devNumLabel.setIcon(MainFrame.imageDevice);
		pcNumLabel.setIcon(MainFrame.imageComputer);
		segNumLabel.setIcon(MainFrame.imageSegFlux);
		hostNumLabel.setIcon(MainFrame.imageHost);
		// ���з����豸���������ͳ��
		devAllLabel.setIcon(MainFrame.imageDevice);
		pcAllLabel.setIcon(MainFrame.imageComputer);
		hostAllLabel.setIcon(MainFrame.imageHost);
		tablePanel.add(statPanel, "statPanel");
		statPanel.add(typeLabel);
		statPanel.add(devNumLabel);
		statPanel.add(pcNumLabel);
		statPanel.add(hostNumLabel);

		jPanelMaterial.setLayout(new BorderLayout());
		jPanelMaterial.add(materialPanel, BorderLayout.CENTER);
		jPanelProduct.setLayout(new BorderLayout());
		jPanelProduct.add(materialPanel, BorderLayout.CENTER);
		jPanelMaterialBuy.setLayout(new BorderLayout());
		jPanelMaterialBuy.add(materialBuyPanel, BorderLayout.CENTER);
		jPanelProductSell.setLayout(new BorderLayout());
		jPanelProductSell.add(productSellPanel, BorderLayout.CENTER);
		tablePanel.add(jPanelMaterial, "jPanelMaterial");
		tablePanel.add(jPanelProduct, "jPanelProduct");
		tablePanel.add(jPanelMaterialBuy, "jPanelMaterialBuy");
		tablePanel.add(jPanelProductSell, "jPanelProductSell");
	}

	void myexit() {
	}

	// -----------------------����SNMP��������
	void jMenuSnmpQuery_actionPerformed(ActionEvent e) {
		
	}

	// ------------------------�����豸�����˿ڣ�
	void jMenuLinkDisc_actionPerformed(ActionEvent e) {
	}

	// ----------------------------���������е�����
	void jMenuSegment_actionPerformed(ActionEvent e) {
		
	}

	private void hostStatus(String type) {
	}

	private void segmentStatus() {
	}

	void jMenuStop_actionPerformed(ActionEvent e) {
	}

	// -------------------------��������
	void jMenuRename_actionPerformed(ActionEvent e) {
	}


	// -------------------------����ɾ��
	void jMenuDel_actionPerformed(ActionEvent e) {
	}

	// ����һ���������豸��SNMP������
	void jMenuAdd_actionPerformed(ActionEvent e) {
	}

	@SuppressWarnings("unchecked")
	private void addRecord() {
	}

	void jTree_mouseClicked(MouseEvent e) {
		if (selectType == null)
			return;
		jMenuSetup.setVisible(false);
		jMenuSnmpQuery.setVisible(false);
		jMenuLinkDisc.setVisible(false);
		jMenuTopNew.setVisible(false);
		jMenuTopEdit.setVisible(false);
		jMenuSegment.setVisible(false);
		jMenuDevLink.setVisible(false);
		jMenuAdd.setVisible(false);
		jMenuRename.setVisible(false);
		jMenuDel.setVisible(false);
		jMenuStop.setVisible(false);

		jMenuTopDyn.setVisible(false);
		jMenuSwitchLayout.setVisible(false);
		jMenuDevStat.setVisible(false);
		jMenuHostStat.setVisible(false);
		jMenuSegBand.setVisible(false);

		if (MainFrame.queryThreadRun) {
			jMenuStop.setVisible(true);
		} else {
			if (selectType.equals("��")) {
				jMenuSetup.setVisible(true);
				jMenuAdd.setVisible(true);
			} else if (selectType.equals("����")) {
				jMenuSetup.setVisible(true);
				jMenuRename.setVisible(true);
				jMenuDel.setVisible(true);
			}

			else if (selectType.equals("�����豸")) {
				jMenuSetup.setVisible(true);
				jMenuAdd.setVisible(true);
				jMenuSnmpQuery.setVisible(true);
				
				jMenuLinkDisc.setVisible(true);
				jMenuTopNew.setVisible(true);
				jMenuDevLink.setVisible(true);
				jMenuDevStat.setVisible(true);

				if (new File(selectNet + "����ͼ.tpg").exists()) {
					jMenuTopDyn.setVisible(true);
					jMenuTopEdit.setVisible(false);
					jMenuSwitchLayout.setVisible(true);
				}
			} else if (selectType.equals("�û�����")) {
				jMenuSetup.setVisible(true);
				jMenuAdd.setVisible(true);
				jMenuSegment.setVisible(true);
			}
		}

		// admin���������в�����guest�������κβ���
		// �Զ����û������ܶԸ�������ӣ�ɾ���������������ܶ��������и�����ɾ������
		// �����ڡ������豸���ڵ㣬���Խ������ɾ���豸������
		if (MainFrame.isAdmin())
			jMenuSetup.setEnabled(true);
		else if (!MainFrame.isGuest()) {
			if (selectType.equals("��") || selectType.equals("����"))
				jMenuSetup.setEnabled(false);
			else
				jMenuSetup.setEnabled(true);
		} else
			jMenuSetup.setEnabled(false);

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
			jPopupMenu.show(jTree, e.getX(), e.getY());
	}

	void jMenuTopNew_actionPerformed(ActionEvent e) {
	}

	void jMenuSwitchLayout_actionPerformed(ActionEvent e) {
	}
	
	void jMenuTopEdit_actionPerformed(ActionEvent e) {
	}

	void jMenuTopDyn_actionPerformed(ActionEvent e) {
	}

	void jMenuDevStat_actionPerformed(ActionEvent e) {
		hostStatus("�豸");
	}

	void jMenuHostStat_actionPerformed(ActionEvent e) {
		hostStatus("����");
	}

	void jMenuSegBand_actionPerformed(ActionEvent e) {
		segmentStatus();
	}

	void jMenuDevLink_actionPerformed(ActionEvent e) {
	}

	@SuppressWarnings("unchecked")
	void createTreeNodes() {
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode child = null;
		
		node = new DefaultMutableTreeNode(new JObject(Constants.TREE_MATERIAL_PRODUCT, "������"));
		root.add(node);
		child = new DefaultMutableTreeNode(new JObject(Constants.TREE_MATERIAL, "����"));
		node.add(child);
		child = new DefaultMutableTreeNode(new JObject(Constants.TREE_PRODUCT, "��Ʒ"));
		node.add(child);
		node = new DefaultMutableTreeNode(new JObject(Constants.TREE_BUY_SELL, "���ۼ�¼"));
		root.add(node);
		child = new DefaultMutableTreeNode(new JObject(Constants.TREE_BUY, "�������"));
		node.add(child);
		child = new DefaultMutableTreeNode(new JObject(Constants.TREE_SELL, "������Ʒ"));
		node.add(child);
		node = new DefaultMutableTreeNode(new JObject(Constants.TREE_REPORT, "����ͳ��"));
		root.add(node);
		jTree.expandRow(0);
		jTree.expandRow(1);
		jTree.expandRow(4);
		jTree.setSelectionRow(2);
	}

	public void rootPanelReset()
	{
//		rootPanel.removeAll();
//		faultTable.setParams("���ϱ�"); // ��ʾͷ30��
//		rootPanel.addTab("����TOP-30", new ImageIcon(crystal.MainFrame.iconPath
//				+ "fail.png"), faultTable);
//		delayTable.setParams("��ʱ��"); // ��ʾͷ30��
//		rootPanel.addTab("��ӦTOP-30", new ImageIcon(crystal.MainFrame.iconPath
//				+ "history16.png"), delayTable);
//		useTable.setParams("���ñ�"); // ��ʾͷ30��
//		rootPanel.addTab("����TOP-30", new ImageIcon(crystal.MainFrame.iconPath
//				+ "chart16.png"), useTable);
//		cpuTable.setParams("CPU��"); // ��ʾͷ30��
//		rootPanel.addTab("CPU TOP-30", MainFrame.imageCpuLoad, cpuTable);
//		
//		linkPortflowTable.setParams("�����˿�������");
//		rootPanel.addTab("�����˿����� TOP-30", MainFrame.imageDevice, linkPortflowTable);
//
//		if(MainFrame.fluxType==1)
//		{
//			usePortflowTable.setParams("�û��˿�������");
//			rootPanel.addTab("�û��˿����� TOP-30", MainFrame.imagePort, usePortflowTable);
//		}
	}
	void removeTreeNodes() {
		root.removeAllChildren();
		jTree.updateUI();
	}

	void jTree_valueChanged(TreeSelectionEvent e) {
		if (MainFrame.queryThreadRun) { // ��ֹ�ƶ�ѡ��
			jTree.setSelectionPath(currentPath);
			return;
		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree
				.getLastSelectedPathComponent();
		if (node == null)
			return;

		currentPath = jTree.getSelectionPath(); // ��¼��ǰ·��

		if (node.isRoot()) {
			selectType = "��";
			// ���з����豸���������ͳ��
			cardLayout.show(tablePanel, "jPanelDeviceStatus");
			cardLayout.show(tablePanel, "jPanelInterfaceStatus");
			cardLayout.show(tablePanel, "rootPanel");
		} else if (node.isLeaf()) {
			selectType = (String) node.getUserObject().toString();
//			selectNet = (String) node.getParent().toString();
//			DefaultMutableTreeNode obj = (DefaultMutableTreeNode) node
//					.getParent();
//			JObject o = (JObject) obj.getUserObject();
			selectId = ((JObject)node.getUserObject()).getId();
			
			// ����ѡ����ʾ��Ӧ��
			if (selectId.equals(Constants.TREE_MATERIAL)) {
				if (materialPanel != null) {
					jPanelMaterial.removeAll();
				}
				materialPanel = new MaterialPanel();
				jPanelMaterial.add(materialPanel, BorderLayout.CENTER);
				materialPanel.revalidate();
				jPanelMaterial.repaint();
				cardLayout.show(tablePanel, "jPanelMaterial");
			}
			if (selectId.equals(Constants.TREE_PRODUCT)) {
				if (productPanel != null) {
					jPanelProduct.removeAll();
				}
				productPanel = new ProductPanel();
				jPanelProduct.add(productPanel, BorderLayout.CENTER);
				productPanel.revalidate();
				jPanelProduct.repaint();
				cardLayout.show(tablePanel, "jPanelProduct");
			}
			if (selectId.equals(Constants.TREE_BUY)) {
				if (materialBuyPanel != null) {
					jPanelMaterialBuy.removeAll();
				}
				materialBuyPanel = new MaterialBuyPanel();
				jPanelMaterialBuy.add(materialBuyPanel, BorderLayout.CENTER);
				materialBuyPanel.revalidate();
				jPanelMaterialBuy.repaint();
				cardLayout.show(tablePanel, "jPanelMaterialBuy");
			}
			if (selectId.equals(Constants.TREE_SELL)) {
				if (productSellPanel != null) {
					jPanelProductSell.removeAll();
				}
				productSellPanel = new ProductSellPanel();
				jPanelProductSell.add(productSellPanel, BorderLayout.CENTER);
				productSellPanel.revalidate();
				jPanelProductSell.repaint();
				cardLayout.show(tablePanel, "jPanelProductSell");
			}

//			if (selectType.equals("���������")) {
//				if (archivesTable != null) {
//					computerPanel.removeAll();
//				}
//				archivesTable = new ArchivesTable(subnet.getSubnetId());
//				computerPanel.add(archivesTable, BorderLayout.CENTER);
//				archivesTable.revalidate();
//				computerPanel.repaint();
//				cardLayout.show(tablePanel, "computerPanel");
//			}
//
//			if (selectType.equals("�û�����")) {
//				if (segmentTable != null) {
//					segmentPanel.removeAll();
//				}
//				segmentTable = new SegmentTable(subnet.getSubnetId(), subnet
//						.getSubnetName());
//				segmentPanel.add(segmentTable, BorderLayout.CENTER);
//				segmentTable.revalidate();
//				segmentPanel.repaint();
//				cardLayout.show(tablePanel, "segmentPanel");
//			}

		} else if (node.isNodeAncestor(root)) {
			selectType = "����";
			selectNet = (String) node.getUserObject().toString();
			JObject obj = (JObject) node.getUserObject();
			selectId = obj.getId();
			// ͬ������ָ��
//			if (selectId > 0) {
//				for (Iterator it = subnetList.iterator(); it.hasNext();) {
//					subnet = (Subnet) it.next();
//					if (subnet.getSubnetId() == selectId)
//						break;
//				}
//			}
			if (!lastSelectNet.equals(selectNet)) {
				lastSelectNet = selectNet;
			}
			/*
			 * subnetDataSet.first(); do { if
			 * (subnetDataSet.getString("����").equals(selectNet)) break; } while
			 * (subnetDataSet.next());
			 */
			// ��������ı���¸�����ͼ
			/*
			 * if (!lastSelectNet.equals(selectNet)) {
			 * deviceSnmpTable.subnetDataView(selectNet);
			 * hostSnmpTable.subnetDataView(selectNet);
			 * computerTable.subnetDataView(selectNet);
			 * segmentTable.subnetDataView(selectNet); lastSelectNet =
			 * selectNet; }
			 */
			// ��ʾͳ������
			showNetNum();
			cardLayout.show(tablePanel, "statPanel");
		}
	}

	private void showNetNum() {
		
	}
}
