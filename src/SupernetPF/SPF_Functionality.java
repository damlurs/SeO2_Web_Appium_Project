package SupernetPF;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ntt.acoe.framework.config.Environment;
import com.ntt.acoe.framework.selenium.By;
import com.ntt.acoe.framework.selenium.FrameworkDriver;
import com.ntt.acoe.framework.selenium.NTTDriver;
import com.ntt.acoe.framework.selenium.Select;
import com.ntt.acoe.framework.selenium.testdata.DataTable;
import com.ntt.acoe.framework.selenium.verify.Assert;

import EnlanceInternet.EI_Functionality;

public class SPF_Functionality {
		//public static WebDriver driver = FrameworkDriver.getDriver();
		//public static WebDriver driver = (WebDriver) new FrameworkDriver();
		public static WebDriver driver = new NTTDriver();
		//public static WebDriver driver=null;
		//WebElement Wait
		public static WebDriverWait wait = new WebDriverWait(driver,30);	
		
		 //Keyboard Class
		//Keyboard key = null;
		
		
		//Loading Data Table for Consulta_De_Bitacora				
		public static DataTable dt_Afiliacion = new DataTable(Environment.get("test_data_path")+"\\td_SupernetPF.xls", "Afiliacion","Afiliacion");
		public static DataTable dt_Transferencias = new DataTable(Environment.get("test_data_path")+"\\td_SupernetPF.xls", "Transferencias","Transferencias");
					
