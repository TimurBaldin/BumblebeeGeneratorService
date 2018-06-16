package Rules.DAO;
import Tables.Client;
public interface UserRepository {
    boolean create(Client client) throws Exception;
    boolean check(String login,String password);
    boolean change(String parametrName,String changeData);
}
