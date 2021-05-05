package currencyrateservice.exception;

import static java.lang.String.format;

public class JsonParsingException extends RuntimeException{

    public JsonParsingException(Throwable cause, String messageTemplate, Object... args) {
        super(format(messageTemplate, args), cause);
    }
}
