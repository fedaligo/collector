package com.htp.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.collection.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","collections"})
@ToString(exclude = {"collections"})
@Entity
@Table(name = "users")
public class Users /*implements UserDetails */{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String username;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private String mail;

    @Column
    private String role;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userCollection")
    private Set<Collection> collections = Collections.emptySet();

    /*@Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
