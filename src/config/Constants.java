package config;
 
public class Constants {
 
    // ���ﶨ��Ϊpublic static�����ͣ����������κ�����з��ʺ͵���
    public static final String URL = "http://10.31.0.59/login.html";
    public static final String Path_TestData = "D:\\Programme\\EclipseSimplified\\workspace\\auto-test-keyword\\Auto-Test-Project2\\src\\dataEngine\\";
    public static final String File_TestData = "dataEngine.xlsx";
 
    // dataEngine.xlsx��һЩ��Ԫ�������ֵ
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID = 1 ;
    public static final int Col_ActionKeyword = 4 ;
    public static final int Col_PageObject = 3;
    public static final int Col_RunMode = 2 ;
 
    // DataEngmine.excel��sheet������
    public static final String Sheet_TestSteps = "Test Steps";
    public static final String Sheet_TestCases = "Test Cases";
 
    // ���Ե�¼�õ����û�����
    public static final String UserName = "123";
    public static final String Password = "456";
    public static final String VeifyCode = "789";
    

    // OR(����ֿ�)�ļ�·��
    public static final String Path_OR =".//src//config/OR.txt";
    

    // ��һ���ǲ��������������е��������ڶ����ǲ��Բ�������Ľ��������
     public static final int Col_Result =3 ;
     public static final int Col_TestStepResult =5 ;
     
  // ���״̬���
     public static final String KEYWORD_FAIL = "FAIL";
     public static final String KEYWORD_PASS = "PASS";
}