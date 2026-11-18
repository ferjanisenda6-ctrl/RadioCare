package Service.ExceptionsServices;


public class ValidationException extends Exception{
    public ValidationException(){
        super("ValidationException");
    }
    public ValidationException(String message){
        super(message);
    }
}
