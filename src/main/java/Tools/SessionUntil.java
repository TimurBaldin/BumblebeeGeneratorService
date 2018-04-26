package Tools;

public class SessionUntil {
    private static SessionUntil ourInstance = new SessionUntil ( );

    public static SessionUntil getInstance() {
        return ourInstance;
    }

    private SessionUntil() {
    }
}
