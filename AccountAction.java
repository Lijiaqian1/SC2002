package MOBLIMA;


public class AccountAction  {

	private AccountLib accountLib;
	
	public AccountAction() {
		accountLib=MyFuns.loadAccountLibFromDataFile();	
	}
	

	/**
	 * Check the administrator password and exit if you enter incorrect passwords for three times
	 * @return if login success ,return true
	 */
	private boolean login() {
		System.out.printf("Please enter the administrator password\r\n");

		int errNum=0;
		while(errNum<3) {
			String pwd=MyFuns.getInput_str("password : ",false);
			if (accountLib.checkAdminPwd(pwd))
				return true;
			else{
				errNum++;
				if(errNum<3)
					System.out.printf("You've entered the wrong password %d times,Please re-enter the password\r\n",errNum);
				else {
					System.out.printf("You've entered the wrong password %d times,can not continue\r\n",errNum);
				}					
			}
		}
		
		return false;
	}

	private String[] mainMenu=new String[] {
			"1: Show account list",
			"2: Change the administrator password ",	
			"3: Create an account",
			"4: Reset the password of an account ",	
			"5: Remove an account ",	
			"others: return "};

	private void showMainMenu() {
		MyFuns.printDoubleLine();
		for(String s:mainMenu) {
			System.out.println(s);
		}
		MyFuns.printDoubleLine();
		
	}

	/**
	 * Start the Movie-goer module
	 */
	public void start() {
		
		if (!login())
			return;
		
		
		while(true) {
			//show main menu 
			showMainMenu();
			int flag;
			boolean quit=false;
			flag=MyFuns.getInput_int("Please enter the menu number to continue:");

			//Displays the selected menu-items
			if(flag>=1 && flag<=mainMenu.length-1) {
				System.out.println("");
				MyFuns.printStarLine();
				System.out.println("\t"+mainMenu[flag-1]);
				MyFuns.printStarLine();
			}
			System.out.println("");
				
			switch(flag) {
				case 1://Show account list
					accountLib.showAccountList();
					break;
				case 2://Change administrator's password
					accountLib.changeAdminPwd();
					break;
				case 3://Show layout of the cinema
					accountLib.createAnAccount();
					break;
				case 4://Reset the password of an account
					accountLib.resetAnAccountPwd();
					break;
				case 5://Remove an account
					accountLib.removeAnAccount();
					break;
				default:				
					quit=true;
			}
			
			if(quit)
				break;

			//Save Data
			DataFileUtil.saveToFile(accountLib, "d:\\MOBLIMA_DATA\\accountlib.ser");
			//The account management module does not need to be paused every time
			//MyFuns.showWaitInfo(); 
		}		
		
	}
	
}
