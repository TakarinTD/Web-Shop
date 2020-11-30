package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "user")
@JsonIgnoreProperties (value = {"createdAt", "updatedAt", "roles"})
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "password_confirm")
    private String passwordConfirm;

    @Column (name = "fullname")
    private String fullName;

    @Column (name = "birthday")
    private String birthday;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private String phone;

    @Column (name = "verification_code")
    private String verificationCode;

    @Column (name = "enabled")
    private boolean enabled;

    @Column (name = "created_at")
    @Temporal (TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column (name = "updated_at")
    @Temporal (TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column (name = "active")
    private int status;

    @OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> roles;

    public User () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getFullName () {
        return fullName;
    }

    public void setFullName (String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday () {
        return birthday;
    }

    public void setBirthday (String birthday) {
        this.birthday = birthday;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getVerificationCode () {
        return verificationCode;
    }

    public void setVerificationCode (String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt () {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Order> getOrders () {
        return orders;
    }

    public void setOrders (Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles (Set<Role> roles) {
        this.roles = roles;
    }

    public void addOrder (Order order) {
        if (orders == null) {
            orders = new HashSet<>();
        }
        orders.add(order);
    }

    public String getPasswordConfirm () {
        return passwordConfirm;
    }

    public void setPasswordConfirm (String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public int getStatus () {
        return status;
    }

    public void setStatus (int status) {
        this.status = status;
    }
}
