package Framework;

import com.ntt.acoe.framework.run.TestRunner;

public class Start {

	public static void main(String[] args) {
		try {
			//Killing IEDriverServer and iexpore applications
			Runtime.getRuntime().exec("taskkill /IM IEDriverServer.exe /F");
			Runtime.getRuntime().exec("taskkill /IM iexplore.exe /F");	
			
			
			// EnlanceInternet
			//TestRunner.runTests("E:\\SeleniumProjects\\SeO2_Project\\config\\cfg_EnlanceInternet.xls");
			
			//SupernetPF
			//TestRunner.runTests("E:\\SeleniumProjects\\SeO2_Project\\config\\cfg_SupernetPF.xls");
			
			//SuperMovil
			TestRunner.runTests("E:\\SeleniumProjects\\SeO2_Project\\config\\cfg_SuperMovil.xls");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
