package Tables;
import javax.persistence.*;
import javax.persistence.Table;
@Entity
@Table(name = "Client", schema = "users")
public class Client {
@Id
@SequenceGenerator(name = "client_id", sequenceName = "users.client_user_id_seq", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id")
@Column(name = "user_id", unique = true, nullable = false)
private int user_id;
@Column(name = "surname")
private String surname;
@Column(name = "name")
private String name;
@Column(name = "patronymic")
private String patronymic;
@Column(name = "email")
private String email;
@Column(name = "login")
private String login;
@Column(name = "password")
private String password;
public int getUser_id() {
    return user_id;
}
public void setUser_id(int user_id) {
    this.user_id = user_id;
}
public String getSurname() {
    return surname;
}
public void setSurname(String surname) {
    this.surname = surname;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getPatronymic() {
    return patronymic;
}
public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getLogin() {
    return login;
}
public void setLogin(String login) {
    this.login = login;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}



}
