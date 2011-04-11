/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.combanc.itsm.ws;

/**
 *
 * @author Administrator
 */
public class RequestInfo {
  private String RequestNo;
    private int id;
    private String department;
    private String submintTime;
    private String errorCause;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestNo() {
        return RequestNo;
    }

    public void setRequestNo(String RequestNo) {
        this.RequestNo = RequestNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubmintTime() {
        return submintTime;
    }

    public void setSubmintTime(String submintTime) {
        this.submintTime = submintTime;
    }
}
