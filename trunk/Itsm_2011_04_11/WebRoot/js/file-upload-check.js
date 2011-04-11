
/**
 * 上传文件大小检查
 * @param {} filename
 * @return {Boolean}
 */
function getFileSize(filename)
{
    // var filename = document.all('fileup').value; //获得上传文件的物理路径
    if(filename =="")
     {
		///alert("请先选择要上传的文件！");
     	alert("请先选择要上传的文件！");
		return false;
     }

     try {
      
		var fso,f,fname,fsize;
		var flength=5120; //设置上传的文件最大值（单位：kb），超过此值则不上传。
		fso=new ActiveXObject("Scripting.FileSystemObject");
        f=fso.GetFile(filename);//文件的物理路径
		fname=fso.GetFileName(filename);//文件名（包括扩展名）
		fsize=f.Size; //文件大小（bit）
		fsize=fsize/1024;
		//去掉注释，可以测试
        //alert("文件路径："+f);
		//alert("文件名："+fname);
		//alert("文件大小："+fsize+"kb");
		if(fsize==0){
			alert("上传文件为空，请选择其它文件!");
			return false;
		}
		if(fsize>flength)
		{
			//alert("上传的文件到小为："+fsize+"kb，超过最大上传文件大小限度5M，不能上传！ ");
			//Ext.MessageBox.alert("提示", "上传的文件大小为："+Math.round(fsize)+"kb，超过最大上传文件大小限度5M，不能上传！");
			alert("上传文件大小超过5M，不能上传！");
			return false;
		}

     }catch(e){
		 //alert("请先设置浏览器的activex控件。\n方法：在浏览器菜单栏上，依次选择\"工具\"->Internet选项->\"安全\"选项卡->自定义级别，然后打开\"安全设置\"对话框，将\"对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本\"选项，改为\"提示\"即可。");
		 alert("请先设置浏览器的activex控件。<br/>方法：在浏览器菜单栏上，依次选择\"工具\"->Internet选项->\"安全\"选项卡->自定义级别，然后打开\"安全设置\"对话框，将\"对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本\"选项，改为\"提示\"即可。");
         return false;
     }
     return true;
}


