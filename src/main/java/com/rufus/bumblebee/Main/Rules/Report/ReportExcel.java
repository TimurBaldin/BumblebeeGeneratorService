package com.rufus.bumblebee.Main.Rules.Report;

import java.io.File;
import java.util.List;

public interface ReportExcel<T> {
File create(String docname, String sheetname, List<T> bufer)throws Exception;
}