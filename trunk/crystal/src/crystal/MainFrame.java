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

	public static JFrame mainFrame;// �����ھ��
    private TrayIcon trayIcon = null;
    private SystemTray systemTray;//ϵͳ����
	
	public final static String USER_ADMIN = "admin";
	public final static String USER_GUEST = "guest";

	// �Ƿ���Ҫд�뱨���м��
	public static boolean rjw_trans = false;
	public static boolean cugb = false;
	
	public static boolean demoMode = false;// �Ƿ���ʾģʽ��
	static boolean evalMode;// �Ƿ�����ģʽ
	static boolean deviceModule = true; //�Ƿ����ģ��
	static boolean hostModule = true; // �Ƿ������ģ��
	static boolean reportModule = true; //�Ƿ񱨱�ģ��
	static boolean webModule = true;// �Ƿ�����webģ��
	static boolean switchModule = true; //�Ƿ񽻻�������ģ��
	static boolean snifferModule = true; //�Ƿ���������ģ��
	static boolean alertModule = true;	//�Ƿ񱨾�����ģ��

	static boolean dataBase = true;// �Ƿ��������ݿ�ģ��
	static int licnum;// �û������
	static String mainTitle = "����̨";

	AppContextUtil bp = (AppContextUtil) AppContextUtil.getInstance();

	// ���ݻָ�������ģʽ��1 ��������ʷ���ݡ�2 ������ʷ����
	static int backupType = 1;

	// static String isRestart = "����";//�Ƿ�������ģʽ�����������ʾ��¼����
	// ���ÿ�ʼ���ڡ�ע�⣺�·��밴ʵ���·ݼ�1��
	static GregorianCalendar evalDate = new GregorianCalendar();
	static String InstallPath;// ϵͳ��װĿ¼
	static int copyStatus;// 0:�޸��ƣ�1�����ƶ˿ڣ�2��������
	// ���ӱ�˿ڸ���(����λ����IP���˿ڡ��ӿڡ��ӿ��������ӿڱ�ע
	static String[] ifCopy = new String[] { "", "", "", "", "", "", "", "", "",
			"" };
	// ϵͳ������
	// ��ʱɨ��ʱ�䣺������������ʱ�䣬24Сʱ��ʾ��ȱʡ-1Ϊ����ʱ
	static int[] scanHour = new int[] { -1, -1, -1 };
	// ��ʱ����ʱ�䣺24Сʱ��ʾ��ȱʡ-1Ϊ������
	// static int pollHour = -1;
	// NETBIOSɨ���̼߳����SNMPɨ���߳���ɨ��ģ���޹أ����ù̶����50��
	static int threadGap = 50;
	// �廪У԰��Զ�̲��Ա�����2�����·����������԰������ģ��2��Ӧ�ÿ��ԣ�3��Ӧ�ϱ��ա�
	// ȱʡsnmpTimeOut = 5��snmpRetry = 0����������Ϊ1��1����1��2�ϼ�3�롣���Կ�ѡ2����3/7�롣
	static int snmpTimeOut = 1;
	static int snmpRetry = 1;
	// 10��15��20ms���Ա�����15ms�����룬������·����ѧ��IP����MAC��30ms�����ա��ڴ�
	// ˮƽ�ϣ�һ��·�����¿ɹ�20��C��������ʹPING��Ч������ARP����ʱ��
	static int pingGap = 30;
	static int pollGap = 30;// ���ˡ��豸������״̬��ѯ���
	public static int cpuLimen = 10;// CPU������ֵ���ٷֱ�
	public static int bandLimen = 55;// �ӿڴ���ʾ��ֵ���ٷֱ�
	static int unicastLimen = 1000;// �ӿڵ�������ֵ����/�룩
	static int broadcastLimen = 100;// �ӿڹ㲥����ֵ����/�룩
	public static int tempLimen = 25;// AKCP�¶���ֵ���ȣ�
	public static String backColor = "��";// ����ͼ����̬����ɫ
	static String exportFile = "��";// �����̬���ļ�
	static String deviceBgPath = "images/deviceBgIcon.jpg"; // �豸���˱���ͼ
	static String hostBgPath = "images/hostBgIcon.jpg"; // ���������˱���ͼ
	static String userBgPath = "images/userBgIcon.jpg"; // �û����˱���ͼ

	//demo ģʽ�£�Ĭ�ϵ���ʷ��������
	public static Timestamp startTime = new Timestamp(110, 0, 4, 0, 0, 0, 0);
	public static Timestamp endTime = new Timestamp(110, 0, 10, 0, 0, 0, 0);
	
	// ��������ֵ
	static int tcpConn = 100;
	static int procNum = 50;
	static double memRate = 90;
	static double vmemRate = 90;
	static double diskRate = 90;

	public static int checkGap = 10;// �豸����������������ӣ�
	public static int errorGap = 1; // �쳣��ļ���������ӣ�
	static String delGap = "������";// �����������Զ�ɾ��ʱ��
	static int syncHour = -1; // �Զ�ͬ��ʱ�䣬-1����ͬ���� 1-24 ��Nʱ:15��ʱͬ��
	static String antiAliased = "��";
	// ������Community
	static String localCommStr = "public";
	// �û�����
	static String adminPwd = "";
	static String userPwd = "";
	public static String userType = "";
	public static ArrayList subnetList = new ArrayList();
	// SMS����
	//public static String smsNum = "";
	public static String smsSn = "";
	public static String smsAlert = "��"; // ���ű���
	public static String emailAlert = "��"; // �ʼ�����
	public static String soundAlert = "��";// ��������
	public static int arpLimen = 5; // Arp��ʾ��ֵ��һMAC�Զ�IP��Ŀ��Ĭ��Ϊ5
	// ����ͬ����������ַ
	static String syncServer = "";
	// �û��Զ���˵�
	static String toolName1 = "";
	static boolean toolIp1 = true;
	static String toolPath1 = "";
	static String toolName2 = "";
	static boolean toolIp2 = false;
	static String toolPath2 = "";
	static boolean dispComm = true;

	// ɨ�����ã�
	static boolean l3Scan = true;// �Ƿ�ɨ��ARP��
	static boolean l2Scan = true;// �Ƿ�ɨ��MACת����
	static boolean arpNbt;// SNMPģʽ�Ƿ����WINSɨ��
	static boolean pingScan;// ʹ��PINGԤɨ�軹�ǲ�ʹ��
	static int scanGapTime = 0;// ����ɨ���ļ����msΪ��λ��0��ʾɨ��һ�Ρ�
	static boolean ignoreError;// �Ƿ����ɨ���쳣��PING/����/��ʱ/ͬһMAC�����ڲ�ͬ�˿�
	public static int fluxType = 2;// 1�����ж˿� ��2�������˿�

	public static String mailAddr1 = "";
	//static String mailAddr2 = "";
	public static String smtpServer = "";
	public static String mailSender = "";
	public static String mailSenderPwd = "";

	// ����ͼ�õ���ʾ
	public static int topIndex = 0;		// ��������Listʱ��index
	public static int scrollTimeLong = 5;	// Timer��ʱ��(��)

	// ģ��������
	static boolean snapShotDisplay;// SnapShotDlg��ʾ���ջ�����־��SnapShotDlg��Mainframe
	static String logQryDate;// ��־��ѯ���ڡ�SnapShotDlg��LogQuerySetupDlg��
	static String tableDataFileName;// ��Ի�����ʾ�����ļ������֡�����QuerySelect,HostQuery��TableDlg

	javax.swing.Timer timer;// ��ʱ������ɶ�ʱ��顣ʹ��javax.swing������java.util�е�Timer��
	public static boolean queryThreadRun = false;// �����齨��ѯ�߳��Ƿ�������־
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// ���ݱ��ļ���
	// 2009-07-14 ���public ���ԣ�������package��Ҳ�ܷ��� deviceFileName, hostFileName,
	public static String alertFileName, argumentFileName, paramsFileName,
			c1FileName, logFileName, interfaceFileName, routerFileName,
			macPortFileName, snmpFileName, arpFileName, sysFileName,
			devMacFileName, neibourFileName, operLogFileName, vendorFileName,
			userFileName, userSubnetFileName, keyDevIf;

	// addCode 2009-03-18
	static String confBakDir;
	static double columnScale = 1.4;
	static double rowHeaderScale = 1.5;

	boolean isLoop = false; // true��ʾ�ڼ��ɨ�裬�����ʾ��ɨ��һ�Ρ�
	boolean isGapLoop = false;
	public static boolean scanLoopRunning = false; // ��ʾ����ɨ������У���ʶScanLoop�̵߳�״̬
	public int scanMin = 0; // ����ʱʱ�� scanGapTime ����
	public static String scanPrefix = "";

	// �ֳ����ļ�
	static TextDataFile argumentDataFile = new TextDataFile();
	static TableDataSet argumentDataSet = new TableDataSet();

	// html�ļ����·��
	static String tomcatPath;
	static String htmlPath;
	JPanel contentPane;
	QuerySelect querySelect;

	// �˵���
	JMenuBar jMenuBar = new JMenuBar();
	// �����˵���
	JMenu jMenuSystem = new JMenu();
	JMenuItem jMenuSync = new JMenuItem();
	static JMenuItem jMenuBackup = new JMenuItem();
	static JMenuItem jMenuRestore = new JMenuItem();
	JMenuItem jMenuMini = new JMenuItem(); //��С����ϵͳ����
	JMenuItem jMenuExit = new JMenuItem();
	static JMenuItem jMenuParams = new JMenuItem();
	static JMenuItem jMenuAlertPolicy = new JMenuItem();
	// ɨ��˵���
	JMenu jMenuScan = new JMenu();
	// ��QuerySelect��ͨ��jMenuStart�ж�ɨ���Ƿ��ڽ��У�������У���ֹQuerySelect�е��йر༭�ؼ���
	static JMenuItem jMenuStart = new JMenuItem();
	static JMenuItem jMenuStop = new JMenuItem();
	static JMenuItem jMenuSnapShot = new JMenuItem();
	static JMenuItem jMenuScanParams = new JMenuItem();
	// ��־�˵���
	JMenu jMenuLog = new JMenu();
	JMenuItem jMenuUserLog = new JMenuItem();
	JMenuItem jMenuArpLog = new JMenuItem();
	JMenuItem jMenuOperLog = new JMenuItem();
	// ���ݵ����˵���
	JMenu jMenuDataExport = new JMenu();
	JMenuItem jMenuDeviceExport = new JMenuItem();
	JMenuItem jMenuHostExport = new JMenuItem();
	JMenuItem jMenuComputerExport = new JMenuItem();
	JMenuItem jMenuArpExport = new JMenuItem();// ʵʱArp��
	JMenuItem jMenuMacExport = new JMenuItem();
	JMenuItem jMenuComputerLogExport = new JMenuItem();
	// �û�Ȩ�޹���˵�
	JMenu jMenuUserManage = new JMenu();
	static JMenuItem jMenuUserAdd = new JMenuItem();
	static JMenuItem jMenuSubnetConf = new JMenuItem();
	// ��¼�˵�
	JMenu jMenuLogin = new JMenu();
	static JMenuItem jMenuUserLogin = new JMenuItem();
	// ���ڲ˵���
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JMenuItem jMenuManual = new JMenuItem();

	// testCode 2009-04-09
	JMenuItem jMenuFont = new JMenuItem("��������");

	// �������Ͱ�ť��
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
	// Logo ͼƬ
	public static ImageIcon imageLogo = new ImageIcon(iconPath + "logo.gif");
	// startLogo ͼƬ
	// static ImageIcon imageStartLogo = new ImageIcon(iconPath + "start.gif");
	// UI ͼƬ
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
	static ImageIcon imageIfPoll = new ImageIcon(iconPath + "interfacepoll.png"); // �ӿ�����
	static ImageIcon imageTopDisc = new ImageIcon(iconPath + "topdisc.png"); // ���˷���
	static ImageIcon imageSearch = new ImageIcon(iconPath + "search.png");
	static ImageIcon imageHub = new ImageIcon(iconPath + "hub.png");
	static ImageIcon imageSwitchOperate = new ImageIcon(iconPath + "switch_operate.png");
	static ImageIcon imageFlowAnalysis = new ImageIcon(iconPath + "flow_Analysis.png");
	static ImageIcon imageHub_RedFrame = new ImageIcon(iconPath + "hub_redframe.png");
	static ImageIcon imageHub_BlueFrame = new ImageIcon(iconPath + "hub_blueframe.png");
	static ImageIcon imageHub_DoubleFrame = new ImageIcon(iconPath + "hub_doubleframe.png");
	static ImageIcon imageClockArrow = new ImageIcon(iconPath + "clock_arrow.png");// hostStatusͼ��

	// other QuerySelect
	static ImageIcon imageCpuLoad = new ImageIcon(iconPath + "cpuload.gif");
	static ImageIcon imageLinkport = new ImageIcon(iconPath + "linkport.gif");
	static ImageIcon imageChartLine = new ImageIcon(iconPath + "chart_line.png");
	// lsj 2009-08-28
	public static ImageIcon imageCatalog = new ImageIcon(iconPath + "catalog.gif");// ������������˵�������ͼ��
	public static ImageIcon imageKeyword = new ImageIcon(iconPath + "keyword.gif");// ���������ѯ�ؼ���
	static ImageIcon imageImport = new ImageIcon(iconPath + "import.gif");// ���������IP��������ֶ�
	static ImageIcon imageSort = new ImageIcon(iconPath + "sort.gif");// �����������
	static ImageIcon imageZoom = new ImageIcon(iconPath + "zoom.gif");
	static ImageIcon imageZoomIn = new ImageIcon(iconPath + "zoomin.gif");
	static ImageIcon imageZoomOut = new ImageIcon(iconPath + "zoomout.gif");
	static ImageIcon imageCrystal = new ImageIcon(iconPath + "crystal.png"); // ������Ϣ
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

	// ָʾ����Ϣ����״̬����
	static JLabel jLabelNote = new JLabel();
	public static JLabel statusBar = new JLabel();

	JSplitPane jPanelTable = new JSplitPane();
	JPanel jPanelInfo = new JPanel();
	JPanel jPanelStatus = new JPanel();
	static JPanel jPanelProgress = new JPanel();


	String startStr = "���������У����Ժ�......   ";

	public MainFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			Logo.progress.setValue(40);
			Logo.progress.setString(startStr + "40%");
			jbInit();
			mainFrame = this;
			// check();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "MainFrame,��ʼ������:" + e);
			System.exit(1);
		}
	}

	private void jbInit() throws Exception {
		backupService = (BackupService) AppContextUtil.getInstance().getBean(
				"backupService");
		
		// ���ñ��ļ���
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

		// ���ó������������Ͻǵ�LOGO��
		setIconImage(imageLogo.getImage());

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		this.setTitle(mainTitle);
		// ��Ϣ����
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		statusBar.setPreferredSize(new Dimension(480, 20));

		// Menu
		// �����˵�
		jMenuSystem.setMnemonic('F');
		jMenuSystem.setText("ϵͳ(F)");

		jMenuSync.setText("ͬ��(N)");
		jMenuSync.setMnemonic('N');
		jMenuSync.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSync_actionPerformed(e);
			}
		});

		Logo.progress.setValue(20);
		Logo.progress.setString(startStr + "20%");

		jMenuBackup.setText("����(B)");
		jMenuBackup.setMnemonic('B');
		jMenuBackup.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuBackup.setIcon(imageBackup);
		jMenuBackup.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuBackup_actionPerformed(e);
			}
		});

		jMenuRestore.setText("�ָ�(R)");
		jMenuRestore.setMnemonic('R');
		jMenuRestore.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuRestore.setIcon(imageRestore);
		jMenuRestore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuRestore_actionPerformed(e);
			}
		});
		
		jMenuMini.setText("��С��������(M)");
		jMenuMini.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuMini.setMnemonic('M');
		jMenuMini.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuMini_actionPerformed(e);
			}
		});


		jMenuExit.setText("�˳�(X)");
		jMenuExit.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuExit.setMnemonic('X');
		jMenuExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuExit_actionPerformed(e);
			}
		});

		// ɨ��˵���
		jMenuScan.setText("ɨ��(C)");
		jMenuScan.setMnemonic('C');

		jMenuStart.setText("��ʼ(S)");
		jMenuStart.setMnemonic('S');
		jMenuStart.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuStart.setIcon(imageStart);
		jMenuStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuStart_actionPerformed(e);
			}
		});

		jMenuStop.setText("ֹͣ(T)");
		jMenuStop.setMnemonic('T');
		jMenuStop.setEnabled(false);
		jMenuStop.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuStop.setIcon(imageStop);
		jMenuStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuStop_actionPerformed(e);
			}
		});

		jMenuSnapShot.setText("����(K)");
		jMenuSnapShot.setMnemonic('K');
		jMenuSnapShot.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuSnapShot.setIcon(imageSnapShot);
		jMenuSnapShot.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuSnapShot_actionPerformed(e);
			}
		});

		jMenuScanParams.setText("ɨ������(Z)");
		jMenuScanParams.setMnemonic('Z');
		jMenuScanParams.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuScanParams.setIcon(imageParams);
		jMenuScanParams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuScanParams_actionPerformed(e);
			}
		});

		jMenuParams.setText("����(Z)");
		jMenuParams.setMnemonic('Z');
		jMenuParams.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuParams.setIcon(imageParams);
		jMenuParams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuParams_actionPerformed(e);
			}
		});
		
		jMenuAlertPolicy.setText("��������(A)");
		jMenuAlertPolicy.setMnemonic('A');
		jMenuAlertPolicy.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuAlertPolicy.setIcon(imageAlertParams);
		jMenuAlertPolicy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuAlertPolicy_actionPerformed(e);
			}
		});

		// ��־�˵���
		jMenuLog.setText("��־(L)");
		jMenuLog.setMnemonic('L');

		jMenuUserLog.setText("����(G)");
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

		jMenuDeviceExport.setText("�豸��");
		jMenuDeviceExport.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuDeviceExport
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// jMenuDeviceExport_actionPerformed(e);
						System.out
								.println("jMenuDeviceExport_actionPerformed(e);");
					}
				});

		jMenuOperLog.setText("����(I)");
		jMenuOperLog.setMnemonic('I');
		jMenuOperLog.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuOperLog.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		// �û�Ȩ�޹���˵���
		jMenuUserManage.setText("Ȩ�޹���(M)");
		jMenuUserManage.setMnemonic('M');

		jMenuUserAdd.setText("�û�����(A)");
		jMenuUserAdd.setMnemonic('A');
		jMenuUserAdd.setHorizontalAlignment(SwingConstants.LEFT);
		jMenuUserAdd.setIcon(imageUsergroup);
		jMenuUserAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuUserAdd_actionPerformed(e);
			}
		});

		// ��¼�˵���
		jMenuLogin.setText("��¼(U)");
		jMenuLogin.setMnemonic('U');

		jMenuUserLogin.setText("�û��л�(D)");
		jMenuUserLogin.setMnemonic('D');
		jMenuUserLogin.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuUserLogin.setIcon(imageLogin);
		jMenuUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuUserLogin_actionPerformed(e);
			}
		});

		// ���ڲ˵���
		jMenuHelp.setText("����(A)");
		jMenuHelp.setMnemonic('A');

		jMenuHelpAbout.setText("����");
		jMenuHelpAbout.setHorizontalTextPosition(SwingConstants.LEFT);
		jMenuHelpAbout.setIcon(imageAbout);
		jMenuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuHelpAbout_actionPerformed(e);
			}
		});

		jMenuManual.setText("ʹ���ֲ�");
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
		jButtonBackup.setToolTipText("���ñ���");
		jButtonBackup.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBackup_actionPerformed(e);
			}
		});

		jButtonRestore.setIcon(imageRestore);
		jButtonRestore.setMaximumSize(new Dimension(32, 24));
		jButtonRestore.setToolTipText("���ûָ�");
		jButtonRestore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRestore_actionPerformed(e);
			}
		});

		jButtonSnapShot.setIcon(imageSnapShot);
		jButtonSnapShot.setMaximumSize(new Dimension(32, 24));
		jButtonSnapShot.setToolTipText("�������");
		jButtonSnapShot.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSnapShot_actionPerformed(e);
			}
		});

		// jButtonStart.setIcon(imageStart);
		jButtonStart.setIcon(imageStart);
		jButtonStart.setToolTipText("��ʼɨ��");
		jButtonStart.setMaximumSize(new Dimension(32, 24));
		jButtonStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonStart_actionPerformed(e);
			}
		});

		jButtonStop.setIcon(imageStop);
		jButtonStop.setEnabled(false);
		jButtonStop.setMaximumSize(new Dimension(32, 24));
		jButtonStop.setToolTipText("ֹͣɨ��");
		jButtonStop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonStop_actionPerformed(e);
			}
		});

		jButtonParams.setIcon(imageParams);
		jButtonParams.setMaximumSize(new Dimension(32, 24));
		jButtonParams.setToolTipText("ϵͳ����");
		jButtonParams.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonParams_actionPerformed(e);
			}
		});
		
		jButtonAlertParams.setIcon(imageAlertParams);
		jButtonAlertParams.setMaximumSize(new Dimension(32, 24));
		jButtonAlertParams.setToolTipText("��������");
		jButtonAlertParams.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAlertParams_actionPerformed(e);
			}
		});

		jButtonLogQuery.setIcon(imageLogComputer);
		jButtonLogQuery.setMaximumSize(new Dimension(32, 24));
		jButtonLogQuery.setToolTipText("������־");
		jButtonLogQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		jButtonLogin.setIcon(imageLogin);
		jButtonLogin.setMaximumSize(new Dimension(32, 24));
		jButtonLogin.setToolTipText("�û���¼");
		jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogin_actionPerformed(e);
			}
		});

		jButtonManual.setIcon(imageManual);
		jButtonManual.setMaximumSize(new Dimension(32, 24));
		jButtonManual.setToolTipText("ʹ���ֲ�");
		jButtonManual.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuManual_actionPerformed(e);
			}
		});
		
		trayOpen.setLabel("�����ؽ���");
		trayOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trayOpen_actionPerformed(e);
			}
		});
		
		trayExit.setLabel("�˳�����");
		trayExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trayExit_actionPerformed(e);
			}
		});

		Logo.progress.setValue(30);
		Logo.progress.setString(startStr + "30%");

		jPanelInfo.setPreferredSize(new Dimension(570, 20));
		jPanelInfo.setLayout(new BorderLayout());

		// ���ñ��ļ���
		// setTableFileNames();

		argumentDataFile.setFileName(argumentFileName);
		argumentDataFile.setLoadAsInserted(true);
		argumentDataSet.setDataFile(argumentDataFile);
		argumentDataSet.open();
		// ���ó���
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
		
		// ������˵�
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
		
		// ������ʱ����ÿ���Ӵ���һ�Σ������鶨ʱ��ʱ�Զ�������
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

		trayIcon = new TrayIcon(imageLogo.getImage(), "���������ع���ϵͳ", popupMenu);

		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					return;
				}
				
				if (e.getClickCount() == 2) {// ˫�����̴�������
					open();
				}
			}
		});
	}

	private static void setTableFileNames() {
		// ����JAR������ļ�·��
		File f = new File("netmon19.jar");
		String tmp = f.getAbsolutePath();
		String path = tmp.substring(0, tmp.lastIndexOf("\\") + 1);

		tomcatPath = path + "\\tomcat";
		htmlPath = tomcatPath + "\\webapps\\ROOT\\";

		paramsFileName = path + "ɨ�����ñ�.txt";
		c1FileName = path + "���б�.txt";
		logFileName = path + "��־��.txt";
		interfaceFileName = path + "�ӿڱ�.txt";
		routerFileName = path + "·�ɱ�.txt";
		macPortFileName = path + "MAC�˿ڱ�.txt";
		argumentFileName = path + "ϵͳ������.txt";
		snmpFileName = path + "SNMP�����.txt";
		arpFileName = path + "IPת����.txt";
		sysFileName = path + "ϵͳ��Ϣ��.txt";
		devMacFileName = path + "�豸MAC��.txt";
		operLogFileName = path + "������־��.txt";
		vendorFileName = path + "���̱�.txt";
		neibourFileName = "�ھӱ�.txt";
		userFileName = path + "�û���.txt";
		userSubnetFileName = path + "�û��������ñ�.txt";
		// bug 2009-04-25 δ��֤ D:\netmon19\\log\\conf_bak
		confBakDir = path + "log\\conf_bak";
		keyDevIf = path + "�ؼ��豸-�ӿ�.txt";
		InstallPath = path;
	}

	// ����ϵͳ������
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
			if (historyValues[0].equals("��"))
				dataBase = false;
			else
				dataBase = true;
			// �ɼ�ȫ���˿ڻ��ǻ����˿�
			if (historyValues[1].equals("ȫ���˿�"))
				fluxType = 1;
			else
				fluxType = 2;
			if (historyValues[4].equals("��"))
				// bug 2009-01-05
				backupType = 1;
			else
				backupType = 1;

			String tmpSyncHour = XmlTool.getNodeValue("config.xml",
					"/config/paramsConfig/", "syncHour");
			if (tmpSyncHour.equals("����ʱͬ��"))
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

	// ����ͬ���˵�
	void jMenuSync_actionPerformed(ActionEvent e) {
		if (syncServer.equals("")) {
			JOptionPane.showMessageDialog(this, "û����������ͬ������վ������ϵͳ���������á�");
			return;
		}
		String select = (String) JOptionPane.showInputDialog(this,
				"ѡ��ͬ�����ݡ���־Ϊ��7�����ݡ�\n" + "ͬ��ʹ�öԷ����ݸ��Ǳ������ݡ�", "����ͬ��",
				// , "������׷�ӣ�"
				JOptionPane.YES_NO_CANCEL_OPTION, null, new String[] { "����",
						"��־", "����+��־" }, "����");
		if (select == null)
			return;
	}

	// �������ݲ˵�
	void jMenuBackup_actionPerformed(ActionEvent e) {
		backup();
	}

	void jButtonBackup_actionPerformed(ActionEvent e) {
		backup();
	}

	private void backup() {
		// ȡ�ñ���·����
		JFileChooser chooser = new JFileChooser();
		fcSetup(chooser);
		chooser.setDialogTitle("ѡ�����ñ��ݵ�Ŀ���ļ���");
		chooser.setFileHidingEnabled(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("ȷ��");
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		final String dir = chooser.getSelectedFile().toString();
		// ����sql���ݿ�
		try {
			backupService.export(dir);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "���ñ�����ϡ������޸����ñ��ݣ������޷��ָ���");
	}

	// �����ָ��˵���
	void jMenuRestore_actionPerformed(ActionEvent e) {
		restore();
	}

	void jButtonRestore_actionPerformed(ActionEvent e) {
		restore();
	}

	private void restore() {
		int confirm = JOptionPane.showConfirmDialog(this, "�ָ����ý��������е����ã��Ƿ������",
				"���ûָ�", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION)
			return;
		// ȡ�ñ���·����
		JFileChooser chooser = new JFileChooser();
		fcSetup(chooser);
		chooser.setDialogTitle("ѡ�����ûָ���Դ�ļ���");
		chooser.setFileHidingEnabled(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("ȷ��");
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		final String dir = chooser.getSelectedFile().toString();
		// �ָ�mysql���ݿ�
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

		JOptionPane.showMessageDialog(null, "���ûָ���ϣ�ϵͳ���Զ��˳�����������ϵͳ��������Ч��");
		System.exit(0);
	}

	private void fcSetup(JComponent fc) {
		String tmp;
		for (int i = 0; i < fc.getComponentCount(); i++) {
			if (JLabel.class.isInstance(fc.getComponents()[i])) {
				tmp = ((JLabel) fc.getComponents()[i]).getText();
				if (tmp.indexOf("�ļ���") != -1)
					fc.getComponents()[i].setEnabled(false);
				if (tmp.indexOf("�ļ�����") != -1)
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
		jLabelScan.setVisible(true);// ��ʾɨ����ͼ��
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
		// ���û�����ڽ������ݺϲ��������ȥ��jLabelScan
		if (!isGuest())
			configEnable(true);
	}

	void jMenuSnapShot_actionPerformed(ActionEvent e) {
		snapShot();
	}

	void jButtonSnapShot_actionPerformed(ActionEvent e) {
		snapShot();
	}

	// ��ǰ���գ�
	private void snapShot() {
	}

	// ȫ�ֲ�������(Ĭ����������)��
	// Ĭ�ϴ򿪵ڼ���ѡ��������������0��Ĭ�ϣ���ɨ������1��
	// ��������2����������3����ʱ����4����������5ʱʹ��
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

	// ɨ������
	void jMenuScanParams_actionPerformed(ActionEvent e) {
		params(1);
	}

	private void params(int num) {
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			/*int confirm = JOptionPane.showConfirmDialog(this, "�˳�" + Constants.softName + "��",
					"�˳�", JOptionPane.YES_NO_OPTION);
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
			systemTray = SystemTray.getSystemTray();// ���ϵͳ���̵�ʵ��
			systemTray.add(trayIcon);
			setExtendedState(JFrame.ICONIFIED);
			dispose();// ������С��ʱdispose�ô���
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
							+ "\\tool\\FoxitReader.exe manual\\�û�ʹ���ֲ�.pdf");
		} catch (IOException ioe) {
		}
	}
	
	void trayOpen_actionPerformed(ActionEvent e) {
		open();
	}
	
	void trayExit_actionPerformed(ActionEvent e) {
		/*
		int confirm = JOptionPane.showConfirmDialog(this, "�˳�" + Constants.softName + "��",
				"�˳�", JOptionPane.YES_NO_OPTION);
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
		// Ϊ�˰�ȫ�������֤�̺߳����ݿ�˳���رգ�ȷ����һ�γɹ����룬�������·�ʽ��
		// ֹͣTimer.
		// timer.stop();
		
		int confirm = JOptionPane.showConfirmDialog(this, "�Ƿ񱸷����ݿⲢ�����ݿⷢ�͵�" + Constants.mailAddr1+ "��",
				"�˳��������ݿ�", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			// �Զ��������ݿ�
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
			System.out.println("���ñ�����ϡ������޸����ñ��ݣ������޷��ָ���\n" + "����λ�ڣ�" + dir);
			
			// �����������ڣ���ֹ�����ʼ�ʱ��ʱ��ȴ�
			this.setVisible(false);
			
			// �����ݿⱸ�ݷ��͵�ָ������
			DatabaseMail mail = new DatabaseMail();
			mail.setBackupPath(dir);
			mail.run();
		}
		querySelect.myexit();
		dispose();
		//����������һ��minimize��ʱ��Ż��ʼ��systemTray
		if(systemTray != null)
			systemTray.remove(trayIcon);
		System.exit(0);
	}// exit
	
	// ��ʱ�¼�����1���Ӷ�ʱ�����¼�����ʱ��һ���������ӡ�
	void jTimer_actionPerformed(ActionEvent e) {
		return;
	}

	// ����Զ����û���
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
		// ����title�����û�Ȩ����Ϣ
		if (userType != null && userType.length() > 0) {
			String temp = mainTitle + "  [";
			if (isAdmin())
				temp = temp + "ϵͳ����Ա]";
			else if (isGuest())
				temp = temp + "��ͨ�û�]";
			else
				temp = temp + userType + "]";
			this.setTitle(temp);
		}
		// Login֮�󣬸���userTypeˢ��������
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
