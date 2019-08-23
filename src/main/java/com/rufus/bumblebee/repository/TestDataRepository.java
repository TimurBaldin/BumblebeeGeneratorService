package com.rufus.bumblebee.Main.Repository;

/**
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.rufus.bumblebee.repository.BaseRepository;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.tables.TestData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TestDataRepository extends BaseRepository<List<TypeTestData>> {

    public List<List<TypeTestData>> getAll() {
        return null;
    }

}
