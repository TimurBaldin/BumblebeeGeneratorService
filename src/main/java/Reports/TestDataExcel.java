package Reports;
import Rules.ReportExcel;
import Tables.StringTableBufer;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class TestDataExcel implements ReportExcel {
private int columnIndex=0;
private  int ROW_MAX_DEF=5000;
private  int COLUMN_MAX_DEF=500;
private  int ROW_MAX_BUF;
private XSSFRow row;
private  XSSFCell cell;
private XSSFSheet sheet;
private XSSFCell cell_bufer;
private XSSFRow row_bufer;
private List<XSSFRow> row_repository=new ArrayList<XSSFRow>();
private  XSSFWorkbook book;
String DOC_NAME;
String Sheet_NAME;
List<StringTableBufer> bufer;
public  void create(String DOC_NAME, String Sheet_NAME,String COLUMN_NAME,List<StringTableBufer> bufer){
    this.DOC_NAME=DOC_NAME;
    this.Sheet_NAME=Sheet_NAME;
    this.bufer=bufer;
    if(!check()){return;}
    try {
        File file = new File ("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\java\\Reports\\DOC\\" + DOC_NAME + ".xlsx");
        FileOutputStream fileOut = new FileOutputStream(file);
        file.createNewFile ();
        if(preparation()) {
            for (int i = 0; i <= bufer.size() - 1; i++) {
                if(i==0){
                    row = sheet.getRow(i);
                    cell = row.getCell(columnIndex);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(COLUMN_NAME);
                    continue;
                }
                row = sheet.getRow(i);
                cell = row.getCell(columnIndex);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(""+bufer.get(i));
                }
        }else {
            int key=0;
            for (int i = 0; i <= bufer.size() - 1; i++) {
                if(i==0){
                    row = sheet.getRow(i);
                    cell = row.getCell(columnIndex);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(COLUMN_NAME);
                    continue;
                }
                if(i<=ROW_MAX_DEF){
                    row = sheet.getRow(i);
                    cell = row.getCell(columnIndex);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(""+bufer.get(i));
                }else {
                    row_bufer=row_repository.get(key);
                    if(row_bufer!=null && sheet.getRow(i)!=null){
                        row_bufer = sheet.getRow(i);
                        cell_bufer= row_bufer.getCell(columnIndex);
                        cell_bufer.setCellType(CellType.STRING);
                        cell_bufer.setCellValue(""+bufer.get(i));
                    }else {
                        ++key;
                        row_bufer=row_repository.get(key);
                        if(row_bufer!=null  && sheet.getRow(i)!=null){
                            row_bufer = sheet.getRow(i);
                            cell_bufer= row_bufer.getCell(columnIndex);
                            cell_bufer.setCellType(CellType.STRING);
                            cell_bufer.setCellValue(""+bufer.get(i));
                        }else {
                            System.out.println("Row not found");
                            return;
                        }
                    }
                }


            }
        }
        ++columnIndex;
        bufer=null;
        book.write(fileOut);
        fileOut.close();
        } catch (IOException e) {
        e.printStackTrace ( );
    }
}
private boolean preparation(){
    if(book==null) {
        book = new XSSFWorkbook();
        sheet= book.createSheet(Sheet_NAME);
        if(ROW_MAX_DEF>=bufer.size()-1) {
            for (int i = 0; i <= ROW_MAX_DEF; i++) {
                row = sheet.createRow(i);
                for (int j = 0; j <= COLUMN_MAX_DEF; j++) {
                    cell = row.createCell(j);
                }
            }
        }else {
            for (int i = 0; i <= bufer.size()-1; i++) {
                row = sheet.createRow(i);
                for (int j = 0; j <= bufer.size()-1; j++) {
                    cell = row.createCell(j);
                }
            }
        }
        return true;
    }else {
        if(ROW_MAX_DEF<(bufer.size()-1)){
            for(int q=ROW_MAX_DEF+1;q<=bufer.size()-1;q++){
                row_bufer=sheet.createRow(q);
                for(int j=0;j<=bufer.size()-1;j++){
                    cell_bufer = row_bufer.createCell(j);

                }
            }
            row_repository.add(row_bufer);
            ROW_MAX_BUF=(bufer.size()-1);
            return false;
        }

    }
    return true;

}
private boolean check(){
    if(DOC_NAME==null || Sheet_NAME==null || bufer.size()==0){
        return false;
    }else {return true;}
}
}
