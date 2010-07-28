package crystal.common;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import crystal.MainFrame;

public class ExcelUtil {
	public static void exportExcel(JPanel jPanel, String fileName,
			String sheetName, String columnName[], int columnLen[],
			List tableData) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("导出视图");
		chooser.setApproveButtonText("确认");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setSelectedFile(new File(fileName));
		int retVal = chooser.showOpenDialog(jPanel);
		if (retVal != JFileChooser.APPROVE_OPTION)
			return;
		String fname = chooser.getSelectedFile().toString();
		File f = new File(fname);
		if (f.exists()) {
			int confirm = JOptionPane.showConfirmDialog(jPanel, "文件已存在，是否导出？",
					"导出文件", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION)
				return;
		}
		// 导出文件
		try {
			FileOutputStream fos = new FileOutputStream(f);
			// 建立excel表格
			WritableWorkbook wbook = Workbook.createWorkbook(fos);
			// 工作表名称
			WritableSheet wsheet = wbook.createSheet(sheetName, 0);
			// 设置字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL,
					14,
					// WritableFont.NO_BOLD, false,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			// 设置表头
			for (int i = 0; i < columnName.length; i++) {
				Label excelTitle;
				if (columnName[i].endsWith("timestamp"))
					excelTitle = new Label(i, 0, columnName[i].replaceFirst(
							"timestamp", ""), titleFormat);
				else
					excelTitle = new Label(i, 0, columnName[i], titleFormat);
				wsheet.addCell(excelTitle);
				wsheet.setColumnView(i, columnLen[i]);
			}
			// excel表格行号
			int num = 1;
			if (tableData.size() == 0) {
				System.out.println("tableData.size == 0");
				return;
			}

			Label[] content = new Label[columnName.length];
			for (Iterator it = tableData.iterator(); it.hasNext();) {
				Vector row = (Vector) it.next();
				for (int i = 0; i < columnName.length; i++) {
					// 通过传入的列标题来判断timestamp类型，否则默认String类型
					if (columnName[i].endsWith("timestamp")) {
						content[i] = new Label(i, num, row.get(i) == null ? ""
								: (String) row.get(i).toString());
					} else {
						content[i] = new Label(i, num, row.get(i) == null ? ""
								: (String) row.get(i).toString());
					}
					wsheet.addCell(content[i]);
				}
				MainFrame.statusBar.setText("write excel row:   " + num);
				num++;
			}

			// 写入excel
			wbook.write();
			wbook.close();
			fos.close();
			JOptionPane.showMessageDialog(null, "信息：" + fileName + "导出完毕！");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
