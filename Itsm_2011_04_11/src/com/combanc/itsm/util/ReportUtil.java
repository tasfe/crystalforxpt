package com.combanc.itsm.util;

import com.raisepartner.chartfusion.api.Category;
import com.raisepartner.chartfusion.api.column3d.Column3D;
import com.raisepartner.chartfusion.api.column3d.Column3DSet;
import com.raisepartner.chartfusion.api.mscolumn3d.MSColumn3DCategories;
import com.raisepartner.chartfusion.api.mscolumn3d.MSColumn3DDataset;
import com.raisepartner.chartfusion.api.mscolumn3d.MSColumn3DSet;
import com.raisepartner.chartfusion.api.msline.MSLineCategories;
import com.raisepartner.chartfusion.api.msline.MSLineDataset;
import com.raisepartner.chartfusion.api.msline.MSLineSet;
import com.raisepartner.chartfusion.api.pie3d.Pie3D;
import com.raisepartner.chartfusion.api.pie3d.Pie3DSet;

public class ReportUtil {
	public static Category setValue(MSLineCategories categories, String label) {
		Category c = categories.createCategoryNode();
		c.setLabel(label);
		return c;
	}

	public static MSLineSet setValue(MSLineDataset dataset, String value) {
		MSLineSet s = dataset.createSetNode();
		s.setValue(value);
		return s;
	}

	public static Column3DSet setValue(Column3D column, String label,
			String value) {
		Column3DSet s = column.createSetNode();
		s.setLabel(label);
		s.setValue(value);
		return s;
	}

	public static Category setValue(MSColumn3DCategories categories,
			String label) {
		Category c = categories.createCategoryNode();
		c.setLabel(label);
		return c;
	}

	public static MSColumn3DSet setValue(MSColumn3DDataset dataset, String value) {
		MSColumn3DSet s = dataset.createSetNode();
		s.setValue(value);
		return s;
	}

	public static Pie3DSet setValue(Pie3D chart, String label, String value) {
		Pie3DSet s = chart.createSetNode();
		s.setLabel(label);
		s.setValue(value);
		return s;
	}

	public static Pie3DSet setValue(Pie3D chart, String label, String value,
			String sliced) {
		Pie3DSet s = chart.createSetNode();
		s.setLabel(label);
		s.setValue(value);
		s.setIsSliced(sliced);
		return s;
	}

	// ��CPUռ���ʽ���ͳ�ƣ��ֶ�Ϊ2,5,10,20,30,40,50,60,80,100
	public static void countByValue(float value, double[] result) {
		if (value <= 2 && value >= 0) {
			result[0] += 1.0D;
		} else if (value <= 5) {
			result[1] += 1.0D;
		} else if (value <= 10) {
			result[2] += 1.0D;
		} else if (value <= 20) {
			result[3] += 1.0D;
		} else if (value <= 30) {
			result[4] += 1.0D;
		} else if (value <= 40) {
			result[5] += 1.0D;
		} else if (value <= 50) {
			result[6] += 1.0D;
		} else if (value <= 60) {
			result[7] += 1.0D;
		} else if (value <= 80) {
			result[8] += 1.0D;
		} else if (value <= 100) {
			result[9] += 1.0D;
		}
	}

	// �������������ͳ�ƣ��ֶ�Ϊ1M,10M,50M,100M,200M,400M,600M,800M,1G,1G����
	public static void countByValue(double value, double[] result) {
		double _1M = 1024D * 1024D;
		if (value <= _1M && value >= 0) {
			result[0] += 1.0D;
		} else if (value <= _1M * 10) {
			result[1] += 1.0D;
		} else if (value <= _1M * 50) {
			result[2] += 1.0D;
		} else if (value <= _1M * 100) {
			result[3] += 1.0D;
		} else if (value <= _1M * 200) {
			result[4] += 1.0D;
		} else if (value <= _1M * 400) {
			result[5] += 1.0D;
		} else if (value <= _1M * 600) {
			result[6] += 1.0D;
		} else if (value <= _1M * 800) {
			result[7] += 1.0D;
		} else if (value <= _1M * 1024) {
			result[8] += 1.0D;
		} else if (value > _1M * 1024) {
			result[9] += 1.0D;
		}
	}
}
