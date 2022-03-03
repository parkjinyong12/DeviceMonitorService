package com.ruokit.device.monitor.model.service.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUserModel extends User {

  public LoginUserModel(String username, String password, boolean isAccountNonExpired,
                        boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled,
                        Collection<? extends GrantedAuthority> authorities) {
    super(username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired,
        isEnabled, authorities);
  }

}
