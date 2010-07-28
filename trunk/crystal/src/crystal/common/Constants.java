package crystal.common;

public class Constants {
	
	// 软件名称
	public static final String softName = "淘宝店铺水晶饰品买卖管理系统";
	// 结构树的各节点的id
	public static final int TREE_ROOT = 0;
	public static final int TREE_MATERIAL_PRODUCT = 1;
	public static final int TREE_MATERIAL = 11;
	public static final int TREE_PRODUCT =12;
	public static final int TREE_BUY_SELL = 2;
	public static final int TREE_BUY = 21;
	public static final int TREE_SELL = 22;
	public static final int TREE_REPORT = 3;
	
	// 区分添加、修改的常量
	public static final int ITEM_ADD = 1;
	public static final int ITEM_MODIFY = 2;
	
	// 每种商品最大支持20种材料
	public static final int MATERIAL_SIZE = 20;
	
	// decimal格式化精度
	public static final String DECIMAL_FORMAT = "#.####";
	
	// 商品的“发货费用”类型对应的id
	public static final int SEND_FEE = 4;
	
}
