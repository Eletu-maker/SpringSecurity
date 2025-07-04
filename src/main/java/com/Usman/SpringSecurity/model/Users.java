package com.Usman.SpringSecurity.model;


import com.Usman.SpringSecurity.repository.UserIdentity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.support.BeanDefinitionDsl;

@Data
@Entity
public class Users implements UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
