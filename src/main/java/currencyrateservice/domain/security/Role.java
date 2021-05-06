package currencyrateservice.domain.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(new HashSet<Permission>(Arrays.asList(Permission.USER_READ)));
    
    private Set<Permission> permissions;

    private Role(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    
    public Set<SimpleGrantedAuthority> receiveAuthorities() {
        return getPermissions().stream()
                               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                               .collect(Collectors.toSet());
    }
}
