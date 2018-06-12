package Tools;

public enum Query {
    KEY;
    private final String GET_TEST_DATA="SELECT value from Tables.StringTableBufer where ColumnName=:val1 and user_id=:val2";
    public String getGET_TEST_DATA() {
    return GET_TEST_DATA;
}
}
