package porunit.comp.exceptions;

public class UsernameTakenException extends Throwable {
    public UsernameTakenException(String usernameTaken) {
        super(usernameTaken);
    }
}
