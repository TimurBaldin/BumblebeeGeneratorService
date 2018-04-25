package Tables;
import org.hibernate.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="users" ,schema="test")
public class UserTest {
    @Id
    @SequenceGenerator( name = "user_id", sequenceName = "user_id",schema="test")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_id")
    @Column(name = "user_id")
    private int user_id;
    @Column(name="user_name")
    private String username;
    public void setId(int id) {
        this.user_id = id;
    }
    public void setName(String name) {
        this.username = name;
    }
    public String getName(){return username;}
    @Column(name="game_id")
    private int game_id=5;
}
