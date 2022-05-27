package server.models;

public enum RoomType {
    CM, TP, TD;

    public static RoomType fromString(String value) {
        if (value == null) {
            return null;
        }
        for (RoomType roomType : RoomType.values()) {
            if (roomType.toString().equals(value))
                return roomType;
        }
        return null;
    }
}
