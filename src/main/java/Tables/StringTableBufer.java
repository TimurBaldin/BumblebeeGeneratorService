package Tables;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table (name="StringTableBufer" ,schema="bufer")
public class StringTableBufer {
    @Id
    @Column(name = "row_id")
    private int id;
    @Column(name="TestValue")
    private String value;
    @Column(name="ReportColumnName")
    private String ColumnName;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setId(int id) {
        this.id = id;
        }

    public void setValue(String value) {
        this.value = value;
        }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
        }
}
