package Tools;

public enum Querys {
    KEY;
    private final String GET_TEST_DATA="SELECT value from Tables.StringTableBufer where ColumnName=:val1 and user_id=:val2";
    private final String DEL_TEST_DATA="UPDATE Tables.StringTableBufer SET alive=false where ColumnName=:val1 and user_id=:val2";
    public String getGET_TEST_DATA() {
    return GET_TEST_DATA;
}

}
