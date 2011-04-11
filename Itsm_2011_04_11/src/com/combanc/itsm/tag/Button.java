package com.combanc.itsm.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class Button extends Component {
	
    Boolean answer;
	private String code;
	private String menu;
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Button(ValueStack stack) {
		super(stack);
	}
		
	public boolean start(Writer writer) {
		if(menu==null||menu.equals("")||code==null||code.equals("")){
			answer = Boolean.FALSE;
		}else {
			Map map=(Map) ActionContext.getContext().getSession().get("privilege");
			if(map==null) answer = Boolean.FALSE;
			else {
				String s=(String) map.get(menu);
				if(s!=null&&!s.equals("")&&s.contains(","+code+",")) answer=Boolean.TRUE;
				else answer = Boolean.FALSE;
			}			
		}
	    return answer.booleanValue();
	}
	
	 public boolean end(Writer writer, String body) {
		 return super.end(writer, body);
	 }

}
