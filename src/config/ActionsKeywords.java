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
     * 以下方法，我们针对dataEngine.xlsx文件中的action_keyword这列的关键字都进行封装
     * 等关键字框架快设计完了，我们再来调整，读取配置文件去启动不同测试浏览器和测试地址
     * 这样就不会代码写死这两个参数。
     */
    public static void openBrowser(String object) {
        // 这里，我们暂时都写死用firefox来进行自动化测试
    	System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
    	//关闭gecko日志
    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
  		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
  		try {
  	        driver = new FirefoxDriver();
  	    	Log4j.info("打开浏览器完成");
  		}catch(Exception e) {
  			Log4j.error("无法打开浏览器" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 
    public static void openUrl(String object) {
    	try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(Constants.URL);
        	Log4j.info("打开网址完成");
    	}catch(Exception e) {
  			Log4j.error("无法打开网址" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 

    public static void input_Username(String object) {
    	try {
    	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.UserName);
    	Log4j.info("输入用户名完成");
    	}catch(Exception e) {
  			Log4j.error("无法输入用户名" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
 
    public static void input_Password(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.Password);
        	Log4j.info("输入密码完成");
    	}catch(Exception e) {
  			Log4j.error("无法输入密码" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void input_VeifyCode(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).sendKeys(Constants.VeifyCode);
        	Log4j.info("输入验证码完成");
    	}catch(Exception e) {
  			Log4j.error("无法输入验证码" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void click_ForgetPWD(String object) {
    	try {
    		driver.findElement(By.id(OR.getProperty(object))).click();
    		Log4j.info("点击忘记密码成功");
    	}catch(Exception e) {
  			Log4j.error("无法点击忘记密码" + e.getMessage());
  			DriverScript.bResult = false;
  		}
    }
    
    public static void click_ImgVerifyCode(String object) {
    	try {
    		driver.findElement(By.id(OR.getProperty(object))).click();
    		Log4j.info("刷新验证码成功");
    	}catch(Exception e) {
  			Log4j.error("无法刷新验证码" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
 
    public static void click_Login(String object) {
    	try {
        	driver.findElement(By.id(OR.getProperty(object))).click();
        	Log4j.info("点击登录成功");
    	}catch(Exception e) {
  			Log4j.error("无法点击登录" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
 
    // 关闭浏览器并退出
    public static void closeBrowser(String object) {
    	try {
            driver.quit();
            Log4j.info("关闭浏览器成功");
    	}catch(Exception e) {
  			Log4j.error("无法关闭浏览器" + e.getMessage());
  			DriverScript.bResult = false;
  		}	
    }
    
   
}