package riwi.filtro.utils.exceptcions;

public class OptionNotFoundException extends RuntimeException {
    
    private static final String ERROR_MESSAGE = "We didn't find the options for this type of %s";
    // use the RuntimeException constructor to handle exceptions and insert the message
    public OptionNotFoundException(String nameEntity) {
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}