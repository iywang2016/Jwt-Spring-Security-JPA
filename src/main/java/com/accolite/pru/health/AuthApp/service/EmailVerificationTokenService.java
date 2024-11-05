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
package com.accolite.pru.health.AuthApp.service;

import com.accolite.pru.health.AuthApp.exception.InvalidTokenRequestException;
import com.accolite.pru.health.AuthApp.model.TokenStatus;
import com.accolite.pru.health.AuthApp.model.User;
import com.accolite.pru.health.AuthApp.model.token.EmailVerificationToken;
import com.accolite.pru.health.AuthApp.repository.EmailVerificationTokenRepository;
import org.apache.log4j.Logger;
import org.checkerframework.checker.confidential.qual.Confidential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailVerificationTokenService {

    private static final Logger logger = Logger.getLogger(EmailVerificationTokenService.class);
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    @Value("${app.token.email.verification.duration}")
    private Long emailVerificationTokenExpiryDuration;

    @Autowired
    public EmailVerificationTokenService(EmailVerificationTokenRepository emailVerificationTokenRepository) {
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
    }

    /**
     * Create an email verification token and persist it in the database which will be
     * verified by the user
     */
    public void createVerificationToken(User user, @Confidential String token) {
        @Confidential EmailVerificationToken emailVerificationToken = new @Confidential EmailVerificationToken();
        emailVerificationToken.setToken(token);
        emailVerificationToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        emailVerificationToken.setUser(user);
        emailVerificationToken.setExpiryDate(Instant.now().plusMillis(emailVerificationTokenExpiryDuration));
        logger.info("Generated Email verification token");
        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    /**
     * Updates an existing token in the database with a new expiration
     */
    public @Confidential EmailVerificationToken updateExistingTokenWithNameAndExpiry(@Confidential EmailVerificationToken existingToken) {
        existingToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        existingToken.setExpiryDate(Instant.now().plusMillis(emailVerificationTokenExpiryDuration));
        logger.info("Updated Email verification token");
        return save(existingToken);
    }

    /**
     * Finds an email verification token by the @NaturalId token
     */
    public Optional<@Confidential EmailVerificationToken> findByToken(String token) {
        @SuppressWarnings("confidential") // force confidential
        Optional<@Confidential EmailVerificationToken> result = emailVerificationTokenRepository.findByToken(token);
        return result;
    }

    /**
     * Saves an email verification token in the repository
     */
    public @Confidential EmailVerificationToken save(EmailVerificationToken emailVerificationToken) {
        @SuppressWarnings("confidential") // force confidential
        @Confidential EmailVerificationToken token = emailVerificationTokenRepository.save(emailVerificationToken);
        return token;
    }

    /**
     * Generates a new random UUID to be used as the token for email verification
     */
    public @Confidential String generateNewToken() {
        @SuppressWarnings("confidential") // force confidential
        @Confidential String result = UUID.randomUUID().toString();
        return result;
    }

    /**
     * Verify whether the token provided has expired or not on the basis of the current
     * server time and/or throw error otherwise
     */
    public void verifyExpiration(EmailVerificationToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new InvalidTokenRequestException("Email Verification Token", token.getToken(), "Expired token. Please issue a new request");
        }
    }

}
