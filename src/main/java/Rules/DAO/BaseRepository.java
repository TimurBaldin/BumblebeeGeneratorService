package Rules.DAO;

import Rules.Rules;

import java.util.List;

public interface BaseRepository<T,S>{
    void create(List<S> values,String COLUMN_NAME);
    List<T> get(List<T> columns);
}
