package SupernetPF;

import SupernetPF.SPF_Functionality;

public class SPF_TestCases {
	
	//Afiliacion
	public static void TS_Afiliacion()
	{				
		//Testcase Afiliacion
		SPF_Functionality.Afiliacion();	
	}
	
	//Transferencias
	public static void TS_Transferencias()
	{			
		System.out.println("inside TS_Transferencias");
		//Testcase Transferencia
		SPF_Functionality.Transferencias();
		System.out.println("outside TS_Transferencias");
	
	}
	

}
