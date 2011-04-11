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
Ext.Msg.confirmYN = function(title,msg,func){
	Ext.Msg.show({
		title: title,
	    buttons: Ext.Msg.YESNO,
	    msg: msg,
	    fn: function(btn,text){
	   		if(btn !='yes'){
	   	   		return;
	   	    }
	   	    func();
	    },
	    animEl: Ext.getBody(),
	    icon: Ext.MessageBox.QUESTION
	});
}

// 单用户选择弹出窗口
UserSelectWindow = Ext.extend(Ext.Window, {
	
	title: "选择用户",
	width: 700,
	height: 460,
	minWidth: 600,
	minHeight: 420,
	layout: "fit",
	closeAction: "close",
	plain: true,
	accepted: null,
	canceled: null,
	deptId : null,
	condition:null,
	initComponent: function() {
		UserSelectWindow.superclass.initComponent.call(this);
		var parent = this;
		var limit = 10;
		
		// 创建一个div，供window显示
		var winContainer = Ext.DomHelper.append(Ext.getBody(), {tag: "div"});
		this.applyTo = winContainer;
		
		// 关键字输入框
		var comKeyword = new Ext.form.TextField({
        	fieldLabel: "关键字",
        	anchor: "98%"
        });
        // 搜索按钮
        var btnSearch = new Ext.Button({
        	text: "搜索",
        	listeners: {
        		click: function() {
        			store.load();
        		}
        	}
        });
		
		// 定义数据源
		var store = new Ext.data.Store({
			proxy: new Ext.data.HttpProxy({
				url: "../user/userGetByDepartAndKeyword.action",
				method: "post"
			}),
			reader: new Ext.data.JsonReader({
				root: "topics",
				totalProperty: "totalProperty",
				id: "id"
			}, ["id", "username", "truename", "mobilephone", "email","departmentid","departmentname"]),
			listeners: {
				beforeload: function() {
					this.baseParams.pageSize = limit;
					this.baseParams.requestData = Ext.util.JSON.encode({
						keyword: comKeyword.getRawValue() || "",
						deptId : parent.deptId == null?"" :parent.deptId,
						condition:parent.condition==null?"":parent.condition
					});
				},
				load: function() {
				
				}
			}
		});
		
		// 定义表格
		var grid = new Ext.grid.GridPanel({
			enableColumnMove :false,
			enableColumnHide : false, 
			frame: true,
			title: "用户列表",
			width: 400,
			height: "100%",
			autoScroll: true,
			columns: [new Ext.grid.RowNumberer, {
				header: "登录名",
				dataIndex: "username",
				sortable: true
			}, {
				header: "用户名",
				dataIndex: "truename",
				sortable: true
			}, {
				header: "部门",
				dataIndex: "departmentname"
			}, {
				header: "电话",
				dataIndex: "mobilephone"
			}, {
				header: "邮件",
				dataIndex: "email"
			}],
			autoExpandColumn: 4,
			sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
			store: store,
			bbar: new Ext.PagingToolbar({
				firstText: "第一页",
				prevText: "上一页",
				beforePageText: "第",
				afterPageText: "页，共 {0} 页",
				nextText: "下一页",
				lastText: "最后一页",
				refreshText: "刷新",
				pageSize: limit,
				store: store,
				displayInfo: true,
				displayMsg: "第 {0} 条到 {1} 条记录，共 {2} 条",
				emptyMsg: "没有记录"
			}),
			buttons: [{
				text: "确定",
				handler: function() {
					if (!grid.getSelectionModel().getSelected()) {
						Ext.Msg.alert("提示","没有选择用户!");
						return;
					}
					if (parent.accepted) {
						var selectedRecord = grid.getSelectionModel().getSelected();
						parent.accepted({
							id: selectedRecord.data["id"],
							name: selectedRecord.data["truename"],
							departmentid:selectedRecord.data["departmentid"],
							departmentname:selectedRecord.data["departmentname"]
						});
					}
					parent.close();

				}
			}, {
				text: "取消",
				handler: function() {
					//if (parent.canceled)
					//	parent.canceled();
					parent.close();
				}
			}],
			anchor: "100% -26"
		});
		grid.on("celldblclick", function(grid, rowIndex, columnIndex, e) {
			if (parent.accepted) {
				var selectedRecord = store.getAt(rowIndex);
				parent.accepted({
					id: selectedRecord.data["id"],
					name: selectedRecord.data["truename"],
					departmentid:selectedRecord.data["departmentid"],
					departmentname:selectedRecord.data["departmentname"]
				});
				parent.close();
			}
		});
		var departTree = new Ext.tree.TreePanel({
			anchor:'100% 100%',
            animate:true, 
            autoScroll:true,
            enableDD : false,
            containerScroll: true,
			split: true,
			collapsible: true,
			collapsed: false,
			root: new Ext.tree.AsyncTreeNode({
				id: "0",
				text: "部门",
				expanded: true
			}),
			rootVisible: false,
			loader: new Ext.tree.TreeLoader({
				dataUrl: "../department/departSynGet.action",
				
				createNode : function(attr) 
					{  
				        if (attr.nt == 1) 
				        {  
				            attr.leaf = true;
				        } 
				        
				      return(attr.leaf ?  
				           new Ext.tree.TreeNode(attr) :  
				           new Ext.tree.AsyncTreeNode(attr));  
					} ,
				listeners: {
					beforeload: function(loader, node) {
						this.baseParams.requestData = Ext.encode({
							parentID: node.id || "0"
						});
					}
				}
			}),
			listeners:{
				click:function (node, e){
					parent.deptId =node.id;
					store.load();
				}
			}
		});
		var left= new Ext.Panel({
			frame: true,
			border:false,
			region:'west',
        	title:'部门',
        	width: 200,
			maxWidth: 200,
			minWidth: 150,
			items:[departTree]
		});
		var right = new Ext.Panel({
        	frame: true,
        	region:'center',
        	layout: "anchor",
        	border:false,
        	items: [{
        		// 列布局
        		layout: "column",
        		items: [{
        			// 关键字输入
        			columnWidth: 0.85,
        			layout: "form",
        			labelAlign: "right",
        			labelWidth: 60,
        			items: [comKeyword]
        		}, {
        			// [搜索]按钮
        			columnWidth: 0.15,
        			items: [btnSearch]
        		}],
        		anchor:'100%'
        	}, grid]
    	});
    	var form = new Ext.Panel({
			layout: "border",
			autoScroll:true,
			items: [ left, right]
		});
    	this.add(form);
    	
    	this.on("beforeshow", function(window) {
			store.load({params: {start: 0}});
		});
		
	}
});



