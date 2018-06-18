package Repository;

import Columns.ColumnLines;
import Factories.RepositoryConfigiration;
import Rules.DAO.BaseRepository;
import Tables.StringTableBufer;

import java.util.List;

public class RepositiryTestValues {
    private BaseRepository lines= RepositoryConfigiration.getLinesRepository();
    public void setLines(List<String> values,String COLUMN_NAME){
        lines.create(values,COLUMN_NAME);
    }
    public List<ColumnLines> getLines(List<ColumnLines> columns){
       return lines.get(columns);
    }
}
