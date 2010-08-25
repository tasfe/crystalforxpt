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
		String result = "制作" + iCount + "个【" + product.getName() + "】成功，消耗材料如下：\n";
		int num = 0;
		for (Integer i : verifyList) {
			Material m = findById(materialList, i);
			if(m != null) {
				m.setCount(m.getCount() - countList.get(num) * iCount);
				materialService.update(m);
				result += "【" + m.getName() + "】" + countList.get(num) * iCount
						+ "个；" + ((num + 1) % 2 == 0 ? "\n" : ""); 
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
		String result = "拆分" + iCount + "个【" + product.getName()
				+ "】成功，获得材料如下：\n";
		int num = 0;
		for (Integer i : verifyList) {
			Material m = findById(materialList, i);
			if (m != null) {
				m.setCount(m.getCount() + countList.get(num) * iCount);
				materialService.update(m);
				result += "【" + m.getName() + "】" + countList.get(num)
						* iCount + "个；" + ((num + 1) % 2 == 0 ? "\n" : "");
				num++;
			}
		}
		product.setCount(product.getCount() - iCount);
		product.setCountMake(product.getCountMake() - iCount);
		productService.update(product);
		JOptionPane.showMessageDialog(null, result);
		return true;
	}
	
	// 验证所需材料数目是否够用
	// 参数ture表示制作材料时验证，库存数必须大于所需数
	// false则是拆分材料时，验证数目并没有意义，此函数只是为了获取verifyList和countList
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
					JOptionPane.showMessageDialog(null, "制作商品所需材料【" + tempm.getName() + "】数目不够，请购买材料\n"
							+ "库存数目：" + tempm.getCount() 
							+ "， 需要数目：" + tempi + " * " + iCount + " = " + (tempi * iCount));
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
	
	// 在materialList中根据id查找material
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
	
	// 拟拆分商品，增加materialList中材料的数目，但是不保存数据库，如果判断出错，在重新查materialList
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
	 * @param isSave	是否保存，当数目判断通过后，真正拆分的时候需要保存。当扣减数目的时候也需要保存
	 * @param isSplit	当模拟拆分的时候此参数为true，当真正扣减数目，需要存储的时候此参数为false，此参数有两个作用：
	 * 					1、false的时候不再调用verifyMaterialCount，而是使用传递进来的verifyList和countList
	 * 						true的时候则调用verifyMaterialCount，目的只是生成verifyList和countList
	 * 					2、是否拆分，false的时候材料的数量为扣减，而true的时候，模拟拆分，所以材料的数量为增加
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
