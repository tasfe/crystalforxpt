/*
 Intro		:	公用JS库，兼容IE、Gecko内核(NN,Mozilla,FireFox)，
 						包含DataCollection,Element,Form,Window,Server类、String扩展、Array扩展以及一些公用函数
 						包含每个页面都可能用到的Menu控件，Dialog控件，Calendar控件
 Author		:	杨坤
 Email		:	jollys@163.com
 Date			:	2008-05-08	
*/
var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;

var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;

if(isGecko){
	HTMLElement.prototype.__defineSetter__("innerText",function(txt){this.textContent = txt;});
	Element.prototype.__defineGetter__("text",function(){return this.textContent;});
	HTMLElement.prototype.insertAdjacentElement=function(where,parsedNode){
		switch(where){
		  case "beforeBegin":
		    this.parentNode.insertBefore(parsedNode,this);
		    break;
		  case "afterBegin":
		    this.insertBefore(parsedNode,this.firstChild);
		    break;
		  case "beforeEnd":
		    this.appendChild(parsedNode);
		    break;
		  case "afterEnd":
		    if(this.nextSibling)
		      this.parentNode.insertBefore(parsedNode,this.nextSibling);
		    else
		      this.parentNode.appendChild(parsedNode);
		    break;
		  }
	};
	HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr){
		var r=this.ownerDocument.createRange();
		r.setStartBefore(this);
		var parsedHTML=r.createContextualFragment(htmlStr);
		this.insertAdjacentElement(where,parsedHTML);
	};
	HTMLElement.prototype.insertAdjacentText = function(where,txtStr){
		var parsedText=document.createTextNode(txtStr);
		this.insertAdjacentElement(where,parsedText);
	};
	HTMLElement.prototype.__defineSetter__("outerHTML",function(sHTML){
    var r=this.ownerDocument.createRange();
    r.setStartBefore(this);
    var df=r.createContextualFragment(sHTML);
    this.parentNode.replaceChild(df,this);
    return sHTML;
  });
  HTMLElement.prototype.__defineGetter__("outerHTML",function(){
    var attr;
		var attrs=this.attributes;
		var str="<"+this.tagName.toLowerCase();
		for(var i=0;i<attrs.length;i++){
		    attr=attrs[i];
		    if(attr.specified){
		        str+=" "+attr.name+'="'+attr.value+'"';
		    }
		}
		if(!this.canHaveChildren){
		    return str+">";
		}
		return str+">"+this.innerHTML+"</"+this.tagName.toLowerCase()+">";
  });
	HTMLElement.prototype.__defineGetter__("canHaveChildren",function(){
	  switch(this.tagName.toLowerCase()){
			case "area":
			case "base":
			case "basefont":
			case "col":
			case "frame":
			case "hr":
			case "img":
			case "br":
			case "input":
			case "isindex":
			case "link":
			case "meta":
			case "param":
			return false;
    }
    return true;
  });
  Event.prototype.__defineGetter__("srcElement",function(){
    var node=this.target;
    while(node.nodeType!=1)node=node.parentNode;
    return node;
  });
  HTMLElement.prototype.__defineGetter__("parentElement",function(){
		if(this.parentNode==this.ownerDocument)return null;
			return this.parentNode;
	});
}

function $(ele) {
    if (typeof(ele) == 'string'){
      ele = document.getElementById(ele)
      if(!ele){
    		return null;
      }
    }
    return ele;
}

function $V(objID){
	return Element.getValue(objID);
}

function $S(objID,v){
	return Element.setValue(objID,v);
}

function $A() {
	var results = [];
	for(var i=0;i<arguments.length;i++){
		results = results.concat(arguments[i]);
	}
	return results;
}

function $F(ele){
	return Form.$(ele);
}

function encodeURL(str){
	return encodeURI(str).replace(/=/g,"%3D").replace(/\+/g,"%2B").replace(/\?/g,"%3F");
}

function htmlEncode(str) {
	return str.replace(/&/g,"&amp;").replace(/\"/g,"&quot;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/ /g,"&nbsp;").replace(/&/g,"%26");
}

function isInt(str){
		var t = ""+str;
		for(var i=0;i<str.length;i++){
			if(isNaN(parseInt(str.charAt(i)))){
				return false;
			}
		}
		return true;
}

function isNumber(str){
		var t = ""+str;
		for(var i=0;i<str.length;i++){
			var chr = str.charAt(i);
			if(chr!="."&&chr!="E"&&isNaN(parseInt(chr))){
				return false;
			}
		}
		return true;
}

function getEvent(event){
	return isIE?window.event:event;
}

String.prototype.startWith = function(str) {
    return this.indexOf(str) == 0;
}

String.prototype.endWith = function(str) {
    return this.indexOf(str) == this.length - str.length;
}

String.prototype.escapeHTML = function() {
    var div = document.createElement('div');
    var text = document.createTextNode(this);
    div.appendChild(text);
    return div.innerHTML;
}

String.prototype.unescapeHTML = function() {
    var div = document.createElement('div');
    div.innerHTML = this.stripTags();
    return div.childNodes[0] ? div.childNodes[0].nodeValue : '';
}

String.prototype.toArray = function() {
    return this.split('');
}

String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
}

String.prototype.toQueryParams = function() {
  var pairs = this.match(/^\??(.*)$/)[1].split('&');
  var params = {};
  for(var i=0;i<pairs.length;i++){
    var pair = pairString.split('=');
    params[pair[0]] = pair[1];
  }
	return params;
}

String.prototype.leftPad = function(c,count){
	if(!isNaN(count)){
		var a = "";
		for(var i=this.length;i<count;i++){
			a = a.concat(c);
		}
		a = a.concat(this);
		return a;
	}
	return null;
}

String.prototype.rightPad = function(c,count){
	if(!isNaN(count)){
		var a = this;
		for(var i=this.length;i<count;i++){
			a = a.concat(c);
		}
		return a;
	}
	return null;
}

Array.prototype.last = function(){
	return this[this.length-1];
}	

Array.prototype.setLast = function(val){
	this[this.length-1] = val;
}	

Array.prototype.clone = function(){
	var len = this.length;
	var r = [];
	for(var i=0;i<len;i++){
		if(Util.isEmpty(this[i])){
			r[i] = this[i];
		}else if(this[i].constructor==Array){
			r[i] = this[i].clone();
		}else{
			r[i] = this[i];
		}
	}
	return r;
}	

Array.prototype.insert = function(index,data){
	if(isNaN(index) || index<0 || index>this.length) {
		this.push(data);
	}else{
		var temp = this.slice(index);
		this[index]=data;
		for (var i=0; i<temp.length; i++){
			this[index+1+i]=temp[i];
		}
	}
}

Array.prototype.clear = function() {
    this.length = 0;
    return this;
}

var Form = {};
Form.$ = function(ele){
	if(!ele)
		return document.forms[0];
	else{
		ele = $(ele);
		if(ele&&ele.tagName.toLowerCase()!="form")
			return null;
		return ele;
	}
}

Form.setValue = function(row,ele){
	ele = Form.$(ele);
	for(var i=0;i<ele.elements.length;i++){
		var id1 = ele.elements[i].id.toLowerCase();
		for(var prop in row){
			if(id1==prop.toLowerCase()){
				$S(ele.elements[i],row[prop]);
				break;
			}
		}
	}
}

Form.getData = function(ele){
	ele = Form.$(ele)
	if(!ele){
		alert("查找表单元素失败!"+ele);
		return;
	}
	var dc = new DataCollection();
	for(var i=0;i<ele.elements.length;i++){
		var ele1 = ele.elements[i];
		var ID = ele1.id;
		if(!ID){
			continue;	
		}
		dc.add(ele1.id,$V(ele1));
	}
	return dc;
}

Form.verify = function(ele){
	if(Page.VerifyTable){
		ele = Form.$(ele)
		if(!ele){
			alert("查找表单元素失败!"+ele);
			return false;
		}
		var eles = ele.elements;
		var values = Page.VerifyTable.Values;
		for(var i=0;i<eles.length;i++){
			for(var j=0;j<values.length;j++){
				if(values[j][0]==eles[i].id){
					if(!Element.verify(eles[i],values[j][1],values[j][2])){
						return false;
					}
					break;
				}
			}
		}
	}
	return true;
}

Form.reset = function(ele){
	ele = Form.$(ele)
	if(!ele){
		alert("查找表单元素失败!"+ele);
		return;
	}
	ele.reset();
}

Form.disable = function(ele) {
	ele = Form.$(ele)
	if(!ele){
		alert("查找表单元素失败!"+ele);
		return;
	}
  for (var i = 0; i < ele.elements.length; i++) {
    var ele1 = ele.elements[i];
    ele.element.blur();
    ele.element.disabled = 'true';
  }
}

Form.enable = function(ele) {
	ele = Form.$(ele)
	if(!ele){
		alert("查找表单元素失败!"+ele);
		return;
	}
  for (var i = 0; i < ele.elements.length; i++) {
    ele.elements[i].disabled = '';
  }
}

