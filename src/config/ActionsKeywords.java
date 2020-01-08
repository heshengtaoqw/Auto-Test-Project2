package config;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;

import executionEngine.DriverScript;
import utility.Log4j;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;

 
public class ActionsKeywords {
 
    public static WebDriver driver;
 
    /**
     * ���·������������dataEngine.xlsx�ļ��е�action_keyword���еĹؼ��ֶ����з�װ
     * �ȹؼ��ֿ�ܿ�������ˣ�����������������ȡ�����ļ�ȥ������ͬ����������Ͳ��Ե�ַ
     * �����Ͳ������д��������������
     */
    public static void openBrowser(String object) {
        // ���������ʱ��д����firefox�������Զ�������
    	System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
    	//�ر�gecko��־
    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
  		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
  		try {
  	        driver = new FirefoxDriver();
  	    	Log4j.info("����������");
  		}catch(Exception e) {
  			Log4j.error("�޷��������" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 
    public static void openUrl(String object) {
    	try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(Constants.URL);
        	Log4j.info("����ַ���");
    	}catch(Exception e) {
  			Log4j.error("�޷�����ַ" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 

    public static void input_Username(String object) {
    	try {
    	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.UserName);
    	Log4j.info("�����û������");
    	}catch(Exception e) {
  			Log4j.error("�޷������û���" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 
    public static void input_Password(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.Password);
        	Log4j.info("�����������");
    	}catch(Exception e) {
  			Log4j.error("�޷���������" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void input_VeifyCode(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.VeifyCode);
        	Log4j.info("������֤�����");
    	}catch(Exception e) {
  			Log4j.error("�޷�������֤��" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void click_ForgetPWD(String object) {
    	try {
    		driver.findElement(By.id(OR.getProperty(object))).click();
    		Log4j.info("�����������ɹ�");
    	}catch(Exception e) {
  			Log4j.error("�޷������������" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void click_ImgVerifyCode(String object) {
    	try {
    		driver.findElement(By.id(OR.getProperty(object))).click();
    		Log4j.info("ˢ����֤��ɹ�");
    	}catch(Exception e) {
  			Log4j.error("�޷�ˢ����֤��" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
 
    public static void click_Login(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).click();
        	Log4j.info("�����¼�ɹ�");
    	}catch(Exception e) {
  			Log4j.error("�޷������¼" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
 
    // �ر���������˳�
    public static void closeBrowser(String object) {
    	try {
            driver.quit();
            Log4j.info("�ر�������ɹ�");
    	}catch(Exception e) {
  			Log4j.error("�޷��ر������" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
    
   
}