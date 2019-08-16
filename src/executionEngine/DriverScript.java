package executionEngine;


import utility.ExcelUtils;
import config.ActionsKeywords;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class DriverScript {

	
	// 声明一个public static的类对象，所以我们可以在main方法范围之外去使用。
    public static ActionsKeywords actionKeywords;
    public static String sActionKeyword;
    // 下面是返回类型是方法，这里用到反射类
    public static Method method[];
 
 
    // 这里我们初始化'ActionsKeywords'类的一个对象
    public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        //actionKeywords = new ActionsKeywords()
        // 原文作者是采用上面这个代码，下面的代码是我查找反射资料，是这么获取class对象的
        Class<?> actionKeywords = Class.forName("config.ActionsKeywords");
        //拿到该类所有public方法
        method = actionKeywords.getMethods();
    }
 
    public static void main(String[] args) throws Exception {
        // 由于上面初始化反射类写在构造函数，而main方法是第一个被最新，如果不添加
        // 下面这个当前类的初始化代码，就会报method.length空指针异常，我这里暂时这里处理
        // 原文作者不知道为啥没有报错。
        DriverScript ds = new DriverScript();
        String excel_path = "D:\\Programme\\EclipseSimplified\\workspace\\auto-test-keyword\\src\\dataEngine\\dataEngine.xlsx";
        // 加载读取excel文件
        ExcelUtils.setExcelFile(excel_path, "Test");
 
        for (int iRow = 1; iRow <= 2; iRow++) {
            //3表示excel中keyword对应的列的索引，也就是左边数起第4列
            sActionKeyword = ExcelUtils.getCellData(iRow, 3);
            //调用'execute_Actions'方法
            execute_Actions();
        }
 
    }
 
    private static void execute_Actions() throws Exception {
        //循环遍历每一个关键字驱动方法（在actionskeywords.java中）
        // 下面method.length表示方法个数，method变量表示任何一个关键字方法，例如openBrowser()
        for(int i = 0;i < method.length;i++){
            //开始对比代码中关键字方法名称和excel中关键字这列值是否匹配
            if(method[i].getName().equals(sActionKeyword)){
                //一但匹配到相关关键字方法，就会调用对应的关键字静态方法
                method[i].invoke(actionKeywords);
                //一旦任何关键字被执行，利用break语句去跳出for循环。
                break;
            }
        }
    }
}
