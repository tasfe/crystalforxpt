//下拉框
function showthemall(id1)
 {
	var disp = document.getElementById(id1);
	if (disp.style.display == '' || disp.style.display == 'none')
	{
		eval("disp.style.display = 'block'");
	}else
	{
		eval("disp.style.display = 'none'");
	}
 }

// Universal Script for dropdown list

var hideDiv = "";

function hideDropDiv(e){
if (document.getElementById(e)!= "null" && document.getElementById!=null && document.getElementById != "")
{
	document.getElementById(e).style.display = "none";
}
}

function delayhideDropDiv(msec,whereto){
clearhideDropDiv()
delayDropDiv=setTimeout("hideDropDiv('"+whereto+"')",msec)
}


function clearhideDropDiv(){
if (typeof delayDropDiv!="undefined")
clearTimeout(delayDropDiv)
}



