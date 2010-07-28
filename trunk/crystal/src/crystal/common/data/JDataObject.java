package crystal.common.data;

import java.text.DecimalFormat;

//数据长度的格式化显示（K, M, G）
public class JDataObject {
	private Long data;
    private String dispData;
    
    public String toString() {
    	return dispData;
    }
    
    public JDataObject(Long d) {
    	super();
        this.data = d;
        this.dispData = dataTransForm(d);
    }

	public Long getData() {
		return data;
	}

	public void setId(Long d) {
		this.data = d;
	}

	public String getDisp() {
		return dispData;
	}
	
	static public String dataTransForm(Long d) {
		String sRet = "";
		DecimalFormat df=new DecimalFormat("#0.00"); 
		if( d < 1024L ) {
			sRet = String.valueOf(d);
		} else if( d < 1024L * 1024L ) {
			float fData = d / 1024.0f;
			sRet = df.format(fData) + "K";
		} else if( d < 1024L * 1024L * 1024L ) {
			float fData = d / (1024.0f * 1024.0f);
			sRet = df.format(fData) + "M";
		} else {
			float fData = d / (1024.0f * 1024.0f * 1024.0f);
			sRet = df.format(fData) + "G";
		}
		return sRet;
	}
}
