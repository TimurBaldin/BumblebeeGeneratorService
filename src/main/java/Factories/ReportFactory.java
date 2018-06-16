package Factories;
import Columns.ColumnLines;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
public class ReportFactory {
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public ColumnLines getColumn(String name){
    return new ColumnLines(name);
}
}
