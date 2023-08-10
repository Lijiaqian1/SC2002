package MOBLIMA;

import java.io.Serializable;
import java.util.Arrays;
/**
 * 
 * Manage the account of staff 
 * @author lijiaqian
 *
 */
public class AccountLib implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2663321087632245953L;
	
	
	private Account[] accounts;
	//Administrator Account
	private Account adminAccount;
	
	public AccountLib() {
		this.accounts=new Account[0];
		adminAccount=new Account();
	}

	/**
	 * Create a new account
	 * Only administrator can perform this operation
	 */
	public Account createAnAccount() {
		Account account;
		String name,pwd,accountID;
		boolean exist;
		exist=false;
		//accountID
		while(true) {
			exist=false;
			accountID=MyFuns.getInput_str("Input accountID : ", false);
			for(Account ac:accounts) {
				if(ac.getAccountID().equals(accountID)) {
					System.out.println("accountID exist ,please re-input ");
					accountID="";
					exist=true;
					break;
				}
			}
			if(!exist)
				break;
		}
		//name
		name=MyFuns.getInput_str("Input staff name : ", false);
		pwd=MyFuns.getInput_str("Input account pwd : ", false);
		
		account=new Account(accountID,name,pwd,true);
		
		accounts=Arrays.copyOf(accounts,accounts.length+1);
		accounts[accounts.length-1]=account;
		
		System.out.println("Creating an account successfully");
		return account;
	}

	/**
	 * change an account's password
	 * Only administrator can perform this operation
	 */
	public void resetAnAccountPwd() {
		Account account = null;

		account=selectAnAccount();
		
		if(account==null) {
			return;
		}
		else
			account.resetAccountPwd();
	}
	/**
	 * change administrator's password
	 * Only administrator can perform this operation
	 */
	public void changeAdminPwd() {
		String s="";
		while(!s.equals(adminAccount.getPwd())) {
			s=MyFuns.getInput_str("Please enter the old password of admin : ", false);			
		}
		s="";
		while(s.equals("")) {
			s=MyFuns.getInput_str("Please enter the new password of admin : ", false);			
		}
		String s2="";
		while(s2.equals("")) {
			s2=MyFuns.getInput_str("Please enter the new password again : ", false);	
			if(!s2.equals(s)) {
				System.out.println("The two new passwords must be the same");
				s2="";
			}
		}
		adminAccount.setPwd(s);
	}
	/**
	 * Check whether the administrator password is correct
	 * @param pwd
	 * @return if admin's pwd correct,return true
	 */
	public boolean checkAdminPwd(String pwd) {
		return pwd.equals(adminAccount.getPwd());
	}
	
	/**
	 * display accounts list
	 */
	public void showAccountList() {
		System.out.println("The accounts information is as follows:");
		if(accounts.length==0) {
			System.out.println("NA");
			return;
		}
		
		for(Account ac:accounts) {
			System.out.println("AccountID : "+ac.getAccountID()+"\tName : "+ac.getName()+
					"\tIsStaff : "+ac.getIsStaff());
		}
		
	}
	/**
	 * remove an account from account-list
	 */
	public void removeAnAccount() {
		Account account;
		account=selectAnAccount();
		if(account==null)
			return;
		int n=accounts.length;
		int flag=0;
		for(int i=1;i<n;i++) {
			if(accounts[i-1]==account) {
				flag=1;
				continue;
			}
			if (flag==1) {
				accounts[i-1]=accounts[i];
			}
		}
		accounts=Arrays.copyOf(accounts, n-1);
		System.out.println("Account "+account.getAccountID()+" has been removed");
	}
	
	public int getAccountsCount() {
		return accounts.length;
	}
	
	public Account selectAnAccount() {
		String accountID;
		Account account = null;
		if(accounts.length==0) {
			return null;
		}

		accountID=MyFuns.getInput_str("Input accountID : ", false);
		for(Account ac:accounts) {
			if(ac.getAccountID().equals(accountID)) {
				account=ac;
				break;
			}
		}
		if(account==null) {
			System.out.println("The accountID do not exist  ");
			return null;
		}
		else
			return account;
	}	

	
	

}
