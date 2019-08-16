package executionEngine;


import utility.ExcelUtils;
import config.ActionsKeywords;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class DriverScript {

	
	// ����һ��public static��������������ǿ�����main������Χ֮��ȥʹ�á�
    public static ActionsKeywords actionKeywords;
    public static String sActionKeyword;
    // �����Ƿ��������Ƿ����������õ�������
    public static Method method[];
 
 
    // �������ǳ�ʼ��'ActionsKeywords'���һ������
    public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        //actionKeywords = new ActionsKeywords()
        // ԭ�������ǲ�������������룬����Ĵ������Ҳ��ҷ������ϣ�����ô��ȡclass�����
        Class<?> actionKeywords = Class.forName("config.ActionsKeywords");
        //�õ���������public����
        method = actionKeywords.getMethods();
    }
 
    public static void main(String[] args) throws Exception {
        // ���������ʼ��������д�ڹ��캯������main�����ǵ�һ�������£���������
        // ���������ǰ��ĳ�ʼ�����룬�ͻᱨmethod.length��ָ���쳣����������ʱ���ﴦ��
        // ԭ�����߲�֪��Ϊɶû�б���
        DriverScript ds = new DriverScript();
        String excel_path = "D:\\Programme\\EclipseSimplified\\workspace\\auto-test-keyword\\src\\dataEngine\\dataEngine.xlsx";
        // ���ض�ȡexcel�ļ�
        ExcelUtils.setExcelFile(excel_path, "Test");
 
        for (int iRow = 1; iRow <= 2; iRow++) {
            //3��ʾexcel��keyword��Ӧ���е�������Ҳ������������4��
            sActionKeyword = ExcelUtils.getCellData(iRow, 3);
            //����'execute_Actions'����
            execute_Actions();
        }
 
    }
 
    private static void execute_Actions() throws Exception {
        //ѭ������ÿһ���ؼ���������������actionskeywords.java�У�
        // ����method.length��ʾ����������method������ʾ�κ�һ���ؼ��ַ���������openBrowser()
        for(int i = 0;i < method.length;i++){
            //��ʼ�Աȴ����йؼ��ַ������ƺ�excel�йؼ�������ֵ�Ƿ�ƥ��
            if(method[i].getName().equals(sActionKeyword)){
                //һ��ƥ�䵽��عؼ��ַ������ͻ���ö�Ӧ�Ĺؼ��־�̬����
                method[i].invoke(actionKeywords);
                //һ���κιؼ��ֱ�ִ�У�����break���ȥ����forѭ����
                break;
            }
        }
    }
}
