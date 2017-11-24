package EnlanceInternet;


import com.ntt.acoe.framework.selenium.FrameworkDriver;
import EnlanceInternet.EI_Functionality;

public class EI_TestCases {
	static String defaultWindow;
	
	public static void TS_Consulta_De_Bitacora()
	{
		//Login_EnlanceInternet		
		EI_Functionality.Login_Bitacora();	
		
		//Bitacora
		EI_Functionality.Consulta_De_Bitacora();
	
	}
	
	public static void TS_Registro_De_Un_Pago_Directo_En_Linea()
	{
		//Login_EnlanceInternet		
		EI_Functionality.Login_Directo_En_Linea();
		
		//Directo_En_Linea
		EI_Functionality.Registro_De_Un_Pago_Directo_En_Linea();
		
		
	
	}
}
