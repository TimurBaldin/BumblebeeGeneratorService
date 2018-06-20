package com.rufus.bumblebee.Main.Tools;

public enum Querys {
    KEY;
    private final String GET_TEST_DATA="SELECT value FROM com.rufus.bumblebee.Main.Tables.StringTableBufer where ColumnName=:val1 and user_id=:val2";
    private final String DEL_TEST_DATA="UPDATE Tables.StringTableBufer SET alive=false where ColumnName=:val1 and user_id=:val2";
    private final String CHECK_USER="SELECT login FROM Tables.Client where login=:val1";
    public String getGET_TEST_DATA() {
    return GET_TEST_DATA;
}
    public String getDEL_TEST_DATA() {
    return DEL_TEST_DATA;
}
    public String getCHECK_USER() {
    return CHECK_USER;
}

}
