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
 * Created on 19.02.2008
 *
 */

package crystal.common.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author Oleh Hapon
 * $Id: TextWorker.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */

public class TextWorker {

    public static int UPPER = 1;
    public static int LOWER = 2;
    
    
    public static void filterText(String inputFile, String outputFile, String filter) throws FileNotFoundException, IOException  {
	filterText(inputFile, outputFile, filter, 0, 0);
    }
    public static void filterText(String inputFile, String outputFile, String filter, int sorter, int textCase) throws FileNotFoundException, IOException  {
	if (filter != null) {
	    filter = filter.trim();
	}
	if (filter == null || filter.length() == 0) {
	    throw new IllegalArgumentException("Filter is empty");
	}
	if (filter.length() > 2) {
	    if (filter.startsWith("'") && filter.endsWith("'")) {
		filter = filter.substring(1, filter.length() - 1);
	    }
	}
	System.out.println("Filter=" + filter);
	BufferedReader reader = null;
	BufferedWriter writer = null;
	try {
	    reader = new BufferedReader(new FileReader(inputFile));
	    List<String> data = new ArrayList<String>();
	    String line = null;
	    while (reader.ready()) {
		line = reader.readLine();
		if (line != null) {
		    line = line.trim();
		    if (line.length() == 0) {
			continue;
		    }
		    if (line.toUpperCase().startsWith(filter.toUpperCase())) {
			String subLine = line.substring(filter.length()).trim();
			int index = subLine.indexOf(" ");
			if (index > 0) {
			    subLine = subLine.substring(0, index);
			}
			if (UPPER == textCase) {
			    subLine = subLine.toUpperCase();
			} else if (LOWER == textCase) {
			    subLine = subLine.toLowerCase();
			}
			data.add(filter + subLine);
		    }
		    
		}
	    }
	    writer = new BufferedWriter(new FileWriter(outputFile));
	    if (sorter >= 0) {
		for (int i = 0; i < data.size(); i++) {
		    writer.write(data.get(i) + "\n");
		}
	    } else {
		for (int i = data.size() - 1; i >=0; i--) {
		    writer.write(data.get(i) + "\n");
		}
	    }
	    writer.flush();
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
		if (writer != null) {
		    writer.close();
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	
	    
    }
    
    public static void specialText(String inputFile, String outputFile) throws FileNotFoundException, IOException  {
	String filter1 = "alter table";
	String filter2 = "add constraint";
	String filter3 = "references";
	
	BufferedReader reader = null;
	BufferedWriter writer = null;
	try {
	    reader = new BufferedReader(new FileReader(inputFile));
	    List<String> data = new ArrayList<String>();
	    String line = null;
	    while (reader.ready()) {
		line = reader.readLine();
		if (line != null) {
		    line = line.trim();
		    if (line.length() == 0) {
			data.add("\n");
			continue;
		    }
		    
		    if (line.toUpperCase().startsWith(filter1.toUpperCase())) {
			String subLine = line.substring(filter1.length()).trim();
			int index = subLine.indexOf(" ");
			if (index > 0) {
			    subLine = subLine.substring(0, index);
			}
			data.add(filter1 + " " + subLine);
		    } else  if (line.toUpperCase().startsWith(filter2.toUpperCase())) {
			String subLine = line.substring(filter2.length()).trim();
			int index = subLine.indexOf(" ");
			if (index > 0) {
			    subLine = subLine.substring(0, index);
			}
			data.add("   " + filter2 + " " + subLine + ";");
		    }
		}
	    }
	    writer = new BufferedWriter(new FileWriter(outputFile));
	    for (int i = 0; i < data.size(); i++) {
		    writer.write(data.get(i) + "\n");
		}
	    writer.flush();
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
		if (writer != null) {
		    writer.close();
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	
	    
    }
    
    
    public static void main(String[] args) {
	if (args.length < 2) {
	    System.out.println("Invalid arguments");
	    return;
	}
	try {
	    String inputFile = args[0];
	    String outputFile = args[1];
	    TextWorker.specialText(inputFile, outputFile);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    
    public static void main1(String[] args) {
	if (args.length < 3) {
	    System.out.println("Invalid arguments");
	    return;
	}
	try {
	    String inputFile = args[0];
	    String outputFile = args[1];
	    StringBuffer buf = new StringBuffer();
	    for (int i = 2; i < args.length; i++) {
		if (i > 0) {
		    buf.append(" ");
		}
		buf.append(args[i]);
	    }
//	    String filter = args[2];
	    String filter = buf.toString();
	    TextWorker.filterText(inputFile, outputFile, filter, -1, UPPER);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
