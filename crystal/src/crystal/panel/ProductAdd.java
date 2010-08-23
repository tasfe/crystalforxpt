package crystal.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import crystal.MainFrame;
import crystal.common.AppContextUtil;
import crystal.common.Constants;
import crystal.hibernate.po.Material;
import crystal.hibernate.po.Product;
import crystal.hibernate.po.ProductCategory;
import crystal.service.MaterialService;
import crystal.service.ProductCategoryService;
import crystal.service.ProductService;

public class ProductAdd extends JDialog{
	private static final long serialVersionUID = 1L;
	
	// Ĭ��Ϊ���item
	int type = Constants.ITEM_ADD;
	DecimalFormat formatter = new DecimalFormat(Constants.DECIMAL_FORMAT);
	
	// �洢������Ʒ���жϳɹ�����Ҫ�ۼ����ϵ� List
	List<Material> verifyList = new ArrayList<Material>();
	// ��Ҫ�ۼ����ϵ���Ŀ��������Ӧ�ú�verifyList�������
	List<Integer> countList = new ArrayList<Integer>();
	
	JPanel contentPane;
	JPanel jPanelInfo = new JPanel();
	JPanel jPanelMaterial = new JPanel();
	JPanel jPanelButton = new JPanel();
	ProductPanel parent = new ProductPanel();
	
	MaterialService materialService = null;
	List<Material> materialList = new ArrayList<Material>();
//	MaterialCategoryService materialCategoryService = null;
//	List<MaterialCategory> materialCategoryList = new ArrayList<MaterialCategory>();
	Product product = null;
	ProductService productService = null;
	List<Product> productList = new ArrayList<Product>();
	ProductCategoryService productCategoryService = null;
	List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

	public void setParent(ProductPanel parent) {
		this.parent = parent;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// ���ϳɱ���
	double materialPrice = 0;
	// �������࣬Ĭ�����20��
	int materialSize = 20;
//	JComboBox jComboBoxMaterialCategory[];
	JLabel jLabelMaterial[] = new JLabel[materialSize];
	JComboBox jComboBoxMaterial[] = new JComboBox[materialSize];
	JLabel jLabelPrices[] = new JLabel[materialSize];
	SpinnerNumberModel numberModel[] = new SpinnerNumberModel[materialSize];
	JSpinner jSpinnerMaterial[] = new JSpinner[materialSize];

	JLabel jLabelInfo = new JLabel("��*��Ϊ������");
	JLabel jLabelName = new JLabel("����(*)");
	JTextField jTextFieldName = new JTextField();
	JLabel jLabelPrice = new JLabel("����(*)");
	JTextField jTextFieldPrice = new JTextField();
	JLabel jLabelMaterialPrice = new JLabel("�����ܳɱ���Ϊ��0 Ԫ");
	JTextField jTextFieldMaterialPrice = new JTextField();
	JLabel jLabelCount = new JLabel("������Ŀ(*)");
	JTextField jTextFieldCount = new JTextField("1");
	JLabel jLabelCategory = new JLabel("��������(*)");
	JComboBox jComboBoxCategory = new JComboBox();

	JLabel jLabelColor = new JLabel("��ɫ");
	JTextField jTextFieldColor = new JTextField();
	JLabel jLabelNote1 = new JLabel("��ע");
	JTextField jTextFieldNote1 = new JTextField();
	
	JButton jButtonOk = new JButton("ȷ��");
	JButton jButtonExit = new JButton("ȡ��");

	public ProductAdd(int type) {
		super();
		this.type = type;
		try {
			init();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "ProductAdd,��ʼ������:" + ex);
		}
	}

