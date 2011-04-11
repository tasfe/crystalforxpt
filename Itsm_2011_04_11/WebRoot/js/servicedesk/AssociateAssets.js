/*
 * 涉及配置(事件管理)
 */

function addRow(table_id,arr){
	if(!arr||arr.length<=0){
		return;
	}
	var table=document.getElementById(table_id);
	if(!table){
		return;
	}
	for(var i=0;i<arr.length;i++){
		var oldArr=getIds();
		var isContinue=false;
		for(var j=0;j<oldArr.length;j++){
			if(oldArr[j]==arr[i].id){
				isContinue=true;
				break;
			}
		}
		if(isContinue){
			continue;
		}
		var row=table.insertRow(table.rows.length);
		var cell1=row.insertCell();
		var cell2=row.insertCell();
		var cell3=row.insertCell();
		var cell4=row.insertCell();
		var cell5=row.insertCell();
		var cell6=row.insertCell();
		var cell7=row.insertCell();
		var cell8=row.insertCell();
		
		cell1.innerHTML=arr[i].code;
		cell2.innerHTML=arr[i].name;
		//if(arr[i].type.length>16){
			//cell3.innerHTML=arr[i].type.substring(0,16)+'...';
		//}else{
			cell3.innerHTML=arr[i].type;
		//}
		cell4.innerHTML=arr[i].supplier;
		cell5.innerHTML=arr[i].producer;
		cell6.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../images/ck.gif'><a href='../assets/show.action?assetsId="+arr[i].id+"' target='_blank'>查看</a>";
		cell7.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='删除' class=mmBtn_sm onClick='delRow(window.event.srcElement.parentElement.parentElement.rowIndex)'>";
		cell8.innerHTML=arr[i].id;
		cell8.style.visibility='hidden';
	}
	document.getElementById('associate_assets').value=getStrIds();
}

function delRow(value){
	document.getElementById('configTab').deleteRow(value);
	document.getElementById('associate_assets').value=getStrIds();
}

function getIds(){
	var rows=document.getElementById('configTab').rows;
	var array=new Array();
	if(rows.length==1){
		return array;
	}
	for(var i=1;i<rows.length;i++){
		array.push(rows[i].cells[7].innerHTML);
	}
	return array;
}
function getStrIds(){
	var rows=document.getElementById('configTab').rows;
	var array="";
	if(rows.length==1){
		return array;
	}
	for(var i=1;i<rows.length;i++){
		array+=(rows[i].cells[7].innerHTML);
		array+=",";
	}
	return array.substring(0,array.lastIndexOf(','));
}