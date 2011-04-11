function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11) 
{
this[0] = m0;
this[1] = m1;
this[2] = m2;
this[3] = m3;
this[4] = m4;
this[5] = m5;
this[6] = m6;
this[7] = m7;
this[8] = m8;
this[9] = m9;
this[10] = m10;
this[11] = m11;
}
//ʵ
function calendar() {
var monthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
var today = new Date();
var thisDay;
var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
year = today.getYear() +1900;
thisDay = today.getDate();
if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29;
nDays = monthDays[today.getMonth()];
firstDay = today;
firstDay.setDate(1);
testMe = firstDay.getDate();
if (testMe == 2) firstDay.setDate(0);
startDay = firstDay.getDay();
var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
var monthNames = new Array("1","2","3","4","5","6","7","8","9","10","11","12")
var now = new Date();
document.writeln("<FONT STYLE='font-size:9pt;Color:#ffffff'>" + "今天是"+now.getYear() + "年" + monthNames[now.getMonth()]+"月" + now.getDate() + "日&nbsp;&nbsp;" + dayNames[now.getDay()] + "</FONT>");
}