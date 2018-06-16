package Reports;
import Rules.Report.ReportCSV;
import Tables.StringTableBufer;
import com.opencsv.*;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class TestDataCSV implements ReportCSV<StringTableBufer> {
RandomAccessFile writer=null;
    @Override
public void create(String DOC_NAME, String COLUMN_NAME, List<StringTableBufer> bufer) {
        File file = new File ("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\java\\Reports\\DOC\\" + DOC_NAME + ".csv");
        try {
            file.createNewFile ();
            if(writer!=null){
                long id_bufer=1;
                int out_bufer;
                writer=new RandomAccessFile(file,"rw");
                for(int i=0;i<=bufer.size()-1;i++){
                    while ( (out_bufer=writer.read())!=59){
                        id_bufer++;
                    }
                    out_bufer=1;
                    writer.seek(id_bufer);
                    writer.writeBytes(bufer.get(i).getValue()+";");
                    writer.writeBytes(System.getProperty("line.separator"));

                }

                return;
}
            writer=new RandomAccessFile(file,"rw");
            for(int i=0;i<=bufer.size()-1;i++){
                writer.writeBytes(bufer.get(i).getValue()+";");
                writer.writeBytes(System.getProperty("line.separator"));
                }

            } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
