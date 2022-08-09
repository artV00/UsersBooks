package users_books.customException;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
