/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.combanc.itsm.ws;

/**
 *
 * @author Administrator
 */
public class AssetsState {

    private int id;
    private String name;

    public AssetsState(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
