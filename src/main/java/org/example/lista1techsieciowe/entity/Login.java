package org.example.lista1techsieciowe.entity;

import jakarta.persistence.*;
import org.example.lista1techsieciowe.commonTypes.UserRole;

@Entity
@Table(name="login")
public class Login {
    @Id
    @Column(name = "login_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer loginId;
    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Basic
    @Column(name="password", nullable = false)
    private String password;
    @Basic
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
