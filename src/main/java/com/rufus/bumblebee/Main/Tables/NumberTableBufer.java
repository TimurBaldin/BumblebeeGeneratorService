package com.rufus.bumblebee.Main.Tables;
import javax.persistence.*;
import java.text.NumberFormat;

@Entity
@Table(name = "NumberTableBufer", schema = "bufer")
public class NumberTableBufer {
    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "bufer.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "row_id", unique = true, nullable = false)
    private int id;
    @Column(name = "valuetest")
    private String valuetest;
    @Column(name = "reportcolumnname")
    private String ColumnName;
    @Column(name = "user_id")
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValuetest() {
        return valuetest;
    }

    public void setValuetest(String valuetest) {
        this.valuetest = valuetest;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
