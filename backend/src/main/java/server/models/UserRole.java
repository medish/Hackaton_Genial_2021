package server.models;

public enum UserRole {
    ADMIN, PROFESSOR;
    public static UserRole fromString(String str){
        if("ADMIN".equals(str))return ADMIN;
        if("PROFESSOR".equals(str))return PROFESSOR;
        return null;
    }
}
