package com.rufus.bumblebee.Main.System.Log;




import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.rules.Stopwatch;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@Aspect
@Configuration
@EnableAspectJAutoProxy
public class Logging {
    private  File file;
    private Date date = new Date();
    private final String LOG_FOLDER = "LogsTime\\";
    private  String PATH=System.getProperty("java.io.tmpdir")+LOG_FOLDER+"logtime.txt";
    private Long startTime=0L;
    {
        try {
            System.out.println(PATH);
            File filebufer=new File(System.getProperty("java.io.tmpdir")+LOG_FOLDER);
            filebufer.mkdir();
            file=new File(PATH);
            file.createNewFile();
            System.setErr(new PrintStream(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
    @Pointcut("execution(* com.rufus.bumblebee.Main.Services.LinesService.selectionBoundaryTest(..))")
    public void boundarytest(){

    }
    @Before("boundarytest()")
    public void two(){
         startTime = System.currentTimeMillis();
        System.err.println("Start work : "+startTime.toString());
        }
    @AfterReturning("boundarytest()")
    public void three(){
        long stopTime = System.currentTimeMillis();
        Long elapsedTime = stopTime - startTime;
        System.err.println("End work : "+elapsedTime.toString());

    }
}
