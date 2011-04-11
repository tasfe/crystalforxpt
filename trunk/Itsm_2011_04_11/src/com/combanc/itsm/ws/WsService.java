/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.combanc.itsm.ws;

import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsTypeDAO;
import com.combanc.itsm.dao.UserDAO;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.UserService;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author Administrator
 */
public class WsService {
    @Resource
    private UserDAO userDAO;
    //@Resource
    private AssetsBaseDAO assetsBaseDAO;
    //@Resource
    private AssetsTypeService assetsTypeService;
    //@Resource
    private AssetsService assetsService;
    //@Resource
    private AssetsStateService assetsStateService;
    //@Resource
    private AssetsTypeDAO assetsTypeDAO;
    private DepartmentService departmentService;
    private UserService userService;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    
    /*
     * 验证有无此用户
     */
    public boolean validate(String username, String password) {
        List list = userDAO.findByUsername(username);
        if (list == null || list.size() == 0) {
            return false;
        }
        if (list != null && list.size() > 0) {
            Users user = (Users) list.get(0);
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