var Element = {};

Element.bindData = function(ele,dataTable){
	ele = $(ele);
	if(!ele){
		alert("Element.bindData:错误的目标控件!");
	}
	if(!dataTable){
		if(ele.DataTable){
			dataTable = ele.DataTable;
		}else{
			alert("Element.bindData:错误的数据源!");
		}
	}
  switch (ele.type.toLowerCase()) {
    case 'select-one':
    	for(var i=ele.options.length;i>0;i--){
    		ele.remove(i-1);
    	}
    	if(isIE){
    		ele.add(new Option("","",true,true));
    	}else{
    		ele.add(new Option("","",true,true),null);
    	}
    	for(var i=0;i<dataTable.Rows.length;i++){
	    	if(isIE){
	    		ele.add(new Option(dataTable.Values[i][1], dataTable.Values[i][0], false,false));
	    	}else{
	    		ele.add(new Option(dataTable.Values[i][1], dataTable.Values[i][0], false,false),null);
	    	}
    	}
    	ele["DataTable"] = dataTable;
			break;
  }
}

Element.visible = function(ele) {
	ele = $(ele);
  return ele.style.display != 'none';
}

Element.toggle = function(ele) {
	ele = $(ele);
	var inputType = ele.getAttribute("inputType");
	if(inputType=="Selector"||inputType=="DateSelector"){
		ele = $(ele.id+"_selector");
	}
  Element[Element.visible(ele) ? 'hide' : 'show'](ele);
}

Element.toString = function(ele) {
	ele = $(ele);
	var arr = [];
	for(var prop in ele){
		arr.push(prop+":"+ele[prop]);
	}
  return arr.join("\n");
}

Element.hide = function(ele) {
	ele = $(ele);
	var inputType = ele.getAttribute("inputType");
	if(inputType=="Selector"||inputType=="DateSelector"){
		ele = $(ele.id+"_selector");
	}
  ele.style.display = 'none';
}

Element.show = function(ele) {
	ele = $(ele);
	var inputType = ele.getAttribute("inputType");
	if(inputType=="Selector"||inputType=="DateSelector"){
		ele = $(ele.id+"_selector");
	}
  ele.style.display = '';
}

Element.remove = function(ele) {
	ele = $(ele);
	ele.parentNode.removeChild(ele);
}

Element.getHeight = function(ele) {
	ele = $(ele);
  return ele.offsetHeight;
}

Element.empty = function(ele) {
	ele = $(ele);
  return ele.innerHTML.match(/^\s*$/);
}

Element.verify = function(ele,fieldName,rule) {
	ele = $(ele);
	if(rule){
		var sqlFlag = true;
		var Features = rule.split("\&\&");
		var value = $V(ele);
		for(var i = 0; i < Features.length; i++) {
			var op = "=";
			if(Features[i].indexOf('>') > 0) {
				op = ">";
			}else if (Features[i].indexOf('<') > 0) {
				op = "<";
			}
			var f = Features[i].split(op);
			var fName = f[0];
			var fValue = null;
			if(f.length > 1) {
				fValue = f[1];
			}
			if(fName=="Any") {
				sqlFlag = false;
			}else if (fName=="NotNull") {
				if (!value&&value!=-1) {
					alert(fieldName+"不能为空");
					ele.focus();
					return false;
				}
			}else if (fName=="Code") {
				return true;
			}else if (fName=="Number") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				if(isNaN(value)){
					alert(fieldName+"必须是数字");
					ele.focus();
					return false;
				}
			}else if (fName=="Time") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				var timearr = value.split(":");
				if(timearr.length!=2){
						alert(fieldName+"的值"+value+"不是正确的时间!");
						ele.focus();
						return false;
				}else{
						if(!isInt(timearr[0])||timearr[0]<0||timearr[0]>23){
							alert(fieldName+"的值"+value+"错误，小时数"+timearr[0]+"不正确!");
							ele.focus();
							return false;
						}else	if(!isInt(timearr[1])||timearr[1]<0||timearr[1]>59){
							alert(fieldName+"的值"+value+"错误，分钟数"+timearr[1]+"不正确!");
							ele.focus();
							return false;
						}
				}
				return true;
			}else if (fName=="Int") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				if(isNaN(parseInt(value))){
					alert(fieldName+"必须是整数");
					ele.focus();
					return false;
				}
			}else if (fName=="Date") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				var t = $V(ele);
		    if(t){
	    		try{
		    	  var ts = t.split("-");
					  var d = new Date(ts[0],ts[1],ts[2]);
					}catch(ex){
						alert(fieldName+"必须是正确的日期");
						ele.focus();
						return false;
					}
		    }
			}else if(fName=="Email") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				//[a-zA-Z0-9_.]{1,}@[a-zA-Z0-9_]{1,}.[a-zA-Z0-9_]{1,}/;
				if(value&&value.match(pattern)==null){
				  alert(fieldName+"不是正确的电子邮箱地址");
				  ele.focus();
				  return false;
				}							
			}else if(fName=="Length") {
				if (value==null||value==undefined||value==""||typeof(value)=="undefined") {continue;}				
				if(isNaN(fValue)) {
					alert("校验规则错误，Length后面必须是数字");
					return false;
				}else{
					try{
						var len = parseInt(fValue);
						if(op=="="&&value.length!=len) {
						  alert(fieldName+"长度必须是" + len);
						  ele.focus();
						  return false;
						}else if (op==">"&&value.length<=len) {
							alert(fieldName+"长度必须大于" + len);
						  ele.focus();
						  return false;
						}else if (op=="<"&& value.length>=len) {
							alert(fieldName+"长度必须小于" + len);
						  ele.focus();
						  return false;
						}
					} catch (ex) {
						alert("校验规则错误，Length后面必须是整数"+ex.message);
						return false;
					}
				}
			}
		}
	}
  return true;
}

Element.getForm = function(ele) {
	ele = $(ele);
	if(isIE){
		ele = ele.parentElement;
	}else if(isGecko){
		ele = ele.parentNode;
	}
	if(!ele){
		return null;
	}
	if(ele.tagName.toLowerCase()=="form"){
		return ele
	}else{
		return Element.GetForm(ele);
	}
}

Element.scrollTo = function(ele) {
	ele = $(ele);
  var x = ele.x ? ele.x : ele.offsetLeft,
      y = ele.y ? ele.y : ele.offsetTop;
  window.scrollTo(x, y);
}

Element.getPosition = function(ele){
	ele = $(ele);
	var cX = 0,cY = 0;
	var oParent = ele;
	try{
		while(oParent&&oParent.tagName.toLowerCase()!="body"){//考虑父级元素的位置
			var tagName = oParent.tagName.toLowerCase();
	  	//if (tagName!="tr"&&tagName!="form"&&tagName!="span"&&tagName!="div"){
				cX += oParent.x ? oParent.x : oParent.offsetLeft;
				cY += oParent.y ? oParent.y : oParent.offsetTop;
			//}
			oParent = oParent.offsetParent;
		}
	}catch(ex){alert(oParent+","+ex.message);}
	cX = cX + document.body.offsetLeft;
	cY = cY + document.body.offsetTop;//考虑页边距'
	return {x:cX,y:cY};
}

Element.getDimensions = function(ele){
  ele = $(ele);
  if (Element.visible(ele)){
    return {width: ele.offsetWidth, height: ele.offsetHeight};
  }
  var els = ele.style;
  var originalVisibility = els.visibility;
  var originalPosition = els.position;
  els.visibility = 'hidden';
  els.position = 'absolute';
  els.display = '';
  var originalWidth = ele.clientWidth;
  var originalHeight = ele.clientHeight;
  els.display = 'none';
  els.position = originalPosition;
  els.visibility = originalVisibility;
  return {width: originalWidth, height: originalHeight};
}

Element.computePosition = function(ele1,ele2){//以ele1的右下角为顶点，尽可能地将ele2全部显示所需要的起始坐标
	ele1 = $(ele1);
	ele2 = $(ele2);
	var pos = Element.getPosition(ele1);
	//alert([pos.x,pos.y]);
	var cX = pos.x ;//+ ele1.offsetWidth;
	var cY = pos.y + ele1.offsetHeight;
	var wW = document.body.clientWidth,wH = document.body.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sW = document.body.scrollLeft,sH = document.body.scrollTop;//考虑滚动的情况
	if(wW<cX-sW+ele2.offsetWidth){
		cX -= ele2.offsetWidth;
	}
	if(wH<cY-sH+ele2.offsetHeight){
		cY = cY-ele2.offsetHeight-ele1.offsetHeight;
	}
	return {x:cX,y:cY};
}

Element.disable = function(ele) {
	ele = $(ele);
	if(ele.tagName.toLowerCase()=="form"){
		var elements = ele.elements;
		for (var i = 0; i < elements.length; i++) {
		  var element = elements[i];
		  ele.blur();
		  ele.disabled = 'true';
		}
	}else{
    ele.disabled = 'true';  	
	}
}

