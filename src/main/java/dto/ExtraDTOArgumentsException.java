package dto;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 27-May-2020
 */
public class ExtraDTOArgumentsException extends Exception {

    private static final long serialVersionUID = -5510923972323934955L;

    public ExtraDTOArgumentsException() {
    }

    public ExtraDTOArgumentsException( String message ) {
        super( message );
    }
}
