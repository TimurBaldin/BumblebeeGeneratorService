package Rules.Report;

import Tables.StringTableBufer;

import java.util.List;

public interface ReportCSV<T> {
void create(String DOC_NAME,String delimetr,List<T> bufer) throws Exception;
}
