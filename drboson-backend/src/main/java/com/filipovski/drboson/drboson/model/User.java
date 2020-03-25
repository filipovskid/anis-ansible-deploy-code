package com.filipovski.drboson.drboson.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;

@Entity
@NoArgsConstructor
@Data
public class User implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String username;

    private String password;

    private String email;

    private String name;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<Project> projects;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public User(String username, String password, String email, String name, boolean enabled,
                boolean accountNonExpired, boolean credentialsNonExpired,
                boolean accountNonLocked) {

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException(
                    "Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

//    public void addProject(Project project) {
//        projects.add(project);
//        project.setOwner(this);
//    }
//
//    public void removeProject(Project project) {
//        projects.remove(project);
//        project.setOwner(null);
//    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return username.equals(((User) obj).username);
        }

        return false;
    }

    public static UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String email;
        private String name;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder = password -> password;

        private UserBuilder() {}

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "Encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public User build() {
            String encodedPassword = this.passwordEncoder.apply(password);
            return new User(username, encodedPassword, email, name, !disabled, !accountExpired,
                    !credentialsExpired, !accountLocked);
        }
    }
}
