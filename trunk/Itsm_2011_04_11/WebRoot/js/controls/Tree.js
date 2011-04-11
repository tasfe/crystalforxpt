var Tree = {};

Constant.TreeLevel = "_ZVING_TREE_LEVEL";
Constant.TreeStyle = "_ZVING_TREE_STYLE";

Constant.Icon_Branch_NotLast_NotExpand = "images/icons/treeicon01.gif";
Constant.Icon_Branch_NotLast_Expand = "images/icons/treeicon02.gif";
Constant.Icon_Branch_Last_NotExpand = "images/icons/treeicon04.gif";
Constant.Icon_Branch_Last_Expand = "images/icons/treeicon05.gif";

Constant.Branch_NotLast_NotExpand = "1";
Constant.Branch_NotLast_Expand = "2";
Constant.Branch_Last_NotExpand = "3";
Constant.Branch_Last_Expand = "4";

Tree.CurrentItem = null;

Tree.onItemClick = function(evt,item){
	if(Tree.CurrentItem){
		Tree.CurrentItem.className = '';	
	}
	Tree.CurrentItem = item;
	item.className = "cur";
}

Tree.onItemDblClick = function(evt,item){
	evt = getEvent(evt);
	Tree.CurrentItem = item;
	if(Tree.hasChild(item)&&!Tree.isRoot(item)){
		Tree.onBranchIconClick(evt,Tree.getLastBranchIcon(item));
	}
}

Tree.getLastBranchIcon = function(item){//获得节点图标
		var arr = item.$T("img");
		for(var i=0;i<arr.length;i++){
			var ele = arr[i];			
			if(ele.src.indexOf("images/icons/treeicon01")>0||ele.src.indexOf("images/icons/treeicon02")>0||ele.src.indexOf("images/icons/treeicon04")>0||ele.src.indexOf("images/icons/treeicon05")>0){
				return ele;
			}	
		}
}

Tree.onContextMenu = function(evt){
	evt = getEvent(evt);
	Tree.CurrentItem = evt.srcElement;
}

Tree.onMouseOver = function(evt){
	evt = getEvent(evt);
	var ele = evt.srcElement;
	if(ele==Tree.CurrentItem){
		return;
	}
	var ztype = ele.getAttribute("ztype");
	if(ztype&&ztype.toLowerCase()=="rootmenu"){
		return;
	}else{
		ele.className = 'over';	
	}
}

Tree.onMouseOut = function(evt){
	evt = getEvent(evt);
	var ele = evt.srcElement;
	if(ele==Tree.CurrentItem){
		return;
	}
	var ztype = ele.getAttribute("ztype");
	if(ztype&&ztype.toLowerCase()=="rootmenu"){
		return;
	}else{
		ele.className = '';	
	}
}

Tree.isRoot = function(ele){
	return $(ele).$A("level")==="0";
}

Tree.hasChild = function(ele){
	ele = $(ele);
	if(ele.getAttribute("lazy")=="1"){
		return true;
	}
	if(ele.nextElement() && ele.nextElement().tagName.toLowerCase()=="div"){
		return true;
	}
	return false;
}

Tree.onBranchIconClick = function(evt,ele){
	evt = getEvent(evt);
	if(!ele){
		ele = evt.srcElement;//双击调用时会传入ele参数
	}
	var imgEle = ele;
	ele = $(ele).getParent("p");	
	var lazy = ele.getAttribute("lazy");
	if(lazy=="1"){
		Tree.lazyLoad(ele);		
	}else{
		if(Tree.hasChild(ele)){
			$E.toggle(ele.nextElement());
		}		
	}
	Tree.changeIcon(imgEle,ele);
	stopEvent(evt);
}

Tree.changeIcon = function(imgEle,ele){
	var expand = ele.getAttribute("expand");
	var imgPath;
	if(expand == Constant.Branch_NotLast_NotExpand){
		imgPath = Constant.Icon_Branch_NotLast_Expand;
		expand = Constant.Branch_NotLast_Expand;
	}else if(expand == Constant.Branch_NotLast_Expand){
		imgPath = Constant.Icon_Branch_NotLast_NotExpand;
		expand = Constant.Branch_NotLast_NotExpand;
	}else if(expand == Constant.Branch_Last_Expand){
		imgPath = Constant.Icon_Branch_Last_NotExpand;
		expand = Constant.Branch_Last_NotExpand;
	}else if(expand == Constant.Branch_Last_NotExpand){
		imgPath = Constant.Icon_Branch_Last_Expand;
		expand = Constant.Branch_Last_Expand;
	}
	ele.setAttribute("expand",expand);
	imgEle.src = Server.ContextPath + imgPath;
}

