package com.netblizzard.util;
import java.io.UnsupportedEncodingException;


public class Unicode2Char {
	public static String parseUnicode(String line){
	     int len=line.length();
	     char[] out=new char[len];//保存解析以后的结果
	     int outLen=0;
	     for(int i=0;i<len;i++){
	      char aChar=line.charAt(i); 
	      if(aChar=='\\'){
	       aChar=line.charAt(++i);
	       if(aChar=='u'){
	        int value=0;
	     for(int j=0;j<4;j++){
	        aChar=line.charAt(++i);
	         switch (aChar) {
	         case '0': case '1': case '2': case '3': case '4':
	         case '5': case '6': case '7': case '8': case '9':
	          value = (value << 4) + aChar - '0';
	         break;
	         case 'a': case 'b': case 'c':
	         case 'd': case 'e': case 'f':
	         value = (value << 4) + 10 + aChar - 'a';
	         break;
	         case 'A': case 'B': case 'C':
	         case 'D': case 'E': case 'F':
	         value = (value << 4) + 10 + aChar - 'A';
	         break;
	         default: throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
	        }
	     }
	     out[outLen++]=(char)value;
	    }else{
	        if (aChar == 't') aChar = '\t'; 
	                    else if (aChar == 'r') aChar = '\r';
	                    else if (aChar == 'n') aChar = '\n';
	                    else if (aChar == 'f') aChar = '\f'; 
	                    out[outLen++] = aChar;
	    }
	   }else{
	       out[outLen++] = aChar;
	   }
	  }
	  return new String (out, 0, outLen);
	 }
	//调用的时候:
	public static void main(String rags[])throws Exception{
	   // BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("1.txt")));
	    String line="\\u8282\\u70B9\\u7D22\\u5F15";
//	   while((line=br.readLine())!=null){
//	    System.out.println(parseUnicode(line));
//	   }
	    System.out.println(parseUnicode(line));
//	   br.close();
	}
}
