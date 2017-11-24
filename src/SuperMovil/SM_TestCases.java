package SuperMovil;

import com.ntt.acoe.framework.selenium.FrameworkDriver;
import SuperMovil.SM_Functionality;
import SupernetPF.SPF_Functionality;

public class SM_TestCases {
	
	//Afiliacion
		public static void TS_Afiliacion()
		{				
			//Testcase Afiliacion			
			SM_Functionality.Afiliacion();	
		}
		
		//Transferencias
		public static void TS_Transferencias()
		{							
			SM_Functionality.Transferencias();			
		}

}
