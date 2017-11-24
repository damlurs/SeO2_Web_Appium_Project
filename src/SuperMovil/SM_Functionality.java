package SuperMovil;

import java.util.ArrayList;
import java.util.HashSet;
//Java Jars
import java.util.List;
import java.util.Set;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.time.Duration;
import java.net.MalformedURLException;

//Appium Jars
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Dimension;
//Selenium Jars
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//Framework Jars
import com.ntt.acoe.framework.config.Environment;
import com.ntt.acoe.framework.selenium.By;
import com.ntt.acoe.framework.selenium.FrameworkDriver;
import com.ntt.acoe.framework.selenium.NTTDriver;
import com.ntt.acoe.framework.selenium.NTTMobileDriver;
import com.ntt.acoe.framework.selenium.Select;
import com.ntt.acoe.framework.selenium.testdata.DataTable;
import com.ntt.acoe.framework.selenium.verify.Assert;

public class SM_Functionality {
	// Start Appium Driver
			public static AppiumDriver<MobileElement> driver = NTTMobileDriver.getDriver();
			public static TouchAction ta = new TouchAction(driver);
			public static WebDriverWait wait = new WebDriverWait(driver, 180);
			
			public static JavascriptExecutor executor = (JavascriptExecutor) driver;
			
			//Loading Data Table for Consulta_De_Bitacora				
			public static DataTable dt_Afiliacion = new DataTable(Environment.get("test_data_path")+"\\td_SuperMovil.xls", "Afiliacion","Afiliacion");
			public static DataTable dt_Transferencias = new DataTable(Environment.get("test_data_path")+"\\td_SuperMovil.xls", "Transferencias","Transferencias");
			
