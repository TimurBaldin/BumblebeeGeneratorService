package Tables;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table (name="StringTableBufer" ,schema="bufer")
public class StringTableBufer {
    @Id
   // @SequenceGenerator( name = "row_id", sequenceName = "bufer.row_id")
   // @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "row_id")
    private int id;
    @Column(name="TestValue")
    private String value;
    @Column(name="ReportColumnName")
    private String ColumnName;
    @Column(name="user_id")
    private int user_id;
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }
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