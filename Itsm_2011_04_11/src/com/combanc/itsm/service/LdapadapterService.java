package com.combanc.itsm.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.LdapadapterDAO;
import com.combanc.itsm.pojo.Ldapadapter;

public class LdapadapterService extends BaseService<Ldapadapter, Integer> {
	
	private LdapadapterDAO LdapadapterDAO;


	private String logPath;

	private String ldapLogUid;

	private String ldapLogPwd;

	private String srvName;

	private int ldapPort = 389;

	private String baseDn = "o=tcl,dc=cn";

	private DirContext ldapContext;

	private String treeName;
	
	final String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
	
	/**
	 * @return the logPath
	 */
	public String getLogPath() {
		return logPath;
	}

	/**
	 * @param logPath the logPath to set
	 */
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	/**
	 * @return the ldapLogUid
	 */
	public String getLdapLogUid() {
		return ldapLogUid;
	}

	/**
	 * @param ldapLogUid the ldapLogUid to set
	 */
	public void setLdapLogUid(String ldapLogUid) {
		this.ldapLogUid = ldapLogUid;
	}

	/**
	 * @return the ldapLogPwd
	 */
	public String getLdapLogPwd() {
		return ldapLogPwd;
	}

	/**
	 * @param ldapLogPwd the ldapLogPwd to set
	 */
	public void setLdapLogPwd(String ldapLogPwd) {
		this.ldapLogPwd = ldapLogPwd;
	}

	/**
	 * @return the srvName
	 */
	public String getSrvName() {
		return srvName;
	}

	/**
	 * @param srvName the srvName to set
	 */
	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	/**
	 * @return the ldapPort
	 */
	public int getLdapPort() {
		return ldapPort;
	}

	/**
	 * @param ldapPort the ldapPort to set
	 */
	public void setLdapPort(int ldapPort) {
		this.ldapPort = ldapPort;
	}

	/**
	 * @return the baseDn
	 */
	public String getBaseDn() {
		return baseDn;
	}

	/**
	 * @param baseDn the baseDn to set
	 */
	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	/**
	 * @return the ldapContext
	 */
	public DirContext getLdapContext() {
		return ldapContext;
	}

	/**
	 * @param ldapContext the ldapContext to set
	 */
	public void setLdapContext(DirContext ldapContext) {
		this.ldapContext = ldapContext;
	}

	/**
	 * @return the treeName
	 */
	public String getTreeName() {
		return treeName;
	}

