package com.combanc.itsm.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PermissionBodyTag extends BodyTagSupport {
	private static final long serialVersionUID = 1279413659974128440L;
	private List<String> permissions=null;
    private String operate; //对应Attribute,加上set方法。

 	public void setOperate(String operate) {
 	   this.operate = operate;
 	}
     public int doStartTag() throws JspTagException {  
    	 //得到用户拥有的权限：页面上操作
    	  
    	permissions = (List<String>) pageContext.getSession().getAttribute("permissions"); 
    	 
    	 //判断用户是否有权限
    	 boolean show=false;
    	 for(String per:permissions) 
    	 {
    		 if(operate.equals(per))
    		 { 
    			 show=true;
    			 break;
    		 }
    	 }
    	 if (show) {
             return EVAL_BODY_INCLUDE;  //跳过了开始和结束标签之间的代码
         } else {  
             return SKIP_BODY;  //将body的内容输出到存在的输出流中
         }  
     }  
     public int doEndTag() throws JspTagException {  
       System.out.println("endtag");
         try {  
             if (bodyContent != null) {  
                 bodyContent.writeOut(bodyContent.getEnclosingWriter());  
             }else{  
             }  
         } catch (IOException e) {  
             throw new JspTagException("IO ERROR:" + e.getMessage());  
         }  
         return EVAL_PAGE;  //继续执行下面的页
     }  
     public void doInitBody() throws JspTagException {  
     }  
     public void setBodyContent(BodyContent bodyContent) {  
         this.bodyContent = bodyContent;  
     }  

}
