package crystal;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.borland.dbswing.FontChooser;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;

import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.common.DatabaseMail;
import crystal.common.data.IniReader;
import crystal.service.BackupService;

/**
 * <p>
 * Title:MainFrame
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

public class MainFrame extends JFrame {
	private final Log logger = LogFactory.getLog(MainFrame.class);
	private Runtime runtime = Runtime.getRuntime();
	private BackupService backupService;

	public static JFrame mainFrame;// 主窗口句柄
    private TrayIcon trayIcon = null;
    private SystemTray systemTray;//系统托盘
	
	public final static String USER_ADMIN = "admin";
	public final static String USER_GUEST = "guest";

	// 是否需要写入报警中间表
	public static boolean rjw_trans = false;
	public static boolean cugb = false;
	
	public static boolean demoMode = false;// 是否演示模式：
	static boolean evalMode;// 是否试用模式
	static boolean deviceModule = true; //是否基本模块
	static boolean hostModule = true; // 是否服务器模块
	static boolean reportModule = true; //是否报表模块
	static boolean webModule = true;// 是否增加web模块
	static boolean switchModule = true; //是否交换机操作模块
	static boolean snifferModule = true; //是否流量分析模块
	static boolean alertModule = true;	//是否报警策略模块

	static boolean dataBase = true;// 是否启用数据库模块
	static int licnum;// 用户许可数
	static String mainTitle = "控制台";

	AppContextUtil bp = (AppContextUtil) AppContextUtil.getInstance();

	// 数据恢复、备份模式：1 不包含历史数据。2 包含历史数据
	static int backupType = 1;

	// static String isRestart = "正常";//是否是重启模式，如果是则不显示登录窗口
	// 试用开始日期。注意：月份须按实际月份减1。
	static GregorianCalendar evalDate = new GregorianCalendar();
	static String InstallPath;// 系统安装目录
	static int copyStatus;// 0:无复制，1：复制端口，2：复制行
	// 连接表端口复制(上下位）：IP、端口、接口、接口描述、接口备注
	static String[] ifCopy = new String[] { "", "", "", "", "", "", "", "", "",
			"" };
	// 系统参数：
	// 定时扫描时间：可以设置三个时间，24小时表示，缺省-1为不定时
	static int[] scanHour = new int[] { -1, -1, -1 };
	// 定时采样时间：24小时表示，缺省-1为不采样
	// static int pollHour = -1;
	// NETBIOS扫描线程间隔。SNMP扫描线程与扫描模版无关，采用固定间隔50。
	static int threadGap = 50;
	// 清华校园网远程测试表明，2秒个别路由器出错，在园区网规模上2秒应该可以，3秒应较保险。
	// 缺省snmpTimeOut = 5；snmpRetry = 0；发行设置为1和1，共1、2合计3秒。重试可选2，共3/7秒。
	static int snmpTimeOut = 1;
	static int snmpRetry = 1;
	// 10、15、20ms测试表明，15ms较理想，可以让路由器学到IP及其MAC。30ms更保险。在此
	// 水平上，一个路由器下可挂20个C类子网，使PING有效，并且ARP表不超时。
	static int pingGap = 30;
	static int pollGap = 30;// 拓扑、设备、主机状态轮询间隔
	public static int cpuLimen = 10;// CPU负荷阈值，百分比
	public static int bandLimen = 55;// 接口带宽警示阈值，百分比
	static int unicastLimen = 1000;// 接口单播包阈值（个/秒）
	static int broadcastLimen = 100;// 接口广播包阈值（个/秒）
	public static int tempLimen = 25;// AKCP温度阈值（度）
	public static String backColor = "黑";// 拓扑图、动态表背景色
	static String exportFile = "否";// 输出动态到文件
	static String deviceBgPath = "images/deviceBgIcon.jpg"; // 设备拓扑背景图
	static String hostBgPath = "images/hostBgIcon.jpg"; // 服务器拓扑背景图
	static String userBgPath = "images/userBgIcon.jpg"; // 用户拓扑背景图

	//demo 模式下，默认的历史数据区段
	public static Timestamp startTime = new Timestamp(110, 0, 4, 0, 0, 0, 0);
	public static Timestamp endTime = new Timestamp(110, 0, 10, 0, 0, 0, 0);
	
	// 服务器阈值
	static int tcpConn = 100;
	static int procNum = 50;
	static double memRate = 90;
	static double vmemRate = 90;
	static double diskRate = 90;

	public static int checkGap = 10;// 设备、主机检查间隔（分钟）
	public static int errorGap = 1; // 异常后的检查间隔（分钟）
	static String delGap = "三个月";// 计算机表过期自动删除时间
	static int syncHour = -1; // 自动同步时间，-1：不同步。 1-24 ：N时:15分时同步
	static String antiAliased = "否";
	// 本机读Community
	static String localCommStr = "public";
	// 用户口令
	static String adminPwd = "";
	static String userPwd = "";
	public static String userType = "";
	public static ArrayList subnetList = new ArrayList();
	// SMS设置
	//public static String smsNum = "";
	public static String smsSn = "";
	public static String smsAlert = "关"; // 短信报警
	public static String emailAlert = "关"; // 邮件报警
	public static String soundAlert = "关";// 声音报警
	public static int arpLimen = 5; // Arp警示阈值，一MAC对多IP数目，默认为5
	// 数据同步服务器地址
	static String syncServer = "";
	// 用户自定义菜单
	static String toolName1 = "";
	static boolean toolIp1 = true;
	static String toolPath1 = "";
	static String toolName2 = "";
	static boolean toolIp2 = false;
	static String toolPath2 = "";
	static boolean dispComm = true;

	// 扫描设置：
	static boolean l3Scan = true;// 是否扫描ARP表
	static boolean l2Scan = true;// 是否扫描MAC转发表
	static boolean arpNbt;// SNMP模式是否进行WINS扫描
	static boolean pingScan;// 使用PING预扫描还是不使用
	static int scanGapTime = 0;// 两次扫描间的间隔，ms为单位，0表示扫描一次。
	static boolean ignoreError;// 是否忽略扫描异常：PING/出错/超时/同一MAC出现在不同端口
	public static int fluxType = 2;// 1：所有端口 ；2：互联端口

	public static String mailAddr1 = "";
	//static String mailAddr2 = "";
	public static String smtpServer = "";
	public static String mailSender = "";
	public static String mailSenderPwd = "";

	// 拓扑图幻灯显示
	public static int topIndex = 0;		// 遍历拓扑List时的index
	public static int scrollTimeLong = 5;	// Timer的时长(秒)

	// 模块间参数：
	static boolean snapShotDisplay;// SnapShotDlg显示快照还是日志：SnapShotDlg和Mainframe
	static String logQryDate;// 日志查询日期。SnapShotDlg和LogQuerySetupDlg。
	static String tableDataFileName;// 表对话框显示数据文件的名字。用在QuerySelect,HostQuery和TableDlg

	javax.swing.Timer timer;// 定时器，完成定时检查。使用javax.swing而不是java.util中的Timer。
	public static boolean queryThreadRun = false;// 档案查建查询线程是否启动标志
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// 数据表文件名
	// 2009-07-14 添加public 属性，在其它package里也能访问 deviceFileName, hostFileName,
	public static String alertFileName, argumentFileName, paramsFileName,
			c1FileName, logFileName, interfaceFileName, routerFileName,
			macPortFileName, snmpFileName, arpFileName, sysFileName,
			devMacFileName, neibourFileName, operLogFileName, vendorFileName,
			userFileName, userSubnetFileName, keyDevIf;

	// addCode 2009-03-18
	static String confBakDir;
	static double columnScale = 1.4;
	static double rowHeaderScale = 1.5;

	boolean isLoop = false; // true表示在间隔扫描，否则表示“扫描一次”
	boolean isGapLoop = false;
	public static boolean scanLoopRunning = false; // 表示正在扫描过程中，标识ScanLoop线程的状态
	public int scanMin = 0; // 倒计时时间 scanGapTime 分钟
	public static String scanPrefix = "";

	// 现场表及文件
	static TextDataFile argumentDataFile = new TextDataFile();
	static TableDataSet argumentDataSet = new TableDataSet();

	// html文件存放路径
	static String tomcatPath;
	static String htmlPath;
	JPanel contentPane;
	QuerySelect querySelect;

	// 菜单：
	JMenuBar jMenuBar = new JMenuBar();
	// 档案菜单：
	JMenu jMenuSystem = new JMenu();
	JMenuItem jMenuSync = new JMenuItem();
	static JMenuItem jMenuBackup = new JMenuItem();
	static JMenuItem jMenuRestore = new JMenuItem();
	JMenuItem jMenuMini = new JMenuItem(); //最小化到系统托盘
	JMenuItem jMenuExit = new JMenuItem();
	static JMenuItem jMenuParams = new JMenuItem();
	static JMenuItem jMenuAlertPolicy = new JMenuItem();
	// 扫描菜单：
	JMenu jMenuScan = new JMenu();
	// 在QuerySelect中通过jMenuStart判断扫描是否在进行，如进行中，禁止QuerySelect中的有关编辑控件。
	static JMenuItem jMenuStart = new JMenuItem();
	static JMenuItem jMenuStop = new JMenuItem();
	static JMenuItem jMenuSnapShot = new JMenuItem();
	static JMenuItem jMenuScanParams = new JMenuItem();
	// 日志菜单：
	JMenu jMenuLog = new JMenu();
	JMenuItem jMenuUserLog = new JMenuItem();
	JMenuItem jMenuArpLog = new JMenuItem();
	JMenuItem jMenuOperLog = new JMenuItem();
	// 数据导出菜单：
	JMenu jMenuDataExport = new JMenu();
	JMenuItem jMenuDeviceExport = new JMenuItem();
	JMenuItem jMenuHostExport = new JMenuItem();
	JMenuItem jMenuComputerExport = new JMenuItem();
	JMenuItem jMenuArpExport = new JMenuItem();// 实时Arp表
	JMenuItem jMenuMacExport = new JMenuItem();
	JMenuItem jMenuComputerLogExport = new JMenuItem();
	// 用户权限管理菜单
	JMenu jMenuUserManage = new JMenu();
	static JMenuItem jMenuUserAdd = new JMenuItem();
	static JMenuItem jMenuSubnetConf = new JMenuItem();
	// 登录菜单
	JMenu jMenuLogin = new JMenu();
	static JMenuItem jMenuUserLogin = new JMenuItem();
	// 关于菜单：
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JMenuItem jMenuManual = new JMenuItem();

	// testCode 2009-04-09
	JMenuItem jMenuFont = new JMenuItem("字体设置");

	// 工具条和按钮：
	JToolBar jPanelButtonBar = new JToolBar();
	static JButton jButtonSnapShot = new JButton();
	static JButton jButtonParams = new JButton();
	static JButton jButtonAlertParams = new JButton();
	static JButton jButtonStart = new JButton();
	static JButton jButtonStop = new JButton();
	static JButton jButtonBackup = new JButton();
	static JButton jButtonRestore = new JButton();
	JButton jButtonLogQuery = new JButton();
	static JButton jButtonLogin = new JButton();
	JButton jButtonManual = new JButton();
	
	PopupMenu popupMenu = new PopupMenu();
	MenuItem trayOpen = new MenuItem();
	MenuItem trayExit = new MenuItem();

	public static String iconPath = "icons/";
	// Logo 图片
	public static ImageIcon imageLogo = new ImageIcon(iconPath + "logo.gif");
	// startLogo 图片
	// static ImageIcon imageStartLogo = new ImageIcon(iconPath + "start.gif");
	// UI 图片
	ImageIcon imageSnapShot = new ImageIcon(iconPath + "snapshot.png");
	ImageIcon imageStart = new ImageIcon(iconPath + "startscan.png");
	ImageIcon imageStop = new ImageIcon(iconPath + "stopscan.png");
	static ImageIcon imageConfig = new ImageIcon(iconPath + "Network_Settings.png");
	ImageIcon imageParams = new ImageIcon(iconPath + "params.png");
	ImageIcon imageAlertParams = new ImageIcon(iconPath + "alert_params.png");
	static ImageIcon imageLogComputer = new ImageIcon(iconPath + "logquery.png");
	static ImageIcon imageLogin = new ImageIcon(iconPath + "user.png");
	static ImageIcon imagePassword = new ImageIcon(iconPath + "password.png");
	static ImageIcon imageUsergroup = new ImageIcon(iconPath + "usergroup.png");
	ImageIcon imageAbout = new ImageIcon(iconPath + "about.png");
	static ImageIcon imageBackup = new ImageIcon(iconPath + "backup.png");
	ImageIcon imageRestore = new ImageIcon(iconPath + "restore.png");
	ImageIcon imageScan = new ImageIcon(iconPath + "scanstatus.gif");

	public static ImageIcon imageFlux = new ImageIcon(iconPath + "interface.gif");
	public static ImageIcon imageNormalReport = new ImageIcon(iconPath + "normalreport.png");
	public static ImageIcon imageReportDay = new ImageIcon(iconPath + "report_day.png");
	public static ImageIcon imageReportWeek = new ImageIcon(iconPath + "report_week.png");
	public static ImageIcon imageReportMonth = new ImageIcon(iconPath + "report_month.png");
	public static ImageIcon imageReportQuarte = new ImageIcon(iconPath + "report_quarte.png");
	public static ImageIcon imageReportHalfyear = new ImageIcon(iconPath + "report_halfyear.png");
	public static ImageIcon imageReportYear = new ImageIcon(iconPath + "report_year.png");
	public static ImageIcon imageReportCustom = new ImageIcon(iconPath + "report_custom.png");
	public static ImageIcon imageHttp = new ImageIcon(iconPath + "http.png");

	static ImageIcon imageDataMerge = new ImageIcon(iconPath + "datamerge.png");
	static ImageIcon imageExit = new ImageIcon(iconPath + "treecollapse.png");
	public static ImageIcon imageOnOff = new ImageIcon(iconPath + "ifcontrol.gif");
	static ImageIcon imageSubnet = new ImageIcon(iconPath + "treenode.png");
	public static ImageIcon imageType = new ImageIcon(iconPath + "sub_type.png");
	static ImageIcon imageDevice = new ImageIcon(iconPath + "device.png");
	static ImageIcon imageHost = new ImageIcon(iconPath + "server16.png");
	public static ImageIcon imageComputer = new ImageIcon(iconPath + "pcgreen.png");
	static ImageIcon imageSegFlux = new ImageIcon(iconPath + "treeexpand.png");
	static ImageIcon imagePort = new ImageIcon(iconPath + "port.gif");
	public static ImageIcon imageAddOne = new ImageIcon(iconPath + "add.png");
	public static ImageIcon imageExport = new ImageIcon(iconPath + "totxt.gif");
	static ImageIcon imageHistory = new ImageIcon(iconPath + "history.png");

	// other file use
	static ImageIcon imagePcgray = new ImageIcon(iconPath + "pcgray.png");
	static ImageIcon imagePcgreen = new ImageIcon(iconPath + "pcgreen.png");
	static ImageIcon imagePcgreen_RedFrame = new ImageIcon(iconPath + "pcgreen_redframe.png");
	static ImageIcon imagePcgreen_BlueFrame = new ImageIcon(iconPath + "pcgreen_blueframe.png");
	static ImageIcon imagePcgreen_DoubleFrame = new ImageIcon(iconPath + "pcgreen_doubleframe.png");

	ImageIcon imageHelp = new ImageIcon(iconPath + "help.png");
	ImageIcon imageGuide = new ImageIcon(iconPath + "guide.png");
	ImageIcon imageManual = new ImageIcon(iconPath + "manual.png");
	static ImageIcon imageCalendar = new ImageIcon(iconPath + "calendar.png");
	static ImageIcon imageLogArp = new ImageIcon(iconPath + "logarp.png");
	static ImageIcon imageIfPoll = new ImageIcon(iconPath + "interfacepoll.png"); // 接口流量
	static ImageIcon imageTopDisc = new ImageIcon(iconPath + "topdisc.png"); // 拓扑发现
	static ImageIcon imageSearch = new ImageIcon(iconPath + "search.png");
	static ImageIcon imageHub = new ImageIcon(iconPath + "hub.png");
	static ImageIcon imageSwitchOperate = new ImageIcon(iconPath + "switch_operate.png");
	static ImageIcon imageFlowAnalysis = new ImageIcon(iconPath + "flow_Analysis.png");
	static ImageIcon imageHub_RedFrame = new ImageIcon(iconPath + "hub_redframe.png");
	static ImageIcon imageHub_BlueFrame = new ImageIcon(iconPath + "hub_blueframe.png");
	static ImageIcon imageHub_DoubleFrame = new ImageIcon(iconPath + "hub_doubleframe.png");
	static ImageIcon imageClockArrow = new ImageIcon(iconPath + "clock_arrow.png");// hostStatus图标

	// other QuerySelect
	static ImageIcon imageCpuLoad = new ImageIcon(iconPath + "cpuload.gif");
	static ImageIcon imageLinkport = new ImageIcon(iconPath + "linkport.gif");
	static ImageIcon imageChartLine = new ImageIcon(iconPath + "chart_line.png");
	// lsj 2009-08-28
	public static ImageIcon imageCatalog = new ImageIcon(iconPath + "catalog.gif");// 计算机表，下拉菜单，分类图标
	public static ImageIcon imageKeyword = new ImageIcon(iconPath + "keyword.gif");// 计算机表，查询关键字
	static ImageIcon imageImport = new ImageIcon(iconPath + "import.gif");// 计算机表，按IP导入管理字段
	static ImageIcon imageSort = new ImageIcon(iconPath + "sort.gif");// 计算机表，排序
	static ImageIcon imageZoom = new ImageIcon(iconPath + "zoom.gif");
	static ImageIcon imageZoomIn = new ImageIcon(iconPath + "zoomin.gif");
	static ImageIcon imageZoomOut = new ImageIcon(iconPath + "zoomout.gif");
	static ImageIcon imageCrystal = new ImageIcon(iconPath + "crystal.png"); // 关于信息
	public static ImageIcon imageCache = new ImageIcon(iconPath + "cache.gif");
	// paramsDlg
	static ImageIcon imageTime = new ImageIcon(iconPath + "time.png");
	static ImageIcon imageAlert = new ImageIcon(iconPath + "alert.png");
	static ImageIcon imageOther = new ImageIcon(iconPath + "other.png");
	static ImageIcon imageScanConfig = new ImageIcon(iconPath
			+ "scanconfig.gif");
	// SegmentStatus
	static ImageIcon imageDual = new ImageIcon(iconPath + "dual.gif");
	static ImageIcon imageSend = new ImageIcon(iconPath + "send.gif");
	static ImageIcon imageRecv = new ImageIcon(iconPath + "recv.gif");
	static ImageIcon imageInfo = new ImageIcon(iconPath + "blockdevice.png");
	static ImageIcon imageBand = new ImageIcon(iconPath + "band.gif");
	static ImageIcon imageSegflux = new ImageIcon(iconPath + "segflux.gif");

	// Topo
	static ImageIcon imageSwith = new ImageIcon(iconPath + "switch_green.png");
	static ImageIcon imageL2Normal = new ImageIcon(iconPath + "switch_green.png");
	static ImageIcon imageL2Red = new ImageIcon(iconPath + "switch_red.png");
	static ImageIcon imageL2Yellow = new ImageIcon(iconPath + "switch_yellow.png");
	static ImageIcon imageL3Normal = new ImageIcon(iconPath + "router_green.png");
	static ImageIcon imageL3Red = new ImageIcon(iconPath + "router_red.png");
	static ImageIcon imageL3Yellow = new ImageIcon(iconPath + "router_yellow.png.gif");

	static ImageIcon imageOtherNode = new ImageIcon(iconPath + "othernode.png");
	static ImageIcon imageOtherNode_RedFrame = new ImageIcon(iconPath + "othernode_red_redframe.png");
	static ImageIcon imageOtherNode_BlueFrame = new ImageIcon(iconPath + "othernode_red_blueframe.png");
	static ImageIcon imageOtherNode_DoubleFrame = new ImageIcon(iconPath + "othernode_red_doubleframe.png");

	static ImageIcon imageBlueFrame = new ImageIcon(iconPath + "blueframe_s.png");
	static ImageIcon imageRedFrame = new ImageIcon(iconPath + "redframe_s.png");

	static ImageIcon imageCloud = new ImageIcon(iconPath + "cloud.gif");
	static ImageIcon imageHostNormal = new ImageIcon(iconPath + "hostnorm.gif");
	static ImageIcon imageHostYellow = new ImageIcon(iconPath + "hostyellow.gif");
	static ImageIcon imageHostRed = new ImageIcon(iconPath + "hostred.gif");
	static ImageIcon imageGreenBall = new ImageIcon(iconPath + "greenball.gif");
	static ImageIcon imageRedBall = new ImageIcon(iconPath + "redball.gif");
	public static ImageIcon imageSlimen = new ImageIcon(iconPath + "slimen.gif");
	public static ImageIcon imageConflict = new ImageIcon(iconPath + "conflict.gif");

	static JLabel jLabelScan;

	// Top-N
	static ImageIcon imageAlarm = new ImageIcon(iconPath + "alarm.gif");
	static ImageIcon imageClock = new ImageIcon(iconPath + "clock.gif");
	static ImageIcon imageStatus = new ImageIcon(iconPath + "list_status.png");
	static ImageIcon imageService = new ImageIcon(iconPath + "greenball.gif");
	static ImageIcon imageCircleGreen = new ImageIcon(iconPath + "circlegreen.png");
	static ImageIcon imageCircleYellow = new ImageIcon(iconPath + "circleyellow.png");
	static ImageIcon imageCircleRed = new ImageIcon(iconPath + "circlered.png");

	// 指示、信息条和状态条：
	static JLabel jLabelNote = new JLabel();
	public static JLabel statusBar = new JLabel();

	JSplitPane jPanelTable = new JSplitPane();
	JPanel jPanelInfo = new JPanel();
	JPanel jPanelStatus = new JPanel();
	static JPanel jPanelProgress = new JPanel();


	String startStr = "程序启动中，请稍候......   ";

	public MainFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			Logo.progress.setValue(40);
			Logo.progress.setString(startStr + "40%");
			jbInit();
			mainFrame = this;
			// check();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "MainFrame,初始化错误:" + e);
			System.exit(1);
		}
	}

	private void jbInit() throws Exception {
		backupService = (BackupService) AppContextUtil.getInstance().getBean(
				"backupService");
		
		// 设置表及文件：
		setTableFileNames();
		
		if(new File(InstallPath + "conf.ini").exists()) {
			IniReader ini = new IniReader(InstallPath + "conf.ini");
			if(ini.getValue("CONF", "rjw_trans") != null
					&& ini.getValue("CONF", "rjw_trans").equals("true")) {
				rjw_trans = true;
			}
			if(ini.getValue("CONF", "demo") != null
					&& ini.getValue("CONF", "demo").equals("true")) {
				demoMode = true;
			}
			if(ini.getValue("CONF", "cugb") != null
					&& ini.getValue("CONF", "cugb").equals("true")) {
				cugb = true;
			}
		}

		Logo.progress.setValue(15);
		Logo.progress.setString(startStr + "15%");

		// 设置程序主窗体右上角的LOGO。
		setIconImage(imageLogo.getImage());

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		this.setTitle(mainTitle);
		// 信息条：
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		statusBar.setPreferredSize(new Dimension(480, 20));

		// Menu
		// 档案菜单
		jMenuSystem.setMnemonic('F');
		jMenuSystem.setText("系统(F)");

		jMenuSync.setText("同步(N)");
		jMenuSync.setMnemonic('N');
		jMenuSync.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSync_actionPerformed(e);
			}
		});

		Logo.progress.setValue(20);
		Logo.progress.setString(startStr + "20%");

		jMenuBackup.setText("备份(B)");
		jMenuBackup.setMnemonic('B');
		jMenuBackup.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuBackup.setIcon(imageBackup);
		jMenuBackup.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuBackup_actionPerformed(e);
			}
		});

		jMenuRestore.setText("恢复(R)");
		jMenuRestore.setMnemonic('R');
		jMenuRestore.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuRestore.setIcon(imageRestore);
		jMenuRestore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuRestore_actionPerformed(e);
			}
		});
		
		jMenuMini.setText("最小化到托盘(M)");
		jMenuMini.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuMini.setMnemonic('M');
		jMenuMini.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuMini_actionPerformed(e);
			}
		});


		jMenuExit.setText("退出(X)");
		jMenuExit.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuExit.setMnemonic('X');
		jMenuExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuExit_actionPerformed(e);
			}
		});

		// 扫描菜单：
		jMenuScan.setText("扫描(C)");
		jMenuScan.setMnemonic('C');

		jMenuStart.setText("开始(S)");
		jMenuStart.setMnemonic('S');
		jMenuStart.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuStart.setIcon(imageStart);
		jMenuStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuStart_actionPerformed(e);
			}
		});

		jMenuStop.setText("停止(T)");
		jMenuStop.setMnemonic('T');
		jMenuStop.setEnabled(false);
		jMenuStop.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuStop.setIcon(imageStop);
		jMenuStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuStop_actionPerformed(e);
			}
		});

		jMenuSnapShot.setText("快照(K)");
		jMenuSnapShot.setMnemonic('K');
		jMenuSnapShot.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSnapShot.setIcon(imageSnapShot);
		jMenuSnapShot.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSnapShot_actionPerformed(e);
			}
		});

		jMenuScanParams.setText("扫描设置(Z)");
		jMenuScanParams.setMnemonic('Z');
		jMenuScanParams.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuScanParams.setIcon(imageParams);
		jMenuScanParams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuScanParams_actionPerformed(e);
			}
		});

		jMenuParams.setText("设置(Z)");
		jMenuParams.setMnemonic('Z');
		jMenuParams.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuParams.setIcon(imageParams);
		jMenuParams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuParams_actionPerformed(e);
			}
		});
		
		jMenuAlertPolicy.setText("报警设置(A)");
		jMenuAlertPolicy.setMnemonic('A');
		jMenuAlertPolicy.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuAlertPolicy.setIcon(imageAlertParams);
		jMenuAlertPolicy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuAlertPolicy_actionPerformed(e);
			}
		});

		// 日志菜单：
		jMenuLog.setText("日志(L)");
		jMenuLog.setMnemonic('L');

		jMenuUserLog.setText("接入(G)");
		jMenuUserLog.setMnemonic('G');
		jMenuUserLog.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuUserLog.setIcon(imageLogComputer);
		jMenuUserLog.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		jMenuArpLog.setText("ARP");
		jMenuArpLog.setMnemonic('R');
		jMenuArpLog.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuArpLog.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		jMenuDeviceExport.setText("设备表");
		jMenuDeviceExport.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuDeviceExport
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// jMenuDeviceExport_actionPerformed(e);
						System.out
								.println("jMenuDeviceExport_actionPerformed(e);");
					}
				});

		jMenuOperLog.setText("操作(I)");
		jMenuOperLog.setMnemonic('I');
		jMenuOperLog.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuOperLog.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		// 用户权限管理菜单：
		jMenuUserManage.setText("权限管理(M)");
		jMenuUserManage.setMnemonic('M');

		jMenuUserAdd.setText("用户管理(A)");
		jMenuUserAdd.setMnemonic('A');
		jMenuUserAdd.setHorizontalAlignment(SwingConstants.LEFT);
		jMenuUserAdd.setIcon(imageUsergroup);
		jMenuUserAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuUserAdd_actionPerformed(e);
			}
		});

		// 登录菜单：
		jMenuLogin.setText("登录(U)");
		jMenuLogin.setMnemonic('U');

		jMenuUserLogin.setText("用户切换(D)");
		jMenuUserLogin.setMnemonic('D');
		jMenuUserLogin.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuUserLogin.setIcon(imageLogin);
		jMenuUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuUserLogin_actionPerformed(e);
			}
		});

		// 关于菜单：
		jMenuHelp.setText("帮助(A)");
		jMenuHelp.setMnemonic('A');

		jMenuHelpAbout.setText("关于");
		jMenuHelpAbout.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuHelpAbout.setIcon(imageAbout);
		jMenuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuHelpAbout_actionPerformed(e);
			}
		});

		jMenuManual.setText("使用手册");
		jMenuManual.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuManual.setIcon(imageManual);
		jMenuManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuManual_actionPerformed(e);
			}
		});

		jMenuFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuFont_actionPerformed(e);
			}
		});

		jButtonBackup.setIcon(imageBackup);
		jButtonBackup.setMaximumSize(new Dimension(32, 24));
		jButtonBackup.setToolTipText("配置备份");
		jButtonBackup.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBackup_actionPerformed(e);
			}
		});

		jButtonRestore.setIcon(imageRestore);
		jButtonRestore.setMaximumSize(new Dimension(32, 24));
		jButtonRestore.setToolTipText("配置恢复");
		jButtonRestore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRestore_actionPerformed(e);
			}
		});

		jButtonSnapShot.setIcon(imageSnapShot);
		jButtonSnapShot.setMaximumSize(new Dimension(32, 24));
		jButtonSnapShot.setToolTipText("接入快照");
		jButtonSnapShot.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSnapShot_actionPerformed(e);
			}
		});

		// jButtonStart.setIcon(imageStart);
		jButtonStart.setIcon(imageStart);
		jButtonStart.setToolTipText("开始扫描");
		jButtonStart.setMaximumSize(new Dimension(32, 24));
		jButtonStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonStart_actionPerformed(e);
			}
		});

		jButtonStop.setIcon(imageStop);
		jButtonStop.setEnabled(false);
		jButtonStop.setMaximumSize(new Dimension(32, 24));
		jButtonStop.setToolTipText("停止扫描");
		jButtonStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonStop_actionPerformed(e);
			}
		});

		jButtonParams.setIcon(imageParams);
		jButtonParams.setMaximumSize(new Dimension(32, 24));
		jButtonParams.setToolTipText("系统设置");
		jButtonParams.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonParams_actionPerformed(e);
			}
		});
		
		jButtonAlertParams.setIcon(imageAlertParams);
		jButtonAlertParams.setMaximumSize(new Dimension(32, 24));
		jButtonAlertParams.setToolTipText("报警设置");
		jButtonAlertParams.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAlertParams_actionPerformed(e);
			}
		});

		jButtonLogQuery.setIcon(imageLogComputer);
		jButtonLogQuery.setMaximumSize(new Dimension(32, 24));
		jButtonLogQuery.setToolTipText("接入日志");
		jButtonLogQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		jButtonLogin.setIcon(imageLogin);
		jButtonLogin.setMaximumSize(new Dimension(32, 24));
		jButtonLogin.setToolTipText("用户登录");
		jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogin_actionPerformed(e);
			}
		});

		jButtonManual.setIcon(imageManual);
		jButtonManual.setMaximumSize(new Dimension(32, 24));
		jButtonManual.setToolTipText("使用手册");
		jButtonManual.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuManual_actionPerformed(e);
			}
		});
		
		trayOpen.setLabel("打开主控界面");
		trayOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trayOpen_actionPerformed(e);
			}
		});
		
		trayExit.setLabel("退出程序");
		trayExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trayExit_actionPerformed(e);
			}
		});

		Logo.progress.setValue(30);
		Logo.progress.setString(startStr + "30%");

		jPanelInfo.setPreferredSize(new Dimension(570, 20));
		jPanelInfo.setLayout(new BorderLayout());

		// 设置表及文件：
		// setTableFileNames();

		argumentDataFile.setFileName(argumentFileName);
		argumentDataFile.setLoadAsInserted(true);
		argumentDataSet.setDataFile(argumentDataFile);
		argumentDataSet.open();
		// 设置场景
		setArguments();

		jLabelNote.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabelNote.setPreferredSize(new Dimension(158, 20));

		jLabelScan = new JLabel(imageScan);
		jLabelScan.setVisible(false);
		jLabelScan.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabelScan.setPreferredSize(new Dimension(200, 20));

		jPanelButtonBar.setPreferredSize(new Dimension(200, 29));

//		jMenuSystem.add(jMenuParams);
//		jMenuSystem.add(jMenuAlertPolicy);
//		jMenuSystem.addSeparator();
		jMenuSystem.add(jMenuBackup);
		jMenuSystem.add(jMenuRestore);
//		jMenuSystem.addSeparator();
//		jMenuSystem.add(jMenuSync);
		jMenuSystem.addSeparator();
		jMenuSystem.add(jMenuMini);
		jMenuSystem.add(jMenuExit);

		jMenuScan.add(jMenuStart);
		jMenuScan.add(jMenuStop);
		jMenuScan.addSeparator();
		jMenuScan.add(jMenuSnapShot);
		jMenuScan.addSeparator();
		jMenuScan.add(jMenuScanParams);

		Logo.progress.setValue(40);
		Logo.progress.setString(startStr + "40%");

		jMenuLog.add(jMenuUserLog);
		jMenuLog.add(jMenuArpLog);
		jMenuLog.add(jMenuOperLog);


		jMenuDataExport.add(jMenuDeviceExport);
		jMenuDataExport.add(jMenuComputerExport);

		//jMenuUserManage.add(jMenuUserAdd);
		//jMenuUserManage.addSeparator();
		//jMenuUserManage.add(jMenuSubnetConf);

		jMenuLogin.add(jMenuUserLogin);
		jMenuLogin.add(jMenuUserAdd);

		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelp.addSeparator();
		jMenuHelp.add(jMenuManual);

		jMenuBar.add(jMenuSystem);
//		jMenuBar.add(jMenuScan);
//		jMenuBar.add(jMenuLog);
//		jMenuBar.add(jMenuLogin);
//		jMenuBar.add(jMenuHelp);
		if(!alertModule){
			jMenuAlertPolicy.setVisible(false);
			jButtonAlertParams.setVisible(false);
		}
		
		popupMenu.add(trayOpen);
		popupMenu.add(trayExit);
		
		// 换界面菜单
		// jMenuBar.add(jMenuSubstance);
		this.setJMenuBar(jMenuBar);

		contentPane.add(jPanelButtonBar, BorderLayout.NORTH);
		contentPane.add(jPanelTable, BorderLayout.CENTER);
		contentPane.add(jPanelInfo, BorderLayout.SOUTH);
		jPanelButtonBar.add(jButtonBackup);
		jPanelButtonBar.add(jButtonRestore);
		jPanelButtonBar.add(jButtonParams);
//		jPanelButtonBar.add(jButtonAlertParams);
//		jPanelButtonBar.addSeparator();
//
//		jPanelButtonBar.add(jButtonStart);
//		jPanelButtonBar.add(jButtonStop);
//		jPanelButtonBar.add(jButtonSnapShot);
//		jPanelButtonBar.addSeparator();
//
//		jPanelButtonBar.add(jButtonLogQuery);
//		jPanelButtonBar.addSeparator();
//
//		jPanelButtonBar.add(jButtonLogin);
//		jPanelButtonBar.addSeparator();
//
//		jPanelButtonBar.add(jButtonManual);
//		jPanelButtonBar.addSeparator();

		Logo.progress.setValue(50);
		Logo.progress.setString(startStr + "50%");

		jPanelInfo.add(jPanelStatus, BorderLayout.CENTER);
		jPanelStatus.setLayout(new BorderLayout());
		jPanelStatus.add(statusBar, BorderLayout.CENTER);
		jPanelStatus.add(jPanelProgress, BorderLayout.EAST);
		jPanelProgress.setLayout(new BorderLayout());
		jPanelInfo.add(jLabelScan, BorderLayout.EAST);
		jPanelInfo.add(jLabelNote, BorderLayout.WEST);

		querySelect = new QuerySelect();

		jPanelTable.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jPanelTable.setDividerLocation(450);
		jPanelTable.setDividerSize(5);
		jPanelTable.setDividerSize(10);
		jPanelTable.setContinuousLayout(true);
		jPanelTable.setOneTouchExpandable(true);
		jPanelTable.add(querySelect, JSplitPane.TOP);
		
		// 启动定时器，每分钟触发一次，如果检查定时则到时自动启动。
		timer = new javax.swing.Timer(60000,
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jTimer_actionPerformed(e);
					}
				});
		// timer.start();

		Logo.progress.setValue(60);
		Logo.progress.setString(startStr + "60%");

		Logo.progress.setValue(90);
		Logo.progress.setString(startStr + "90%");

		trayIcon = new TrayIcon(imageLogo.getImage(), "基线网络监控管理系统", popupMenu);

		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					return;
				}
				
				if (e.getClickCount() == 2) {// 双击托盘窗口再现
					open();
				}
			}
		});
	}

	private static void setTableFileNames() {
		// 根据JAR包获得文件路径
		File f = new File("netmon19.jar");
		String tmp = f.getAbsolutePath();
		String path = tmp.substring(0, tmp.lastIndexOf("\\") + 1);

		tomcatPath = path + "\\tomcat";
		htmlPath = tomcatPath + "\\webapps\\ROOT\\";

		paramsFileName = path + "扫描设置表.txt";
		c1FileName = path + "单列表.txt";
		logFileName = path + "日志表.txt";
		interfaceFileName = path + "接口表.txt";
		routerFileName = path + "路由表.txt";
		macPortFileName = path + "MAC端口表.txt";
		argumentFileName = path + "系统参数表.txt";
		snmpFileName = path + "SNMP对象表.txt";
		arpFileName = path + "IP转发表.txt";
		sysFileName = path + "系统信息表.txt";
		devMacFileName = path + "设备MAC表.txt";
		operLogFileName = path + "操作日志表.txt";
		vendorFileName = path + "厂商表.txt";
		neibourFileName = "邻居表.txt";
		userFileName = path + "用户表.txt";
		userSubnetFileName = path + "用户分区配置表.txt";
		// bug 2009-04-25 未验证 D:\netmon19\\log\\conf_bak
		confBakDir = path + "log\\conf_bak";
		keyDevIf = path + "关键设备-接口.txt";
		InstallPath = path;
	}

	// 设置系统参数：
	private void setArguments() {
		pollGap = argumentDataSet.getInt("PollGap");
		cpuLimen = argumentDataSet.getInt("CpuLimen");
		bandLimen = argumentDataSet.getInt("BandLimen");
		unicastLimen = argumentDataSet.getInt("UnicastLimen");
		broadcastLimen = argumentDataSet.getInt("BroadcastLimen");
		tempLimen = argumentDataSet.getInt("TempLimen");
		backColor = argumentDataSet.getString("BackColor");
		antiAliased = argumentDataSet.getString("AntiAliased");
		exportFile = argumentDataSet.getString("ExportFile");
		deviceBgPath = argumentDataSet.getString("DeviceBgPath");
		hostBgPath = argumentDataSet.getString("HostBgPath");
		userBgPath = argumentDataSet.getString("UserBgPath");
		snmpRetry = argumentDataSet.getInt("SnmpRetry");

		scanHour[0] = argumentDataSet.getInt("ScanHour0");
		scanHour[1] = argumentDataSet.getInt("ScanHour1");
		scanHour[2] = argumentDataSet.getInt("ScanHour2");
		// pollHour = argumentDataSet.getInt("PollHour");
		delGap = argumentDataSet.getString("DelGap");

		adminPwd = argumentDataSet.getString("AdminPwd");
		userPwd = argumentDataSet.getString("UserPwd");
		syncServer = argumentDataSet.getString("SyncServer");

		toolName1 = argumentDataSet.getString("ToolName1");
		toolIp1 = argumentDataSet.getBoolean("ToolIp1");
		toolPath1 = argumentDataSet.getString("ToolPath1");
		toolName2 = argumentDataSet.getString("ToolName2");
		toolIp2 = argumentDataSet.getBoolean("ToolIp2");
		toolPath2 = argumentDataSet.getString("ToolPath2");

		if (new File("config.xml").exists()) {
			String historyValues[] = XmlTool.getNodeValues("config.xml",
					"/config/paramsConfig/historyConfig/", new String[] {
							"dataBase", "fluxType", "minuteDataKeep",
							"hourDataKeep", "backupType"});
			if (historyValues[0].equals("否"))
				dataBase = false;
			else
				dataBase = true;
			// 采集全部端口还是互连端口
			if (historyValues[1].equals("全部端口"))
				fluxType = 1;
			else
				fluxType = 2;
			if (historyValues[4].equals("是"))
				// bug 2009-01-05
				backupType = 1;
			else
				backupType = 1;

			String tmpSyncHour = XmlTool.getNodeValue("config.xml",
					"/config/paramsConfig/", "syncHour");
			if (tmpSyncHour.equals("不定时同步"))
				syncHour = -1;
			else
				syncHour = Integer.parseInt((String) tmpSyncHour);

			String hostValues[] = XmlTool.getNodeValues("config.xml",
					"/config/paramsConfig/hostConfig/", new String[] {
							"tcpConn", "procNum", "memRate", "vmemRate",
							"diskRate" });
			if (hostValues != null) {
				try {
					tcpConn = Integer.parseInt(hostValues[0]);
					procNum = Integer.parseInt(hostValues[1]);
					memRate = Double.parseDouble(hostValues[2]);
					vmemRate = Double.parseDouble(hostValues[3]);
					diskRate = Double.parseDouble(hostValues[4]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 数据同步菜单
	void jMenuSync_actionPerformed(ActionEvent e) {
		if (syncServer.equals("")) {
			JOptionPane.showMessageDialog(this, "没有设置数据同步网管站，请在系统参数中设置。");
			return;
		}
		String select = (String) JOptionPane.showInputDialog(this,
				"选择同步内容。日志为近7日数据。\n" + "同步使用对方数据覆盖本机数据。", "数据同步",
				// , "档案（追加）"
				JOptionPane.YES_NO_CANCEL_OPTION, null, new String[] { "档案",
						"日志", "档案+日志" }, "档案");
		if (select == null)
			return;
	}

	// 档案备份菜单
	void jMenuBackup_actionPerformed(ActionEvent e) {
		backup();
	}

	void jButtonBackup_actionPerformed(ActionEvent e) {
		backup();
	}

	private void backup() {
		// 取得备份路径：
		JFileChooser chooser = new JFileChooser();
		fcSetup(chooser);
		chooser.setDialogTitle("选择配置备份的目标文件夹");
		chooser.setFileHidingEnabled(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("确认");
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		final String dir = chooser.getSelectedFile().toString();
		// 备份sql数据库
		try {
			backupService.export(dir);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "配置备份完毕。请勿修改配置备份，否则无法恢复。");
	}

	// 档案恢复菜单：
	void jMenuRestore_actionPerformed(ActionEvent e) {
		restore();
	}

	void jButtonRestore_actionPerformed(ActionEvent e) {
		restore();
	}

	private void restore() {
		int confirm = JOptionPane.showConfirmDialog(this, "恢复配置将覆盖现有的配置，是否继续？",
				"配置恢复", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		// 取得备份路径：
		JFileChooser chooser = new JFileChooser();
		fcSetup(chooser);
		chooser.setDialogTitle("选择配置恢复的源文件夹");
		chooser.setFileHidingEnabled(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("确认");
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		final String dir = chooser.getSelectedFile().toString();
		// 恢复mysql数据库
		try {
			if (MainFrame.backupType == 1) {
				backupService.importMysql(dir + "\\crystal.sql", "",
						MainFrame.backupType);
			} else if (MainFrame.backupType == 2) {
				backupService.importMysql(dir + "\\crystal.sql", dir
						+ "\\history.sql", MainFrame.backupType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "配置恢复完毕，系统将自动退出。重新启动系统后配置生效。");
		System.exit(0);
	}

	private void fcSetup(JComponent fc) {
		String tmp;
		for (int i = 0; i < fc.getComponentCount(); i++) {
			if (JLabel.class.isInstance(fc.getComponents()[i])) {
				tmp = ((JLabel) fc.getComponents()[i]).getText();
				if (tmp.indexOf("文件名") != -1)
					fc.getComponents()[i].setEnabled(false);
				if (tmp.indexOf("文件类型") != -1)
					fc.getComponents()[i].setEnabled(false);
			}
			if (JTextField.class.isInstance(fc.getComponents()[i]))
				fc.getComponents()[i].setEnabled(false);
			if (JComponent.class.isInstance(fc.getComponents()[i]))
				fcSetup((JComponent) fc.getComponents()[i]);
		}
	}

	void jMenuStart_actionPerformed(ActionEvent e) {
	}

	void jButtonStart_actionPerformed(ActionEvent e) {
	}

	void jMenuStop_actionPerformed(ActionEvent e) {
	}

	void jButtonStop_actionPerformed(ActionEvent e) {
	}

	private void startControlSet() {
		jMenuStart.setEnabled(false);
		jButtonStart.setEnabled(false);
		jMenuStop.setEnabled(true);
		jButtonStop.setEnabled(true);
		jMenuUserLogin.setEnabled(false);
		jButtonLogin.setEnabled(false);
		jLabelScan.setVisible(true);// 显示扫描中图标
		if (!isGuest())
			configEnable(false);
	}

	static void configEnable(boolean b) {
		jMenuParams.setEnabled(b);
		jMenuAlertPolicy.setEnabled(b);
		jButtonAlertParams.setEnabled(b);
		if(!alertModule) {
			jMenuAlertPolicy.setEnabled(false);
			jButtonAlertParams.setEnabled(false);
		}
		if(!isAdmin()) {
			jMenuAlertPolicy.setEnabled(false);
			jButtonAlertParams.setEnabled(false);
		}
		jButtonParams.setEnabled(b);
		jMenuScanParams.setEnabled(b);
		jMenuBackup.setEnabled(b);
		jButtonBackup.setEnabled(b);
		jMenuRestore.setEnabled(b);
		jButtonRestore.setEnabled(b);
	}

	public static void stopControlSet() {
		jMenuStart.setEnabled(true);
		jButtonStart.setEnabled(true);
		jMenuStop.setEnabled(false);
		jButtonStop.setEnabled(false);
		jMenuUserLogin.setEnabled(true);
		jButtonLogin.setEnabled(true);
		// 如果没有正在进行数据合并，则可以去掉jLabelScan
		if (!isGuest())
			configEnable(true);
	}

	void jMenuSnapShot_actionPerformed(ActionEvent e) {
		snapShot();
	}

	void jButtonSnapShot_actionPerformed(ActionEvent e) {
		snapShot();
	}

	// 当前快照：
	private void snapShot() {
	}

	// 全局参数设置(默认子网设置)：
	// 默认打开第几个选项卡，如打开子网设置0（默认），扫描设置1，
	// 拓扑设置2，报警设置3，定时设置4，其它设置5时使用
	void jMenuParams_actionPerformed(ActionEvent e) {
		params(0);
	}
	
	void jMenuAlertPolicy_actionPerformed(ActionEvent e) {
	}

	void jButtonParams_actionPerformed(ActionEvent e) {
		params(0);
	}
	
	void jButtonAlertParams_actionPerformed(ActionEvent e) {
	}

	// 扫描设置
	void jMenuScanParams_actionPerformed(ActionEvent e) {
		params(1);
	}

	private void params(int num) {
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			/*int confirm = JOptionPane.showConfirmDialog(this, "退出" + Constants.softName + "？",
					"退出", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				exit();
			}
			*/
			exit();
		}
	}
	
	void jMenuMini_actionPerformed(ActionEvent e) {
		minimize();
	}

	void jMenuExit_actionPerformed(ActionEvent e) {
		exit();
	}
	
	private void minimize() {
		try {
			systemTray = SystemTray.getSystemTray();// 获得系统托盘的实例
			systemTray.add(trayIcon);
			setExtendedState(JFrame.ICONIFIED);
			dispose();// 窗口最小化时dispose该窗口
		} catch (AWTException e2) {
			e2.printStackTrace();
		}
	}
	
	private void open() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainFrame.repaint();
			}
		});
		setExtendedState(JFrame.NORMAL);
		systemTray.remove(trayIcon);
		setVisible(true);
	}

	void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		about();
	}

	void jMenuManual_actionPerformed(ActionEvent e) {
		try {
			Runtime.getRuntime().exec(
					MainFrame.InstallPath
							+ "\\tool\\FoxitReader.exe manual\\用户使用手册.pdf");
		} catch (IOException ioe) {
		}
	}
	
	void trayOpen_actionPerformed(ActionEvent e) {
		open();
	}
	
	void trayExit_actionPerformed(ActionEvent e) {
		/*
		int confirm = JOptionPane.showConfirmDialog(this, "退出" + Constants.softName + "？",
				"退出", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			exit();
		}
		*/
		exit();
	}
	
	void jMenuFont_actionPerformed(ActionEvent e) {
		final FontChooser fontChooser = new FontChooser();
		fontChooser.setFrame(this);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					if (fontChooser.showDialog()) {
						Enumeration keys = UIManager.getLookAndFeelDefaults()
								.keys();
						Font newFont = null;
						while (keys.hasMoreElements()) {
							Object key = keys.nextElement();
							Object value = UIManager.get(key);
							if (value instanceof Font) {
								Font oldFont = (Font) value;
								// logger.info(oldFont.getName());
								newFont = new Font(fontChooser
										.getSelectedFont().getFontName(),
										oldFont.getStyle(), fontChooser
												.getSelectedFont().getSize());
								UIManager.put(key, newFont);
							}
						}
						for (Frame frame : Frame.getFrames()) {
							// frame.setFont(newFont);
							SwingUtilities.updateComponentTreeUI(frame);
							for (Window window : frame.getOwnedWindows()) {
								// window.setFont(newFont);
								SwingUtilities.updateComponentTreeUI(window);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void about() {
		AboutBox dlg = new AboutBox(this);
		Tools.dispWin(dlg);
	}
	
	private void exit() {
		// 为了安全起见，保证线程和数据库顺利关闭，确保下一次成功重入，采用以下方式。
		// 停止Timer.
		// timer.stop();
		
		int confirm = JOptionPane.showConfirmDialog(this, "是否备份数据库并将数据库发送到" + Constants.mailAddr1+ "？",
				"退出备份数据库", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			// 自动备份数据库
			GregorianCalendar now = new GregorianCalendar();
			String dir = InstallPath + "database-backup\\" + now.get(Calendar.YEAR)
					+ "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DAY_OF_MONTH) + "\\";
			File f = new File(dir);
			if(!f.exists())
				f.mkdirs();
			try {
				backupService.export(dir);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("配置备份完毕。请勿修改配置备份，否则无法恢复。\n" + "配置位于：" + dir);
			
			// 先隐藏主窗口，防止发送邮件时长时间等待
			this.setVisible(false);
			
			// 将数据库备份发送到指定邮箱
			DatabaseMail mail = new DatabaseMail();
			mail.setBackupPath(dir);
			mail.run();
		}
		querySelect.myexit();
		dispose();
		//仅当至少又一次minimize的时候才会初始化systemTray
		if(systemTray != null)
			systemTray.remove(trayIcon);
		System.exit(0);
	}// exit
	
	// 定时事件：按1分钟定时，但事件发生时不一定是整分钟。
	void jTimer_actionPerformed(ActionEvent e) {
		return;
	}

	// 添加自定义用户：
	void jMenuUserAdd_actionPerformed(ActionEvent e) {
//		userEditor editor = new userEditor(MainFrame.mainFrame);
//		editor.setLocationRelativeTo(editor.getOwner());
//		editor.setVisible(true);
	}

	void jMenuUserLogin_actionPerformed(ActionEvent e) {
		querySelect.removeTreeNodes();
		userLogin();
	}

	void jButtonLogin_actionPerformed(ActionEvent e) {
		querySelect.removeTreeNodes();
		userLogin();
	}

	void userLogin() {
		userType = this.USER_ADMIN;

		if (isAdmin()) {
			configEnable(true);
			jMenuUserAdd.setEnabled(true);
			jMenuSubnetConf.setEnabled(true);
		} else if (isGuest()) {
			configEnable(false);
			jMenuUserAdd.setEnabled(false);
			jMenuSubnetConf.setEnabled(false);
		} else {
			configEnable(true);
			jMenuUserAdd.setEnabled(true);
			jMenuSubnetConf.setEnabled(false);
		}
		// 窗口title加入用户权限信息
		if (userType != null && userType.length() > 0) {
			String temp = mainTitle + "  [";
			if (isAdmin())
				temp = temp + "系统管理员]";
			else if (isGuest())
				temp = temp + "普通用户]";
			else
				temp = temp + userType + "]";
			this.setTitle(temp);
		}
		// Login之后，根据userType刷新子网树
		querySelect.createTreeNodes();
		querySelect.jTree.updateUI();
	}
	
	public static boolean isAdmin() {
		return userType.equalsIgnoreCase(USER_ADMIN);
	}
	
	public static boolean isGuest() {
		return userType.equalsIgnoreCase(USER_GUEST);
	}
}
