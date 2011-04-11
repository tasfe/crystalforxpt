var nav=navigator.appVersion;
if (nav.indexOf('MSIE')!=-1){
	var input=document.getElementsByTagName("input");
	for(var i=0; i<input.length; i++){
		var inputs=input[i];
		if(inputs.className=="mmBtn"){
			var Len=inputs.value.length;
			inputs.style.width=Len*13+30;
		}
		if(inputs.className=="mmBtn_sm"){
			var Len=inputs.value.length;
			inputs.style.width=Len*13+10;
		}
	}
}