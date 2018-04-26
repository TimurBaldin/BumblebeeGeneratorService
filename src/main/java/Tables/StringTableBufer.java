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

}
