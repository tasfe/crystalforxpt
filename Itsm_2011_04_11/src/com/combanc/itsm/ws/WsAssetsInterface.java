/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.combanc.itsm.ws;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface WsAssetsInterface {

    public List<String> SynAssetsStates();

    public List<String> SynDepartment();

    public String delAssets(String code);

    public List<String> getAssetsType();

    public String login(String username, String password);

    public String saveAssets(String loginName, String code, String typeName, String date, String department);

    public String updateAssets(String loginName, String code, String state, String date, String toUser,String charge,String his);

    public String getRequestByAssetsId(String assetsId,String userName);

    public String getRequestInfoByRequestId(String requestId);
    
    public String confirmRequest(String requestNo,String confirmName,String loginName,String date,String assetsNo,String requestresion);
    
    
}
