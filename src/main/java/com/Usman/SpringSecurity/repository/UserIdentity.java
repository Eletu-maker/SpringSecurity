package com.Usman.SpringSecurity.repository;

import com.Usman.SpringSecurity.model.Role;

public interface UserIdentity {
    Integer getId();
    String getUsername();
    String getPassword();
    Role getRole();
}
