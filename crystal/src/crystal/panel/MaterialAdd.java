package crystal.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import crystal.MainFrame;
import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.hibernate.po.Material;
import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.MaterialCategory;
import crystal.service.MaterialBuyService;
import crystal.service.MaterialCategoryService;
import crystal.service.MaterialService;

public class MaterialAdd extends JDialog{
	private static final long serialVersionUID = 1L;
	
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	
	JPanel contentPane;
	JPanel jPanelInfo = new JPanel();
	JPanel jPanelControl = new JPanel();
	MaterialPanel parent = new MaterialPanel();
	
	MaterialService materialService = null;
	MaterialCategoryService materialCategoryService = null;
	List<MaterialCategory> materialCategoryList = new ArrayList<MaterialCategory>();
	MaterialBuyService materialBuyService = null;
	
	public void setParent(MaterialPanel parent) {
		this.parent = parent;
	}

	JLabel jLabelInfo = new JLabel("带*号为必填项");
	JLabel jLableName = new JLabel("名称(*)");
	JTextField jTextFieldName = new JTextField();
	JLabel jLabelPrice = new JLabel("单价(*)");
	JTextField jTextFieldPrice = new JTextField();
	JLabel jLabelCount = new JLabel("新增数目(*)");
	JTextField jTextFieldCount = new JTextField("1");
	JLabel jLabelCategory = new JLabel("所属分类(*)");
	JComboBox jComboBoxCategory = new JComboBox();

	JLabel jLabelType = new JLabel("编号");
	JTextField jTextFieldType = new JTextField();
	JLabel jLabelTypeNumber = new JLabel("编号附注");
	JTextField jTextFieldTypeNumber = new JTextField();
	JLabel jLabelColor = new JLabel("颜色");
	JTextField jTextFieldColor = new JTextField();
	JLabel jLabelNote1 = new JLabel("备注");
	JTextField jTextFieldNote1 = new JTextField();
	
	JButton jButtonOk = new JButton("确定");
	JButton jButtonExit = new JButton("取消");