	public static void Afiliacion() {
		try {
			Assert.done("Start - Afiliacion");
			
			//Click on Menu
			MobileElement menu = (MobileElement) driver.findElement(By.logicalName("SM_Menu"));
			menu.click();
			Thread.sleep(500);
			Assert.pass("Menu Click");
			
			//Open Environment Options			
			MobileElement env = (MobileElement) driver.findElement(By.logicalName("SM_Env_Opt"));			
			env.click();
			Thread.sleep(500);
			Assert.pass("Environment Option");
			
			//Select Certification			
			MobileElement envCert = (MobileElement) driver.findElement(By.logicalName("SM_Env_Cer"));			
			envCert.click();
			Thread.sleep(500);
			Assert.pass("Select Certification Environment");			
			
			//Click on Home			
			MobileElement home = (MobileElement) driver.findElement(By.logicalName("SM_Home"));
			home.click();
			Thread.sleep(500);
			Assert.pass("Return to Home");

			//Verify Certification selected			
			MobileElement verifyCert = (MobileElement) driver.findElement(By.logicalName("SM_Cer_Verify"));			
			String certImg=verifyCert.getText();
			if(certImg.trim().equalsIgnoreCase("CER")) {
				Assert.pass("Environment Selected: "+certImg.trim());
			}else {
				Assert.fail("Certification Environment NOT Selected, Selected Environment is: "+certImg.trim());
			}
			Thread.sleep(500);						
			
			//Verify Certification Version			
			MobileElement verifyCertVer = (MobileElement) driver.findElement(By.logicalName("SM_Cer_Ver_Verify"));			
			String CertVerImg=verifyCertVer.getText();
			if(CertVerImg.trim().equalsIgnoreCase("13.1")) {
				Assert.pass("Certification Environment Version Selected: "+CertVerImg.trim());
			}else {
				Assert.fail("Certification Environment Version NOT Selected, Selected Environment Version is: "+CertVerImg.trim());
			}
			Thread.sleep(500);
			
			//Click on Accesso			
			MobileElement accesso = (MobileElement) driver.findElement(By.logicalName("SM_Accesso"));
			accesso.click();
			Thread.sleep(1000);
			Assert.pass("Click Accesso");
			
			//Set OFF Recordar usuraio			
			MobileElement recordarusuario = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Record_User"))));
			String recordarusuariovalue=recordarusuario.getText();
			if(recordarusuariovalue.trim().equalsIgnoreCase("ON")) {
				recordarusuario.click();
				Assert.pass("Recordar Usuario Set to OFF");
			}else {
				Assert.pass("Recordar Usuario Set to: "+recordarusuariovalue.trim());
			}
			Thread.sleep(1000);
			
			//Enter Codigo de Cliente			
			MobileElement codigodecliente = (MobileElement) driver.findElement(By.logicalName("SM_Codig_Cliento"));
			codigodecliente.sendKeys(dt_Afiliacion.getValue("Codigo_Cliente"));
			Thread.sleep(1000);
			Assert.pass("Entered Codigo de Cliente: "+dt_Afiliacion.getValue("Codigo_Cliente"));
			
			//Click Continuar			
			MobileElement continuar = (MobileElement) driver.findElement(By.logicalName("SM_Continuar"));
			continuar.click();
			Thread.sleep(1000);
			Assert.pass("Click on Continuar");
			
			//Verify Pop up - Cliente no registrado & Click on Registrese	
			
			MobileElement popupclientereg = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Popup_Cliente"))));
			String popupclienteregval=popupclientereg.getText();
			if(popupclienteregval.trim().equalsIgnoreCase("Cliente no registrado")) {
				Assert.pass("Popup appeared - Cliente no registrado");
				
				//Click on Registrese				
				MobileElement registrese = (MobileElement) driver.findElement(By.logicalName("SM_Popup_Register"));
				registrese.click();
				Thread.sleep(500);
				Assert.pass("Click on Registrese");
			}else {
				Assert.fail("Cliente no registrado popup not displayed");
			}
			Thread.sleep(1000);
			
			//Set OFF Recordar usuraio			
			MobileElement recordarusuario1 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Record_User1"))));
			String recordarusuariovalue1=recordarusuario1.getText();
			if(recordarusuariovalue1.trim().equalsIgnoreCase("ON")) {
				recordarusuario1.click();
				Assert.pass("Recordar Usuario Set to OFF");
			}else {
				Assert.pass("Recordar Usuario Set to: "+recordarusuariovalue.trim());
			}
			Thread.sleep(1000);
			
			//Select Spinner			
			MobileElement spinner = (MobileElement) driver.findElement(By.logicalName("SM_Spinner"));
			spinner.click();
			Thread.sleep(1000);			
			
			List<MobileElement> spinnerValues = driver.findElementsByClassName("android.widget.TextView");			
			String reqOption = "Código de cliente";
			boolean optFound=false;
			while(!optFound) {
				for(MobileElement spinnerValue: spinnerValues) {
					String eleText = spinnerValue.getText();					
					if(eleText.trim().equalsIgnoreCase(reqOption)) {
						spinnerValue.click();
						Assert.pass("Select the Option Para identificarse"+eleText.trim());
						optFound =true;
						break;
					}
					if(!optFound) {
						//ta.press(212,679).moveTo(0,-10).release().perform();
						ta.press(spinnerValue).moveTo(0,-10).release().perform();
						Thread.sleep(1000);
					}
				}
			}	
			
			//Enter Codigo de Cliente			
			MobileElement codigodecliente1 = (MobileElement) driver.findElement(By.logicalName("SM_Codig_Cliento1"));
			codigodecliente1.sendKeys(dt_Afiliacion.getValue("Codigo_Cliente"));
			Thread.sleep(1000);
			Assert.pass("Entered Codigo de Cliente: "+dt_Afiliacion.getValue("Codigo_Cliente"));
			
			//Click Continuar			
			MobileElement continuar1 = (MobileElement) driver.findElement(By.logicalName("SM_Continuar1"));
			continuar1.click();
			Thread.sleep(1000);
			Assert.pass("Click on Continuar");
			
			//Verify Pop up - Registrado & Click on No cuento con el celular			
			MobileElement popupclientereg1 = (MobileElement) driver.findElement(By.logicalName("SM_Popup_Cliente"));
			String popupclienteregval1=popupclientereg1.getText();
			if(popupclienteregval1.trim().equalsIgnoreCase("Registro")) {
				Assert.pass("Popup appeared - Registro");
				
				//Click on No cuento con el celular				
				MobileElement registrese1 = (MobileElement) driver.findElement(By.logicalName("SM_Accept"));
				registrese1.click();
				Thread.sleep(1000);
				Assert.pass("Click on No cuento con el celular");
			}else {
				Assert.pass("Registro popup not displayed");
			}
			Thread.sleep(1000);			
			
			spinner.click();
			Thread.sleep(1000);
						
			List<MobileElement> spinnerValues1 = driver.findElementsByClassName("android.widget.TextView");
			String reqOption1 = "Clave telefónica";
			boolean optFound1=false;
			while(!optFound1) {
				for(MobileElement spinnerValue1: spinnerValues1) {
					String eleText1 = spinnerValue1.getText();
					System.out.println(eleText1);
					if(eleText1.trim().equalsIgnoreCase(reqOption1)) {
						spinnerValue1.click();
						Assert.pass("Select the Option Para identificarse"+eleText1.trim());
						optFound1 =true;
						break;
					}
					if(!optFound1) {						
						//ta.press(210,671).moveTo(-1,-23).release().perform();
						ta.press(spinnerValue1).moveTo(-1,-23).release().perform();
						Thread.sleep(1000);
					}
				}
			}	
			
			//Enter Clave telefonica			
			MobileElement claveTele = (MobileElement) driver.findElement(By.logicalName("SM_Clave_Telefonica"));
			claveTele.sendKeys(dt_Afiliacion.getValue("Clave_telefonica"));
			Assert.pass("Entered Codigo de Cliente: "+dt_Afiliacion.getValue("Clave_telefonica"));
			Thread.sleep(1000);
						
			//###########################################START - Date Logic############################################
			
			//Click on Calendar	
			String tAction = "";
			MobileElement calendar = (MobileElement) driver.findElement(By.logicalName("SM_Calander"));
			calendar.click();
			Assert.pass("Calendar Click");
			Thread.sleep(1000);
						
			//Get Expected Date
			String dispExpDate = dt_Afiliacion.getValue("Exp_Date");
			
			//Get Expected Date					
			String expDate = dispExpDate.substring(0, 2);
			System.out.println("expDate: "+expDate);
			
			//Get Expected Month
			String expMonth =  dispExpDate.substring(3, 5);
			System.out.println("expMonth: "+expMonth);
			int numExpMonth = 0;
			if(expMonth.trim().equals("01")) {
				expMonth = "Jan";
				numExpMonth = 1;
			}else if(expMonth.trim().equals("02")) {
				expMonth = "Feb";
				numExpMonth = 2;
			}else if(expMonth.trim().equals("03")) {
				expMonth = "Mar";
				numExpMonth = 3;
			}else if(expMonth.trim().equals("04")) {
				expMonth = "Abr";
				numExpMonth = 4;
			}else if(expMonth.trim().equals("05")) {
				expMonth = "May";
				numExpMonth = 5;
			}else if(expMonth.trim().equals("06")) {
				expMonth = "Jun";
				numExpMonth = 6;
			}else if(expMonth.trim().equals("07")) {
				expMonth = "Jul";
				numExpMonth = 7;
			}else if(expMonth.trim().equals("08")) {
				expMonth = "Aug";
				numExpMonth = 8;
			}else if(expMonth.trim().equals("09")) {
				expMonth = "Sep";
				numExpMonth = 9;
			}else if(expMonth.trim().equals("10")) {
				expMonth = "Oct";
				numExpMonth = 10;
			}else if(expMonth.trim().equals("11")) {
				expMonth = "Nov";
				numExpMonth = 11;
			}else if(expMonth.trim().equals("12")) {
				expMonth = "Dec";
				numExpMonth = 12;
			}
			
			//Get Expected Year
			String expYear = dispExpDate.substring(dispExpDate.length()- 4);
			System.out.println("expYear: "+expYear);
			
			//Previous Elements XPath
			String monthPrevXpath = "//android.widget.NumberPicker[@index=0]/android.widget.Button[1]";
			String datePrevXpath = "//android.widget.NumberPicker[@index=1]/android.widget.Button[1]";
			String yearPrevXpath = "//android.widget.NumberPicker[@index=2]/android.widget.Button";
			
			//Current Elements Xpath
			String monthCurrXpath = "//android.widget.NumberPicker[@index=0]/android.widget.EditText";
			String dateCurrXpath = "//android.widget.NumberPicker[@index=1]/android.widget.EditText";
			String yearCurrXpath = "//android.widget.NumberPicker[@index=2]/android.widget.EditText";
			
			//Next Elements XPath
			String monthNextXpath = "//android.widget.NumberPicker[@index=0]/android.widget.Button[2]";
			String dateNextXpath = "//android.widget.NumberPicker[@index=1]/android.widget.Button[2]";			
			
			//Previous Elements 
			MobileElement monthPrev = (MobileElement)driver.findElementByXPath(monthPrevXpath);
			MobileElement datePrev = (MobileElement)driver.findElementByXPath(datePrevXpath);
			MobileElement yearPrev = (MobileElement)driver.findElementByXPath(yearPrevXpath);
			
			//Current Elements 
			MobileElement monthCurr = (MobileElement)driver.findElementByXPath(monthCurrXpath);
			MobileElement dateCurr = (MobileElement)driver.findElementByXPath(dateCurrXpath);
			MobileElement yearCurr = (MobileElement)driver.findElementByXPath(yearCurrXpath);
			
			//Next Elements 
			MobileElement monthNext = (MobileElement)driver.findElementByXPath(monthNextXpath);
			MobileElement dateNext = (MobileElement)driver.findElementByXPath(dateNextXpath);			
			
			//Setting Month*********************************************
			//get Current Number Motnh			 
			String currMonth = monthCurr.getText();
			int numCurrMonth = 0;
			if(currMonth.trim().equalsIgnoreCase("jan")) {
				numCurrMonth = 1;
			}else if(currMonth.trim().equalsIgnoreCase("feb")) {				
				numCurrMonth = 2;
			}else if(currMonth.trim().equalsIgnoreCase("mar")) {				
				numCurrMonth = 3;
			}else if(currMonth.trim().equalsIgnoreCase("apr")) {				
				numCurrMonth = 4;
			}else if(currMonth.trim().equalsIgnoreCase("may")) {				
				numCurrMonth = 5;
			}else if(currMonth.trim().equalsIgnoreCase("jun")) {				
				numCurrMonth = 6;
			}else if(currMonth.trim().equalsIgnoreCase("jul")) {				
				numCurrMonth = 7;
			}else if(currMonth.trim().equalsIgnoreCase("aug")) {				
				numCurrMonth = 8;
			}else if(currMonth.trim().equalsIgnoreCase("sep")) {				
				numCurrMonth = 9;
			}else if(currMonth.trim().equalsIgnoreCase("oct")) {				
				numCurrMonth = 10;
			}else if(currMonth.trim().equalsIgnoreCase("nov")) {				
				numCurrMonth = 11;
			}else if(currMonth.trim().equalsIgnoreCase("dec")) {				
				numCurrMonth = 12;
			}		
			
			//Next or Prev			
			if(numExpMonth>numCurrMonth) {
				tAction = "next";					
			}else if(numExpMonth<numCurrMonth) {
				tAction = "prev";			
			}
			
			if(numExpMonth==12) {				
				tAction = "prev";
			}			
			
			//Setting Month
			String currMonthText="";
			Boolean monthBool = false;			
			while(!monthBool) {	
				Thread.sleep(500);
				currMonthText = monthCurr.getText();			
				if(!currMonthText.trim().equalsIgnoreCase(expMonth)) {
					if(tAction.trim().equalsIgnoreCase("prev")) {												
						ta.press(monthPrev).release().waitAction(Duration.ofMillis(100)).perform();						
					}else if(tAction.trim().equalsIgnoreCase("next")){												
						ta.press(monthNext).release().waitAction(Duration.ofMillis(100)).perform();						
					}					
				}else {						
					monthBool=true;					
					break;	
				}					
			}
								
			//Set Date***************************************			
			int expIntDate = Integer.parseInt(expDate);				
			String currDateText="";
			Boolean dateBool = false;
			currDateText = dateCurr.getText();
			int currIntDate = Integer.parseInt(currDateText);			
			if(expIntDate>currIntDate) {
				tAction = "next";			
			}else if(expIntDate<currIntDate) {
				tAction = "prev";				
			}			
			
			while(!dateBool) {
				Thread.sleep(500);
				currDateText = dateCurr.getText();				
				if(!currDateText.trim().equalsIgnoreCase(expDate)) {
					if(tAction.trim().equalsIgnoreCase("prev")) {							
						ta.press(datePrev).release().waitAction(Duration.ofMillis(100)).perform();			
					}else if(tAction.trim().equalsIgnoreCase("next")){						
						ta.press(dateNext).release().waitAction(Duration.ofMillis(100)).perform();						
					}					
				}else {										
					dateBool=true;					
					break;	
				}				
			}			
						
			//Set Year****************************
			String currYearText="";
			Boolean yearBool = false;			
			while(!yearBool) {
				Thread.sleep(500);
				currYearText = yearCurr.getText();				
				if(!currYearText.trim().equalsIgnoreCase(expYear)) {					
					ta.press(yearPrev).release().waitAction(Duration.ofMillis(100)).perform();					
				}else {					
					yearBool=true;					
					break;		
				}				
			}
			
			//Click on OK button						
			MobileElement okButton = (MobileElement)driver.findElement(By.logicalName("SM_OK"));
			okButton.click();
			Assert.pass("Date Selected: "+dispExpDate);
			Thread.sleep(1000);			
			
			//###########################################END - Date Logic############################################
			
			//Click on Continue						
			MobileElement contButton = (MobileElement)driver.findElement(By.logicalName("SM_Continuar"));
			contButton.click();
			Assert.pass("Continue Button Click");
			Thread.sleep(1000);
			
			//Check for Not Valid Message				
			Boolean invalidMessage = driver.findElementById("mx.bancosantander.smartbank:id/tvTitle").isDisplayed();			
			
			if(invalidMessage) {				
			MobileElement invalidAccept = (MobileElement)driver.findElementById("mx.bancosantander.smartbank:id/btnAccept");
			String invalidMsgText = driver.findElementById("mx.bancosantander.smartbank:id/tvTitle").getText().trim();
			Assert.fail("Data is not valid!!: "+invalidMsgText);
			invalidAccept.click();
			Thread.sleep(1000);				
			}		
			
			//To be Continued with valid data
			Assert.pass("Afiliacion done");			
			Assert.done("End - Afiliacione");

		}catch(Exception e) {
			Assert.fail("Afiliacion Failed");
			e.printStackTrace();
		}
	}
	
