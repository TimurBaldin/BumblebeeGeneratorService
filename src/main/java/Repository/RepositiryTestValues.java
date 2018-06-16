package Repository;

import Columns.ColumnLines;
import Factories.RepositoryFactory;
import Rules.DAO.BaseRepository;
import Tables.StringTableBufer;

import java.util.List;

public class RepositiryTestValues {
    private BaseRepository lines= RepositoryFactory.getLinesRepository();
    public void setLines(List<String> values,String COLUMN_NAME){
        lines.create(values,COLUMN_NAME);
    }
    public List<StringTableBufer> getLines(List<ColumnLines> columns){
       return lines.get(columns);
    }
}
