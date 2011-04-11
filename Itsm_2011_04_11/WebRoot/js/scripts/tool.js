/**
 * 常用javascript函数库
 **/

//创建一个异步对象
function createRequest() {
	var request;
	if(window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		var arr = ["MSXML2.xmlhttp", "Microsoft.xmlhttp"];
		for(var i = 0; i < arr.length; i++) {
			try {
				request = new ActiveXObject(arr[i]);
				break;
			} catch (e) {}
		}
	} else {
		alert("为了享受更好的网页浏览效果，\n请将您的浏览器更新到最新版本!");
	}
	return request;
}

//过滤字符串空格
String.prototype.trim = new Function("return this.replace(/^\\s+|\\s+$/g,'')");

//除去指定标签虚线边框
function onFocusBlur(tags) {
	for(var i in tags) {
		var alinks = document.getElementsByTagName(tags[i]);
		for(var i = 0; i < alinks.length; i++) {
			alinks[i].onfocus =  function () {this.blur();};
		}
	}
}

//复制指定内容
function copy(txt){
    if(window.clipboardData){
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
    }else if(navigator.userAgent.indexOf("Opera") !=-1){
        window.location=txt;
    }else if (window.netscape){
        try{
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        }catch (e){
            alert("复制被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip) return;
        var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        var str=new Object();
        var len=new Object();
        var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext=txt;
        str.data=copytext;
        trans.setTransferData("text/unicode", str, copytext.length*2);
        var clipid=Components.interfaces.nsIClipboard;
        if (!clip) return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
    }
    alert('已经复制');
}

//显示表格标签
function displayTableTag(id) {
	var ff = !(document.all); 
	if(ff) {
		document.getElementById(id).style.display = "table-row";
	} else {
		document.getElementById(id).style.display = "block";
	}
}

//隐藏表格标签
function hiddenTableTag(id) {
	document.getElementById("bankOption").style.display = "none";
}

//判断Email的格式
function isEmail(str) {
	str = str.trim();
	var objRegExp  = /^[a-zA-Z0-9]([\w\-\.\+]*)@([\w\-\.]*)(\.[a-zA-Z]{2,4}(\.[a-zA-Z]{2}){0,2})$/i;
	return objRegExp.test(str);
}

//获取id元素,参数必须是#id格式
function $(obj){
	if(typeof(obj) == "string") {
		if(obj.charAt(0) == "#"){
			return document.getElementById(obj.substring(1, obj.length));
		}
	}
}

//元素透明
function translucent(obj, percent) {
	obj.style.filter = "alpha(opacity=" + (percent * 100) + ")";
	obj.style.opacity = percent;
	obj.style.mozOpacity = percent;
}

//元素渐变
function fade(obj, step, interval, callback) {
	var index = 0;
	var change = setInterval(function(){
		translucent(obj, step[index]);
		if(index == step.length - 1) {
			if(callback)callback();
			clearInterval(change);
		}
		index++;
	}, interval);
}