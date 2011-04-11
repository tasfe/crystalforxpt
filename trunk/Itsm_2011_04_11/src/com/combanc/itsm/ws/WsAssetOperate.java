package com.combanc.itsm.ws;

/*
 * author:jyxiao
 * 功能：此类用于向外提供webservice接口
 * 在里面添加方法后，请重新生成sever-config.wsdd文件
 * 所有方法放入一个service中，便于管理session
 */
import com.combanc.commonsupport.dataaccess.Abstract.AbstractDataAccessFacade;
import com.combanc.commonsupport.dataaccess.factory.DataAccessFacadeCreator;
import com.combanc.itsm.pojo.Department;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.axis.MessageContext;
import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsTypeDAO;
import com.combanc.itsm.dao.RequestAssetsDAO;
import com.combanc.itsm.dao.UserDAO;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsChange;
import com.combanc.itsm.pojo.AssetsInfo;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.RequestAssets;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AssetsChangeService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.ServiceTranService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.util.OperationUtil;
import com.combanc.itsm.util.SpringBeanProxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//@Component
public class WsAssetOperate implements WsAssetsInterface {
//	@Resource

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
    private AssetsChangeService assetsChangeService;
    private RequestAssetsDAO requestAssetsDAO;
    private ServiceRequestService serviceRequestService;
    private ServiceTranService serviceTranService;

    public WsAssetOperate() {
        initDao();
        System.out.println("初始化");
    }

    /*
     * webservice:登录接口
     */
    public String login(String username, String password) {
        if (null == username || username.trim().equals("") || null == password
                || password.trim().equals("")) {
            return "false";
        }
        if (validate(username, password)) {
            MessageContext mc = MessageContext.getCurrentContext();
            mc.getSession().set("login", "登录成功");
            return "true";
        }
        return "false";
    }

    /*
     * 验证调用webservice时是否登录:客户端在调用时应显式的设置维护session
     */
    public boolean getLoginMessage() {
        MessageContext mc = MessageContext.getCurrentContext();
        if (mc.getSession().get("login") == null) {
            return false;
        }
        return ((String) mc.getSession().get("login")).equals("登录成功") ? true
                : false;
    }

