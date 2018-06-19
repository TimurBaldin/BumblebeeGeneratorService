package io.Rules.Report;

import java.io.File;
import java.util.List;

public interface ReportCSV<T> {
    File create(String DOC_NAME, String delimetr, List<T> bufer) throws Exception;
}
