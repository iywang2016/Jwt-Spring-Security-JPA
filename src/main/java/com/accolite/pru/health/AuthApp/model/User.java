/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.accolite.pru.health.AuthApp.model;

import com.accolite.pru.health.AuthApp.model.audit.DateAudit;
import com.accolite.pru.health.AuthApp.validation.annotation.NullOrNotBlank;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import org.checkerframework.checker.confidential.qual.*;

@Entity(name = "USER")
public class User extends DateAudit {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private @NonConfidential Long id;

    @NaturalId
    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "User email cannot be null")
    private @NonConfidential String email;

    @Column(name = "USERNAME", unique = true)
    @NullOrNotBlank(message = "Username can not be blank")
    private @NonConfidential String username;

    @Column(name = "PASSWORD")
    @NotNull(message = "Password cannot be null")
    private @Confidential String password;

    @Column(name = "FIRST_NAME")
    @NullOrNotBlank(message = "First name can not be blank")
    private @NonConfidential String firstName;

    @Column(name = "LAST_NAME")
    @NullOrNotBlank(message = "Last name can not be blank")
    private @NonConfidential String lastName;

    @Column(name = "IS_ACTIVE", nullable = false)
    private @NonConfidential Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_AUTHORITY", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
    private @NonConfidential Set<Role> roles = new @NonConfidential HashSet<>();

    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private @NonConfidential Boolean isEmailVerified;

    public User() {
        super();
    }

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        active = user.getActive();
        roles = user.getRoles();
        isEmailVerified = user.getEmailVerified();
    }

    public void addRole(@Confidential Role role) {
        roles.add(role);
        role.getUserList().add(this);
    }

    public void addRoles(Set<@Confidential Role> roles) {
        roles.forEach(this::addRole);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUserList().remove(this);
    }

    public void markVerificationConfirmed() {
        setEmailVerified(true);
    }

    public @NonConfidential Long getId() {
        return id;
    }

    public void setId(@NonConfidential Long id) {
        this.id = id;
    }

    public @NonConfidential String getUsername() {
        return username;
    }

    public void setUsername(@NonConfidential String username) {
        this.username = username;
    }

    public @Confidential String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        @SuppressWarnings("confidential")
        @Confidential String cPwd = password;
        this.password = cPwd;
    }

    public @NonConfidential String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonConfidential String firstName) {
        this.firstName = firstName;
    }

    public @NonConfidential String getLastName() {
        return lastName;
    }

    public void setLastName(@NonConfidential String lastName) {
        this.lastName = lastName;
    }

    public @NonConfidential String getEmail() {
        return email;
    }

    public void setEmail(@NonConfidential String email) {
        this.email = email;
    }

    public @NonConfidential Boolean getActive() {
        return active;
    }

    public void setActive(@NonConfidential Boolean active) {
        this.active = active;
    }

    public @NonConfidential Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(@NonConfidential Set<Role> authorities) {
        roles = authorities;
    }

    public @NonConfidential Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(@NonConfidential Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @Override
    public @Confidential String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='"
                + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", active="
                + active + ", roles=" + roles + ", isEmailVerified=" + isEmailVerified + '}';
    }
}
