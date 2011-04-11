/**
 * @author vinodvv
 * usage for loader 
 * to show loader image
 * displayAjaxImage('an empty div which is available on your page')
 * 
 * eg when clicked on link to show loader
 * <a href="javascript:displayAjaxImage('ajaxloader')" >click here</a>
 * 
 * eg to remove loader image
 * displayAjaxContent(ajaxloader,'','false','false');
 * 
 * to show ajax error type content
 * displayAjaxContent(ajaxloader,'your html content','typeError','closebutton');
 * don't change the word ajaxloader in the above line, you can specify the html content to display. 
 * optional parameter typeError - set to true if you want the message to appear for ever unless some one clicks on the error msg to disappear
 * optional parameter closebutton - to have x button to appear set this one to true  
 * settimer(val) default timer value is 5000 millsecs, specify some other value to change Kindly note all you value should be 1000* secs 
 * 
 * eg of msg as Error type with close button
 * displayAjaxContent(ajaxloader,'Could not complete your request... Failed.','true','true');
 * 
 * eg of msg as non Error type with close button
 * displayAjaxContent(ajaxloader,'Added Data successfully','false','true');
 * 
 * eg of msg as non Error type with out close button
 * displayAjaxContent(ajaxloader,'Added Data successfully','false','false');
 * 
 * bubble msg usage
 * <a href="#" onmouseover="bubble('bubbles','msg');" onmouseout="bubble('bubbles','');" >show bubble</a>
 * where bubbles is the empty div id available in the html body
 * msg - you own html content
 * 
 * showMsg(divid,contenttxt,closeme)
 * used for showing just the status messages 
 * showMsg usage
 * <a href="#" onclick="showMsg('ajaxloader','updated successfully','true');">click me </a>
 * divid is ajaxloader specify in quotes
 * contenttxt is the msg in quotes
 * closeme - boolean whether to have close button
 * false - no close button
 * true - with close button
 * 
 * 
 */
var over_Lay = null;
var over_Layed = null;
var ajaxloader = null;
var timer = 5000;
var timpop=0;
function settimer(val)
{
	timer = val;
}
/* code taken from http://www.quirksmode.org/js/events_properties.html */
xMouse=0;yMouse=0;      // globals to hold coordinates
document.onmousemove=getMouse; // start event listener
function getMouse(e){
e=e||window.event;
	if (e.pageX||e.pageY){
		xMouse=e.pageX;yMouse=e.pageY;
	} else {
	    de=document.documentElement;b=document.body;
	    xMouse=e.clientX+(de.scrollLeft||b.scrollLeft)-(de.clientLeft||0);
	    yMouse=e.clientY+(de.scrollTop||b.scrollTop) - (de.clientTop||0);
	}
	//window.status = xMouse+"   " + yMouse;
}
function showMsg(divid,contenttxt,closeme){
	displayAjaxImage(divid);
	if(closeme == 'false')
	displayAjaxContent(divid,contenttxt,'false','false');
	else
	displayAjaxContent(divid,contenttxt,'false','true');
}
function displayAjaxImage(ajaxload)
{
	var my_div = document.getElementById(ajaxload);
	ajaxloader = ajaxload;
	over_Lay = document.createElement("div");
	over_Lay.innerHTML = "<table align='right' class='loader'><tr><td width='25' align='left' ><img src='/webclient/common/images/loader-gray1.gif'></td><td align='center' class='loader'>&nbsp;&nbsp;Loading...</td></tr></table>";
	over_Lay.onclick = function()
	{
		
	};
	over_Lay.className = "loadercls";
	de=document.documentElement;b=document.body;
	yPos=(de.scrollTop||b.scrollTop) - (de.clientTop||0);
	if(yPos==0)
		over_Lay.style.top = 110+"px";
	else
		over_Lay.style.top = yPos+110+"px";
	document.getElementById(ajaxloader).appendChild(over_Lay);
	//timpop1= setTimeout("displayAjaxContent('"+ajaxloader+"','Added Successfully','true','true')",timer);
}
function displayAjaxImageWithMessage(ajaxload,message)
{
	var my_div = document.getElementById(ajaxload);
	ajaxloader = ajaxload;
	over_Lay = document.createElement("div");
	over_Lay.innerHTML = "<table align='left' class='loader'><tr><td width='25' align='left' ><img src='/webclient/common/images/loader-gray1.gif'></td><td align='center' class='loader'>&nbsp;&nbsp;"+message+"</td></tr></table>";
	document.getElementById(ajaxloader).appendChild(over_Lay);

}
function displayAjaxContent(div,msg,typeError,closebutton){
	if(over_Lay != null)
	{
		document.getElementById(div).removeChild(over_Lay);
	}
	if(msg=="")
	return;
	over_Lay = document.createElement("div");
	over_Lay.style.position = "absolute";
	over_Lay.style.width = "100%";
	over_Lay.style.height =20+"px";
	over_Lay.style.right =90+"px";
	de=document.documentElement;b=document.body;
	yPos=(de.scrollTop||b.scrollTop) - (de.clientTop||0);
	if(yPos==0)
		over_Lay.style.top = 110+"px";
	else
		over_Lay.style.top = yPos+110+"px";
	if(closebutton =='true'){
		if(typeError == 'false'){
			over_Lay.innerHTML = "<table align='right' class='responseNew'><tr><td align='center' class='responseNew'>&nbsp;&nbsp;" +msg+ "</td><td align='right' width='40px'><a href='#' title='close'><img src='/webclient/common/images/close.png' border='0'></a></td></tr></table>";
		}else{
			over_Lay.innerHTML = "<table align='right' class='error'><tr><td align='center'class='error'>&nbsp;&nbsp;" +msg+ "</td><td align='right' width='40px'><a href='#' title='close'><img src='/webclient/common/images/close.png' border='0'></a></td></tr></table>";
		}
	}
	else
	{
		if(typeError == 'false'){
			over_Lay.innerHTML = "<table align='right' class='responseNew'><tr><td align='center' class='responseNew'>&nbsp;&nbsp;" +msg+ "</td></tr></table>";
		}else{
			over_Lay.innerHTML = "<table align='right' class='error'><tr><td align='center' class='error'>&nbsp;&nbsp;" +msg+ "</td></tr></table>";
		}
	}
	if(typeError == 'false')
	{
		timpop= setTimeout("removedisplayAjaxContent("+ajaxloader+")",timer);
		over_Lay.onclick = function()
		{
			removedisplayAjaxContent(ajaxloader,timpop);
		}
	}
	else
	{
		over_Lay.onclick = function()
		{
			removedisplayAjaxContent(ajaxloader);
		}
	}
	document.getElementById(ajaxloader).appendChild(over_Lay);
}
function removedisplayAjaxContent(mydiv,clearID)
{
	if(clearID!=undefined)
	clearTimeout(clearID);
	document.getElementById(mydiv).removeChild(over_Lay);
}

