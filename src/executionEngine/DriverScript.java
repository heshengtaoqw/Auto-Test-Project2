package executionEngine;


import utility.ExcelUtils;
import utility.Log4j;
import config.ActionsKeywords;
import config.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;

public class DriverScript {

	
	// 声明一个public static的类对象，所以我们可以在main方法范围之外去使用。
    public static ActionsKeywords actionsKeywords;
    public static String sActionKeyword;
    // 下面是返回类型是方法，这里用到反射类
    public static Method method[];
    

    // 新建一个Properties对象
    public static Properties OR;
    public static String sPageObject;
    public static Boolean bResult;
 
    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sRunMode;
 
 
    // 这里我们初始化'ActionsKeywords'类的一个对象
    public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        //actionKeywords = new ActionsKeywords()
        // 原文作者是采用上面这个代码，下面的代码是我查找反射资料，是这么获取class对象的
        //Class<?> actionsKeywords = Class.forName("config.ActionsKeywords");
    	actionsKeywords = new ActionsKeywords();
        method = actionsKeywords.getClass().getMethods();
        //拿到该类所有public方法
        //method = actionsKeywords.getMethods();
    }
 
    
    public static void main(String[] args) throws Exception {
    	
        // 这样一定要加，否则报log4j初始化的警告
        DOMConfigurator.configure("log4j.xml");
        //DOMConfigurator.configure("log4j.properties");
        
        
        Log4j.startTestCase("Login_01");
        // 由于上面初始化反射类写在构造函数，而main方法是第一个被最新，如果不添加
        // 下面这个当前类的初始化代码，就会报method.length空指针异常，我这里暂时这里处理
        // 原文作者不知道为啥没有报错。
        Log4j.info("加载和读取Excel数据文件");
        ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData);
        // 创建一个文件输入流对象，参数来源外部OR.txt文件
        FileInputStream fs = new FileInputStream(Constants.Path_OR);
        // 创建一个Properties对象
        OR = new Properties(System.getProperties());
        OR.load(fs);
        DriverScript startEngine = new DriverScript();
        Log4j.info("开始执行测试用例。");
        startEngine.execute_TestCase();
        Log4j.info("测试用例执行结束。");
       
    }
    
    private void execute_TestCase() throws Exception{
    	//获取测试用例数量
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        //外层for循环，有多少个测试用例就执行多少次循环
        for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
            //从Test Case表获取测试ID
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
            //获取当前测试用例的Run Mode的值
            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
            // Run Mode的值控制用例是否被执行
            if (sRunMode.equals("Yes")){
                // 只有当Run Mode的单元格数据是Yes，下面代码才会被执行
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
 
                bResult=true;
                
                //下面这个for循环的次数就等于测试用例的步骤数
                for (;iTestStep <= iTestLastStep;iTestStep++){
                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
                    sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
                    execute_Actions();
                    if(bResult==false){
                        //If 'false' then store the test case result as Fail
                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
                        //End the test case in the logs
                        Log4j.endTestCase(sTestCaseID);
                        //By this break statement, execution flow will not execute any more test step of the failed test case
                        break;
                    }
                }
                if(bResult==true){
                    //Storing the result as Pass in the excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
                    Log4j.endTestCase(sTestCaseID);
                }

            }
        }

    	
    }
 
    private static void execute_Actions() throws Exception {
        //循环遍历每一个关键字驱动方法（在actionskeywords.java中）
        // 下面method.length表示方法个数，method变量表示任何一个关键字方法，例如openBrowser()
        for(int i = 0;i < method.length;i++){
            //开始对比代码中关键字方法名称和excel中关键字这列值是否匹配
            if(method[i].getName().equals(sActionKeyword)){
                //一但匹配到关键字，并传递页面对象参数和动作关键字参数
                method[i].invoke(actionsKeywords,sPageObject);
                if(bResult==true){
                    //If the executed test step value is true, Pass the test step in Excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    break;
                }
                else {
                	ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    //In case of false, the test execution will not reach to last step of closing browser
                    //So it make sense to close the browser before moving on to next test case
                    ActionsKeywords.closeBrowser("");
                    break;
                }
            }
        }
    }
}
