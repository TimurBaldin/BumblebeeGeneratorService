package Rules.Report;

import Rules.Rules;
import Tables.StringTableBufer;

import java.util.List;

public interface ReportExcel<T> {
void create(String DOC_NAME, String Sheet_NAME, List<T> bufer)throws Exception;
}
