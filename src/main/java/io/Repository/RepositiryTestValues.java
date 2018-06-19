package io.Repository;

import io.Columns.ColumnLines;
import io.Factories.RepositoryFactory;
import io.Rules.DAO.BaseRepository;

import java.util.List;

public class RepositiryTestValues {
    private BaseRepository lines = RepositoryFactory.getLinesRepository();

    public void setLines(List<String> values, String COLUMN_NAME) {
        lines.create(values, COLUMN_NAME);
    }

    public List<ColumnLines> getLines(List<ColumnLines> columns) {
        return lines.get(columns);
    }
}
