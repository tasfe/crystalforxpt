function ipCheck(address) {
	if (address.length == 0)
		return false;
	if (address == "localhost")
		return true;
	// IPV4正则表达式
	// 以下为js的正则表达式，以/^ 开头，以 $/结尾，但是不知regex怎么引用regex0
//	var regex0 = /^ (2[0-4]\\d) + |(25[0-5]) + $/;
//	var regex1 = /^ 1\\d{2} + $/;
//	var regex2 = /^ [1-9]\\d + $/;
//	var regex3 = /^\\d + $/;
//	var regex = /^ ( + regex0 + )|( + regex1 + )|( + regex2 + )|( + regex3 + ) + $/;
//	regex = /^ ( + regex + ).( + regex + ).( + regex + ).( + regex + ) + $/;
	
	// 直接用字符串生成RegExp
	var regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
	var regex1 = "1\\d{2}";
	var regex2 = "[1-9]\\d";
	var regex3 = "\\d";
	var regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
			+ regex3 + ")";
	regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
			+ ")";
	
	var regIPv4 = new RegExp(regex);
	// Pattern p = Pattern.compile(regex);
	// Matcher m = p.matcher(address);
	// if (m.matches()) { // 判断是否IPV4格式
	// return true;
	// }
	if (regIPv4.test(address)) {
		return true;
	} else {
		// IPV6正则表达式
		var result = false;
		var regHex = "(\\p{XDigit}{1,4})";
		var regIPv6Full = "^(" + regHex + ":){7}" + regHex + "$";
		var regIPv6AbWithColon = "^(" + regHex + "(:|::)){0,6}" + regHex + "$";
		var regIPv6AbStartWithDoubleColon = "^(" + "::(" + regHex + ":){0,5}"
				+ regHex + ")$";
		var regIPv6Str = "^(" + regIPv6Full + ")|("
				+ regIPv6AbStartWithDoubleColon + ")|(" + regIPv6AbWithColon
				+ ")$";
		var regIPv6 = new RegExp(regIPv6Str);
		
		if (address.indexOf(":") != -1) {
			if (address.length <= 39) {
				var addressTemp = address;
				var doubleColon = 0;
				while (addressTemp.indexOf("::") != -1) {
					addressTemp = addressTemp.substring(addressTemp
							.indexOf("::") + 2, addressTemp.length);
					doubleColon++;
				}
				if (doubleColon <= 1) {
					//result = address.matches(regIPv6);
					result = regIPv6.test(address)
				}
			}
		}
		if (!result) {
			alert('IP地址格式不正确！');
		}
		return result;
	}
}

 
  //是否为IPv6
    function isIPv6(ip){
        var ipV6Pattern=/^\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})))(%.+)?\s*$/;
        return ipV6Pattern.test(ip);
    }
 

 
/*
* isValidIP
* 检查是否符合正确的IP表达式(IPv6 && IPv4)
*/
function  isValidIP(ip){
	 if (isNull(ip)) return false;
	 if(isIPv4(ip))
		 return true;
	 else{
		
		return 	 isIPv6(ip);
	 }
} 
/*
* isValidIP
* 检查是否符合正确的IP表达式(only check IPv4)
*/
function isIPv4(strIP) {
		if (isNull(strIP)) return false;
		var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式
		if(re.test(strIP))
		{
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;
		}
		return false;
}

function CheckMac(strMAC) { 
		//mac地址正则表达式 
		var reg_name=/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(\s[0-9A-Fa-f]{2}){5}/ ; 
		if(!reg_name.test(strMAC)){ 
		
		return false; 
		} 
		return true; 
}
function isNull(v){
	return v===null||typeof(v)=="undefined";
}

function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
}
 