package currencyrateservice.domain.security;

public enum Permission {
    USER_READ("user_read");
    
    private String permission;

    private Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
