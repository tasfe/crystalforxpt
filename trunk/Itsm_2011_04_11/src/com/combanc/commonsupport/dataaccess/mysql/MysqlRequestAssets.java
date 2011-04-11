/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.combanc.commonsupport.dataaccess.mysql;

import com.combanc.commonsupport.dataaccess.Abstract.AbstractRequestAssets;

/**
 *
 * @author Administrator
 */
public class MysqlRequestAssets extends AbstractRequestAssets {

    @Override
    public String getRequestIdByAssetsId(int assetsId) {
        String sql = "select  service_id  from  service_request_assets where assets_id= " + assetsId;

        return sql;
    }
}
