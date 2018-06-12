package Rules;

import Tables.StringTableBufer;

import java.util.List;

public interface ReportExcel {
void create(String DOC_NAME, String Sheet_NAME,String COLUMN_NAME,List<StringTableBufer> bufer);
}
