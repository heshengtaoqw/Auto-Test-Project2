package config;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
 
import java.util.concurrent.TimeUnit;
 
public class ActionsKeywords {
 
    public static WebDriver driver;
 
    /**
     * 以下方法，我们针对dataEngine.xlsx文件中的action_keyword这列的关键字都进行封装
     * 等关键字框架快设计完了，我们再来调整，读取配置文件去启动不同测试浏览器和测试地址
     * 这样就不会代码写死这两个参数。
     */
    public static void openBrowser() {
        // 这里，我们暂时都写死用firefox来进行自动化测试
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
 
    // 关闭浏览器并退出
    public static void closeBrowser() {
        driver.quit();
    }
    
   
}