var bublDivId;
var bublMsg;

function bubble(divid,msg){
	if(msg=="" || msg== undefined){
		document.getElementById(divid).removeChild(over_Layed);
	}else{
		over_Layed = document.createElement("div");	
		over_Layed.style.position = "absolute";
		over_Layed.style.width =350+"px";
		over_Layed.style.left =xMouse+50+"px";
		over_Layed.style.top =yMouse-30+"px";
		over_Layed.style.display = "none";
        over_Layed.style.zIndex = 415;

        over_Layed.innerHTML = "<table width='100%' class='bubbleDiv' height='100%' border='0' cellpadding='0' cellspacing='0'><tr><td width='16' height='17' align='left' valign='top' class='bubbleLT'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td><td align='left' valign='top' class='bubble_bgT'></td><td width='16' height='17' align='left' valign='top' class='bubbleRT'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td></tr><tr><td align='left' valign='top' class='bubble_bgL'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td><td align='left' valign='top' bgcolor='#FFFFFF' >"+msg+"</td><td align='left' valign='top' class='bubble_bgR'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td></tr><tr><td width='16' height='17' align='left' valign='top' class='bubbleLB'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td><td align='left' valign='top' class='bubble_bgB'></td><td align='left' valign='top' class='bubbleRB'><img src='/img/monitor/ceruleanBlue/spacer.gif' width='16' height='17' /></td></tr></table>";
/*
		over_Layed.innerHTML = "<table align='left'  class='bubbleNew'><tr><td align='center' class='bubbleNew'>&nbsp;&nbsp;" +msg+ "</td></tr></table>";
*/
		 bublDivId = divid;
  	     bublMsg  = msg;
		document.getElementById(divid).appendChild(over_Layed);
		timpop1= setTimeout("populateBubble()",600);
	}
}

function populateBubble()
{
      showmsg(bublDivId,bublMsg);
}

function showmsg(divid,msg){
	if(msg=="" || msg== undefined){
		document.getElementById(divid).removeChild(over_Layed);
	}else{
		over_Layed.style.display = "block";
	}
}
/* check width and height */
var myWidth = 0, myHeight = 0;
function alertSize() {
 myWidth = 0;
 myHeight = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }
  wid = myWidth;
  ht = myHeight;
  return [myWidth,myHeight];
}
/* flash detection */
function readMyCookie(cookieName)
{
	var cookName = cookieName+"=";
	var cookie_arr = document.cookie.split(";");
	for(var k=0;k<cookie_arr.length;k++)
	{
		var retCookie = cookie_arr[k];
		
		while(retCookie.charAt(0)== ' ')
		{
			retCookie = retCookie.substring(1,retCookie.length);
		}
		if(retCookie.indexOf(cookName)==0) 
		{
			var toReturn = retCookie.substring(cookName.length,retCookie.length);
		//	alert(toReturn);
			return toReturn
		}
	}
	return null;
	
}
var version_str ="";
var version_arr = "";
var major_ver = "";
var minor_ver = "";
var revision_ver = "";

function opennewMap(){
	version_str =readMyCookie("flashversionInstalled");
	version_arr = version_str.split(".");
	major_ver = version_arr[0];
	minor_ver = version_arr[1];
	revision_ver = version_arr[2];
	displayAjaxImage('ajaxloader');
	var popsize = alertSize();
	var x= 0;
	var y= 0;
	if(browser == 'Explorer'){
		x= popsize[0];
		y  = popsize[1];
	}else{
		x= popsize[0] -30;
		y  = popsize[1];
	}
	if(major_ver >8){
		location.href = "/map/EditMap.do";
		displayAjaxContent(ajaxloader,'','false','false');
	}
	else if(major_ver == 8 && revision_ver >=24){
		displayAjaxContent(ajaxloader,'','false','false');
		location.href = "/map/EditMap.do";
	}
	else if(major_ver == 0)
	{
		displayAjaxContent(ajaxloader,'<span class=\'error\'><a href=\'http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash&promoid=BIOW\' target=\'_blank\'>Currently your browser does not have flash player to edit business view. Click to install latest version of flash player</a></span>','true','true');
	}
	else{
		displayAjaxContent(ajaxloader,'<span class=\'error\'><a href=\'http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash&promoid=BIOW\' target=\'_blank\'>Currently your browser has Flash Player '+version_str+' Click to install the latest version of flash player</a></span>','true','true');
	}
}
