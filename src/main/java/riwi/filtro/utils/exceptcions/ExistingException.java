package riwi.filtro.utils.exceptcions;

public class ExistingException extends RuntimeException {
    
    private static final String ERROR_MESSAGE = "Tittle already exist";
    // use the RuntimeException constructor to handle exceptions and insert the message
    public ExistingException(String nameEntity) {
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}