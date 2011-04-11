package com.combanc.common.jbpm;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;

import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.UserService;

public class UserSession implements IdentitySession {

	private UserService userService;

	private RoleteamService roleteamService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	public String createGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	public String createUser(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteGroup(String arg0) {
		// TODO Auto-generated method stub

	}

	public void deleteMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub

	}

	public Group findGroupById(String groupId) {
		// TODO Auto-generated method stub
		return (Group) this.roleteamService.get(Roleteam.class, new Integer(
				groupId));
	}

	public List<Group> findGroupsByUser(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Group> findGroupsByUserAndGroupType(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findUserById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findUsersByGroup(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findUsersById(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
