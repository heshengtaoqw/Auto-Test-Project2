package utility;
 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import config.Constants;
import executionEngine.DriverScript;

 
public class ExcelUtils {
 
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
 
    // ����Excel�ļ�·���������ȡ���ļ�
    public static void setExcelFile(String Path) throws IOException {
        FileInputStream ExcelFile = new FileInputStream(Path);
        ExcelWBook = new XSSFWorkbook(ExcelFile);
    }
    
    
 
    // ��ȡExcel�ļ���Ԫ������
    public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
    	ExcelWSheet = ExcelWBook.getSheet(SheetName);
    	try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
    	}catch(Exception e){
    		Log4j.error(e.getMessage());
    		DriverScript.bResult = false;
    		return "";
    	}
    }
    

    //�õ�һ������������
    public static int getRowCount(String SheetName){
    	int number = 0;
    	try {
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
            number = ExcelWSheet.getLastRowNum()+1;
    	}catch(Exception e){
    		Log4j.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
    	}
    	return number; 
    }
       

    //�õ������������к�,��һ�γ��ָ��������к�
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int i;
        try {
        	ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int rowCount = ExcelUtils.getRowCount(SheetName);
            for (i=0 ; i<rowCount; i++){
                if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
                    break;
                }
            }
            return i;
        }catch (Exception e){
            Log4j.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
            return 0;
        }
    }
    
    
    //����һ�����������ж��ٸ�����
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
    	try {
    		for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName)-1;i++){
                if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
                    int number = i-1;
                    return number;
                }
            }
            //���һ�ж��Ҳ��������ж��ļ��Ƿ�Ϊ�գ����Ϊ�������һ�е��ڵ�һ�У�����ļ���Ϊ�գ���ʹ�����һ��+1
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int number=ExcelWSheet.getLastRowNum()+1;
            return number;
    	}catch (Exception e){
            Log4j.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
            return 0;
        }  
    }
    
    
    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
        try{
 
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData+Constants.File_TestData);
            ExcelWBook.write(fileOut);
            //fileOut.flush();
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData+Constants.File_TestData));
        }catch(Exception e){
            DriverScript.bResult = false;
        }
    }

}