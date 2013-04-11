package com.netblizzard.basic;

public class NumberIncrease {
	// INSERT INTO `lrms_member` VALUES ('402880f3294b708001294b8216d80006', 'scau', '广州', 'qq@qq.com', '1', 'qq@qq.com', '汉族', '123456', '广东', '416403557, 416403557', '2010-06-18 22:41:43', '1', '男', '13745145869', '402880f3294a0f4801294a1361f40002', '402880f3294b708001294b8216a90005', '1', '100');
	public static void main(String[] args) {
/*		int count = 1;
		String pre = "402880f3294b708001294b8216d800";
		StringBuilder str = new StringBuilder();
		while(count<42) {
			String tmp = String.valueOf(count);
			String tmp2 = String.valueOf(count);
			if(tmp.length() < 2) {
				tmp2 = "0" + tmp2;
			}
			str.append("INSERT INTO `lrms_member` (id,isGrouper,registrationTime,reviewStatus,sex,userGroup_id,user_id,isDelete,score) VALUES('");
			str.append(pre + tmp2 + "',1,'2013-03-20 08:41:43',1,'男','402880f3294a0f4801294a1361f40002','" + tmp + "',0,1000);\n");
			count++;
		}
		System.out.println(str.toString());*/
		
		
		int count = 1;
		StringBuilder str = new StringBuilder();
		while(count<42) {
			String tmp = String.valueOf(count);
			String tmp2 = String.valueOf(count);
			if(tmp.length() < 2) {
				tmp2 = "0" + tmp2;
			}
			str.append("INSERT INTO `comm_user_role` (user_id,role_id) VALUES('");
			str.append(count + "','402880f3294e098401294e1435e90003');\n");
			count++;
		}
		System.out.println(str.toString());
	}
	
	
}
