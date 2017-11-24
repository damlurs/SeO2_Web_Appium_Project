package EnlanceInternet;

import java.util.List;
import java.util.Set;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.ntt.acoe.framework.config.Environment;
import com.ntt.acoe.framework.selenium.By;
import com.ntt.acoe.framework.selenium.FrameworkDriver;
import com.ntt.acoe.framework.selenium.NTTDriver;
import com.ntt.acoe.framework.selenium.NTTRemoteWebElement;
import com.ntt.acoe.framework.selenium.testdata.DataTable;
import com.ntt.acoe.framework.selenium.verify.Assert;
import com.ntt.acoe.framework.selenium.Select;

public class EI_Functionality {	
	//public static WebDriver driver = FrameworkDriver.getDriver();
	//public static WebDriver driver;// = FrameworkDriver.driver;
	public static WebDriver driver = new NTTDriver();
	
	//WebElement Wait
	public static WebDriverWait wait = new WebDriverWait(driver,30);
	
	
	//Loading Data Table for Consulta_De_Bitacora				
	public static DataTable dt_Consulta_De_Bitacora = new DataTable(Environment.get("test_data_path")+"\\td_EnlanceInternet.xls", "Consulta_De_Bitacora","Consulta De Bitacora");
	public static DataTable dt_Registro_Pagp_Directo_Enlinea = new DataTable(Environment.get("test_data_path")+"\\td_EnlanceInternet.xls", "Registro_Pagp_Directo_Enlinea","Registro De Un Pago Directo En Linea");
	
	public static void Login_Bitacora(){	
		try
		{				
			//Navigating to Site
			Assert.done("Start --> Login_Bitacora");
			driver.get(dt_Consulta_De_Bitacora.getValue("URL"));
			Assert.pass("Navigated to Site: "+dt_Consulta_De_Bitacora.getValue("URL"));
			
			//Click on Continue to Site Link
			driver.findElement(By.logicalName("EI_ContinueToSite")).click();
			Assert.pass("Click on Continue Link");
															
			//Enter Codigo de Cliente
			WebElement codigoCliente = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_CodigodeCliente"))));
			codigoCliente.sendKeys(dt_Consulta_De_Bitacora.getValue("Codigo_Cliente"));
			Assert.pass("Codigo de Cliente Entered: "+ dt_Consulta_De_Bitacora.getValue("Codigo_Cliente"));
			
			//Click Enviar Button
			WebElement enviar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Enviar"))));
			enviar.submit();
			Assert.pass("Enviar Button Click");
			
			//Check for Image and click on Proceed
			WebElement imagenCorrecta =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_ImagenCorrecta"))));
			boolean bool_imagenCorrecta=imagenCorrecta.isDisplayed();
			if(bool_imagenCorrecta) {
				imagenCorrecta.click();
				Assert.pass("ImagenCorrecta Button Click");	
			}else {
				Assert.warning("ImagenCorrecta Button not displayed");	
			}
						
			//Enter Contresena
			WebElement contrasena = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Contrasena"))));
			contrasena.sendKeys(dt_Consulta_De_Bitacora.getValue("Contrasena"));
			Assert.pass("Contrasena Entered: "+ dt_Consulta_De_Bitacora.getValue("Contrasena"));
			
			//Click Enviar Button
			enviar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Enviar"))));
			enviar.submit();
			Assert.pass("Enviar Button Click");
			Thread.sleep(1000);
			
			driver.switchTo().frame("Principal");
			Thread.sleep(1000);
			
			//Click Enviar Button
			WebElement enviar1 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Envia1"))));
			if(enviar1.isDisplayed()) {
				enviar1.click();
				Assert.pass("Enviar Button Click");
			}				
			
			//Enter Contrasena dinamica			
			WebElement contrasenaDinamica = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Contrasena_Dinamica"))));
			contrasenaDinamica.sendKeys("00000000");
			Assert.pass("Contrasena Dinamica Entered: 00000000");
						
			//Click on Accept
			WebElement AcceptImg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Accept"))));
			AcceptImg.click();
			Assert.pass("Contrasena Dinamica Accepted");	
			
			//Wait for Page to Load
			Thread.sleep(3000);
						
			//Click on Accept
			AcceptImg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Accept"))));			
			AcceptImg.click();
			Assert.pass("Contrasena Dinamica Confirmed");		
						
			//Back to Main context on page
			Thread.sleep(2000);
			//driver.switchTo().defaultContent();
			
			//Method Result
			Assert.done("End --> Login_Bitacora");
			
		}catch (Exception e) {
			//Method Result
			Assert.fail("Exception occured while executing Login_EnlanceInternet");
			//Exception Trace
			e.printStackTrace();				
		}								
	}

