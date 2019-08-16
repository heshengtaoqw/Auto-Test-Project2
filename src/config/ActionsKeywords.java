package config;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
 
import java.util.concurrent.TimeUnit;
 
public class ActionsKeywords {
 
    public static WebDriver driver;
 
    /**
     * ���·������������dataEngine.xlsx�ļ��е�action_keyword���еĹؼ��ֶ����з�װ
     * �ȹؼ��ֿ�ܿ�������ˣ�����������������ȡ�����ļ�ȥ������ͬ����������Ͳ��Ե�ַ
     * �����Ͳ������д��������������
     */
    public static void openBrowser() {
        // ���������ʱ��д����firefox�������Զ�������
    	System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
  		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");       
        driver = new FirefoxDriver();
    }
 
    public static void openUrl() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://10.31.0.59/login.html");
 
    }
 

    public static void input_Username() {
    	driver.findElement(By.id("user")).sendKeys("13923811009");
    }
 
    public static void input_Password() {
    	driver.findElement(By.id("password")).sendKeys("112232");
    }
    
    public static void input_VeifyCode() {
    	driver.findElement(By.id("verify_code")).sendKeys("123456");
    }
    
    public static void click_ForgetPWD() {
    	driver.findElement(By.id("f_password")).click();
    }
    
    public static void click_ImgVerifyCode() {
    	driver.findElement(By.id("img_verifycode")).click();
    }
 
    public static void click_Login() {
    	driver.findElement(By.id("login_btn")).click();
    }
 
    // �ر���������˳�
    public static void closeBrowser() {
        driver.quit();
    }
    
   
}