Element.enable = function(ele) {
	ele = $(ele);
	if(ele.tagName.toLowerCase()=="form"){
		var elements = ele.elements;
    for (var i = 0; i < elements.length; i++) {
      var element = elements[i];
      ele.disabled = '';
    }
	}else{
    ele.disabled = '';  	
	}
}

Element.setValue = function(ele,v) {
	ele = $(ele);
	if(!v&&v!=0){
		v = "";
	}
	var inputType = ele.getAttribute("inputType");
	if(inputType=="Selector"){
		if(v==""){
			$S(ele.id+"_name","")
			return true;
		}
		if(!ele.Data){
			var codeType = ele.getAttribute("code");
			if(codeType){
				var codedata = new CodeData(codeType);
				dataTable = codedata.getData();
				Selector.bindData(ele,dataTable);
			}
		}
		if(ele.Data){
			for(var i=0;i<ele.Data.Rows.length;i++){
				if(ele.Data.Values[i][0]==v){
					$S(ele.id+"_name",ele.Data.Values[i][1])
					break;
				}
			}
		}
	}
  switch (ele.type.toLowerCase()) {
    case 'submit':
    case 'hidden':
    case 'password':
    case 'textarea':
    case 'button':
    case 'file':
    case 'image':
    case 'text':
      ele.value = v;
      break;
    case 'checkbox':
    case 'radio':
      if(ele.value==""+v)ele.checked = true;
      break;
    case 'select-one':
			var value,options = ele.options, index = ele.selectedIndex;
			if (index >= 0) {
			  options[index].selected = false;
			}
			for(var i=0;i<options.length;i++){
				if(options[i].value==""+v){
					options[i].selected = true;
					break;
				}
			}
			break;
    default:
    		return false;
  }
  return true;
}

Element.getValue = function(ele) {
	ele = $(ele);
	if(!ele){
		alert("表单元素不存在:"+ele);
		return null;
	}
  switch (ele.type.toLowerCase()) {
    case 'submit':
    case 'hidden':
    case 'password':
    case 'textarea':
    case 'file':
    case 'image':
    case 'text':
      return ele.value;
    case 'checkbox':
    case 'radio':
      if (ele.checked){
    		return ele.value;
    	}else{
    		return "";
    	}
    case 'select-one':
			var value = false, opt, index = ele.selectedIndex;
			if (index >= 0) {
			  opt = ele.options[index];
			  value = opt.value;
			  if (!value && !('value' in opt))
			    value = opt.text;
			}
			return value;
    default:
    		return "";
  }
}

Element.initCtrlType = function(ele){
	ele = $(ele);
	var ctrltype = ele.getAttribute("ctrlType");
	if(ctrltype){
		if(ctrltype=="Calendar"){
	    ele.insertAdjacentHTML("afterEnd","<img src='"+Server.ContextPath+"framework/controls/Calendar.gif' align='absmiddle' vspace='1' onclick=\"Calendar.show('"+ele.id+"')\">");    
		}
		if(ctrltype=="DataGrid"){
			DataGrid.init(ele.id);
		}
		if(ctrltype=="Selector"){
			var code = ele.getAttribute("code");
			var strID = ele.id;
			ele.outerHTML = "<div class='selector' id='"+ele.id+"_selector' align='right'><input id='"+ele.id+"_name' type='text' readonly='true'><input inputType='Selector' id='"+ele.id+"' type='hidden'><img style='border-left-width: 1px;border-left-color: #ccc;background-color:#EEE' onClick=\"Selector.showList('"+ele.id+"');\" onMouseOver='Selector.onMouseOver(this);' onMouseOut='Selector.onMouseOut(this);' src='../framework/controls/select_arrow.gif' border='0' align='absmiddle'></div>";
			ele = $(strID);
			ele.setAttribute("code",code);
			ele.getSelectedRow = function(){
				var v = $V(this);
				if(!this.Data||!v){
					return null;
				}else{
					for(var i=0;i<this.Data.Rows.length;i++){
						if(this.Data.Values[i][0]==v){
							return this.Data.Rows[i];
							break;
						}
					}
				}
			}
		}
		if(ctrltype=="CodeSelector"){
			var code = ele.getAttribute("code");
			var strID = ele.id;
			ele.outerHTML = "<div class='selector' id='"+ele.id+"_selector' align='left'><input id='"+ele.id+"_name' type='text'><input id='"+ele.id+"' type='hidden'><img style='border-left-width: 1px;border-left-color: #ccc;background-color:#EEE' onClick=\"Selector.showList('"+ele.id+"');\" onMouseOver='Selector.onMouseOver(this);' onMouseOut='Selector.onMouseOut(this);' src='../framework/controls/select_arrow.gif' border='0' align='absmiddle'></div>";
			ele = $(strID);
			ele.setAttribute("code",code);
		}
		if(ctrltype=="DateSelector"){
			ele.outerHTML = "<div class='selector' id='"+ele.id+"_selector' align='left'><input id='"+ele.id+"' inputType='DateSelector' type='text'><img style='border-left-width: 1px;border-left-color: #ccc;background-color:#EEE' onClick=\"Calendar.show('"+ele.id+"');\" onMouseOver='Selector.onMouseOver(this);' onMouseOut='Selector.onMouseOut(this);' src='../framework/controls/select_arrow.gif' border='0' align='absmiddle'></div>";
		}
		if(ctrltype=="FileSelector"){
			var fileType = ele.getAttribute("fileType");
			if(!fileType){
				fileType="0";
			}
			ele.outerHTML = "<div id='"+ele.id+"_selector' align='left'><input id='"+ele.id+"' type='hidden'><input id='OldFileName_"+ele.id+"' type='hidden'>"
			+"<iframe style='width:300;height:22;' hspace=0 vspace=0 frameBorder=0 scrolling='no'  src='"+Server.ContextPath+"upload/uploadCtrl.jsp?FileType="+fileType+"&CtrlID="+ele.id+"' border=0></iframe></div>";
		}
	}
}
/*---------------------------Server-------------------------*/
var Server = {};
/*
Server.MainServletURL = "MainServlet.jsp";
Server.ContextPath = null;

Server.getXMLHttpRequest = function(){
	if (window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else if(window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

Server.loadURL = function(url){
	var Request = Server.getXMLHttpRequest();
	Request.open("GET", Server.ContextPath+url, false);
	Request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); 
	Request.send(null);
	return Request.responseText;
}

Server.getOneValue = function(methodName,dc){//dc既可是一个DataCollection，也可以是一个单值
	if(dc&&dc.prototype==DataCollection.prototype){
		Server.sendRequest(methodName,dc);
	}else{
		var dc1 = new DataCollection();
		dc1.add("_Param0",dc);
		Server.sendRequest(methodName,dc1);
	}
	var response = Server.getResponse();
	if(response){
		for(prop in response.map){
			return response.map[prop];
		}
	}
	return null;
}

Server.sendRequest = function(methodName,dataCollection,isNoModal,waitMsg){
	if(!isNoModal){

	}
	var Request = Server.getXMLHttpRequest();
	Request.open("POST", Server.MainServletURL, false);
	Request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); 
	var url = "Method="+methodName+"&Data=";
	if(dataCollection){
		//alert(htmlEncode(dataCollection.toXML()));
		url += encodeURL(htmlEncode(dataCollection.toXML()));
	}
	url += "&Url="+encodeURL(window.location.pathname);
	Request.send(url);
	var xmlDoc = Request.responseXML;
	if(xmlDoc){
		var dc = new DataCollection();
		dc.parseXML(xmlDoc);
		//特别处理
		if(dc.get("_RedirectUrl")){//跳转指令
			window.location = dc.get("_RedirectUrl");
			Server._ResponseDC = dc;
			return;
		}
		if(dc.get("_Script")){//直接传来JavaScript语句
			eval(dc.get("_Script"));
		}
		if(dc.get("_VerifyStatus")&&dc.get("_VerifyStatus")=="0"){//跳转指令
			alert(dc.get("_VerifyMessage"));
			Server._ResponseDC = null;
		}else{
			Server._ResponseDC = dc;
		}
	}
	return true;
}

Server.getResponse = function(){
	return Server._ResponseDC;
}
*/
/*---------------------------Page、User-------------------------*/
var User = {};

var Page = {};

function URLParams(){
	this.Params = [];
	
	URLParams.prototype.add = function(paramName,paramValue){
		this.Params.push([paramName,paramValue]);
	}
}

Page.onLoad = function(f){
	if(!Page.onLoadFunctions){
		Page.onLoadFunctions = [];
	}
	Page.onLoadFunctions.push(f);
}

Page.onClick = function(f){
	if(!Page.onClickFunctions){
		Page.onClickFunctions = [];
	}
	Page.onClickFunctions.push(f);
}

Page.redirect = function(url,params){
	var arr = [];
	arr.push(url);	
	if(params){
		for(var i=0;i<params.Params.length;i++){
			if(i==0){
				arr.push("?");
			}else{
				arr.push("&");
			}
			arr.push(params.Params[0]);
			arr.push("=");
			arr.push(params.Params[1]);
		}
	}
	window.location = arr.join('');
}

