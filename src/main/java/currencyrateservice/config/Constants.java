package currencyrateservice.config;

public final class Constants {

    private Constants() {
    }

    public static final class ApiVersion {

        private static final String VERSION_1_0 = "/v1.0";

        private ApiVersion() {
        }
    }

    public static final class URL {

        private static final String CURRENCY_RATE_ENDPOINT = "/currency/rate";

        public static final String CURRENCY_RATE_BASE_URL =
                ApiVersion.VERSION_1_0 + CURRENCY_RATE_ENDPOINT;

        private URL() {
        }
    }

    public static final class Param {

        public static final String JSON = "json";

        public static final String DATE = "date";

        private Param() {
        }
    }

    public static final class Pattern {

        public static final String DATE = "dd.MM.yyyy";

        private Pattern() {
        }
    }

    public static final class ErrorMessage {

        public static final String CURRENCY_NOT_FOUND_EXCEPTION = "The %s currency data isn't found";

        public static final String INVALID_INPUT_EXCEPTION = "The currency is empty";

        public static final String JSON_PARSING_EXCEPTION = "Fail to parse json string:[%s] to object of type [%s]";

        private ErrorMessage() {
        }
    }

    public static final class LogMessage {

        public static final String IN_FIND_BY_CURRENCY = "In findByCurrency - currency: {}";

        public static final String IN_GET_RATE_MODEL_BY_CURRENCY = "In getRateModelByCurrency - currency: {}";

        public static final String IN_DO_GET = "In doGet";

        public static final String IN_GET_CURRENCY_RATE = "In getCurrencyRate - currency: {}";

        private LogMessage() {
        }
    }

}
