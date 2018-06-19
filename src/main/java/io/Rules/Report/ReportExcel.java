package io.Rules.Report;

import java.util.List;

public interface ReportExcel<T> {
    void create(String DOC_NAME, String Sheet_NAME, List<T> bufer) throws Exception;
}
