package server.models;

public enum UserRole {
    ADMIN, PROFESSOR, GUEST;

    public static UserRole fromString(String value) {
        if (value == null) {
            return null;
        }
        for (UserRole userRole : UserRole.values()) {
            if (userRole.toString().equals(value))
                return userRole;
        }
        return null;
    }
}