	public static void Transferencias(){
		MobileElement continuar= null;
		try {		
		//Start Test Case
		Assert.done("Start - Transferencias");
		
		//Click on Menu
		MobileElement menu = (MobileElement) driver.findElement(By.logicalName("SM_Menu"));
		menu.click();
		Assert.pass("Menu Click");
		Thread.sleep(1000);	
		
		//Click on Home		
		MobileElement home = (MobileElement) driver.findElement(By.logicalName("SM_Home"));
		home.click();
		Assert.pass("Return to Home");
		Thread.sleep(1000);
				
		//Click on Accesso
		MobileElement accesso = (MobileElement) driver.findElement(By.logicalName("SM_Accesso"));
		Assert.pass("Click Accesso");
		accesso.click();
		Thread.sleep(1000);		
		
		//Set OFF Recordar usuraio		
		MobileElement recordarusuario = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Record_User"))));
		String recordarusuariovalue=recordarusuario.getText();
		if(recordarusuariovalue.trim().equalsIgnoreCase("ON")) {
			recordarusuario.click();
			Assert.pass("Recordar Usuario Set to OFF");
		}else {
			Assert.pass("Recordar Usuario Set to: "+recordarusuariovalue.trim());
		}
		
		//Enter Codigo de Cliente		
		MobileElement codigodecliente = (MobileElement) driver.findElement(By.logicalName("SM_Codig_Cliento"));
		codigodecliente.sendKeys(dt_Transferencias.getValue("Codigo_Cliente"));
		Assert.pass("Entered Codigo de Cliente: "+dt_Transferencias.getValue("Codigo_Cliente"));
		Thread.sleep(1000);		
		
		//Click Continuar
		try {
			continuar = (MobileElement) driver.findElement(By.logicalName("SM_Continuar"));
			continuar.click();
			Assert.pass("Click on Continuar");
			Thread.sleep(1000);		
		}catch(Exception e) {}		
		
		//Enter NIP		
		MobileElement nip = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_NIP"))));
		nip.sendKeys(dt_Transferencias.getValue("Contrasenia"));
		Assert.pass("Contrensia Entered: "+dt_Transferencias.getValue("Contrasenia"));
		Thread.sleep(1000);		
		
		//Click Continuar
		try {
		continuar = (MobileElement) driver.findElement(By.logicalName("SM_Continuar"));
		continuar.click();
		Assert.pass("Click on Continuar");
		Thread.sleep(1000);		
		}catch(Exception e) {}	
		
		//Click on Menu		
		menu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Menu"))));
		menu.click();
		Assert.pass("Menu Click");
		Thread.sleep(1000);		
		
		//Open Transferencias Option		
		MobileElement transferencias = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Transferencias"))));			
		transferencias.click();
		Assert.pass("Transferencias Option Click");
		Thread.sleep(1000);		
		
		//Open Cuentas Propias Option		
		MobileElement cuentasPropias = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Cuentas_Propias"))));		
		cuentasPropias.click();
		Assert.pass("Cuentas Propias Option Click");
		Thread.sleep(10000);	
		
		//Wait to load Page
		MobileElement pageLoad = (MobileElement) wait.until(ExpectedConditions.visibilityOf((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/tv_title_account_selector_from")));
		
		//Selecting Origin Account
		MobileElement originSearch = null;
		MobileElement destSearch = null;		
		List<MobileElement> searchImages = driver.findElementsByXPath("(//android.widget.ImageView[@resource-id=\"mx.bancosantander.smartbank:id/iv_search_product\"])");
		
		if (searchImages.size()==2) {
			originSearch = searchImages.get(0);
			destSearch = searchImages.get(1);
		}else if (searchImages.size()==3) {
			originSearch = searchImages.get(0);
			destSearch = searchImages.get(2);		
		}	
		
		//Click on Origin Search
		originSearch.click();
		Thread.sleep(2000);
		
		//Search Product
		MobileElement searchBox = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/et_search")));		
		searchBox.sendKeys(dt_Transferencias.getValue("Origin"));
		Assert.pass("Origin Product Search");
		Thread.sleep(1000);		
		
		//Select Product
		MobileElement productItem = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/rl_content")));		
		productItem.click();
		Thread.sleep(1000);		
		
		//Click on Dest Search
		destSearch.click();
		Thread.sleep(2000);
		
		//Search Product
		MobileElement searchBox1 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/et_search")));		
		searchBox1.sendKeys(dt_Transferencias.getValue("Destination"));
		Assert.pass("Dest Product Search");
		Thread.sleep(1000);
		
		//Select Product
		MobileElement productItem1 = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/rl_content")));		
		productItem1.click();
		Thread.sleep(1000);
		
		//Touch Action to Scroll Down
		ta.press(229,602).moveTo(-4,-100).release().perform();
		Thread.sleep(1000);
		
		//Enter Concepto		
		MobileElement concepto = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/et_concept_transfer")));
		concepto.sendKeys("Prueba");
		Assert.pass("Concepto Entered: Prueba");
		Thread.sleep(2000);		
		
		//Enter Amount
		MobileElement amount = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/et_amount_transfer")));
		amount.sendKeys(dt_Transferencias.getValue("Amount"));
		/*char[] AmountSplit = dt_Transferencias.getValue("Amount").toCharArray();
				
		for(char c: AmountSplit) {		
			amount.sendKeys( Character.toString(c));
			Thread.sleep(2);
		}*/		
		Assert.pass("Amount Entered");
		Thread.sleep(1000);			
		
		//Click on Transfer Confirm
		MobileElement tConfirm = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/btn_transfer_confirm")));			
		tConfirm.click();	
		Assert.pass("Click on Transfer Confirm");
		Thread.sleep(2000);
		
		//Touch Action to Scroll Down
		ta.press(229,602).moveTo(-4,-100).release().perform();
		Thread.sleep(1000);
		
		//Click on Transfer		
		MobileElement transfer = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/btn_transfer_transfer")));		
		transfer.click();		
		Assert.pass("Click on Transfer");
		Thread.sleep(2000);		
		
		//Transfer Realiza		
		MobileElement tRealiza = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"design image\"])[1]")));
		if(tRealiza.isDisplayed()) {
			Assert.pass("Transfer Success");
		}else {
			
			Assert.pass("Transfer Failed");
		}
		Thread.sleep(2000);
		
		//Touch Action to Scroll Down
		ta.press(229,602).moveTo(7,-100).release().perform();
		//ta.press(tRealiza).moveTo(7,-200).release().perform();
		Thread.sleep(2000);
		
		//Click on finalize		
		MobileElement finalize = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/btn_finalize")));			
		finalize.click();		
		Assert.pass("Click on Finalizer");
		Thread.sleep(20000);
		
		//Logout Session		
		//Click on Menu
		menu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(By.logicalName("SM_Menu"))));
		menu.click();
		Thread.sleep(1000);		
		
		//Click on Logout
		MobileElement logout = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/menu_logout")));		
		logout.click();		
		Assert.pass("Logout Click");
		Thread.sleep(1000);
		
		//Accept Logout
		MobileElement alogout = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElementById("mx.bancosantander.smartbank:id/btnAccept")));			
		alogout.click();
		Assert.pass("Confirm Logout");
		Thread.sleep(1000);		
		
		}catch(Exception e) {
			e.printStackTrace();			
		}	
	}
	
	protected static boolean isElementPresent(By by) {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	    List<MobileElement> list = driver.findElements(by);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    if (list.size() == 0) {
	        return false;
	    } else {
	        return list.get(0).isDisplayed();
	    }
	}
	
	public boolean elementIsNotPresent(By by) {
	    try {
	        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	        return driver.findElements(by).isEmpty();
	    } finally {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
	}
	
	public void scrollDown() {
	    Dimension size = driver.manage().window().getSize();
	    int x = size.getWidth() / 2;
	    int starty = (int) (size.getHeight() * 0.60);
	    int endy = (int) (size.getHeight() * 0.10);
	    //driver.swipe(x, starty, x, endy, 2000);
	}
	
	
}