	/**
	 * @param treeName the treeName to set
	 */
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}


	public LdapadapterDAO getLdapadapterDAO() {
		return LdapadapterDAO;
	}

	public void setLdapadapterDAO(LdapadapterDAO ldapadapterDAO) {
		super.setDao(ldapadapterDAO);
		LdapadapterDAO = ldapadapterDAO;
	}
	
	
	public void conn() {
		this.setSrvName("localhost");
		this.setLdapPort(389);
		this.setLdapLogUid("cn=Manager,o=tcl,c=cn");

		// this.setLogUid("cn=admin"+","+"dc=mor,dc=gov,dc=cn");
		this.setLdapLogPwd("123456");
		this.setBaseDn(baseDn);
		 this.setLogPath("f:\\test.log");
		/*
		 * 
		 * demo.setSrvName("192.168.129.202"); demo.setLdapPort(389);
		 * demo.setLogUid("cn=Directory Manager"); demo.setLogPwd("password");
		 * demo.setPabBaseDn("dc=dlys,dc=petrochina");
		 * demo.setLogPath("f:\\test.log");
		 */
		try {
			this.init();
		} catch (NamingException e) {
			System.out.println("ldap认证失败");
			e.printStackTrace();
		}
	}
	public void init() throws javax.naming.NamingException {
		

		Hashtable env = new Hashtable(10, 0.75f);

		env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);

		/* Specify host and port to use for directory service */
		env.put(Context.PROVIDER_URL, "ldap://" + srvName + ":" + ldapPort);

		/* specify authentication information */
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldapLogUid);
		env.put(Context.SECURITY_CREDENTIALS, ldapLogPwd);
		/* get a handle to an Initial DirContext */
		ldapContext = new InitialDirContext(env);
		System.out.println("ldap认证成功");
	}
	public void close() {
		try {
			this.ldapContext.close();
		} catch (NamingException e) {
	
			e.printStackTrace();
		}
	}
	public boolean addUser(String loginName, String pwd, String path) {
		boolean modified = true;
		Attributes atts = new BasicAttributes();
		atts.put("objectClass", "cmuuserinfo");
		atts.put("CMUName", loginName);
		atts.put("CMUPassword", pwd);
		try {
			ldapContext.createSubcontext("CMUName=" + loginName + "," + "o="
					+ path + "," + baseDn, atts);
		} catch (NamingException e) {
			modified = false;
			e.printStackTrace();
			return modified;
		}
		return modified;
	}

	public boolean modUser(String userName, String pwd, String groupName,
			String oldGroupName) {

		try {
			boolean modified = false;
			// set log
			String filter = "CMUName=" + userName;
			SearchResult entry = null;
			SearchControls pabConstraints = new SearchControls();
			pabConstraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration neUsers = ldapContext.search(baseDn, filter,
					pabConstraints);
			Attributes atts = null;
			Attribute att = null;
			while (neUsers.hasMore()) {
				try {
					modified = false;
					entry = (SearchResult) neUsers.nextElement();
					atts = entry.getAttributes();
					if (atts != null) {
						att = atts.get("CMUPassword");
						if (att != null && att.size() > 0) {
							att.set(0, pwd);
						} else {
							atts.put("CMUPassword", pwd);
						}
					}
					if (groupName.equals(oldGroupName)) {
						ldapContext.modifyAttributes(entry.getName() + ","
								+ baseDn, DirContext.REPLACE_ATTRIBUTE, atts);
					} else {
						ldapContext.destroySubcontext("CMUName=" + userName + ",o=" + oldGroupName
								+ "," + baseDn);
						String[] groups = entry.getName().split(",");
						ldapContext.createSubcontext(groups[0].toString()
								+ ",o=" + groupName + "," + baseDn, atts);
						
//						
//						String[] groups = entry.getName().split(",");
//						System.out.println(groups[0].toString()
//								+ ",o=" + groupName + "," + baseDn);
//						ldapContext.modifyAttributes(groups[0].toString()
//								+ ",o=" + oldGroupName + "," + baseDn,
//								DirContext.REPLACE_ATTRIBUTE, atts);
					}

				} catch (Exception e) {
                 e.printStackTrace();
				}
			}
		} catch (Exception e) {
			   e.printStackTrace();
		}

		return true;

	}

	public boolean delUser(String userName, String group) {

		try {
			ldapContext.destroySubcontext("CMUName=" + userName + ",o=" + group
					+ "," + baseDn);
		} catch (NamingException e) {
			return false;

		}
		return true;
	}

	public void update(String ip, String port, String group)

	{

		try {
			boolean modified = false;
			// set log
			String filter = "o="+group;
			SearchResult entry = null;
			SearchControls pabConstraints = new SearchControls();
			pabConstraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration neUsers = ldapContext.search(baseDn, filter,
					pabConstraints);
			Attributes atts = null;
			Attribute att = null;
			while (neUsers.hasMore()) {
				try {
					modified = false;
					entry = (SearchResult) neUsers.nextElement();
					atts = entry.getAttributes();
					if (atts != null) {
						att = atts.get("ip");
						if (att != null && att.size() > 0) {
							att.set(0, ip);
						} else {
							atts.put("ip", ip);
						}
						att = atts.get("port");
						if (att != null && att.size() > 0) {
							att.set(0, port);
						} else {
							atts.put("port", port);
						}
					}
					ldapContext.modifyAttributes(baseDn,
							DirContext.REPLACE_ATTRIBUTE, atts);
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {

		}

	}
}