	public static void Login_Directo_En_Linea(){	
		try
		{				
			//Navigating to Site
			Assert.done("Start --> Login_Directo_En_Linea");
			driver.get(dt_Consulta_De_Bitacora.getValue("URL"));
			Assert.pass("Navigated to Site: "+dt_Registro_Pagp_Directo_Enlinea.getValue("URL"));
			
			//Click on Continue to Site Link
			driver.findElement(By.logicalName("EI_ContinueToSite")).click();
			Assert.pass("Click on Continue Link");
															
			//Enter Codigo de Cliente
			WebElement codigoCliente = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_CodigodeCliente"))));
			codigoCliente.sendKeys(dt_Registro_Pagp_Directo_Enlinea.getValue("Codigo_Cliente"));
			Assert.pass("Codigo de Cliente Entered: "+ dt_Registro_Pagp_Directo_Enlinea.getValue("Codigo_Cliente"));
			
			//Click Enviar Button
			WebElement enviar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Enviar"))));
			enviar.submit();
			Assert.pass("Enviar Button Click");
			Thread.sleep(2000);
			
			//Check for Image and click on Proceed
			/*WebElement imagenCorrecta =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_ImagenCorrecta"))));
			boolean bool_imagenCorrecta=imagenCorrecta.isDisplayed();
			if(bool_imagenCorrecta) {
				imagenCorrecta.click();
				Assert.pass("ImagenCorrecta Button Click");	
			}else {
				Assert.warning("ImagenCorrecta Button not displayed");	
			}*/
				
			//Enter Contresena
			WebElement contrasena = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Contrasena"))));
			contrasena.sendKeys(dt_Consulta_De_Bitacora.getValue("Contrasena"));
			Assert.pass("Contrasena Entered: "+ dt_Registro_Pagp_Directo_Enlinea.getValue("Contrasena"));
			
			//Click Enviar Button
			enviar =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Enviar"))));
			enviar.submit();
			Assert.pass("Enviar Button Click");
			Thread.sleep(1000);
			
			driver.switchTo().frame("Principal");
			Thread.sleep(1000);
			
			//Click Enviar Button
			/*WebElement enviar1 =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Envia1"))));
			if(enviar1.isDisplayed()) {
				enviar1.click();
				Assert.pass("Enviar Button Click");
			}	*/			
			
			//Enter Contrasena dinamica			
			WebElement contrasenaDinamica = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Contrasena_Dinamica"))));
			contrasenaDinamica.sendKeys("00000000");
			Assert.pass("Contrasena Dinamica Entered: 00000000");
						
			//Click on Accept
			WebElement AcceptImg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Accept"))));
			AcceptImg.click();
			Assert.pass("Contrasena Dinamica Accepted");	
			
			//Wait for Page to Load
			Thread.sleep(3000);
						
			//Click on Accept
			AcceptImg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Accept"))));			
			AcceptImg.click();
			Assert.pass("Contrasena Dinamica Confirmed");		
						
			//Back to Main content on page
			Thread.sleep(2000);
			//driver.switchTo().defaultContent();
			
			//Method Result
			Assert.done("End --> Login_Directo_En_Linea");
			Thread.sleep(3000);
			
		}catch (Exception e) {
			//Method Result
			Assert.fail("Exception occured while executing Login_EnlanceInternet");
			//Exception Trace
			e.printStackTrace();	
		}

	}
	public static void Consulta_De_Bitacora() {
		
		try {		
			//Consulta_De_Bitacora
			Assert.done("Start --> Consulta_De_Bitacora");
			
			//Switch to frame
			//driver.switchTo().frame("Principal");
			Thread.sleep(5000);
									
			//Initiate Robot Class for Mouse move
			Robot robot = new Robot();				
			robot.mouseMove(30, 110);			
			Thread.sleep(2000);	
			
			//Click on Bitacora Link			
			WebElement BitacoraLink = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Bitacora"))));
			BitacoraLink.click();
			Assert.pass("BitacoraLink Clicked");
			
			//Reset <mouse move	
			robot.mouseMove(0, 0);
						
			//Select Radio Button
			List<WebElement> radioButtons= driver.findElements(By.logicalName("EI_BitacoraDelDia"));

			//Select Bitacora Del Historica
			for(WebElement radioButton: radioButtons) {
				if(radioButton.getAttribute("value").equals("HIST"))//NORM {
					radioButton.click();
				}
			
			Thread.sleep(5000);
			
			//Compare and Select Account Number		
			Select cuentaSelect = new Select(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Select_Cuenta")))));	
			
			List<WebElement> cuentaValues= cuentaSelect.getOptions();
			int listSize = cuentaValues.size();
			System.out.println("size: "+cuentaValues.size());
			
			if(listSize>0) {
				for(WebElement cuentaValue:cuentaValues) {
					String reqVal = cuentaValue.getAttribute("value");										
					if(reqVal.trim().contains(dt_Consulta_De_Bitacora.getValue("Cuenta"))) {										
						cuentaSelect.selectByValue(reqVal);
						Assert.pass("Cuenta Selected: "+reqVal);
						break;
					}			
				}
			}
			//Get Parent window handle
			String windowHandleBefore1 = driver.getWindowHandle();
			
			//Click on Calendar Image
			WebElement Calendar = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Calendar"))));
			Calendar.click();
			Assert.pass("Historical Calendar Click");
			
			Thread.sleep(10000);
						
			//Switch to Calendar window
			for(String windowHandle: driver.getWindowHandles()) {
				System.out.println("hnd: "+windowHandle);
				driver.switchTo().window(windowHandle);					
			}	
			
			Thread.sleep(2000);
			
			//Click on Continue to Site Link
			driver.findElement(By.logicalName("EI_ContinueToSite")).click();
			Assert.pass("Click on Continue Link");
			
			Thread.sleep(5000);
			//Select the required date	
			WebElement dateToSelect = driver.findElement(By.linkText("1"));
			dateToSelect.click();
			Assert.pass("Selected the Date");
			
			Thread.sleep(1000);
			//Set driver back
			//Switch to Calendar window
			for(String windowHandle: driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);					
			}			
			driver.switchTo().frame("Principal");				
			
			//click on Consultar
			WebElement Consultar = driver.findElement(By.logicalName("EI_Envia1"));			
			Consultar.click();
			Assert.pass("Consultar Click");
			
			Thread.sleep(5000);
			//Check Bitacora Page Loaded
			boolean checkPage=false;
			List<WebElement>tables=driver.findElements(By.tagName("td"));
			for(WebElement table:tables) {
				if (table.getText().trim().contains(dt_Consulta_De_Bitacora.getValue("Cuenta"))) {					
					checkPage = true;
					break;
				}
			}
			if(checkPage) {
				Assert.pass("Bitacora Page displayed");
			}else {
				Assert.fail("Bitacora Page NOT displayed");
			}			
			
			Assert.done("End --> Consulta_De_Bitacora");
				
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Consulta_De_Bitacora failed");
		}
	}
	