Page.beforeInit = function(){}

Page.init = function(){
	Page.beforeInit();
	Server.sendRequest("Framework.init",null);
	var dc = Server.getResponse();
	Server.ContextPath = dc.get("_ContextPath");
	Server.MainServletURL = Server.ContextPath + Server.MainServletURL
	for(var prop in dc.map){
		if(prop.startWith("User.")){
			User[prop.substring(5)] = dc.get(prop);
		}
	}
	Page.VerifyTable = dc.get("_VerifyTable");
	var eles = document.getElementsByTagName("*");
	for(var i=0;i<eles.length;i++){
		Element.initCtrlType(eles[i]);
	}

	if(Page.onLoadFunctions){
		for(var i=0;i<Page.onLoadFunctions.length;i++){
			Page.onLoadFunctions[i]();
		}
	}
	document.onclick = Page.click;
}

Page.click = function(event){
	if(Page.onClickFunctions){
		for(var i=0;i<Page.onClickFunctions.length;i++){
			Page.onClickFunctions[i](event);
		}
	}
}

//window.onload = Page.init;

/*---------------------------Selector、CodeData、CodeTable-------------------------*/
var Selector = {};

Selector.showList = function(ele){
	ele = $(ele);
	var codeType = ele.getAttribute("code");
	if(!ele.Data){
		var dataTable;
		if(codeType){
			var codedata = new CodeData(codeType);
			dataTable = codedata.getData();
			Selector.bindData(ele,dataTable);
		}
	}
	if(!ele.Data){
		return;
	}
	var divList = $("_DivSelectorList");
	if(!divList){
		divList = document.createElement("div");
	  divList.id = "_DivSelectorList";
	  divList.style.display = "none";
	  divList.className = "selector_list";
	  document.getElementsByTagName("body")[0].appendChild(divList);
	}
	dataTable = ele.Data;
	var html = [];
	html.push("<table id='_TableSelectorList' width='141' border='0' cellpadding='0' cellspacing='0'>");
	for(var i=0;i<dataTable.Rows.length;i++){
		html.push("<tr onclick=\"Selector.setValue('"+ele.id+"',"+i+");\" onmouseover='Selector.onListItemMouseOver(this);' onmouseout='Selector.onListItemMouseOut(this);' valign='middle'>");
		html.push("<td nowrap>");
		html.push(dataTable.Values[i][1]);
		html.push("</td></tr>");
	}
	html.push("</table>");
	divList.innerHTML = html.join('');
	var wH = document.body.clientHeight
	divList.style.display = '';
	var ds = Element.getDimensions("_TableSelectorList");
	if(ds.height>wH/2){
		divList.style.height = wH/2;
	}else{
		divList.style.height = $("_TableSelectorList").offsetHeight+10;
	}
	divList.style.width = ds.width+20;
	var pos = Element.computePosition(ele.id+"_selector",divList);
	//alert([pos.x,pos.y]);
	divList.style.left = pos.x;   
	divList.style.top = pos.y+11; 
	divList.ForControl = ele.id;
}

Selector.setValue = function(ele,rowIndex){
	ele = $(ele);
	$S(ele.id+"_name",ele.Data.Values[rowIndex][1]);
	$S(ele,ele.Data.Values[rowIndex][0]);
	Element.hide("_DivSelectorList");
	try{
		afterSelect(ele);
	}catch(ex){}
}

Selector.onMouseOver = function(ele){
	ele = $(ele);
	ele.style.backgroundColor='#E3E3E3';
}

Selector.onMouseOut = function(ele){
	ele = $(ele);
	ele.style.backgroundColor='#EEE';
}

Selector.onListItemMouseOver = function(ele){
	ele = $(ele);
	ele.style.backgroundColor='#E3E3E3';
}

Selector.onListItemMouseOut = function(ele){
	ele = $(ele);
	ele.style.backgroundColor='#FFF';
}

Selector.bindData = function(ele,dataTable){
	ele = $(ele);
	ele.Data = dataTable;
}

Selector.closeList = function(){
	Element.hide("_DivSelectorList");
}

Page.onClick(function(event){
	var ele = $("_DivSelectorList");
	if(!ele){
		return;
	}
	if(Element.visible(ele)){
		var evt = getEvent(event);
		var pos = Element.getPosition(ele);
		var eX = document.body.scrollLeft+evt.clientX;
		var eY = document.body.scrollTop+evt.clientY;
		if(!(pos.x<eX&&pos.y<eY&&eX<pos.x+ele.offsetWidth&&eY<pos.y+ele.offsetHeight)){
			var ctrl = $(ele.ForControl+"_selector");
			pos = Element.getPosition(ctrl);
			if(!(pos.x<eX&&pos.y<eY&&eX<pos.x+ctrl.offsetWidth&&eY<pos.y+ctrl.offsetHeight)){
				Element.hide("_DivSelectorList");
			}
		}
	}
});

var CodeSelect = {};

CodeSelect.showCodeList = function(ctrlID){
	if(!$("_DivCodeList")){
		var s = document.createElement("span");
	  s.id = "_DivCodeList";
	  s.style.display = "none";
	  s.style.position = "absolute";
	  s.style.color = "slategray";
	  s.style.border = "1px solid #6FAB51";
	  //s.onmouseout = closeCodeList;
	  document.getElementsByTagName("body")[0].appendChild(s);
	}
}

function CodeData(codeType){
	this.CodeType = codeType;
	this.Conditions = {};
	this.Data = null;
	
	CodeData.prototype.setConditionField = function(fieldName,fieldValue){
		this.Conditions[fieldName] = fieldValue
	}
	
	CodeData.prototype.getData = function(){
		if(this.Data){
			return this.Data;
		}else{
			var dc = new DataCollection();
			dc.add("_CodeType",this.CodeType);
			for(var prop in this.Conditions){
				dc.add(prop,this.Conditions[prop]);
			}
			Server.sendRequest("Framework.getCodeData",dc);
			var response = Server.getResponse();
			this.Data = response.get("CodeData");
			return this.Data;
		}
	}
}

/*---------------------------DataGrid-------------------------*/
var DataGrid = {};

DataGrid.PropCollection = {};

DataGrid.DefaultLineCount = 10;

DataGrid.init = function(ctrlID){
	ele = $(ctrlID);
	var len = ele.rows.length;
	for(var i=1;i<len;i++){
		if(i>1){
			ele.deleteRow(2);
			continue;
		}
	}
	ele.rows[1].style.display = 'none';
	
	if(ele.getAttribute("canSelect")=="true"){
		ele.rows[0].insertCell(0).innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		ele.rows[1].insertCell(0).innerHTML = "<input type='radio' name='"+ele.id+"_Radio' id=\""+ele.id+"_Radio{'_RowIndex'}\" value=''>";
	}
	var initLineCount = ele.getAttribute("initLineCount");
	if(!isNaN(initLineCount)){
		DataGrid.fillEmptyLine(ctrlID,initLineCount)
	}
	ele = $(ctrlID);
	DataGrid.PropCollection[ele.id] = {};
	var autoPaging = ele.getAttribute("autoPaging");
	ele.PageIndex = 0;
	ele.PageSize = DataGrid.DefaultLineCount;
	DataGrid.PropCollection[ele.id]["PageSize"]=ele.PageSize;
	DataGrid.PropCollection[ele.id]["PageIndex"]=ele.PageIndex;
	if(autoPaging=="true"){
		DataGrid.addPagingRow(ele.id);
		ele = $(ctrlID);
	}
	DataGrid.bindEvent(ele);
}

DataGrid.fillEmptyLine = function(ctrlID,initLineCount){
	var table = $(ctrlID);
	var tableHtml = table.outerHTML;
	var tableHead = tableHtml.substring(0,tableHtml.toLowerCase().indexOf("</tbody>"));
	var tableTail = "</table>";
	var templateTRHtml = table.rows[1].outerHTML;
	var arr = [];
	arr.push(tableHead);
	var emptyLineCount = initLineCount-table.rows.length+2;
	for(var i=0;i<emptyLineCount;i++){
		arr.push(templateTRHtml);	
	}
	arr.push("</tbody>");
	arr.push(tableTail);
	table.outerHTML = arr.join('');
	table = $(ctrlID);
	for(var i=initLineCount - emptyLineCount + 2;i<table.rows.length;i++){
		table.rows[i].style.display = '';
		for(var j=0;j<table.rows[1].cells.length;j++){
			table.rows[i].cells[j].innerHTML = "&nbsp;";
		}
	}
}