	@SuppressWarnings("unchecked")
	private void init() {
		materialService = (MaterialService) AppContextUtil.getInstance()
				.getBean("materialService");
//		materialCategoryService = (MaterialCategoryService) AppContextUtil.getInstance()
//				.getBean("materialCategoryService");
		productService = (ProductService) AppContextUtil.getInstance()
				.getBean("productService");
		productCategoryService = (ProductCategoryService) AppContextUtil
				.getInstance().getBean("productCategoryService");
		String hql = "FROM Material m WHERE materialCategory.id != 2 and m.count > 0 ORDER BY name ASC";
		materialList = materialService.findBySql(hql);
//		materialCategoryList = materialCategoryService.findAll();
		productCategoryList = productCategoryService.findAll();
		
//		jComboBoxMaterialCategory = new JComboBox[materialCategoryList.size()];

		contentPane = (JPanel) this.getContentPane();

		jTextFieldCount.setText("1");
		jTextFieldCount.setEditable(false);
		
//		initInfo();
		initMaterial();
		for(ProductCategory mc : productCategoryList) {
			jComboBoxCategory.addItem(mc);
		}
		jLabelMaterialPrice.setForeground(Color.RED);
		jPanelInfo.setBorder(BorderFactory.createTitledBorder("��*��Ϊ������"));
		jPanelInfo.setLayout(new XYLayout());

		jPanelInfo.add(jLabelName, new XYConstraints(5, 10, 80, 20));
		jPanelInfo.add(jTextFieldName, new XYConstraints(100, 10, 120, 20));
		jPanelInfo.add(jLabelPrice,
				new XYConstraints(240, 10, 80, 20));
		jPanelInfo.add(jTextFieldPrice, new XYConstraints(320, 10,
				120, 20));
		jPanelInfo.add(jLabelMaterialPrice, new XYConstraints(5, 35, 450,
				20));
		jPanelInfo.add(jLabelCount, new XYConstraints(5, 60, 80,
				20));
		jPanelInfo.add(jTextFieldCount, new XYConstraints(100, 60,
				120, 20));
		jPanelInfo.add(jLabelCategory, new XYConstraints(240, 60, 80, 20));
		jPanelInfo.add(jComboBoxCategory, new XYConstraints(320, 60,
				120, 20));
		jPanelInfo.add(jLabelColor, new XYConstraints(5, 85, 80, 20));
		jPanelInfo.add(jTextFieldColor, new XYConstraints(100, 85,
				120, 20));
		jPanelInfo.add(jLabelNote1, new XYConstraints(240, 85, 80, 20));
		jPanelInfo.add(jTextFieldNote1,
				new XYConstraints(320, 85, 120, 20));
		
		// jPanelMaterial
		jPanelMaterial.setBorder(BorderFactory.createTitledBorder("ѡ���������"));
		jPanelMaterial.setLayout(new XYLayout());
		for (int i = 0; i < materialSize; i++) {
			jPanelMaterial.add(jLabelMaterial[i], new XYConstraints(
					5 + 490 * (i % 2), 5 + 25 * (i / 2), 70, 20));
			jPanelMaterial.add(jComboBoxMaterial[i], new XYConstraints(
					80 + 490 * (i % 2), 5 + 25 * (i / 2), 240, 20));
			jPanelMaterial.add(jSpinnerMaterial[i], new XYConstraints(
					330 + 490 * (i % 2), 5 + 25 * (i / 2), 50, 20));
			jPanelMaterial.add(jLabelPrices[i], new XYConstraints(
					390 + 490 * (i % 2), 5 + 25 * (i / 2), 90, 20));
		}

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

		contentPane.setLayout(new BorderLayout());
		contentPane.add(jPanelInfo, BorderLayout.NORTH);
		contentPane.add(jPanelMaterial, BorderLayout.CENTER);
		contentPane.add(jPanelButton, BorderLayout.SOUTH);

		this.setTitle("�����������Ʒ");
		if(type == Constants.ITEM_MODIFY)
			this.setTitle("�޸���Ʒ����");
		this.setIconImage(MainFrame.imageLogo.getImage());
		this.setSize(990, 500);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@SuppressWarnings("unchecked")
	void initProduct() {
		if(type == Constants.ITEM_MODIFY) {
			jTextFieldName.setText(product.getName());
			jTextFieldPrice.setText(formatter.format(product.getPrice()));
			jTextFieldColor.setText(product.getColor());
			jTextFieldNote1.setText(product.getNote1());
			jTextFieldCount.setText("1");
			jTextFieldCount.setEditable(false);
			for (int j = 0; j < productCategoryList.size(); j++) {
				if(product.getProductCategory().getName().equals(((ProductCategory)jComboBoxCategory.getItemAt(j)).toString())) {
					jComboBoxCategory.setSelectedIndex(j);
					break;
				}
			}
			// ʹ��java�ķ�����ƣ�������������
			try {
				Class c = product.getClass();
				for (int i = 0; i < materialSize; i++) {
					String strId = "getMaterialByMid" + (i + 1);
					String strCount = "getMcount" + (i + 1);
					Method mid = c.getMethod(strId, null);
					// Material tempm = (Material)mid.invoke(product, null);
					Object obj = mid.invoke(product, null);
					if(obj == null)
						continue;
					Material tempm = (Material)obj;
					Method mcount = c.getMethod(strCount, null);
					Object obj1 = mcount.invoke(product, null);
					if(obj1 == null)
						continue;
					int tempi = (Integer)obj1;
					if(tempi <= 0)
						continue;
					for(int j = 1; j< jComboBoxMaterial[i].getItemCount();j++) {
						if(((Material)jComboBoxMaterial[i].getItemAt(j)).getId().equals(tempm.getId()))
							jComboBoxMaterial[i].setSelectedIndex(j);
					}
					numberModel[i].setValue(tempi);
				}
			} catch (Exception e) {
			}
		}
	}
	
	private void initMaterial() {
		for (int i = 0; i < materialSize; i++) {
			jComboBoxMaterial[i] = new JComboBox();
			jComboBoxMaterial[i].addItem("");
			jLabelMaterial[i] = new JLabel("ѡ�����" + (i + 1));
			jLabelPrices[i] = new JLabel();
			numberModel[i] = new SpinnerNumberModel(0, 0, 2147483647, 1);
			jSpinnerMaterial[i] = new JSpinner(numberModel[i]);
			jSpinnerMaterial[i].setModel(numberModel[i]);
		}
		int temp = materialList.size();
		for (int j = 0; j < (temp > materialSize ? materialSize : temp); j++) {
			for (Material m : materialList) {
				jComboBoxMaterial[j].addItem(m);
			}
			// jComboBoxMaterial[j].setSelectedIndex(j);
		}
		// ����������಻��materialSize�֣�ʣ��������˵���Spinner����
		if (temp < materialSize) {
			for (int k = temp; k < materialSize; k++) {
				jComboBoxMaterial[k].addItem("");
				jComboBoxMaterial[k].setEnabled(false);
				jSpinnerMaterial[k].setEnabled(false);
			}
		}
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computePrice();
			}
		};
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				computePrice();				
			}
		};
		for (int i = 0; i < materialSize; i++) {
			jComboBoxMaterial[i].addActionListener(actionListener);
			numberModel[i].addChangeListener(changeListener);
		}
	}

	@SuppressWarnings("unchecked")
	protected void jButtonOk_actionPerformed(ActionEvent e) {
		if (verifyInput()) {
			int count = 0;
			double price = 0.0d;
			if (!"".equals(jTextFieldCount.getText())) {
				try {
					count = Integer.parseInt(jTextFieldCount.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "������Ŀ������������");
					return;
				}
				if (count < 0) {
					JOptionPane.showMessageDialog(this, "������Ŀ������������");
					return;
				}
			}

			if (!"".equals(jTextFieldPrice.getText())) {
				try {
					price = Double.parseDouble(jTextFieldPrice.getText());
				} catch (NumberFormatException e4) {
					JOptionPane.showMessageDialog(this, "���������ʽ����");
					return;
				}
			}
			if (type == Constants.ITEM_ADD) {
				Product p = new Product();
				String name = jTextFieldName.getText() + "-" + jTextFieldColor.getText();
				if(parent.hasProduct(name)) {
					JOptionPane.showMessageDialog(this, "�Ѻ��д����Ƶ���Ʒ");
					return;
				}
				if (verifyMaterialCount()) {
					// ����һ����Ʒ��¼
					p.setName(name);
					// if (price > 0)
					p.setPrice(price);
					if (count >= 0) {
						p.setCount(count);
						p.setCountMake(count);
					}
					computePrice();
					if (materialPrice >= 0) {
						p.setMaterialPrice(materialPrice);
					}
					p.setProductCategory((ProductCategory) jComboBoxCategory
							.getSelectedItem());
					p.setColor(jTextFieldColor.getText());
					p.setNote1(jTextFieldNote1.getText());

					// ʹ��java�ķ�����ƣ�������������
					try {
						Class c = p.getClass();
						// ʵ�ʿۼ�������Ŀ
						String result = "���1����" + p.getName() + "���ɹ������Ĳ������£�\n";
						int num = 0;
						for (Material m : verifyList) {
							if(m != null) {
								m.setCount(m.getCount() - countList.get(num));
								materialService.update(m);
								result += "��" + m.getName() + "��" + countList.get(num)
										+ "����" + ((num + 1) % 2 == 0 ? "\n" : ""); 
								num++;
							}
						}
						// �洢�������
						for (int i = 0; i < materialSize; i++) {
							if ("".equals(jComboBoxMaterial[i].getSelectedItem()))
								continue;
							int tempi = numberModel[i].getNumber().intValue();
							if (tempi <= 0)
								continue;
							String strId = "setMaterialByMid" + (i + 1);
							String strCount = "setMcount" + (i + 1);
							Material tempm = (Material) jComboBoxMaterial[i].getSelectedItem();
							Method mid = c.getMethod(strId,
									new Class[] { Material.class });
							mid.invoke(p, tempm);
							Method mcount = c.getMethod(strCount,
									new Class[] { Integer.class });
							mcount.invoke(p, tempi);
						}
						productService.save(p);
						parent.addRow(p);
						JOptionPane.showMessageDialog(this, result);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
			else {
				product.setName(jTextFieldName.getText());
				//if (price > 0)
				product.setPrice(price);
				computePrice();
				if (materialPrice >= 0) {
					product.setMaterialPrice(materialPrice);
				}
				product.setProductCategory((ProductCategory) jComboBoxCategory
						.getSelectedItem());
				product.setColor(jTextFieldColor.getText());
				product.setNote1(jTextFieldNote1.getText());

				// ʹ��java�ķ�����ƣ�������������
				try {
					Class c = product.getClass();
					for (int i = 0; i < materialSize; i++) {
//						if ("".equals(jComboBoxMaterial[i].getSelectedItem()))
//							continue;
						int tempCount = numberModel[i].getNumber().intValue();
//						if (tempCount <= 0)
//							continue;
						String strId = "setMaterialByMid" + (i + 1);
						String strCount = "setMcount" + (i + 1);
						Method mid = c.getMethod(strId,
								new Class[] { Material.class });
						Method mcount = c.getMethod(strCount,
								new Class[] { Integer.class });
						if("".equals(jComboBoxMaterial[i].getSelectedItem()) || tempCount <=0) {
							mid.invoke(product, materialList.get(0));
							mcount.invoke(product, 0);
						} else {
							mid.invoke(product, (Material) jComboBoxMaterial[i]
							                 								.getSelectedItem());
							mcount.invoke(product, tempCount);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				productService.update(product);
				parent.reloadTable();
			}
			this.dispose();
		}
	}
	// ʹ��JComboBoxMaterial�����е�������֤���������Ŀ�Ƿ���
	private boolean verifyMaterialCount() {
		verifyList.clear();
		countList.clear();
		try {
			for (int i = 0; i < materialSize; i++) {
				if ("".equals(jComboBoxMaterial[i].getSelectedItem()))
					continue;
				int tempi = numberModel[i].getNumber().intValue();
				if (tempi <= 0)
					continue;
				Material tempm = (Material) jComboBoxMaterial[i].getSelectedItem();
				if(tempm.getCount() < tempi) {
					JOptionPane.showMessageDialog(this, "������Ʒ������ϡ�" + tempm.getName() + "����Ŀ�������빺�����\n"
							+ "�����Ŀ��" + tempm.getCount() + "�� ��Ҫ��Ŀ��" + tempi);
					return false;
				} else {
					verifyList.add(tempm);
					countList.add(tempi);
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	// ��materialList�и���id����material
	private Material findById(int id) {
		if(materialList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM crystal.hibernate.po.Material where id = :id";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("id", id);
		try {
			qr = q.execute(materialList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		
		List<Material> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0);
		}
		return null;
	}

	protected void jButtonExit_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void computePrice() {
		materialPrice = 0;
		for(int i =0;i<materialSize;i++) {
			int count = numberModel[i].getNumber().intValue();
			Object ob =jComboBoxMaterial[i].getSelectedItem();
			if(ob.equals(""))
				continue;
			Material m = (Material)ob;
			jLabelPrices[i].setText(formatter.format(m.getPrice()) + "��" + m.getCount() + "����");
			if(count <= 0)
				continue;
			materialPrice += m.getPrice() * count;
		}
		
		jLabelMaterialPrice.setText("�����ܳɱ���Ϊ��"
				+ formatter.format(materialPrice) + " Ԫ");
	}

	private boolean verifyInput() {
		String strName = jTextFieldName.getText();
		String strPrice = jTextFieldPrice.getText();
		String strCount = jTextFieldCount.getText();
		if (strName == null || strName.equals("")) {
			JOptionPane.showMessageDialog(this, "���Ʋ���Ϊ�գ�");
			return false;
		}
		if (strPrice == null || strPrice.equals("")) {
			JOptionPane.showMessageDialog(this, "���۲���Ϊ�գ�");
			return false;
		}
		if (strCount == null || strCount.equals("")) {
			JOptionPane.showMessageDialog(this, "������Ŀ����Ϊ�գ�");
			return false;
		}
		return true;
	}
}