	public static void Registro_De_Un_Pago_Directo_En_Linea() {
		
		try {		
			//Registro_De_Un_Pago_Directo_En_Linea
			Assert.done("Start --> Registro_De_Un_Pago_Directo_En_Linea");			
			Thread.sleep(2000);
			
			//Services Menu
			WebElement services = driver.findElement(By.name("gbo25070"));
			int servicesX = EI_Functionality.getX(services);
			int servicesY = EI_Functionality.getY(services);
									
			//Initiate Robot Class for Mouse move
			Robot robot = new Robot();				
			//robot.mouseMove(30, 110);		
			robot.mouseMove(servicesX+20, servicesY+60);
			Thread.sleep(2000);	
			
			//Pago Directo Menu
			WebElement pagoDirecto = driver.findElement(By.id("menuItemHilite199"));
			servicesX = EI_Functionality.getX(pagoDirecto);
			servicesY = EI_Functionality.getY(pagoDirecto);
			robot.mouseMove(servicesX+20, servicesY+60);
			Thread.sleep(2000);
			
			//Registre de Pagos
			WebElement registredePagos = driver.findElement(By.id("menuItemHilite164"));
			servicesX = EI_Functionality.getX(registredePagos);
			servicesY = EI_Functionality.getY(registredePagos);
			robot.mouseMove(servicesX+20, servicesY+60);
			Thread.sleep(2000);
			
			//Enlinea
			WebElement enlinea = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menuItemText157"))));
			servicesX = EI_Functionality.getX(enlinea);
			servicesY = EI_Functionality.getY(enlinea);
			robot.mouseMove(servicesX+20, servicesY+60);
			Thread.sleep(2000);					
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(2000);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			Assert.pass("enlinea Clicked");
			Thread.sleep(5000);			
			
			//Click on Search Image
			List<WebElement> allImages = driver.findElements(By.tagName("img"));//("EI_SearchImg"));
			for(WebElement srcImage:allImages) {
				String reqSRC= srcImage.getAttribute("src");
				System.out.println("SRC: "+reqSRC);
				if(reqSRC.trim().contains("/accesobia1/gifs/EnlaceMig/gbo25420.gif")) {
					srcImage.click();
					Assert.pass("Search Image Click");
					break;
				}				
			}				
			Thread.sleep(5000);			
			
			//Loop to Window to appear
			boolean loopWindow =false;
			while(!loopWindow) {
				Set<String> i =driver.getWindowHandles();
				System.out.println("windows: "+i.size());
				if(i.size()>1) {
					loopWindow=true;
					break;
				}
				Thread.sleep(1000);
			}
			//Switch to Search window
			for(String windowHandle: driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);					
			}				
			Thread.sleep(2000);			
			
			//Click on Continue to Site Link
			List<WebElement> allLinks=driver.findElements(By.tagName("a"));
			for(WebElement link:allLinks) {
				System.out.println(link.getText());
				if(link.getText().trim().contains("Continue to this website")) {
					link.click();
					Assert.pass("Click on Continue Link on search window");
					break;
				}				
			}			
						
			Thread.sleep(5000);
			
			driver.switchTo().frame("framecuentas");
			Thread.sleep(2000);
						
			//Select the Cuenta displayed
			WebElement cuentaToSelect = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(dt_Registro_Pagp_Directo_Enlinea.getValue("Cuenta")))));
			cuentaToSelect.click();			
			
			Thread.sleep(2000);			
			
			//Setting back driver to frame Principal
			for(String windowHandle: driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);					
			}			
			driver.switchTo().frame("Principal");
			Thread.sleep(5000);
			
			// Select Benificiary					
			Select benNombre = new Select(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Select_Cuenta1")))));	
			
			List<WebElement> nombreValues= benNombre.getOptions();
			int listSize = nombreValues.size();
			System.out.println("size: "+nombreValues.size());
			
			if(listSize>0) {
				for(WebElement nombre:nombreValues) {
					String reqVal = nombre.getText();
					System.out.println("reqVal: "+reqVal);
					System.out.println("dt Value: "+dt_Registro_Pagp_Directo_Enlinea.getValue("Benficiary_Nombre"));
					
					if(reqVal.trim().contains(dt_Registro_Pagp_Directo_Enlinea.getValue("Benficiary_Nombre"))) {										
						benNombre.selectByVisibleText(reqVal);
						Assert.pass("Nombre Selected: "+reqVal);
						break;
					}			
				}
			}
			
			//Enter Concept
			WebElement concepto = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Concepto"))));
			concepto.sendKeys("Prueba");	
			Assert.pass("Concepto Entered: Prueba");
			
			//Enter Pago Number
			WebElement noPago = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_NoPago"))));
			noPago.sendKeys(dt_Registro_Pagp_Directo_Enlinea.getValue("Pago_Number"));	
			Assert.pass("Pago Entered is: "+dt_Registro_Pagp_Directo_Enlinea.getValue("Pago_Number"));
			
			//Store new value in data sheet
			int noPago_Update= Integer.parseInt(dt_Registro_Pagp_Directo_Enlinea.getValue("Pago_Number"));
			noPago_Update = noPago_Update + 1;			
			dt_Registro_Pagp_Directo_Enlinea.setValue("Pago_Number", 1, Integer.toString(noPago_Update));
			
			//Enter Amount
			WebElement importe = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Importe"))));
			importe.sendKeys(dt_Registro_Pagp_Directo_Enlinea.getValue("Importe"));	
			Assert.pass("Concepto Entered: Prueba");
			
			//Click on Registrar Pago
			List<WebElement> allImg =driver.findElements(By.tagName("img"));
			for(WebElement img:allImg) {				
				if(img.getAttribute("alt").trim().contains("Registrar pago")) {
					img.click();
					Assert.pass("Click on Registrar pago Button");
					break;
				}				
			}
						
			//Enter Contrasena dinamica			
			WebElement contrasenaDinamica = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Contrasena_Dinamica"))));
			contrasenaDinamica.sendKeys("00000000");
			Assert.pass("Contrasena Dinamica Entered: 00000000");
						
			//Click on Accept
			WebElement AcceptImg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.logicalName("EI_Accept"))));
			AcceptImg.click();
			Assert.pass("Contrasena Dinamica Accepted");	
			
			//Wait for Page to Load
			Thread.sleep(3000);
			
			//Check for Confirmation
			Thread.sleep(5000);			
			
			//Loop to Window to appear
			boolean loopCnfWindow =false;
			while(!loopCnfWindow) {
				Set<String> i1 =driver.getWindowHandles();
				System.out.println("windows: "+i1.size());
				if(i1.size()>1) {
					loopCnfWindow=true;
					break;
				}
				Thread.sleep(1000);
			}
			
			Thread.sleep(5000);
			if(loopCnfWindow) {
				Assert.pass("Transaction Successfull");
			}else {
				Assert.fail("Transaction NOT Successfull");
					
			}			
			
			/*//Switch to Confirmation window
			for(String windowHandle: driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);					
			}				
			Thread.sleep(2000);		
			
			//Print Confirmation			
			List<WebElement> allTD =driver.findElements(By.tagName("td"));
			for(WebElement td:allTD) {				
				if(td.getText().trim().contains("Transacción")) {					
					Assert.pass("Transaction: "+td.getText().trim());
					break;
				}				
			}*/
						
			Assert.done("End --> Registro_De_Un_Pago_Directo_En_Linea");
				
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Registro_De_Un_Pago_Directo_En_Linea");
		}
	}

	public static int  getX(WebElement ele) {
		int xVal = 0;
		try {
			Point point = ele.getLocation();
			xVal = point.getX();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return xVal;
	}
	
	public static int  getX(Select ele) {
		int xVal = 0;
		try {
			System.out.println("inside getX");
			Point point = ((WebElement) ele).getLocation();
			xVal = point.getX();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return xVal;
	}
	
		
	public static int  getY(WebElement ele) {
		int yVal = 0;
		try {
			Point point = ele.getLocation();
			yVal = point.getY();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return yVal;
	}
	
	public static int  getY(Select ele) {
		int yVal = 0;
		try {
			Point point = ((WebElement) ele).getLocation();
			yVal = point.getY();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return yVal;
	}
}
