package com.rufus.bumblebee.Main.Repository;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Columns.ColumnNum;
import com.rufus.bumblebee.Main.Factories.RepositoryFactory;
import com.rufus.bumblebee.Main.Rules.DAO.BaseRepository;

import java.util.List;

public class RepositiryTestValues {
    private BaseRepository lines= RepositoryFactory.getLinesRepository();
    private BaseRepository num=RepositoryFactory.getNumberRepository();
    public void setLines(List<String> values,String COLUMN_NAME){
        lines.create(values,COLUMN_NAME);
    }
    public void setNum(List<Number> values,String COLUMN_NAME){
        num.create(values,COLUMN_NAME);
    }
    public List<ColumnLines> getLines(List<ColumnLines> columns){
       return lines.get(columns);
    }
    public List<ColumnNum> getNum(List<ColumnNum> columns){
        return num.get(columns);
    }
}
