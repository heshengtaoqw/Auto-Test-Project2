package config;
 
public class Constants {
 
    // 这里定义为public static的类型，方便其他任何类进行访问和调用
    public static final String URL = "http://10.31.0.59/login.html";
    public static final String Path_TestData = "D:\\Programme\\EclipseSimplified\\workspace\\auto-test-keyword\\Auto-Test-Project2\\src\\dataEngine\\";
    public static final String File_TestData = "dataEngine.xlsx";
 
    // dataEngine.xlsx中一些单元格的索引值
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID = 1 ;
    public static final int Col_ActionKeyword = 4 ;
    public static final int Col_PageObject = 3;
    public static final int Col_RunMode = 2 ;
 
    // DataEngmine.excel中sheet的名称
    public static final String Sheet_TestSteps = "Test Steps";
    public static final String Sheet_TestCases = "Test Cases";
 
    // 测试登录用到的用户数据
    public static final String UserName = "123";
    public static final String Password = "456";
    public static final String VeifyCode = "789";
    

    // OR(对象仓库)文件路径
    public static final String Path_OR =".//src//config/OR.txt";
    

    // 第一个是测试用例结果标记列的索引，第二个是测试步骤里面的结果列索引
     public static final int Col_Result =3 ;
     public static final int Col_TestStepResult =5 ;
     
  // 结果状态标记
     public static final String KEYWORD_FAIL = "FAIL";
     public static final String KEYWORD_PASS = "PASS";
}