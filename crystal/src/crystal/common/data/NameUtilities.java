/*
 * Copyright (C) 2005-2007 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

/* 
 * Created on 05.09.2007
 *
 */

package crystal.common.data;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author Oleh Hapon
 * $Id: NameUtilities.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */

public class NameUtilities {

    /** Name of default name context **/
    public static final String DEFAULT_NAME = "DEFAULT";
    
    /** Default properties for name context **/
    public static final String DEFAULT_SEPARATOR = " ";
    public static final String DEFAULT_LEFT_BRACKET = "(";
    public static final String DEFAULT_RIGHT_BRACKET = ")";

    
    /** Nax length of name token **/
    public static final int MAX_LENGTH_OF_NAME_TOKEN  = 1;
    
    
    private static Map<String, NameContext> detailMap = new HashMap<String, NameContext>();
    
    static {
	NameContext defaultContext = new NameContext(DEFAULT_SEPARATOR, DEFAULT_LEFT_BRACKET, DEFAULT_RIGHT_BRACKET);
	detailMap.put(DEFAULT_NAME, defaultContext);
    }
    
    public static void addNameContext(String name, NameContext context) {
	if (name == null) {
	    throw new IllegalArgumentException("Name must be not null");
	}
	if (context == null) {
	    throw new IllegalArgumentException("Context must be not null");
	}
	if (DEFAULT_NAME.equals(name)) {
	    throw new IllegalArgumentException("'" + DEFAULT_NAME + "' is reserved name");
	}
	
	validateToken("Separator", context.separator);
	validateToken("LeftBracket", context.leftBracket);
	validateToken("RightBracket", context.rightBracket);
	
	detailMap.put(name, context);
    }
    
    private static void validateToken(String tokenName, String token) {
	if (token == null) {
	    throw new IllegalArgumentException(tokenName + " must be not null");
	}
	if (token.length() > MAX_LENGTH_OF_NAME_TOKEN) {
	    throw new IllegalArgumentException(tokenName + "Length of " + token + " is long");
	}
    }

    public static NameContext getDefaultNameContext() {
	return detailMap.get(DEFAULT_NAME);
    }

    public static NameContext getNameContext(String name) {
	NameContext context = detailMap.get(name);
	if (context == null) {
	    context = getDefaultNameContext();
	}
	return context;
    }
    
    public static class NameContext {
	
	private String separator;
	private String leftBracket;
	private String rightBracket;
	
	
	public NameContext() {
	    super();
	}
	
	
	public NameContext(String detailSeparator, String leftBracket, String rightBracket) {
	    super();
	    this.separator = detailSeparator;
	    this.leftBracket = leftBracket;
	    this.rightBracket = rightBracket;
	}


	public String getLeftBracket() {
	    return leftBracket;
	}
	public void setLeftBracket(String leftBracket) {
	    this.leftBracket = leftBracket;
	}
	public String getRightBracket() {
	    return rightBracket;
	}
	public void setRightBracket(String rightBracket) {
	    this.rightBracket = rightBracket;
	}
	public String getSeparator() {
	    return separator;
	}
	public void setSeparator(String separator) {
	    this.separator = separator;
	}
    }
    
    public static String getDetailName(String contextName, String baseName, String additionalName) {
	if (baseName == null && additionalName == null) {
	    return null;
	}
	NameContext context = getNameContext(contextName); 
	
	return (baseName == null ? "" : baseName) + 
	(additionalName == null ? "" :  (baseName == null ? "" : context.getSeparator())  + context.getLeftBracket()+ additionalName + context.getRightBracket());

    }
}
