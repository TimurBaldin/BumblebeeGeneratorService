package Factories;

import Columns.ColumnLines;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Repository.RepositiryTestValues;
import Services.ReportService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class LineFactory {
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public  ColumnLines getColumn(String name){
        return new ColumnLines(name);
    }
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public  StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap,Boolean NullValue,ColumnLines column){
        return new StringBoundaryValues(Len,INCREASE_QUANTITY,Low,Cap,NullValue,column);
    }
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public  StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column){
    return new StringSpecialValues(SPECIAL_LEN,INCREASE_QUANTITY,ESC_SPECIAL,SPECIAL,column);
}
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public  RepositiryTestValues getRepositiry(){return new RepositiryTestValues();}
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public ReportService getReportService(){
    return new ReportService();
}
}