Tree.init = function(ele,resizeable){
	ele = $(ele);
	Tree.setParam(ele,Constant.ID, ele.id);
	Tree.setParam(ele,Constant.Method, ele.getAttribute("method"));
	Tree.setParam(ele,Constant.TagBody, ele.TagBody);
	if(resizeable&&window.Resize){
		new Resize($(ele.id+'_container'),{ Max: true, mxContainer:{clientWidth:400,clientHeight:600}});//可调整宽度
	}
}

Tree.getParam = function(ele,name){
	ele = $(ele);
	return ele.Params.get(name);
}

Tree.setParam = function(ele,name,value){
	ele = $(ele);
	if(!ele.Params){
		ele.Params = new DataCollection();
	}
	ele.Params.add(name,value);
}

Tree.loadData = function(ele,func){
	ele = $(ele);
	var id  = ele.id;
	var currentID = null;
	if(Tree.CurrentItem){
		currentID = Tree.CurrentItem.getAttribute("cid");
	}
	Server.sendRequest("com.zving.framework.controls.TreePage.doWork",ele.Params,function(response){
		var newEle = document.createElement("div");
		newEle.setAttribute("ztype","_Tree");
		newEle.innerHTML = response.get("HTML");
		var tree = $E.getParentByAttr(ele,"ztype","_Tree");
		if(tree){
			ele = tree;
		}
		ele.parentElement.replaceChild(newEle,ele);
		if(isIE){
			execScript(newEle.getElementsByTagName("script")[0].text);
		}
		ele = null;
		Tree.CurrentItem = null;
		if(currentID!=null){
			Tree.select(newEle,"cid",currentID,false); //加载后尝试还原上一次的选中情况
		}
		if(func){
			func();
		}
	});
}

Tree.lazyLoad = function(ele,func){
	ele = $(ele);
	var tree = ele.getParentByAttr("ztype","_Tree");
	Tree.setParam(tree,"ParentLevel", ele.getAttribute("level"));
	Tree.setParam(tree,"ParentID", ele.getAttribute("cid"));
	Tree.setParam(tree,"LevelStr", ele.getAttribute("levelstr"));
	Server.sendRequest("com.zving.framework.controls.TreePage.doWork",tree.Params,function(response){
		if(ele.getAttribute("lazy")=="1"){//可避免多次加载的BUG
			ele.setAttribute("lazy","0");
			var newEle = document.createElement("div");
			newEle.innerHTML = response.get("HTML");
			ele.insertAdjacentElement("afterEnd",newEle);
			
			Tree.setParam(tree,"ParentLevel", "");
			Tree.setParam(tree,"ParentID", "");
			Tree.setParam(tree,"LevelStr", "");
			
			if(func){
				func();
			}
		}
	});
}

Tree.select = function(tree,attr,v,execEventFlag){//根据属性attr选中该属性值为v的节点
	tree = $(tree);
	var arr = tree.getElementsByTagName("p");
	for(var i=0;i<arr.length;i++){
		var p = arr[i];
		if(p.getAttribute(attr)==v){
			Tree.selectNode(p,execEventFlag);
			break;
		}
	}
}

Tree.selectNode = function(ele,execEventFlag){
	ele = $(ele);
	Tree.onItemClick(null,ele);
	if(execEventFlag){
		ele.onclick.apply(ele);
	}
}

Tree.getNode = function(tree,attr,v){//根据属性attr获取该属性值为v的节点
	tree = $(tree);
	var arr = tree.getElementsByTagName("p");
	for(var i=0;i<arr.length;i++){
		var p = arr[i];
		if(p.getAttribute(attr)==v){
			return p;
		}
	}
	return null;
}

Tree.scrollToNode = function(ele){
	ele = $(ele);
	var tree = ele.getParentByAttr("ztype","_Tree");
	var h = tree.getDimensions().height;
	if(ele.offsetTop>h){
		tree.scrollTop = ele.offsetTop-100;
	}
}

Tree.filter = function(tree,v){
	var tree = $(tree);
	var arr = tree.getElementsByTagName("p");
	v = v.toLowerCase();
	for(var i=0;i<arr.length;i++){
		var p = arr[i];
		if(i==0&&Tree.isRoot(p)){
			continue;
		}
		var str = p.innerText;
		str = str.replace(/　/g,"").trim().toLowerCase();
		if(str.indexOf(v)>=0){
			p.style.display = '';
		}else{
			p.style.display = 'none';
		}
	}
}

Tree.clear = function(ele){
	ele = $(ele);
	ele.innerHTML = "";
	Tree.CurrentItem = null;
}

Tree.dragEnd = function(evt){
	var afterDrag = $(this).$A("afterDrag");
	if(!afterDrag){
		return;
	}
	var func = eval(afterDrag);
	func.apply(this,arguments);
}

Tree.dragOver = function(evt){
	this.style.fontWeight = 'bold';
	this.style.backgroundColor = '#EDFBD2';
}

Tree.dragOut = function(evt){
	this.style.fontWeight = 'normal';
	this.style.backgroundColor = '#FFF';
}