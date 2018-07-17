package com.rufus.bumblebee.Main.Tables;
import javax.persistence.*;

@Entity
@Table(name = "stringtablebufer", schema = "bufer")
public class StringTableBufer {
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
    private int user_id;
    @Column(name = "alive")
    boolean alive;
    public void  setAlive(boolean alive){this.alive=alive;}
    public boolean getAlive(){return alive;}
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
}
