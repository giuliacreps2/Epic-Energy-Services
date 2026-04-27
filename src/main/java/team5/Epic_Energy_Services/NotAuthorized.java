package team5.Epic_Energy_Services;

public class NotAuthorized extends RuntimeException {
    public NotAuthorized(String message) {
        super(message);
    }
}
