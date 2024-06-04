package riwi.filtro.utils.exceptcions;

public class IdNotMatchedException extends RuntimeException {
    
    private static final String ERROR_MESSAGE = "Request Id must match with path Id for %s ";
    // use the RuntimeException constructor to handle exceptions and insert the message
    public IdNotMatchedException(String nameEntity) {
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}