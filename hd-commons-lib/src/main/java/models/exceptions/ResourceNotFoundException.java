package models.exceptions;

/*Class created to assist in the treatment of EX*/
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
