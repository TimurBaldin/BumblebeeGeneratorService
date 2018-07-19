package com.rufus.bumblebee.Main.System.Log;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggTime {
    private final String LOG_FOLDER = "LogsTime\\";
    private File file;
    private String PATH = System.getProperty("java.io.tmpdir") + LOG_FOLDER + "logtime.txt";
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss:SSS a zzz");
    private Long startTime = 0L;

    {
        try {
            System.out.println(PATH);
            File filebufer = new File(System.getProperty("java.io.tmpdir") + LOG_FOLDER);
            filebufer.mkdir();
            file = new File(PATH);
            file.createNewFile();
            System.setErr(new PrintStream(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Pointcut("execution(* com.rufus.bumblebee.Main.Services.LinesService.selectionBoundaryTest(..))")
    public void boundarytest() {

    }

    @Pointcut("execution(* com.rufus.bumblebee.Main.Services.LinesService.selectionSpecialLinesTest(..))")
    public void specialtest() {

    }
    @Pointcut("execution(* com.rufus.bumblebee.Main.Services.ReportService.createCSV(..))")
    public void createreportCSV() {

    }
    @Pointcut("execution(* com.rufus.bumblebee.Main.Services.ReportService.createExcel(..))")
    public void createreportExcel() {

    }

    @Before("boundarytest()")
    private void beforboundary() {
        startTimeLog("StringBoundaryValues");
    }

    @AfterReturning("boundarytest()")
    private void afterboundary() {
        stopTimeLog("StringBoundaryValues");
    }
    @Before("specialtest()")
    private void beforspecial() {
        startTimeLog("StringSpecialValues");
    }
    @AfterReturning("specialtest()")
    private void afterspecial() {
        stopTimeLog("StringSpecialValues");
    }
    @Before("createreportCSV()")
    private void reportStart() {
        startTimeLog("reportCSV");
    }
    @AfterReturning("createreportCSV()")
    private void reportEnd() {
        stopTimeLog("reportCSV");
    }



    @Before("createreportExcel()")
    private void reportExcelStart() {
        startTimeLog("reportExcel");
    }
    @AfterReturning("createreportExcel()")
    private void reportExcelEnd() {
        stopTimeLog("reportExcel");
    }


    private void startTimeLog(String method) {
        Date date = new Date();
        System.err.println("Start work "+method+" : -> " + formatForDateNow.format(date));
    }

    private void stopTimeLog(String method) {
        Date date = new Date();
        System.err.println("End work : " +method+" : -> "+ formatForDateNow.format(date));
    }
}