    /*
     * 初始化bean,没有用spring与webservice联合开发，自己实现servlet来获得bean
     * 用到spring配置文件中的bean的一律在此初始化
     */
    public void initDao() {
        if (this.userDAO == null) {
            this.userDAO = (UserDAO) SpringBeanProxy.getBean("userDAO");
        }
        if (this.assetsBaseDAO == null) {
            this.assetsBaseDAO = (AssetsBaseDAO) SpringBeanProxy.getBean("assetsBaseDAO");
        }
        if (this.assetsTypeService == null) {
            this.assetsTypeService = (AssetsTypeService) SpringBeanProxy.getBean("assetsTypeService");
        }
        if (this.assetsService == null) {
            this.assetsService = (AssetsService) SpringBeanProxy.getBean("assetsService");
        }
        if (this.assetsStateService == null) {
            this.assetsStateService = (AssetsStateService) SpringBeanProxy.getBean("assetsStateService");
        }
        if (this.assetsTypeDAO == null) {
            this.assetsTypeDAO = (AssetsTypeDAO) SpringBeanProxy.getBean("assetsTypeDAO");
        }
        if (this.departmentService == null) {
            this.departmentService = (DepartmentService) SpringBeanProxy.getBean("departmentService");
        }

        if (this.userService == null) {
            this.userService = (UserService) SpringBeanProxy.getBean("userService");
        }
        if (this.assetsChangeService == null) {
            this.assetsChangeService = (AssetsChangeService) SpringBeanProxy.getBean("assetsChangeService");
        }

        if (this.serviceRequestService == null) {
            this.serviceRequestService = (ServiceRequestService) SpringBeanProxy.getBean("serviceRequestService");
        }
        if (this.serviceTranService == null) {
            this.serviceTranService = (ServiceTranService) SpringBeanProxy.getBean("serviceTranService");
        }

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

    /*
     * 测试：返回大写
     */
    public String toUpper(String toUpperStr) {
        if (getLoginMessage()) {// 此方法验证客户端session
            if (toUpperStr == null) {
                return "字符串为空!!!";
            }
            return toUpperStr.toUpperCase();
        }
        return "非法调用，请先登录!!!";
    }

    /*
     * public byte[] getTest(){ Test t1=new Test();
     * t1.setProperty("t1ngjghjgjh"); return t1.serialize(); }
     */
    public ArrayList geTest() {
        ArrayList list = new ArrayList();
        // Test t1=new Test();
        // t1.setProperty("t1ng");
        // list.add(t1);
        // Test t2=new Test();
        // t1.setProperty("t1ng");
        // list.add(t2);

        return list;
    }

    /*
     * 新增资产
     */
    public String saveAssets(String loginName, String code, String typeName, String date, String department) {
        AssetsBase a = new AssetsBase();
        if (assetsService.findByCodeId(code) != null) {
            return "1"; //资产已经存在！
        }
        Users u = userService.findByUsername(loginName);
        if (u == null) {
            return "2"; //无此用户
        }
        a.setUsersByChargeId(u);
        a.setCodeId(code);
        a.setM_des(typeName+"--"+department);
//        List dplist = departmentService.findByName(department);
//        if (dplist == null || dplist.isEmpty()) {
//            return "3";
//        }
//
//        a.setDepartment((Department) dplist.get(0));
        try {

            a.setInDate(StringTime(date));
        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println(a.getCodeId());
        System.out.println(a.getInDate());
        try {
            assetsService.save(a);
            AssetsInfo info = new AssetsInfo();
            info.setAssetsBase(a);

            assetsService.assetsinfosave(info);
        } catch (Exception e) {
            e.printStackTrace();
            return "99"; //异常错误
        }

        return "0";
    }

    public String updateAssets(String loginName, String code, String state, String date, String toUser, String charge,String his) {
        AssetsChange ac = new AssetsChange();
        try {
            AssetsBase assetsBase = assetsService.findByCodeId(code);
            if (assetsBase == null) {
                return "1";
            }
            if (loginName != null && !loginName.equals("")) {
                Users u = userService.findByUsername(loginName);
                if (u == null) {
                    return "2"; //无此用户

                }
                ac.setUsersByManagersid(u);

            }



            if (toUser != null && !toUser.isEmpty()) {
                Users tu = userService.findByUsername(toUser);
                if (tu == null) {
                    return "3"; //无此用户
                }
                ac.setUsersByUserid(tu);
                assetsBase.setUsersByUsersId(tu);
            }
            ac.setUsersByUserid(assetsBase.getUsersByUsersId());

            if (charge != null && !charge.equals("")) {
                Users cu = userService.findByUsername(charge);
                if (cu == null) {
                    return "3"; //无此用户
                }
                ac.setUsersByChargeid(cu);
                assetsBase.setUsersByChargeId(cu);
            }
            ac.setUsersByChargeid(assetsBase.getUsersByChargeId());
            ac.setChangeTime(StringTime(date));
            ac.setAssetsBase(assetsBase);
            AssetsState state2 = assetsStateService.findbyNames(state);
            ac.setAssetsState(state2);
            assetsBase.setAssetsState(state2);
            if(his!=null)
            {
                if(his.equals("true"))
                {
                   assetsBase.setIshis(1);
                 }
                else{
                    assetsBase.setIshis(0);
                }
            }
         
            assetsService.updateAssetsByCode(assetsBase);
            assetsChangeService.save(ac);

        } catch (Exception e) {
            e.printStackTrace();
            return "99";
        }
        return "0";
    }

    public String delAssets(String code) {
        return "true";

    }

    public List<String> getAssetsType() {

        List<String> aList = new ArrayList<String>();

        List<AssetsType> typelistAssetsTypes = assetsTypeDAO.findAll();
        String tmpString = "";
        for (AssetsType t : typelistAssetsTypes) {
            tmpString = t.getId() + "," + t.getName();
            aList.add(tmpString);
        }
        return aList;

    }

    public Timestamp StringTime(String value) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return new Timestamp(simpleDateFormat.parse(value).getTime());
    }

    public List<String> SynAssetsStates() {
        List<String> statesList = new ArrayList<String>();
        List<AssetsState> sList = assetsStateService.findAll();
        String tmpString = "";
        for (AssetsState state : sList) {
            if(state.getName().equals("入库"))
            {
                continue;
            }
            tmpString = state.getId() + "," + state.getName();
            statesList.add(tmpString);
        }
        return statesList;
    }

    public List<String> SynDepartment() {
        List<String> statesList = new ArrayList<String>();
        List<com.combanc.itsm.pojo.Department> sList = departmentService.findAll();
        String tmpString = "";
        for (com.combanc.itsm.pojo.Department dp : sList) {
            tmpString = dp.getId() + "," + dp.getName();
            statesList.add(tmpString);
        }
        return statesList;
    }

    public String getRequestByAssetsId(String assetsId, String userName) {

        Iterator it = null;
        //List<String> relist = new ArrayList<String>();
        if (assetsId != null && !assetsId.equals("")) {
            AssetsBase ab = assetsBaseDAO.findByCodeId(assetsId);
            if (ab == null) {
                return "1";
            }
            Set<ServiceRequest> set = ab.getServiceRequests();

            it = set.iterator();
        }
        else if (userName != null && !userName.equals("")) {
            Users tu = userService.findByUsername(userName);
            if (tu == null) {
                return "2";
            }
            List<ServiceRequest> list = serviceRequestService.findNotFinishedByUserId(tu.getId());
            if (list == null || list.isEmpty()) {
                return null;
            }
            it = list.iterator();

        }
        else
        {
            return null;
        }
        StringBuffer info = new StringBuffer();
        for (; it.hasNext();) {
            ServiceRequest e = (ServiceRequest) it.next();

          //  if (e.getIsFinished() == null) {

                info.append(
                        e.getRequestNo()).append(",").append(e.getDepartmentByRequestDept().getName()).append(",").append(e.getSubmintTime()).append(",").append(e.getDescription()).append(",").append(e.getErrorCause());

                info.append("@");


                //  +","+e.getId() + "," +e.getDepartmentByRequestDept()+","+e.getSubmintTime()+","+ e.getDescription() + "," + e.getCause();
                //   relist.add(info);
          //  }
        }
        System.out.println(info.toString());
        return info.toString();

    }

    public String getRequestByLoginUser(String userName) {

        Users tu = userService.findByUsername(userName);
        if (tu == null) {
            return null;
        }
        List<ServiceRequest> list = serviceRequestService.findNotFinishedByUserId(tu.getId());
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuffer info = new StringBuffer();
        Iterator it = list.iterator();

        for (; it.hasNext();) {
            ServiceRequest e = (ServiceRequest) it.next();

            if (e.getIsFinished() == null) {

                info.append(
                        e.getRequestNo()).append(",").append(e.getDepartmentByRequestDept().getName()).append(",").append(e.getSubmintTime()).append(",").append(e.getDescription()).append(",").append(e.getErrorCause());

                info.append("@");
            }
        }
        System.out.println(info.toString());
        return info.toString();

    }

    public String getRequestInfoByRequestId(String requestId) {
        StringBuffer info = new StringBuffer();
        ServiceRequest request = serviceRequestService.findServiceRequestById(Integer.valueOf(requestId));
        info.append("保修日期：" + request.getSubmintTime() + "\n");
        info.append("保修部门" + request.getDepartmentByRequestDept().getName() + "\n");
        return info.toString();
    }

    public String confirmRequest(String requestNo, String confirmName,
            String loginName, String date,String assetsNo,String requestresion) {
        String re = "0";
        ServiceTran serviceTran = new ServiceTran();
        ServiceRequest serviceRequest = serviceRequestService.findServiceRequestByRequestNo(requestNo);
        serviceTran.setUsersByServiceFrom(commons(loginName));
        Timestamp finishtime = new Timestamp(System.currentTimeMillis());
        try {
            serviceTran.setOperatorTime(finishtime);
        } catch (Exception e) {
          
            e.printStackTrace();
            re = "1"; //时间不正确

        }
        serviceTran.setType(OperationUtil.FEEDBACK_CN);
        serviceTran.setRequNo(requestNo);
        //serviceTran.setNote(serviceRequest.getFeedback());
        serviceTranService.save(serviceTran);
        serviceRequest.setUsersByEngineerId(commons(loginName));
        serviceRequest.setTemp2(confirmName);
        
        serviceRequest.setFinishTime(finishtime);
        try {
			serviceRequest.setBeginTime(StringTime(date));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       serviceRequest.setDescription(serviceRequest.getDescription()+ requestresion) ;
       if(assetsNo!=null&&!assetsNo.equals(""))
       {
           	AssetsBase u = assetsBaseDAO.findByCodeId(assetsNo);
				if(u!=null)
                                {
                                 serviceRequest.getAssets().add(u);
                                }
                                else{
                                   return "2";
                                }
				
            
       }
      
        try {

            serviceRequestService.feedback(serviceRequest);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            re = "2";//b保存失败
        }
        return re;
    }

    private Users commons(String loginName) {
        if (loginName != null && !loginName.isEmpty()) {
            return userService.findByUsername(loginName);

        } else {
            return null;
        }

    }

}
