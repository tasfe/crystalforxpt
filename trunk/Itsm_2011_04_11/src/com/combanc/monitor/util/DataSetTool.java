package com.combanc.monitor.util;

import java.io.File;

import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;

public class DataSetTool {
  public DataSetTool() {
  }

  public static TableDataSet initDataSet(String[] columnNames, int[] columnTypes) {
	    if(columnNames.length != columnTypes.length)
	      return null;
	    int[] columnWidth = new int[columnNames.length];
	    for(int i = 0; i < columnNames.length;i++)
	      columnWidth[i] = 0;
	    return initDataSet("tmp.txt", columnNames, columnTypes, columnWidth);
	  }
  
  public static TableDataSet initDataSet(String fileName, String[] columnNames, int[] columnTypes) {
    if(fileName == null || fileName.equals("") || columnNames.length <= 0)
      return null;
    if(columnNames.length != columnTypes.length)
      return null;
    int[] columnWidth = new int[columnNames.length];
    for(int i = 0; i < columnNames.length;i++)
      columnWidth[i] = 0;
    return initDataSet(fileName, columnNames, columnTypes, columnWidth);
  }

  public static TableDataSet initDataSet(String fileName, String[] columnNames, int[] columnTypes, int[] columnWidth) {
    if(fileName == null || fileName.equals("") || columnNames.length <= 0)
      return null;
    if (! (columnNames.length == columnTypes.length &&
           columnNames.length == columnWidth.length))
      return null;
    TextDataFile resultDF = new TextDataFile();
    TableDataSet resultDS = new TableDataSet();
    int columnNum;
    File f = new File(fileName);
//文件存在
    if(f.exists()) {
      resultDF.setFileName(fileName);
      resultDF.setLoadAsInserted(true);
      resultDS.setDataFile(resultDF);
      return resultDS;
//文件不存在，在内存中构造
    }else {
      columnNum = columnNames.length;
      Column columns[] = new Column[columnNum];
      for(int i = 0; i < columnNum; i++) {
        columns[i] = new Column();
        columns[i].setColumnName(columnNames[i]);
        columns[i].setDataType(columnTypes[i]);
        columns[i].setWidth(columnWidth[i]);
        resultDS.addColumn(columns[i]);
      }
      return resultDS;
    }
  }
}
