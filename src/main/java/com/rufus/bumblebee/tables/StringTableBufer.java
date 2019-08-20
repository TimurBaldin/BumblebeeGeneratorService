package com.rufus.bumblebee.tables;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "test_data", schema = "repository")
public class StringTableBufer implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "bufer.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "row_id", unique = true, nullable = false)
    private int id;
    @Column(name = "testvalue")
    private String value;
    @Column(name = "reportcolumnname")
    private String ColumnName;
    @Column(name = "user_id")
    @NotNull
    private int user_id;
    @Column(name = "alive")
    @NotNull
    boolean alive;

    @Column(name = "DATA_REF")
    private Long dataRef;

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getAlive() {
        return alive;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public Long getDataRef() {
        return dataRef;
    }

    public void setDataRef(Long dataRef) {
        this.dataRef = dataRef;
    }

}