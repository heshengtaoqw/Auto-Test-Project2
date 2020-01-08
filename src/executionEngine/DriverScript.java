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

	
	// ����һ��public static��������������ǿ�����main������Χ֮��ȥʹ�á�
    public static ActionsKeywords actionsKeywords;
    public static String sActionKeyword;
    // �����Ƿ��������Ƿ����������õ�������
    public static Method method[];
    

    // �½�һ��Properties����
    public static Properties OR;
    public static String sPageObject;
    public static Boolean bResult;
 
    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sRunMode;
 
 
    // �������ǳ�ʼ��'ActionsKeywords'���һ������
    public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        //actionKeywords = new ActionsKeywords()
        // ԭ�������ǲ�������������룬����Ĵ������Ҳ��ҷ������ϣ�����ô��ȡclass�����
        //Class<?> actionsKeywords = Class.forName("config.ActionsKeywords");
    	actionsKeywords = new ActionsKeywords();
        method = actionsKeywords.getClass().getMethods();
        //�õ���������public����
        //method = actionsKeywords.getMethods();
    }
 
    
    public static void main(String[] args) throws Exception {
    	
        // ����һ��Ҫ�ӣ�����log4j��ʼ���ľ���
        DOMConfigurator.configure("log4j.xml");
        //DOMConfigurator.configure("log4j.properties");
        
        
        Log4j.startTestCase("Login_01");
        // ���������ʼ��������д�ڹ��캯������main�����ǵ�һ�������£���������
        // ���������ǰ��ĳ�ʼ�����룬�ͻᱨmethod.length��ָ���쳣����������ʱ���ﴦ��
        // ԭ�����߲�֪��Ϊɶû�б���
        Log4j.info("���غͶ�ȡExcel�����ļ�");
        ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData);
        // ����һ���ļ����������󣬲�����Դ�ⲿOR.txt�ļ�
        FileInputStream fs = new FileInputStream(Constants.Path_OR);
        // ����һ��Properties����
        OR = new Properties(System.getProperties());
        OR.load(fs);
        DriverScript startEngine = new DriverScript();
        Log4j.info("��ʼִ�в���������");
        startEngine.execute_TestCase();
        Log4j.info("��������ִ�н�����");
       
    }
    
    private void execute_TestCase() throws Exception{
    	//��ȡ������������
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        //���forѭ�����ж��ٸ�����������ִ�ж��ٴ�ѭ��
        for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
            //��Test Case���ȡ����ID
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
            //��ȡ��ǰ����������Run Mode��ֵ
            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
            // Run Mode��ֵ���������Ƿ�ִ��
            if (sRunMode.equals("Yes")){
                // ֻ�е�Run Mode�ĵ�Ԫ��������Yes���������Żᱻִ��
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
 
                bResult=true;
                
                //�������forѭ���Ĵ����͵��ڲ��������Ĳ�����
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
        //ѭ������ÿһ���ؼ���������������actionskeywords.java�У�
        // ����method.length��ʾ����������method������ʾ�κ�һ���ؼ��ַ���������openBrowser()
        for(int i = 0;i < method.length;i++){
            //��ʼ�Աȴ����йؼ��ַ������ƺ�excel�йؼ�������ֵ�Ƿ�ƥ��
            if(method[i].getName().equals(sActionKeyword)){
                //һ��ƥ�䵽�ؼ��֣�������ҳ���������Ͷ����ؼ��ֲ���
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
