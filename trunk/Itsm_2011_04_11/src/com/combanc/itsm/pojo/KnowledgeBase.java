package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



/**
 * KnowledgeBase entity. @author MyEclipse Persistence Tools
 */

public class KnowledgeBase  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String title;
     private ServiceCategory categoryId;
     private String symptom;
     private String indexcode;
     private String solution;
     private Users engineerId;
     private String mode;
     private Timestamp currentTime;
 	 private Set accessoryList = new HashSet(0);



    // Constructors



	public Set getAccessoryList() {
		return accessoryList;
	}


	public void setAccessoryList(Set accessoryList) {
		this.accessoryList = accessoryList;
	}


	public KnowledgeBase(String title, ServiceCategory categoryId,
			String symptom, String indexcode, String solution,
			Users engineerId, String mode, Timestamp currentTime) {
		super();
		this.title = title;
		this.categoryId = categoryId;
		this.symptom = symptom;
		this.indexcode = indexcode;
		this.solution = solution;
		this.engineerId = engineerId;
		this.mode = mode;
		this.currentTime = currentTime;
	}


	/** default constructor */
    public KnowledgeBase() {
    }

    
    /** full constructor */


   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

  

    public ServiceCategory getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(ServiceCategory categoryId) {
		this.categoryId = categoryId;
	}


	public String getSymptom() {
        return this.symptom;
    }
    
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }


    public String getSolution() {
        return this.solution;
    }
    
    public void setSolution(String solution) {
        this.solution = solution;
    }


    public Users getEngineerId() {
		return engineerId;
	}


	public void setEngineerId(Users engineerId) {
		this.engineerId = engineerId;
	}


	public String getMode() {
        return this.mode;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }


	public Timestamp getCurrentTime() {
		return this.currentTime;
	}


	public void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}


	public String getIndexcode() {
		return indexcode;
	}


	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}

}