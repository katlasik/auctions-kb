package pl.sda.auctions.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    @Size(min = 6)
    @NotEmpty
    @NotNull
    private String password;

    @Size(min = 3)
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String name;

    private boolean active;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    protected User() {}

    public User(
            Long id,
            @Email @NotEmpty @NotNull String email,
            @Size(min = 6) @NotEmpty @NotNull String password,
            @Size(min = 3) @NotEmpty @NotNull String name,
            boolean active,
            @NotNull Role role
    ) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.name = Objects.requireNonNull(name);
        this.active = active;
        this.role = Objects.requireNonNull(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Objects.requireNonNull(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", active=" + active +
                ", name=" + name +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }


}
