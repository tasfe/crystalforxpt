/*
 * 必填字段加入红色(*)
 */
function getAlarm(){
	return "<span style = 'color : red; vertical-align   :middle'>*</span>";
}
/*
 * 验证手机号，13,15,18.....
 */
function checkMobile( s ){ 
	var regu =/^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	}else{ 
		return false; 
	} 
}
/*
 * 验证电话，三位或四位区号 - 七位或八位数字
 */
function checkPhone( strPhone ) { 
	var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	var re=new RegExp(pattern);
	if(re.test(strPhone)){
		return true;
	}else{
		return false;
	}

}
/*
 * 验证邮箱
 */
function checkEmail(strEmail) { 
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; 
	var re=new RegExp(emailReg);
	if( re.test(strEmail)){ 
		return true; 
	}
	else{ 
		return false; 
	} 
}