	public MaterialAdd() {
		super();
		try {
			init();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "MaterialAdd,初始化错误:" + ex);
		}
	}

	@SuppressWarnings("unchecked")
	private void init() {
		materialService = (MaterialService) AppContextUtil.getInstance()
				.getBean("materialService");
		materialCategoryService = (MaterialCategoryService) AppContextUtil
				.getInstance().getBean("materialCategoryService");
		materialBuyService = (MaterialBuyService) AppContextUtil.getInstance()
				.getBean("materialBuyService");
		materialCategoryList = materialCategoryService.findAll();

		for (MaterialCategory mc : materialCategoryList) {
			jComboBoxCategory.addItem(mc);
		}
		contentPane = (JPanel) this.getContentPane();

		jPanelInfo.setBorder(BorderFactory.createTitledBorder("带*号为必填项"));
		jPanelInfo.setLayout(new XYLayout());

		jPanelInfo.add(jLableName, new XYConstraints(5, 10, 90, 20));
		jPanelInfo.add(jTextFieldName, new XYConstraints(100, 10, 120, 20));
		jPanelInfo.add(jLabelPrice, new XYConstraints(5, 35, 90, 20));
		jPanelInfo.add(jTextFieldPrice, new XYConstraints(100, 35, 120, 20));
		jPanelInfo.add(jLabelCount, new XYConstraints(5, 60, 90, 20));
		jPanelInfo.add(jTextFieldCount, new XYConstraints(100, 60, 120, 20));
		jPanelInfo.add(jLabelCategory, new XYConstraints(5, 85, 90, 20));
		jPanelInfo.add(jComboBoxCategory, new XYConstraints(100, 85, 120, 20));

		jPanelInfo.add(jLabelType, new XYConstraints(5, 125, 90, 20));
		jPanelInfo.add(jTextFieldType, new XYConstraints(100, 125, 120, 20));
		jPanelInfo.add(jLabelTypeNumber, new XYConstraints(5, 150, 90, 20));
		jPanelInfo.add(jTextFieldTypeNumber, new XYConstraints(100, 150, 120,
				20));
		jPanelInfo.add(jLabelColor, new XYConstraints(5, 175, 90, 20));
		jPanelInfo.add(jTextFieldColor, new XYConstraints(100, 175, 120, 20));
		jPanelInfo.add(jLabelNote1, new XYConstraints(5, 200, 90, 20));
		jPanelInfo.add(jTextFieldNote1, new XYConstraints(100, 200, 120, 20));

		// jPanelControl
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
		jPanelControl.setBorder(BorderFactory.createEtchedBorder());
		jPanelControl.add(jButtonOk);
		jPanelControl.add(jButtonExit);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(jPanelInfo, BorderLayout.CENTER);
		contentPane.add(jPanelControl, BorderLayout.SOUTH);

		this.setTitle("添加新种类材料");
		this.setIconImage(MainFrame.imageLogo.getImage());
		this.setSize(260, 350);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void copyMaterial(Material materialSrc) {
		if (materialSrc == null)
			return;
		String name = materialSrc.getName();
		name = name.substring(0, name.indexOf("-"));
		jTextFieldName.setText(name);
		jTextFieldPrice.setText(formatter.format(materialSrc.getPrice()));
		jTextFieldType.setText(materialSrc.getType() == null ? "" : materialSrc
				.getType().toString());
		jTextFieldTypeNumber.setText(materialSrc.getTypeNumber() == null ? ""
				: materialSrc.getTypeNumber());
		jTextFieldColor.setText(materialSrc.getColor() == null ? ""
				: materialSrc.getColor());
		jTextFieldNote1.setText(materialSrc.getNote1() == null ? "" : materialSrc
				.getNote1());
	}
	
	protected void jButtonOk_actionPerformed(ActionEvent e) {
		if (verifyInput()) {
			int count = 0;
			int type = 0;
			long batchNum = 0;
			double price = 0.0d;
			if (!"".equals(jTextFieldCount.getText())) {
				try {
					count = Integer.parseInt(jTextFieldCount.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "新增数目请输入正整数");
					return;
				}
				if (count < 0) {
					JOptionPane.showMessageDialog(this, "新增数目请输入正整数");
					return;
				}
			}
			if (!"".equals(jTextFieldType.getText())) {
				try {
					type = Integer.parseInt(jTextFieldType.getText());
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(this, "编号请输入正整数");
					return;
				}
				if (type < 0) {
					JOptionPane.showMessageDialog(this, "编号请输入正整数");
					return;
				}
			}

			if (!"".equals(jTextFieldPrice.getText())) {
				try {
					price = Double.parseDouble(jTextFieldPrice.getText());
				} catch (NumberFormatException e4) {
					JOptionPane.showMessageDialog(this, "单价输入格式错误");
					return;
				}
			}
			Material m = new Material();
			MaterialBuy mb = new MaterialBuy();
			String name = jTextFieldName.getText() + "-"
					+ jTextFieldColor.getText() + "-"
					+ jTextFieldTypeNumber.getText();
			if (parent.hasMaterial(name)) {
				JOptionPane.showMessageDialog(this, "已含有此名称的材料");
				return;
			}
			m.setName(name);
			if (price > 0)
				m.setPrice(price);
			if (count >= 0) {
				m.setCount(count);
				m.setCountBuy(count);
			}
			m.setMaterialCategory((MaterialCategory)jComboBoxCategory.getSelectedItem());
			if (type > 0)
				m.setType(type);
			m.setTypeNumber(jTextFieldTypeNumber.getText());
			m.setColor(jTextFieldColor.getText());
			// m.setHeight(height);
			// m.setWidth(width);
			if (batchNum > 0)
				m.setBatchNum(batchNum);
			m.setNote1(jTextFieldNote1.getText());
			materialService.save(m);
			parent.addRow(m);
			if(count > 0 && price > 0) {
				mb.setMaterialName(name);
				mb.setMaterialPrice(price);
				mb.setCount(count);
				mb.setTotalPrice(price * count);
				mb.setMaterial(m);
				mb.setTime(new Timestamp(System.currentTimeMillis()));
				materialBuyService.save(mb);
			}
			this.dispose();
		}
	}

	protected void jButtonExit_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	private boolean verifyInput() {
		String strName = jTextFieldName.getText();
		String strPrice = jTextFieldPrice.getText();
		String strCount = jTextFieldCount.getText();
		if (strName == null || strName.equals("")) {
			JOptionPane.showMessageDialog(this, "名称不能为空！");
			return false;
		}
		if (strPrice == null || strPrice.equals("")) {
			JOptionPane.showMessageDialog(this, "单价不能为空！");
			return false;
		}
		if (strCount == null || strCount.equals("")) {
			JOptionPane.showMessageDialog(this, "新增数目不能为空！");
			return false;
		}
		return true;
	}
}

