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

import io.swagger.v3.oas.annotations.media.Schema;
import org.checkerframework.checker.confidential.qual.*;

import javax.validation.constraints.NotBlank;

@Schema(name = "Token refresh Request", description = "The jwt token refresh request payload")
public class TokenRefreshRequest {

    @NotBlank(message = "Refresh token cannot be blank")
    @Schema(name = "Valid refresh token passed during earlier successful authentications", required = true,
            allowableValues = "NonEmpty String")
    private @Confidential String refreshToken;

    public TokenRefreshRequest(@Confidential String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenRefreshRequest() {
    }

    public @Confidential String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(@Confidential String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
