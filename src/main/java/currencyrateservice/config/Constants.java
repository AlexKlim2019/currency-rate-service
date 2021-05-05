package currencyrateservice.config;

public final class Constants {

    private Constants() {
    }

    public static final class URL {

//        public static final String REGISTRATION_CONFIRMATION_JAVA_FRONT = "/registration/confirm?token={token}";

        private URL() {
        }
    }

    public static final class ErrorMessage {

        public static final String CURRENCY_NOT_FOUND_EXCEPTION = "The %s currency data isn't found";

        public static final String INVALID_INPUT_EXCEPTION = "The currency is empty";

        public static final String JSON_PARSING_EXCEPTION = "Fail to parse json string:[%s] to object of type [%s]";

        private ErrorMessage() {
        }
    }

}
