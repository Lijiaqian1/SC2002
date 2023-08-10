package MOBLIMA;
import java.io.Serializable;
import java.util.*;

/**
 * CineplexLib Class
 * cineplexlib contains all cineplex
 * new cineplexes can be created here
 * @author lijiaqian
 *
 */
public class CineplexLib implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2245490022329957642L;
	
	private Cineplex[] cineplexes;
	public CineplexLib() {
		this.cineplexes=new Cineplex[0];
	}
	public void addCineplex(Cineplex cineplex) {
		int n;
		n=cineplexes.length+1;
		cineplexes=Arrays.copyOf(cineplexes, n);
		cineplexes[n-1]=cineplex;
	}
	public void showCineplexes() {
		System.out.println("Cineplexes Information is as below : ");
		MyFuns.printSingleLine();
		if(cineplexes.length==0) {
			System.out.println("NA");
		}
		
		for(int i=0;i<cineplexes.length;i++) {
			cineplexes[i].printCineplexInfo();
			MyFuns.printSingleLine();
		}
	}
	
	public Cineplex newCineplex() {
		String cxcode,cxname,cxlocation;
		cxcode=MyFuns.getInput_str("cineplex code : ", false);
		cxname=MyFuns.getInput_str("cineplex name : ", false);
		cxlocation=MyFuns.getInput_str("cineplex location : ", false);
		
		//show cineplex detail ,and wait user confirm
		if(!MyFuns.confirmDlg("New cineplex : code is "+cxcode+" name is "+cxname+" location is "+cxlocation+"\n Confirm?(yes or not)")) {
			return null;
		}
		
		Cineplex c=new Cineplex(cxcode,cxname,cxlocation);		
		addCineplex(c);
		
		return c;
	}
	
	public Cineplex selectACineplex() {
		showCineplexes();
		while(true) {
			String cxcode=MyFuns.getInput_str("Please enter the code of the cineplex : ", false);
			MyFuns.printSingleLine();
			
			int i,n;
			n=cineplexes.length;
			if(n==0)
				return null;
			
			for(i=1;i<=n;i++) {
				if(cineplexes[i-1].getCineplexCode().equals(cxcode)) {
					return cineplexes[i-1];
				}
			}
			System.out.println("cineplex code invalid.");
		}
	}
}