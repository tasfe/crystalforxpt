package crystal.panel;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JOptionPane;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import crystal.common.Constants;
import crystal.hibernate.po.Material;
import crystal.hibernate.po.Product;
import crystal.service.MaterialService;
import crystal.service.ProductService;

public class ProductTool {

	/**
	 * @param product
	 * @param verifyList
	 * @param countList
	 * @param iCount
	 * @param materialService
	 * @param productService
	 * @param materialList
	 * @param isSave
	 * @return
	 */
	public static boolean make(Product product, List<Integer> verifyList, List<Integer> countList, 
			int iCount, MaterialService materialService, ProductService productService, List<Material> materialList) {
		if(!verifyMaterialCount(product, verifyList, countList, true, iCount)) {
			return false;
		}
		String result = "����" + iCount + "����" + product.getName() + "���ɹ������Ĳ������£�\n";
		int num = 0;
		for (Integer i : verifyList) {
			Material m = findById(materialList, i);
			if(m != null) {
				m.setCount(m.getCount() - countList.get(num) * iCount);
				materialService.update(m);
				result += "��" + m.getName() + "��" + countList.get(num) * iCount
						+ "����" + ((num + 1) % 2 == 0 ? "\n" : ""); 
				num++;
			}
		}
		product.setCount(product.getCount() + iCount);
		product.setCountMake(product.getCountMake() + iCount);
		productService.update(product);
		JOptionPane.showMessageDialog(null, result);
		return true;
	}
	
	
	public static boolean split(Product product, List<Integer> verifyList, List<Integer> countList, 
			int iCount, MaterialService materialService, ProductService productService, List<Material> materialList) {
		if(!verifyMaterialCount(product, verifyList, countList, false, iCount)) {
			return false;
		}
		String result = "���" + iCount + "����" + product.getName()
				+ "���ɹ�����ò������£�\n";
		int num = 0;
		for (Integer i : verifyList) {
			Material m = findById(materialList, i);
			if (m != null) {
				m.setCount(m.getCount() + countList.get(num) * iCount);
				materialService.update(m);
				result += "��" + m.getName() + "��" + countList.get(num)
						* iCount + "����" + ((num + 1) % 2 == 0 ? "\n" : "");
				num++;
			}
		}
		product.setCount(product.getCount() - iCount);
		product.setCountMake(product.getCountMake() - iCount);
		productService.update(product);
		JOptionPane.showMessageDialog(null, result);
		return true;
	}
	
	// ��֤���������Ŀ�Ƿ���
	// ����ture��ʾ��������ʱ��֤��������������������
	// false���ǲ�ֲ���ʱ����֤��Ŀ��û�����壬�˺���ֻ��Ϊ�˻�ȡverifyList��countList
	private static boolean verifyMaterialCount(Product product, List<Integer> verifyList, List<Integer> countList, boolean isMake, int iCount) {
		verifyList.clear();
		countList.clear();
		try {
			Class c = product.getClass();
			for (int i = 0; i < Constants.MATERIAL_SIZE; i++) {
				String strId = "getMaterialByMid" + (i + 1);
				String strCount = "getMcount" + (i + 1);
				Method mid = c.getMethod(strId, null);
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
				if(isMake && tempi * iCount > tempm.getCount()) {
					JOptionPane.showMessageDialog(null, "������Ʒ������ϡ�" + tempm.getName() + "����Ŀ�������빺�����\n"
							+ "�����Ŀ��" + tempm.getCount() 
							+ "�� ��Ҫ��Ŀ��" + tempi + " * " + iCount + " = " + (tempi * iCount));
					return false;
				}
				verifyList.add(tempm.getId());
				countList.add(tempi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// ��materialList�и���id����material
	public static Material findById(List<Material> materialList, int id) {
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
	
	// ������Ʒ������materialList�в��ϵ���Ŀ�����ǲ��������ݿ⣬����жϳ��������²�materialList
	/**
	 * @param product
	 * @param verifyList
	 * @param countList
	 * @param materialList
	 * @param materialService
	 * @param isSave 
	 */
	/**
	 * @param product
	 * @param verifyList
	 * @param countList
	 * @param materialList
	 * @param materialService
	 * @param isSave	�Ƿ񱣴棬����Ŀ�ж�ͨ����������ֵ�ʱ����Ҫ���档���ۼ���Ŀ��ʱ��Ҳ��Ҫ����
	 * @param isSplit	��ģ���ֵ�ʱ��˲���Ϊtrue���������ۼ���Ŀ����Ҫ�洢��ʱ��˲���Ϊfalse���˲������������ã�
	 * 					1��false��ʱ���ٵ���verifyMaterialCount������ʹ�ô��ݽ�����verifyList��countList
	 * 						true��ʱ�������verifyMaterialCount��Ŀ��ֻ������verifyList��countList
	 * 					2���Ƿ��֣�false��ʱ����ϵ�����Ϊ�ۼ�����true��ʱ��ģ���֣����Բ��ϵ�����Ϊ����
	 */
	public static void simulateSplit(Product product, List<Integer> verifyList, List<Integer> countList, 
			List<Material> materialList, MaterialService materialService, boolean isSave, boolean isSplit) {
		if(isSplit)
			verifyMaterialCount(product, verifyList, countList, false, product.getCount());
		int num = 0;
		for (Integer i : verifyList) {
			Material m = findById(materialList, i);
			if (m != null) {
				m.setCount(isSplit ? m.getCount() + countList.get(num) * product.getCount() : m.getCount() - countList.get(num) * product.getCount());
				if(isSave)
					materialService.update(m);
				num++;
			}
		}
	}
}