		public static void Afiliacion(){	
			try
			{		
				//Start Afiliacion
				Assert.done("Start --> Afiliacion");
				
				//Navigating to Site
				//Get First window handle
				String windowHandleBefore = driver.getWindowHandle();				
				
				
				driver.get(dt_Afiliacion.getValue("URL"));
				Assert.pass("Navigated to Site: "+dt_Afiliacion.getValue("URL"));
				
				//Click on Continue to Site Link
				driver.findElement(By.logicalName("SPF_ContinueToSite")).click();
				Assert.pass("Click on Continue Link");
				
				//Switch to new window
				for(String windowHandle: driver.getWindowHandles()) {
					driver.switchTo().window(windowHandle);					
				}
				
				//Maximize second browser
				driver.manage().window().maximize();
				
				//Click on Continue to Site Link on Login Page
				driver.findElement(By.logicalName("SPF_ContinueToSite")).click();
				Assert.pass("Click on Continue Link on Login Page");
																
				//Enter Codigo de Cliente
				WebElement codigoCliente = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_CodigodeCliente"))));
				codigoCliente.sendKeys(dt_Afiliacion.getValue("Codigo_Cliente"));
				Assert.pass("Codigo de Cliente Entered: "+ dt_Afiliacion.getValue("Codigo_Cliente"));
				
				//Click Continuar Button
				WebElement continuar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar"))));
				continuar.click();
				Assert.pass("Continuar Button Click");
				
				//Click on SPF_Afiliarse
				WebElement afiliarse =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Afiliarse"))));
				afiliarse.click();
				Assert.pass("Afiliarse Button Click");
				
				//Select para identificarse for Cliente
				Select paraIdentificarse = new Select(driver.findElement(By.logicalName("SPF_Para_Identificarse")));
				//paraIdentificarse.selectByVisibleText("Código de cliente");
				paraIdentificarse.selectByValue("COD");
				Assert.pass("Código de cliente Selected for para identificarse");
								
				//Enter value for codigo de cliente
				WebElement paraIdentificarse_codigoCliente = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Para_Identificarse_Value"))));
				paraIdentificarse_codigoCliente.sendKeys(dt_Afiliacion.getValue("Codigo_Cliente"));
				Assert.pass("Codigo de Cliente Entered for para identificarse: "+ dt_Afiliacion.getValue("Codigo_Cliente"));
				
				//Click on Continuar button
				WebElement continuar1 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar_1"))));
				continuar1.click();
				Assert.pass("Continuar Button Click");
				
				//Click on No cuento con celular
				WebElement no_cuento_con_celular =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_OTP_Msg"))));
				no_cuento_con_celular.click();
				Assert.pass("No cuento con celular");
				
				//Seleccione una opción para autenticarse
				Select paraAutenticarse = new Select(driver.findElement(By.logicalName("SPF_Una_Para_Autenticarse")));
				//paraIdentificarse.selectByVisibleText("Código de cliente");
				paraAutenticarse.selectByValue("NBT");
				Assert.pass("Clave telefónica Selected for para autenticarse");
				
				//Enter value for para autenticarsee
				WebElement paraAutenticarse_Clave_telefonica = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Una_Para_Autenticarse_Value"))));
				paraAutenticarse_Clave_telefonica.sendKeys(dt_Afiliacion.getValue("Clave_telefonica"));
				Assert.pass("Codigo de Cliente Entered for para identificarse: "+ dt_Afiliacion.getValue("Clave_telefonica"));
												
				//Get Parent window handle
				String windowHandleBefore1 = driver.getWindowHandle();	
				
				Thread.sleep(1000);
				
				//Click on the Calendar Image
				WebElement fecha_de_nacimiento =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Fecha_De_Nacimiento"))));
				fecha_de_nacimiento.click();
				Assert.pass("Click on Calendar Image");
				
				Thread.sleep(5000);
				
				//Switch to Calendar window
				for(String windowHandle: driver.getWindowHandles()) {
					driver.switchTo().window(windowHandle);	
					Thread.sleep(1000);
				}
								
				//Get Expected Date
				String dispExpDate = dt_Afiliacion.getValue("Exp_Date");
								
				//Convert Expected Date in to Calendar format
				//Expected Date					
				String expDate = dispExpDate.substring(0, 2);
								
				//Expected Month				
				String expMonth =  dispExpDate.substring(3, 5);				
				if(expMonth.trim().equals("01")) {
					expMonth = "Enero";
				}else if(expMonth.trim().equals("02")) {
					expMonth = "Febrero";
				}else if(expMonth.trim().equals("03")) {
					expMonth = "Marzo";
				}else if(expMonth.trim().equals("04")) {
					expMonth = "Abril";
				}else if(expMonth.trim().equals("05")) {
					expMonth = "Mayo";
				}else if(expMonth.trim().equals("06")) {
					expMonth = "Junio";
				}else if(expMonth.trim().equals("07")) {
					expMonth = "Julio";
				}else if(expMonth.trim().equals("08")) {
					expMonth = "Agosto";
				}else if(expMonth.trim().equals("09")) {
					expMonth = "Septiembre";
				}else if(expMonth.trim().equals("10")) {
					expMonth = "Octubre";
				}else if(expMonth.trim().equals("11")) {
					expMonth = "Noviembre";
				}else if(expMonth.trim().equals("12")) {
					expMonth = "Diciembre";
				}	
					
				//Expected Year
				String expYear = dispExpDate.substring(dispExpDate.length()- 4);				
				
				//Construct Calendar Month and Year String
				String expMonthYear = expMonth+","+" "+expYear;
				System.out.println("expMonthYear: "+expMonthYear);
				
				//Get the current Month and Year
				WebElement disp_Date = driver.findElement(By.logicalName("SPF_Fecha_De_Nacimiento_Current"));
				String currMonthYear = disp_Date.getText();
				Assert.pass("Fecha De Nacimiento Current Date: "+ currMonthYear);
				System.out.println("currMonthYear: "+currMonthYear);
				Thread.sleep(2000);			
				
				//Get the Current Year
				String currYear = currMonthYear.substring(currMonthYear.length()- 4);
				
				System.out.println("currYear: "+currYear);
				System.out.println("expYear: "+expYear);
				
				//Get Month Name				
				String[] exMonth = expMonthYear.trim().split(",");
				String[] cuMonth = currMonthYear.trim().split(",");
				
				//Expected curInt Month
				int curIntMonth = 0;							
				if(cuMonth[0].trim().equals("Enero")) {
					curIntMonth = 1;
				}else if(cuMonth[0].trim().equals("Febrero")) {
					curIntMonth = 2;
				}else if(cuMonth[0].trim().equals("Marzo")) {
					curIntMonth = 3;
				}else if(cuMonth[0].trim().equals("Abril")) {
					curIntMonth = 4;
				}else if(cuMonth[0].trim().equals("Mayo")) {
					curIntMonth = 5;
				}else if(cuMonth[0].trim().equals("Junio")) {
					curIntMonth = 6;
				}else if(cuMonth[0].trim().equals("Julio")) {
					curIntMonth = 7;
				}else if(cuMonth[0].trim().equals("Agosto")) {
					curIntMonth = 8;
				}else if(cuMonth[0].trim().equals("Septiembre")) {
					curIntMonth = 9;
				}else if(cuMonth[0].trim().equals("Octubre")) {
					curIntMonth = 10;
				}else if(cuMonth[0].trim().equals("Noviembre")) {
					curIntMonth = 11;
				}else if(cuMonth[0].trim().equals("Diciembre")) {
					curIntMonth = 12;
				}
				
				//Expected exIntMonth
				int exIntMonth = 0;							
				if(exMonth[0].trim().equals("Enero")) {
					exIntMonth = 1;
				}else if(exMonth[0].trim().equals("Febrero")) {
					exIntMonth = 2;
				}else if(exMonth[0].trim().equals("Marzo")) {
					exIntMonth = 3;
				}else if(exMonth[0].trim().equals("Abril")) {
					exIntMonth = 4;
				}else if(exMonth[0].trim().equals("Mayo")) {
					exIntMonth = 5;
				}else if(exMonth[0].trim().equals("Junio")) {
					exIntMonth = 6;
				}else if(exMonth[0].trim().equals("Julio")) {
					exIntMonth = 7;
				}else if(exMonth[0].trim().equals("Agosto")) {
					exIntMonth = 8;
				}else if(exMonth[0].trim().equals("Septiembre")) {
					exIntMonth = 9;
				}else if(exMonth[0].trim().equals("Octubre")) {
					exIntMonth = 10;
				}else if(exMonth[0].trim().equals("Noviembre")) {
					exIntMonth = 11;
				}else if(exMonth[0].trim().equals("Diciembre")) {
					exIntMonth = 12;
				}
				
				//Iterate through Year				
				while (!expYear.trim().equals(currYear.trim())) {					
					//Click on Previous Year
					WebElement prev_Year =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"prevYear\"]"))));
					prev_Year.click();					
					Thread.sleep(100);
					disp_Date = driver.findElement(By.logicalName("SPF_Fecha_De_Nacimiento_Current"));
					currMonthYear = disp_Date.getText();
					currYear = currMonthYear.substring(currMonthYear.length()- 4);
					prev_Year = null;
				}				
				Thread.sleep(1000);
				
				//Iterate through Month	
				WebElement req_Month = null;				
				if(curIntMonth>exIntMonth) {
					req_Month =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"prevMonth\"]"))));
					
				}else if(curIntMonth<exIntMonth) {
					req_Month =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"nextMonth\"]"))));					
				}
				while (!currMonthYear.trim().contains(expMonth.trim())) {					
					//Click on Previous or Next Month					
					req_Month.click();					
					Thread.sleep(100);
					disp_Date = driver.findElement(By.logicalName("SPF_Fecha_De_Nacimiento_Current"));
					currMonthYear = disp_Date.getText();					
					req_Month = null;
				}				
				Thread.sleep(2000);
				
				//Select the required date
				if(expDate.trim().substring(0,1).equalsIgnoreCase("0")) {
					expDate = expDate.trim().substring(1,2);					
				}
				List<WebElement> all_Days =driver.findElements(By.className("days"));				
				for(WebElement date: all_Days) {
					String reqDate = date.getText();					
					if(reqDate.trim().equals(expDate.trim())) {
						date.click();
						break;
					}				
				}
				
				//Set driver back
				driver.switchTo().window(windowHandleBefore1);
				
				//Click on Continuar button				
				WebElement continuar2 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar_2"))));
				continuar2.click();
				Assert.pass("Continuar Button Click");
				
				//Enter contrasenia
				WebElement contrasenia = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Contrasenia"))));
				contrasenia.sendKeys(dt_Afiliacion.getValue("Contrasenia"));
				Assert.pass("Contrasenia Entered: "+ dt_Afiliacion.getValue("Contrasenia"));
				
				//Enter confirm contrasenia
				WebElement cnfcontrasenia = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_CnfContrasenia"))));
				cnfcontrasenia.sendKeys(dt_Afiliacion.getValue("Contrasenia"));
				Assert.pass("Contrasenia Entered: "+ dt_Afiliacion.getValue("Contrasenia"));
				
				//Click on Continuar button				
				WebElement continuar3 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar_3"))));
				continuar3.click();
				Assert.pass("Continuar Button Click");
				
				//Click on Continuar button				
				WebElement continuar4 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar_3"))));
				continuar4.click();
				Assert.pass("Continuar Button Click");
				
				//Verify Confirmation Page
				WebElement cnfImg =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_CnfImg"))));
				if(cnfImg.isDisplayed()) {
					Assert.pass("Confirmation Page Displayed");
				}
				
				//Click on Continuar button				
				WebElement continuar5 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar_4"))));
				continuar5.click();
				Assert.pass("Continuar Button Click");
				
				Thread.sleep(5000);			
				
				//End Afiliacion
				Assert.done("End --> Afiliacion");
				
			}catch (Exception e) {
				//Method Result
				Assert.fail("Exception occured while executing Afiliacion");
				//Exception Trace
				e.printStackTrace();				
			}								
		}

		public static void Transferencias() {
			try {	
				System.out.println("inside Transferencias");	
			if(driver==null) {	
			setUpIE();			
			}
			
			//Start Transferencias
			Assert.done("Start --> Transferencias");
			
			//Get First window handle
			String windowHandleBefore = driver.getWindowHandle();				
			
			//Navigating to Site
			driver.get(dt_Afiliacion.getValue("URL"));
			Assert.pass("Navigated to Site: "+dt_Transferencias.getValue("URL"));
			
			//Click on Continue to Site Link
			driver.findElement(By.logicalName("SPF_ContinueToSite")).click();
			Assert.pass("Click on Continue Link");
			
			Thread.sleep(10000);
			
			//Switch to new window
			for(String windowHandle: driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);					
			}
			
			//Maximize second browser
			driver.manage().window().maximize();
			
			//Click on Continue to Site Link on Login Page
			driver.findElement(By.logicalName("SPF_ContinueToSite")).click();
			Assert.pass("Click on Continue Link on Login Page");
															
			//Enter Codigo de Cliente
			WebElement codigoCliente = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_CodigodeCliente"))));
			codigoCliente.sendKeys(dt_Transferencias.getValue("Codigo_Cliente"));
			Assert.pass("Codigo de Cliente Entered: "+ dt_Transferencias.getValue("Codigo_Cliente"));
			
			//Click Continuar Button
			WebElement continuar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Continuar"))));
			continuar.click();
			Assert.pass("Continuar Button Click");
			
			//Enter NIP
			WebElement nip =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_NIP"))));
			nip.sendKeys(dt_Transferencias.getValue("NIP"));
			Assert.pass("NIP Entered: "+dt_Transferencias.getValue("NIP"));
			Thread.sleep(2000);
			
			//Click on continuar			
			WebElement loginB = null;
			int xVal=0;
			int yVal=0;
			List<WebElement> allSpan = driver.findElements(By.tagName("span"));
			for(WebElement span:allSpan) {				
				String loginButton1 =span.getText().trim();	
				System.out.println(loginButton1);
				if(loginButton1.trim().equalsIgnoreCase("Continuar")) {						
					loginB = span;
					System.out.println("inside if");
					xVal = EI_Functionality.getX(loginB);
					yVal = EI_Functionality.getY(loginB);
				}			
			}			
			//WebElement loginB = driver.findElement(By.logicalName("SPF_NIP"));
			
			
			//Initiate Robot Class for Mouse move
			Robot robot = new Robot();				
				
			robot.mouseMove(xVal+20, yVal+60);
			Thread.sleep(2000);				
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(2000);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);			
			Thread.sleep(5000);
			
			//Select Transferencias
			List<WebElement> allLinks = driver.findElements(By.tagName("font"));
			for(WebElement link:allLinks) {
				String menuItem =link.getText().trim();
				if(menuItem.trim().equalsIgnoreCase("Transferencias")) {
					link.click();
					Assert.pass("Click on Transferencias");
				}
			}
			
			Thread.sleep(3000);
			
			//Select Cuentas Propias
			allLinks = driver.findElements(By.tagName("a"));
			for(WebElement link:allLinks) {
				String menuItem =link.getText().trim();
				if(menuItem.trim().equalsIgnoreCase("Cuentas Propias")) {
					link.click();
					Assert.pass("Click on Cuentas Propias");
				}
			}			
			Thread.sleep(3000);
			
			//click on window
			//WebElement win = driver.findElement(By.xpath("/html/body/div[6]/div[2]/"));
			//win.click();
			//Thread.sleep(2000);
			
			//Select Origin Accont			
			driver.findElement(By.logicalName("SPF_Cuenta_Origin")).sendKeys("11");			
			Assert.pass("Cuenta Origin: "+dt_Transferencias.getValue("Cuenta_Origin"));			
			Thread.sleep(2000);			
					
			//Select Dest Account				
			driver.findElement(By.logicalName("SPF_Cuenta_Dest")).sendKeys("25");			
			Assert.pass("Cuenta Destination: "+dt_Transferencias.getValue("Cuenta_Dest"));			
			Thread.sleep(2000);
			
			//win.click();
			//Thread.sleep(2000);			
			
			//Enter Concept			
			WebElement concepto =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Trans_Concept"))));
			concepto.sendKeys("Prueba");
			Assert.pass("Concept Entered: Prueba");			
			
			Thread.sleep(2000);
			
			//Enter Importe			
			WebElement monto =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("SPF_Trans_Monto"))));
			monto.sendKeys(dt_Transferencias.getValue("Trans_Monto"),Keys.TAB,Keys.TAB,Keys.ENTER);
			Assert.pass("Monto Entered: "+dt_Transferencias.getValue("Trans_Monto"));					
			Thread.sleep(2000);		
			
			//Click Confirm
			WebElement confCont =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("UtilConfirm.Continuar"))));
			confCont.sendKeys(Keys.ENTER);
			Assert.pass("Confirm Screen");					
			Thread.sleep(5000);		
			
			//Click Confirm
			WebElement confComp =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("UtilConfirm.Comprobante"))));
			confComp.sendKeys(Keys.ENTER);
			Assert.pass("Confirm Screen");					
			Thread.sleep(5000);		
			
			//Start Transferencias
			Assert.done("Start --> Transferencias");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		public static void rsendKeys(Robot robot,String keys) {
			try {
				for(char c: keys.toCharArray()) {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
					if(KeyEvent.CHAR_UNDEFINED==keyCode) {
						robot.keyPress(keyCode);
						//robot.delay(100);
						robot.keyRelease(keyCode);
						//robot.delay(100);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
						}
		
		public static void setUpIE() {		
			System.out.println("inside setupIE");
			try {
				System.setProperty("webdriver.ie.driver", Environment.get("driver_path"));
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				capabilities.setCapability("requireWindowFocus", true);
				driver = new InternetExplorerDriver(capabilities);				
			}catch(Exception e) {						
			e.printStackTrace();
			System.out.println("Driver retuen null.. Please Restart Test");			
			}			
		}

}