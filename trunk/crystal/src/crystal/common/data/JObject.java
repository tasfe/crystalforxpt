package crystal.common.data;

import javax.swing.Icon;

public class JObject {
	
	private Integer id;
    private String name;
    private Icon icon;
    
    public String toString() {
    	return name;
    }
    
    public JObject(Integer id, String name) {
    	super();
        this.id = id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

}
