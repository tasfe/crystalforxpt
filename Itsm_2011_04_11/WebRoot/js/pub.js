function RowsPerPage(pagesize){
	document.form.pageSize.value = pagesize;
	document.form.submit();
  	return ;
}


function Jumping(tpage){
	document.form.page.value = tpage;
	document.form.submit();
  return ;
}

function gotoPage(pagenum){
	document.form.page.value = pagenum;
  	document.form.submit();
  	return ;
}
function trimAll(str){
	/*************************************************************
	Input Parameter :str
	Purpose : remove all white spaces in front and back of string
	Return  : str without white spaces
	***************************************************************/
	//check for all spaces
	var objRegExp =/^(\s*)$/;
	if (objRegExp.test(str)){
	str = str.replace(objRegExp,'');
	if (str.length == 0)
	return str;
	}
	// check for leading and trailling spaces
	objRegExp = /^(\s*)([\W\w]*)(\b\s*$)/;
	if(objRegExp.test(str)){
	str = str.replace(objRegExp, '$2');
	}
	return str;
}

