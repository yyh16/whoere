package Position;

public class CheckMobile {
	public CheckMobile(){
		
	}
	
	public static boolean check(String userAgent){ 
		if (userAgent != null) { 
			if (userAgent.indexOf("iPhone") >-1 || userAgent.indexOf("iPad") >-1 || (userAgent.indexOf("Android") >-1 && userAgent.indexOf("WebKit") >-1)) { 
			return true;
		}else
			return false;
		}
		return false;
	}
}
