package com.combanc.itsm.tag;

import com.opensymphony.xwork2.util.ValueStack;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import org.apache.struts2.components.Component;   
import org.apache.struts2.views.jsp.ComponentTagSupport;   
 
public class ButtonTag extends ComponentTagSupport {  
	private static final long serialVersionUID =1L;
    private String code;
    private String menu;
 
    public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
       return new Button(arg0);
    }  
    
    protected void populateParams() { 
    	 super.populateParams();
    	 Button button=(Button) getComponent();
    	 button.setCode(code);
    	 button.setMenu(menu);
    }

	public void setCode(String code) {
		this.code = code;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	} 
	
}  