DataGrid.addPagingRow = function(ctrlID){
		var table = $(ctrlID);
		var tableHtml = table.outerHTML;
		var tableHead = tableHtml.substring(0,tableHtml.toLowerCase().indexOf("</tbody>"));
		var tableTail = "</tbody></table>";
		var trHtml = table.rows[1].outerHTML;
		var trHead = trHtml.substring(0,trHtml.indexOf(">")+1);
		var trTail = "</tr>";
		var trCell = table.rows[1].cells[0].outerHTML;
		var arr = [];
		arr.push(tableHead);
		arr.push(trHead);
		arr.push(trCell);
		arr.push(trTail);
		arr.push(tableTail);
		table.outerHTML = arr.join('');
		table = $(ctrlID);
		var row = table.rows[table.rows.length-1];
		row.style.display = '';
		var cell = row.cells[0];
		cell.align = "center";
		cell.height = "23px";
		cell.colSpan = table.rows[1].cells.length;
		arr = [];
		arr.push("<a href='#' onclick=\"$('"+table.id+"').firstPage();\">第一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		arr.push("<a href='#' onclick=\"$('"+table.id+"').previousPage();\">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		arr.push("<a href='#' onclick=\"$('"+table.id+"').nextPage();\">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		arr.push("<a href='#' onclick=\"$('"+table.id+"').lastPage();\">最末页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		cell.innerHTML = arr.join('');
		
}

DataGrid.bindData = function(ctrlID,dataSrc){
	var table = $(ctrlID);
	var tableHtml = table.outerHTML;
	var tableHead = tableHtml.substring(0,tableHtml.indexOf(">")+1);
	var tableTail = "</table>";
	var templateTRHtml = table.rows[1].outerHTML;
	var arr = [];
	arr.push(tableHead);
	arr.push("<tbody>");
	arr.push(table.rows[0].outerHTML);
	arr.push(templateTRHtml);
	
	//解析占位符
	var reg = /(\{\'.*?\'\})|(\{\-?\d{1,2}\})/
	var str = templateTRHtml;
	var fields = [];
	var others = [];
	var lastIndex = 0;
	while(reg.exec(str)){
		var f,index;
		if(RegExp.$1){
			f = RegExp.$1;
		}else if(RegExp.$2){
			var f = RegExp.$2;
		}else{
			break;
		}
		index = str.indexOf(f);
		fields.push(f.substring(1,f.length-1));
		others.push(str.substring(lastIndex,index));
		lastIndex = index;
		str = str.replace(f,'');
	}
	others.push(str.substring(lastIndex));
	
	//将名称转换为索引
	for(var i=0;i<fields.length;i++){
		var field = fields[i].toLowerCase();
		if(field.startWith("'")){
			field = field.substring(1,field.length-1);
			for(var j=0;j<dataSrc.Columns.length;j++){
				if(field=="_rowindex"){
					fields[i] = -2;
				}
				if(dataSrc.Columns[j].Name.toLowerCase()==field){
					fields[i] = j;
					break;
				}
			}
		}
	}
	//alert([table.PageSize,table.PageIndex]);
	for(var i=0;i<dataSrc.Rows.length;i++){
		for(var j=0;j<others.length-1;j++){
			arr.push(others[j]);
			var colIndex = fields[j];
			var v;
			if(colIndex==-1){
				v = table.PageSize*table.PageIndex+i+1;
			}else if(colIndex==-2){
				v = i;
			}else{
			 	v = dataSrc.Values[i][colIndex];
				if(v!=0&&!v){
					v = "&nbsp;";
				}
			}
			arr.push(v);
		}
		arr.push(others.last());
	}
	arr.push("</tbody>");
	arr.push(tableTail);
	table.outerHTML = arr.join('');
	
	table = $(ctrlID);
	var initLineCount = table.getAttribute("initLineCount");
	if(!isNaN(initLineCount)){
		DataGrid.fillEmptyLine(ctrlID,initLineCount)
	}
	var autoPaging = table.getAttribute("autoPaging");
	if(autoPaging=="true"){
		DataGrid.addPagingRow(table.id);
		table = $(ctrlID);
	}

	table = $(ctrlID);
	
	var len = table.rows.length;
	for(var i=2;i<len;i++){
		table.rows[i].style.display = '';
	}
	table.setAttribute("isDataLoaded",true);
	table["_DataTable"] = dataSrc;
	DataGrid.bindEvent(table);
}

DataGrid.clear = function(ele){
		ele = $(ele);
		var len = ele.rows.length;
		for(var i=2;i<len;i++){
			ele.deleteRow(2);
		}
}

DataGrid.getDataTable = function(ele){
	ele = $(ele);
	return ele["_DataTable"];
}

DataGrid.bindEvent = function(ele){
	ele = $(ele);
	ele.bindData = function(dataSrc){
		if(dataSrc){
			DataGrid.bindData(ele.id,dataSrc);
			return true;
		}else{//自动分页的情况
			var dataEvent = ele.getAttribute("dataEvent");
			if(dataEvent){
				var dc = ele.Conditions;
				dc.add("_PageSize",ele.PageSize);
				dc.add("_PageIndex",ele.PageIndex);
				Server.sendRequest(dataEvent,dc);
				var response = Server.getResponse();
				for(var prop in response.map){
					var vtype = response.valuetype[prop];
					if(vtype=="DataTable"||vtype=="Schema"||vtype=="SchemaSet"){
						var lastPageIndex = response.get("_LastPageIndex");
						if(ele.PageIndex==-1){
							ele.PageIndex = lastPageIndex;
							DataGrid.PropCollection[ele.id]["PageIndex"] = ele.PageIndex;
							ele.MaxPageIndex = ele.PageIndex;
							DataGrid.PropCollection[ele.id]["MaxPageIndex"] = ele.MaxPageIndex;
						}else{//点下一页到达最后一页
							if(lastPageIndex){
								ele.MaxPageIndex = ele.PageIndex;
								DataGrid.PropCollection[ele.id]["MaxPageIndex"] = ele.MaxPageIndex;
							}
						}
						DataGrid.bindData(ele.id,response.map[prop]);
						return true;
					}
				}
				return false;
			}else{
				alert("DataGrid绑定数据失败，未指定eventHandler");
				return true;
			}
		}
	}
	
	ele.getDataTable = function(){
		return DataGrid.getDataTable(ele.id);
	}
	
	ele.setConditions = function(dc){//重新设置条件，分页重新算起
		ele.Conditions = dc;
		ele.PageIndex = 0;
		ele.PageSize = DataGrid.DefaultLineCount;
		DataGrid.PropCollection[ele.id]["PageSize"]=ele.PageSize;
		DataGrid.PropCollection[ele.id]["PageIndex"]=ele.PageIndex;
		DataGrid.PropCollection[ele.id]["Conditions"] = dc;
	}
	
	ele.setPageSize = function(pagSize){
		ele.PageSize = pagSize;
		DataGrid.PropCollection[ele.id]["PageSize"] = pagSize;
	}
	
	ele.firstPage = function(){
		if(!$(ele.id).getDataTable()){
			alert("请先查询!");
			return;
		}
		if(ele.PageIndex!=0){
			ele.PageIndex = 0;
		}else{
			return;
		}
		DataGrid.PropCollection[ele.id]["PageIndex"] = ele.PageIndex;
		$(ele.id).bindData();
	}
	
	ele.previousPage = function(){
		if(!$(ele.id).getDataTable()){
			alert("请先查询!");
			return;
		}
		if(ele.PageIndex>0){
			ele.PageIndex--;
		}else{
			return;
		}
		DataGrid.PropCollection[ele.id]["PageIndex"] = ele.PageIndex;
		$(ele.id).bindData();
	}
	
	ele.nextPage = function(){
		if(!$(ele.id).getDataTable()){
			alert("请先查询!");
			return;
		}
		if(ele.MaxPageIndex){
			if(ele.PageIndex<ele.MaxPageIndex){
				ele.PageIndex++;
			}else{
				return;
			}
		}else{
			ele.PageIndex++;
		}
		DataGrid.PropCollection[ele.id]["PageIndex"] = ele.PageIndex;
		$(ele.id).bindData();
	}
	
	ele.lastPage = function(){
		if(!$(ele.id).getDataTable()){
			alert("请先查询!");
			return;
		}
		if(ele.MaxPageIndex){
			if(ele.PageIndex!=ele.MaxPageIndex){
				ele.PageIndex = -1;
			}else{
				return;
			}
		}else{
			ele.PageIndex = -1;
		}
		DataGrid.PropCollection[ele.id]["PageIndex"] = ele.PageIndex;
		$(ele.id).bindData();
	}
	
	ele.clear = function(){
		DataGrid.clear(ele.id);
	}
	
	//需重新得到值，因为对象引用已经改变
	var dgProps = DataGrid.PropCollection[ele.id];
	for(var prop in dgProps){
		ele[prop] = dgProps[prop];
	}
	
	if(ele.getAttribute("canSelect")=="true"&&ele.getAttribute("isDataLoaded")){
		var radios = document.getElementsByName(ele.id+"_Radio");
		for(var i=2;i<ele.rows.length&&i<=radios.length;i++){
			//alert(radios[i-1].outerHTML);
			radios[i-1].onclick = function(event){
				event = getEvent(event);
				//alert(event.srcElement.id);
				DataGrid.onRadioClick(ele.id,event.srcElement.id);
			}
		}
	}
}

DataGrid.onRadioClick = function(ele,radio){
	ele = $(ele);
	radio = $(radio);
	eval("var func = on"+ele.id+"RadioClick;");
	var len = (ele.id+"_Radio").length;
	var rowIndex = radio.id.substring(len);
	if(func){
		func(rowIndex);
	}
}

/*---------------------------Data-------------------------*/
function DataCollection(){
	this.map = {};
	this.valuetype = {};
	
	DataCollection.prototype.get = function(ID){
		var o = this.map[ID];
		if(o){
			return o;
		}else{
			return null;
		}
	}
	
	DataCollection.prototype.parseXML = function(xmlDoc){
		var coll = xmlDoc.documentElement;
		if(!coll){
			alert("解析DataCollection错误，传入的XML字符串为空!");
			return;
		}
		var nodes = coll.childNodes;
		for(var i=0;i<nodes.length;i++){
			var node = nodes[i];
			var Type = node.getAttribute("Type");
			var ID = node.getAttribute("ID");
			this.valuetype[ID] = Type;
			if(Type=="String"){
				this.map[ID] = node.text;
			}else if(Type=="Int"||Type=="Long"||Type=="Float"||Type=="Double"){
				this.map[ID] = node.getAttribute("Value");
			}else if(Type=="IntArray"||Type=="LongArray"||Type=="FloatArray"||Type=="DoubleArray"){
				var v = node.getAttribute("Value");
				v = "var _TMP = ["+v+"]";
				eval(v);
				v = _TMP;
				this.map[ID] = v;
			}else if(Type=="StringArray"){
				var v = node.text;
				v = "var _TMP = ["+v+"]";
				eval(v);
				v = _TMP;
				this.map[ID] = v;
			}else if(Type=="DataTable"){
				this.parseDataTable(node,"DataTable");
			}else if(Type=="Schema"){
				this.parseDataTable(node,"Schema");
			}else if(Type=="SchemaSet"){
				this.parseDataTable(node,"SchemaSet");
			}
		}
	}
	
	DataCollection.prototype.parseDataTable = function(node,strType){
		var cols = node.childNodes[0].text;
		cols = "var _TMP1 = "+cols+"";
		eval(cols);
		cols = _TMP1;
		var values = node.childNodes[1].text;
		values = "var _TMP2 = "+values+"";
		eval(values);
		values = _TMP2;
		var obj;
		if(strType=="DataTable"||strType=="SchemaSet"||strType=="Schema"){//暂不区分
			obj = new DataTable();
			obj.init(cols,values);
		}
		this.map[node.getAttribute("ID")] = obj;
	}
	
	DataCollection.prototype.toXML = function(){
		var arr = [];
		arr.push("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if(isIE){
			var doc = new ActiveXObject("MSXML2.DOMDocument");
			var coll = doc.createElement("collection");
			for(var prop in this.map){
				try{
					var ele = doc.createElement("element");
					ele.setAttribute("ID",prop);
					ele.text = this.map[prop];
					ele.setAttribute("Type",this.valuetype[prop]);
					coll.appendChild(ele);
				}catch(ex){alert("DataCollection.toXML():"+prop+","+ex.message);}
			}
			doc.appendChild(coll);
			arr.push(doc.xml);
		}else if(isGecko){
			arr.push("<collection>");
			for(var ID in this.map){
				try{
					arr.push("<element ID=\""+ID+"\" Type=\""+this.valuetype[ID]+"\">");
					arr.push(this.map[ID]);
					arr.push("</element>");
				}catch(ex){alert("DataCollection.toXML():"+ID+","+ex.message);}
			}
			arr.push("</collection>");
		}
		//alert(arr.join(''));
		return arr.join('');
	}
	
	DataCollection.prototype.toString = function(){
		return this.toXML();
	}

	DataCollection.prototype.add = function(ID,Value){
		this.addString(ID,Value);
	}
	
	DataCollection.prototype.addString = function(ID,Value){
		this.map[ID] = Value;
		this.valuetype[ID] = "String";
	}
	
	DataCollection.prototype.addStringArray = function(ID,Value){
		
	}
	
	DataCollection.prototype.addDataTable = function(ID,Value){
		
	}
}

function DataTable(){
	this.Columns = null;
	this.Values = null;
	this.Rows = null;
	
	DataTable.prototype.init = function(cols,values){
		this.Values = values;
		this.Columns = [];
		for(var i=0;i<cols.length;i++){
			var col = {};
			col.Name = cols[i][0];
			col.Type = cols[i][1];
			this.Columns[i] = col;
		}
		//生成各个行对象
		this.Rows = [];
		for(var i=0;i<values.length;i++){
			var row = {};
			for(var j=0;j<this.Columns.length;j++){
				row[this.Columns[j].Name] = values[i][j]; 
			}
			row.get = function(str){
				str = str.toLowerCase();
				for(var prop in row){
					if(prop.toLowerCase()==str){
						return this[prop];
						break;
					}
				}
			}
			this.Rows[i] = row;
		}
	}
	
	DataTable.prototype.get = function(i,j){
		return this.Values[i][j];
	}
}
/*---------------------------Window-------------------------*/
var Window = {};


/*---------------------------Control-------------------------*/
var Control = {};
Control.showModalDialog = function(msg,w,h,x,y){
	var d = new Dialog();
	d.Width = 400;
	d.Height = 300;
	d.Title = "提示信息";
	d.Message = msg;
	if(x){
		d.Left = x;
	}else{
		
	}
	if(y){
		d.Top = y;	
	}
	d.show();
}

Control.showCalendar = function(ele){
	Calendar.show(ele);
}

/*---------------------------Dialog-------------------------*/
function Dialog(strID){
	var CSSFile = "framework/controls/Dialog.css";
	if(!strID){
		alert("错误的Dialod ID！");
		return;
	}
	this.ID = strID;
	this.Width = 0;
	this.Height = 0;
	this.Top = 0;
	this.Left = 0;
	
	this.Title = null;
	this.Message = null;
	this.URL = null;
	this.isModalDialog = true;
	this.isDarging = false;
	this.LastX = 0;
	this.LastY = 0;
	
	this.Ele = document.createElement("div");
	
	this.ModalLayer;
	
	Dialog.prototype.show = function(){
		if(!window[CSSFile]){
			var cssEle = document.createElement('link');		
			cssEle.rel	= 'stylesheet' ;
			cssEle.type	= 'text/css' ;
			cssEle.href = Server.ContextPath+CSSFile ;
			document.getElementsByTagName("head")[0].appendChild(cssEle) ; 
			window[CSSFile] = true;
		}
		if(this.isModalDialog){
			if($("_ModalLayer")){
				this.ModalLayer = $("_ModalLayer");
				Element.show(this.ModalLayer);
			}else{
				this.ModalLayer = document.createElement("div")
				this.ModalLayer.style.position = "absolute";
				this.ModalLayer.id = "_ModalLayer";
				this.ModalLayer.style.width = "100%";
				this.ModalLayer.style.height = "100%";			
				this.ModalLayer.style.top = 0;
				this.ModalLayer.style.left = 0;	
				this.ModalLayer.style.zIndex = 9998;			
				this.ModalLayer.onselectstart = function(){return false;};
				this.ModalLayer.oncontextmenu = function(){return false;};
				this.ModalLayer.innerHTML = "<img src='"+Server.ContextPath+"framework/controls/none.gif' width='100%' height='100%'>";
				document.body.appendChild(this.ModalLayer);
			}
		}
		this.Ele.Instance = this;
		this.Ele.style.position = "absolute";
		this.Ele.style.width = this.Width+"px";
		this.Ele.style.height = this.Height+"px";
		if(this.Top){
			this.Ele.style.top = this.Top+"px";
		}else{
			var ch = isIE?parent.document.getElementById(frameElement.id).height:window.innerHeight;
			this.Ele.style.top = (ch/2 - this.Height/2)+"px";
		}
		if(this.Left){
			this.Ele.style.left = this.Left+"px";
		}else{
			var cw = isIE?parent.document.getElementById(frameElement.id).width:window.innerWidth;
			this.Ele.style.left = (cw/2 - this.Width/2)+"px";
		}
		this.Ele.id = this.ID;
		this.Ele.style.zIndex = 9999;			
		var MessageFlag = true;
		if(this.URL){
			this.Message =  Server.loadURL(this.URL);
			MessageFlag = false;
		}
		var html = [];
		html.push("<table id='DialogTable"+this.ID+"' width='100%' cellpadding='0' cellspacing='0' class='DialogOutBorder'>");
		html.push("  <tr>");
		html.push("    <td bgcolor='#FFFFFF'><table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' class='DialogInBorder'>");
		html.push("      <tr>");
		html.push("        <td>");
		html.push("          <table width='100%' border='0' cellpadding='0' cellspacing='0' class='DialogContentBorder'>");
		html.push("                <tr>");
		html.push("				<td height='18' class='DialogTitle' onMouseDown=\"$('"+this.ID+"').Instance.onMouseDown();\" onMouseMove=\"$('"+this.ID+"').Instance.onMouseMove();\" onMouseUp=\"$('"+this.ID+"').Instance.onMouseUp();\">");
		html.push("				<img src='"+Server.ContextPath+"framework/controls/TitleBarBg.gif' width='"+this.Width+"' height='18' /></td>");
		html.push("                </tr>");
		html.push("                <tr>");
		html.push("                  <td height='100' align='center' style='background-color:#E4E4E4'><table width='95%' border='0' cellspacing='0' cellpadding='0'>");
		html.push("                    <tr>");
		html.push("                      <td align='center' id='DialogTD"+this.ID+"'>"+this.Message+"</td>");
		html.push("                    </tr>");
		html.push("                  </table></td>");
		html.push("                </tr>");
		if(MessageFlag){
			html.push("                <tr>");
			html.push("                  <td height='38' align='right' bgcolor='#D4D0C8'><label>");
			html.push("                    <input type='submit' name='Submit' value='   关闭   ' />");
			html.push("                  &nbsp;</label></td>");
			html.push("            </tr>");
		}
		html.push("          </table>");
		html.push("          </td>");
		html.push("      </tr>");
		html.push("    </table></td>");
		html.push("  </tr>");
		html.push("</table>");
		html.push("<div id='divTitle"+this.ID+"'>");
		html.push("<span id='spanTitle"+this.ID+"' style='color: #FFFFFF'>&nbsp;"+this.Title+"</span>");
		html.push("</div>");
		html.push("<div id='divClose"+this.ID+"' >");
		html.push("<input type='image' onclick=\"$('"+this.ID+"').Instance.Close();\" src='"+Server.ContextPath+"framework/controls/Close.gif' width='16' height='14'/>");
		html.push("</div>");		
		this.Ele.innerHTML = html.join('');
		document.body.appendChild(this.Ele);
					
		var ele = $("divTitle"+this.ID);
		ele.style.position = "absolute";
		ele.style.top = 5+"px";
		ele.style.left = 6+"px";		
		ele.style.zIndex = 9999;			
		ele = $("divClose"+this.ID);
		ele.style.position = "absolute";
		ele.style.top = 4+"px";
		ele.style.left = (Element.getDimensions("DialogTable"+this.ID).width-19)+"px";		
		ele.style.zIndex = 9999;			

	}
	
	Dialog.prototype.Close = function(){
			if($("_ModalLayer")){
				Element.hide($("_ModalLayer"));
			}
			this.Ele.outerHTML = "";
	}
	
	Dialog.prototype.onMouseDown = function(event){
		var evt = getEvent(event);
		var intTop = evt.clientY;
		var intLeft = evt.clientX;
		
		var pos = Element.getPosition(this.Ele);
		this.LastX = evt.clientX-pos.x;
		this.LastY = evt.clientY-pos.y + 12;
		this.isDarging = true;
	}
	
	Dialog.prototype.onMouseMove = function(event){
		if(!this.isDarging ){
			return;
		}
		var evt = getEvent(event);
				
		var intTop = evt.clientY + document.body.scrollTop;
		var intLeft = evt.clientX + document.body.scrollLeft;
		
		var cx=0,cy=0;
		var elCurrent = this.Ele.offsetParent;
		if (elCurrent != null){
	    while (elCurrent.offsetParent != null) {
	            cx += elCurrent.offsetTop;
	            cy += elCurrent.offsetLeft;
	            elCurrent = elCurrent.offsetParent;
	    }
		}
    this.Ele.style.pixelTop  = intTop - cy - this.LastY;
    this.Ele.style.pixelLeft = intLeft - cx - this.LastX;

		evt.cancelBubble = false;
		evt.returnValue = false;
	}
	
	Dialog.prototype.onMouseUp = function(event){
		this.isDarging = false;
	}
}
/*---------------------------Control-------------------------*/
function _LeftPad(str,c,count){
	str = ""+str;
	return str.leftPad(c,count);
}

var Calendar = {
	cssFile : "framework/controls/Calendar.css",
	monthNames : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
	weekNames : ["日","一","二","三","四","五","六"],
	control : null,
	isMouseOut : false
}

Calendar.initHtml = function(){
	var html=[];
	html.push("<table id='_Calendar_Table' cellspacing=0 cellpadding=0 width=190px onselectstart='return false;' oncontextmenu='return false'>");
	html.push("<thead><tr><td colspan=7>");
	html.push("<table width='100%' class='nostyle' cellspacing=0 cellpadding=0>");
	html.push("<tr height=20px>");
	html.push("<td onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.previousYear();' class='button' width=12px>&#8249;</td>");
	html.push("<td class=title>");
	html.push("<div id=_Calendar_Year onclick='Calendar.showYearSelector();'  style='width:63px'>2006</div>");
	html.push("<select id='_Calendar_YearSelector' onblur='Calendar.hideYearSelector()' onchange='Calendar.onYearSelectorChange()' style='font-size:11px;display:none;width:63px;'></select></td>");
	html.push("<td onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.nextYear();' class='button' width=12px>&#8250;</td>");
	html.push("<td onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.previousMonth();' class='button' width=12px>&#8249;</td>");
	html.push("<td class=title>");
	html.push("<div id=_Calendar_Month onclick='Calendar.showMonthSelector();' style='width:63px'>十二月</div>");
	html.push("<select id='_Calendar_MonthSelector' onblur='Calendar.hideMonthSelector()' onchange='Calendar.onMonthSelectorChange()' style='font-size:11px;display:none;width:63px;'>");
	for(var i=0;i<Calendar.monthNames.length;i++)
		html.push("<option value='"+i+"'>"+Calendar.monthNames[i]+"</option>");
	html.push("</select></td>");
	html.push("</td>");
	html.push("<td  onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.nextMonth();' class='button' width=12px>&#8250;</td>");
	html.push("<td class=buttonclose width=16px onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.hide();'>×</td>");
	html.push("<tr></table></td></tr>");
	html.push("<tr class=daynames>");
	html.push("<td class='name weekend'>"+Calendar.weekNames[0]+"</td>");
	html.push("<td class='name'>"+Calendar.weekNames[1]+"</td>");
	html.push("<td class='name'>"+Calendar.weekNames[2]+"</td>");
	html.push("<td class='name'>"+Calendar.weekNames[3]+"</td>");
	html.push("<td class='name'>"+Calendar.weekNames[4]+"</td>");
	html.push("<td class='name'>"+Calendar.weekNames[5]+"</td>");
	html.push("<td class='name weekend'>"+Calendar.weekNames[6]+"</td>");
	html.push("</tr></thead>");
	html.push("<tbody id='_Calendar_Body'>");
	for(var i=0;i<6;i++){
		html.push("<tr id=_Calendar_DayRow"+i+" class=daysrow>");
		for(var j=0;j<7;j++)
			html.push("<td id=_Calendar_Day"+(i*7+j)+" onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.returnDate(this);' class='day'>&nbsp;</td>");
		html.push("</tr>");
	}
	html.push("</tbody><tfoot><tr class=footrow>");
	html.push("<td id=_Calendar_Today onmouseover='Calendar.onMouseOver(this);' onmouseout='Calendar.onMouseOut(this);' onclick='Calendar.returnDate(this);' class=buttontoday colspan=2>今日</td>");
	html.push("<td id=_Calendar_Tip colspan=5 class=tiptoday align=center>&nbsp;</td>");
	html.push("</tr></tfoot></table>");
	Page.onClick(function(event){
	    if(Calendar.isMouseOut) Calendar.hide();
	});
	return html.join('');
}
	
Calendar.showYearSelector = function(){
	var eleYear = $("_Calendar_Year"),eleSelector = $("_Calendar_YearSelector");
	eleYear.style.display = 'none';
	eleSelector.style.display = '';
	var year = eleYear.Year;
	for(var i=year>50?year-50:0;i<=50+parseInt(year);i++){
		eleSelector.options.add(new Option(i, i));
	}
	eleSelector.focus();
	eleSelector.selectedIndex = 50;
}

Calendar.showMonthSelector = function(){
	var eleMonth = $("_Calendar_Month"),eleSelector = $("_Calendar_MonthSelector");
	eleMonth.style.display = 'none';
	eleSelector.style.display = '';
	eleSelector.focus();
	eleSelector.selectedIndex = eleMonth.Month;
}

Calendar.hideYearSelector = function(){
	var eleYear = $("_Calendar_Year"),eleSelector = $("_Calendar_YearSelector");
	eleYear.style.display = '';
	eleSelector.style.display = 'none';
	for(var i=eleSelector.options.length; i>-1; i--) eleSelector.remove(i);
}
	
Calendar.hideMonthSelector = function(){
	$("_Calendar_Month").style.display = '';
	$("_Calendar_MonthSelector").style.display = 'none';
}
	
Calendar.onYearSelectorChange = function(){
	var eleYear = $("_Calendar_Year"),eleSelector = $("_Calendar_YearSelector");
	eleYear.Year = eleSelector.value;
	var date = eleYear.Year+"-"+_LeftPad($("_Calendar_Month").Month+1,"0",2)+"-01";
	Calendar.setDate(date);
	eleYear.style.display = '';
	eleSelector.style.display = 'none';
}
	
Calendar.onMonthSelectorChange = function(){
	var eleMonth = $("_Calendar_Month"),eleSelector = $("_Calendar_MonthSelector");
	eleMonth.Month = parseInt(eleSelector.value);
	var date = $("_Calendar_Year").Year+"-"+_LeftPad(eleMonth.Month+1,"0",2)+"-01";
	Calendar.setDate(date);
	eleMonth.style.display = '';
	eleSelector.style.display = 'none';
}

Calendar.getDateString = function(cell){
	if(cell.Day){
		var day = cell.Day,month=$("_Calendar_Month").Month,year=$("_Calendar_Year").Year;
		if(day<0){
			day = -day;
			if(month==0){month = 11;year--;}else{month--;}
		}else if(day>32){
			day -= 40;
			if(month==11){month = 0;year++;}else{month++;}
		}
		return year+"-"+_LeftPad(month+1,"0",2)+"-"+_LeftPad(day,"0",2);
	}else if(cell.id=="_Calendar_Today"){
		var d = new Date();
		return (isGecko?d.getYear()+1900:d.getYear())+"-"+_LeftPad(d.getMonth()+1,"0",2)+"-"+_LeftPad(d.getDate(),"0",2)
	}else{
		return false;
	}
}
	
Calendar.onMouseOver = function(cell){
	cell.oldCssText = cell.style.cssText;
	var str = Calendar.getDateString(cell);
	if(str){$("_Calendar_Tip").innerText = str;}
	if(cell.Day){
		cell.style.cssText = "border-top: 1px solid #fff;"
				  +"border-right: 1px solid #000;"
				  +"border-bottom: 1px solid #000;"
				  +"border-left: 1px solid #fff;"
				  +"padding: 2px 2px 0px 2px;"
				  +"background: #ddd;";
	}else{
		cell.style.cssText = "background: #ccc;";
	}
}
	
Calendar.onMouseOut = function(cell){
	cell.style.cssText = cell.oldCssText;
}
	
Calendar.returnDate = function(cell){
	Calendar.control.value = Calendar.getDateString(cell);
	Calendar.hide();
}
	
Calendar.previousYear = function(){
	var date = (--$("_Calendar_Year").Year)+"-"+_LeftPad(++$("_Calendar_Month").Month,"0",2)+"-01";
	Calendar.setDate(date);
}
	
Calendar.nextYear = function(){
	var date = (++$("_Calendar_Year").Year)+"-"+_LeftPad(++$("_Calendar_Month").Month,"0",2)+"-01";
	Calendar.setDate(date);
}
Calendar.previousMonth = function(){
	var month = $("_Calendar_Month").Month,year = $("_Calendar_Year").Year;
	if(month==0){month=11;year--;}else{month--;}
	var date = ""+year+"-"+_LeftPad(month+1,"0",2)+"-01";
	Calendar.setDate(date);
}
	
Calendar.nextMonth = function(){
	var month = $("_Calendar_Month").Month,year = $("_Calendar_Year").Year;
	if(month==11){month=0;year++;}else{month++;}
	var date = ""+year+"-"+_LeftPad(month+1,"0",2)+"-01";
	Calendar.setDate(date);
}
	
Calendar.setDate = function(date){//日期算法在这里
	Calendar.isMouseOut = false;
	var array;
	if(!date){
		var d = new Date();
		array = [isGecko?d.getYear()+1900:d.getYear(),d.getMonth()+1,d.getDate()];
	}else{
		array = date.split("-");
	}
	var year = array[0];
	var isRightDate = true;
	if(isNaN(year)){
		year = '2000';
		isRightDate = false;
	}
	$("_Calendar_Year").innerText = year;
	$("_Calendar_Year").Year = year;
	var month =	array.length>1?array[1]-1:0;
	if(array.length==1)isRightDate = false;
	if(isNaN(month)||month<0||month>11){month = 0;isRightDate = false;}
	$("_Calendar_Month").innerText = Calendar.monthNames[month];
	$("_Calendar_Month").Month = month;
	var day = array.length>2?array[2]:0;
	var d2 = new Date();
	d2.setYear(year);
	d2.setMonth(month);
	d2.setDate(1);
	var week = d2.getDay();
	//计算上个月最后几天
	if(month==0){
		d2.setYear(year-1);
		d2.setMonth(11);
	}else{
		d2.setYear(year);
		d2.setMonth(month-1);
	}
	var days = [],i,j;
	for(i=28;i<33;i++){
		d2.setDate(i);
		if(d2.getMonth()==month){//等于表明是前一月的日期
			for(j=i-week;j<i;j++){
				days.push([0,j]);
			}
			break;
		}
	}
	//计算本月的所有天数
	d2.setYear(year);
	d2.setMonth(month);
	for(i=1;i<32;i++){
		if(i>=28){
			d2.setDate(i);
			if(d2.getMonth()!=month){
				break;//到了该月最后一天了
			}
		}
		if((week+i)%7==0||(week+i)%7==1){
			days.push([1,i]);
		}else{
			days.push([2,i]);
		}
	}
	//计算下月的开头几天
	for(j=0;j<7-((i-1+week)%7==0?7:(i-1+week)%7);j++){
		days.push([3,j+1]);
	}
	var html = [],rows = $("_Calendar_Table").rows;
	for(i=0;i<days.length;i++){
		var flag = days[i][0];
		var cell = rows[Math.floor(2+i/7)].cells[i%7];
		cell.innerText = days[i][1];
		if(flag==0){cell.className = "day disabled";cell.Day = -days[i][1];}
		if(flag==3){cell.className = "day disabled";cell.Day = 40+days[i][1];}
		if(flag==1){cell.className = "day weekend";cell.Day = days[i][1];}
		if(flag==2){cell.className = "day";cell.Day = days[i][1];}
	}
	for(j=4;j<6;j++){
		if(j>days.length/7-1){
			$("_Calendar_DayRow"+j).style.display = 'none';
		}else{
			$("_Calendar_DayRow"+j).style.display = '';					
		}
	}
	if(array.length==2)isRightDate = false;
	if(isNaN(day)||day<1||day>31){day = 1;isRightDate = false;}
	$("_Calendar_Day"+(day-1+week)).className += " selected";
	$("_Calendar_Tip").innerText = year+"-"+_LeftPad(month+1,"0",2)+"-"+_LeftPad(day,"0",2)
	d2 = new Date();
	if((isGecko?d2.getYear()+1900:d2.getYear())==year&&d2.getMonth()==month){
		$("_Calendar_Day"+(d2.getDate()-1+week)).className += " today";//当前日期样式
	}
	return isRightDate;
}
	
Calendar.show = function(ctrl,date){
	ctrl = $(ctrl);
	date = date?date:ctrl.value;
	Calendar.control = ctrl;	
	var divEle;
	if(!window[Calendar.cssFile]){
		var cssEle = document.createElement('link');		
		cssEle.rel	= 'stylesheet' ;
		cssEle.type	= 'text/css' ;
		cssEle.href = Server.ContextPath+Calendar.cssFile ;
		document.getElementsByTagName("head")[0].appendChild(cssEle) ; 
		window[Calendar.cssFile] = true;
		
		var divFrame = 	document.createElement('div');
		divFrame.id = "_Calendar";
		divFrame.className = "calendar";

		
		divEle = document.createElement('div');
		divEle.id = "_Calendar";
		divEle.className = "calendar";
		divEle.innerHTML = Calendar.initHtml();
		//divEle.style.zIndex = ;
		divEle.style.width = "190px";
		document.body.appendChild(divEle) ; 
	}else{
		divEle = $("_Calendar");
	}
	divEle.style.display = '';
	if(!Calendar.setDate(date)){
		alert("日期格式错误!");
	}
	var pos1 = Element.computePosition(ctrl,divEle);
	var pos2 = Element.getPosition(ctrl);
	if(pos2.y<pos1.y){
		divEle.style.top = pos1.y+3;
	}else{
		divEle.style.top = pos1.y-1;
	}
	divEle.style.left = pos1.x;
	
	var tableEle = $("_Calendar_Table");
	if(isIE){
		divEle.onmouseout = Calendar.divMouseOut;
	}else{
		divEle.addEventListener("mouseout",Calendar.divMouseOut,false);
	}
}
	
Calendar.divMouseOut = function(event){
	var evt = isIE?window.event:event;
	var divEle = $("_Calendar");
	var eX = evt.clientX+document.body.scrollTop;
	var eY = evt.clientY+document.body.scrollTop;
	var dX = parseInt(divEle.style.left);
	var dY = parseInt(divEle.style.top);
	if(dX+divEle.offsetWidth>eX&&dY+divEle.offsetHeight>eY&&dX<eX&&dY<eY){
		Calendar.isMouseOut = false;
	}else{
		Calendar.isMouseOut = true;
	}
}

Calendar.hide = function(){
	$("_Calendar").style.display = 'none';
}
