package com.combanc.itsm.pojo;



/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String ownertype;
     private Integer owenr;
     private String privtype;
     private String mid;
     private String code;
     private String value;


    // Constructors

    /** default constructor */
    public Privilege() {
    }

    
    /** full constructor */
    public Privilege(String ownertype, Integer owenr, String privtype, String mid, String code, String value) {
        this.ownertype = ownertype;
        this.owenr = owenr;
        this.privtype = privtype;
        this.mid = mid;
        this.code = code;
        this.value = value;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnertype() {
        return this.ownertype;
    }
    
    public void setOwnertype(String ownertype) {
        this.ownertype = ownertype;
    }

    public Integer getOwenr() {
        return this.owenr;
    }
    
    public void setOwenr(Integer owenr) {
        this.owenr = owenr;
    }

    public String getPrivtype() {
        return this.privtype;
    }
    
    public void setPrivtype(String privtype) {
        this.privtype = privtype;
    }

    public String getMid() {
        return this.mid;
    }
    
    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
   








}