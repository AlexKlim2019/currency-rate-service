package currencyrateservice.config;

public final class Constants {

    private Constants() {
    }

    public static final class URL {

        public static final String REGISTRATION_CONFIRMATION_JAVA_FRONT = "/registration/confirm?token={token}";

        public static final String REGISTRATION_CONFIRMATION_REACT = "/#/registration/confirm/{token}";

        public static final String VOLUNTEER_TASK = "/tasks/volunteer/%d";

        public static final String PARTNER_TASK = "/tasks/partner/%d";

        public static final String PASSWORD_RECOVERY_CONFIRMATION = "/password/reset/success?token=";

        private URL() {
        }
    }

}
