package com.rufus.bumblebee.Main.Rules.Report;

import com.rufus.bumblebee.Main.Tables.StringTableBufer;

import java.io.File;
import java.util.List;

public interface ReportCSV<T> {
File create(String DOC_NAME, String delimetr, List<T> bufer) throws Exception;
}
