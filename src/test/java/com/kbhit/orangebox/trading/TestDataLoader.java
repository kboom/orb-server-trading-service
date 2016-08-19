package com.kbhit.orangebox.trading;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class TestDataLoader {

    @Autowired
    private DataSource dataSource;

    public void reloadTestData() {
        DataSourceDestination dataSourceDestination = new DataSourceDestination(dataSource);
        DbSetup dbSetup = new DbSetup(dataSourceDestination,
                sequenceOf(TestDataSet.DELETE_TRADE_RESOURCES, TestDataSet.INSERT_TRADE_RESOURCES));
        dbSetup.launch();
    }

}
