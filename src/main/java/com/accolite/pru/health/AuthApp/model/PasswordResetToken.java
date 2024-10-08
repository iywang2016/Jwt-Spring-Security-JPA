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
import org.checkerframework.checker.confidential.qual.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.Instant;

@Entity(name = "PASSWORD_RESET_TOKEN")
public class PasswordResetToken extends DateAudit {

    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pwd_reset_token_seq")
    @SequenceGenerator(name = "pwd_reset_token_seq", allocationSize = 1)
    private @NonConfidential Long id;

    @NaturalId
    @Column(name = "TOKEN_NAME", nullable = false, unique = true)
    private @Confidential String token;

    @Column(name = "EXPIRY_DT", nullable = false)
    private @NonConfidential Instant expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    @Column(name = "IS_ACTIVE", nullable = false)
    private @NonConfidential Boolean active;

    @Column(name = "IS_CLAIMED", nullable = false)
    private @NonConfidential Boolean claimed;

    public PasswordResetToken(@NonConfidential Long id, @Confidential String token, @NonConfidential Instant expiryDate, User user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public PasswordResetToken() {
    }

    public @NonConfidential Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(@NonConfidential Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @Confidential String getToken() {
        return token;
    }

    public void setToken(@Confidential String token) {
        this.token = token;
    }

    public @NonConfidential Boolean getActive() {
        return active;
    }

    public void setActive(@NonConfidential Boolean active) {
        this.active = active;
    }

    public @NonConfidential Boolean getClaimed() {
        return claimed;
    }

    public void setClaimed(@NonConfidential Boolean claimed) {
        this.claimed = claimed;
    }
}
