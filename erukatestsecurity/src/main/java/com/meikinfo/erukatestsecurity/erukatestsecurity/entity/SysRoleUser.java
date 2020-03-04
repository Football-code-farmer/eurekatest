package com.meikinfo.erukatestsecurity.erukatestsecurity.entity;


/**
 * @Description  
 * @Author  Mr.hws
 * @Date 2019-12-27 
 */
public class SysRoleUser {
	private int id;
	private int sysUserId;
	private int sysRoleId;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public int getSysRoleId() {
		return this.sysRoleId;
	}

	public void setSysRoleId(int sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}
