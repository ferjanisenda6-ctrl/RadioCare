package Service;

public class EntiteIntrouvableException extends Exception{
    public EntiteIntrouvableException(){
        super("entite introuvable");

    }
    public EntiteIntrouvableException(String message){
        super(message);
    }

}
