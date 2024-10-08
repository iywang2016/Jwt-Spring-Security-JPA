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
package com.accolite.pru.health.AuthApp.model.payload;

import org.checkerframework.checker.confidential.qual.*;

public class JwtAuthenticationResponse {

    private @Confidential String accessToken;

    private @Confidential String refreshToken;

    private @NonConfidential String tokenType;

    private @NonConfidential Long expiryDuration;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, @NonConfidential Long expiryDuration) {
        @SuppressWarnings("confidential")
        @Confidential String confAccToken = accessToken;
        @SuppressWarnings("confidential")
        @Confidential String confRefToken = refreshToken;
        this.accessToken = confAccToken;
        this.refreshToken = confRefToken;
        this.expiryDuration = expiryDuration;
        tokenType = "Bearer ";
    }

    public @Confidential String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(@Confidential String accessToken) {
        this.accessToken = accessToken;
    }

    public @NonConfidential String getTokenType() {
        return tokenType;
    }

    public void setTokenType(@NonConfidential String tokenType) {
        this.tokenType = tokenType;
    }

    public @Confidential String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(@Confidential String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public @NonConfidential Long getExpiryDuration() {
        return expiryDuration;
    }

    public void setExpiryDuration(@NonConfidential Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }
}
