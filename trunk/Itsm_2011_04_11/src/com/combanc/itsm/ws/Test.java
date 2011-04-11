package com.combanc.itsm.ws;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Test {
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public byte[] serialize() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try{
               dos.writeUTF(property);
               baos.close();
               dos.close();
        }
        catch(Exception exc){
               exc.printStackTrace();
        }
        return baos.toByteArray();
	 }
	 public static Test deserialize(byte[] data){
		 ByteArrayInputStream bais = new ByteArrayInputStream(data);
	     DataInputStream dis = new DataInputStream(bais);
	     Test test=new Test();
	     try{
	    	 test.property=dis.readUTF();
	    	 bais.close();
	    	 dis.close();
	     }catch (IOException e) {
	    	 System.out.println("deserialize faliled!");
		}
	     return test;
	 }


}
