package MOBLIMA;

import java.io.Serializable;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5702367436335829557L;
	private String accountID;
	private String name;
	private String pwd;
	private Boolean isStaff;
	
	public Account() {
		this.pwd="123456";
	}
	
	public Account(String accountID,String name,String pwd,Boolean isStaff) {
		this.accountID=accountID;
		this.name=name;
		this.pwd=pwd;
		this.isStaff=isStaff;
	}
	


	/**
	 * Only administrator can perform this operation
	 */
	public void resetAccountPwd() {
		String pwd=MyFuns.getInput_str("Please enter the new password of "+this.accountID+" : ", false);	

		this.setPwd(pwd);
		System.out.println("Password reset  complete");
	}

	/**
	 * Staff changes his account password
	 */
	public void changeAccountPwd() {
		String s="";
		while(!s.equals(this.pwd)) {
			s=MyFuns.getInput_str("Please enter the old password : ", false);			
		}
		s="";
		while(s.equals("")) {
			s=MyFuns.getInput_str("Please enter the new password : ", false);			
		}
		String s2="";
		while(s2.equals("")) {
			s2=MyFuns.getInput_str("Please enter the new password again : ", false);	
			if(!s2.equals(s)) {
				System.out.println("The two new passwords must be the same");
			}
		}
		this.pwd=s;
		System.out.println("Password changed successfully");
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getIsStaff() {
		return isStaff;
	}

	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}

	
}
