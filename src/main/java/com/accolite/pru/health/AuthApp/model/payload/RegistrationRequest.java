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

import com.accolite.pru.health.AuthApp.validation.annotation.NullOrNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import org.checkerframework.checker.confidential.qual.*;

import javax.validation.constraints.NotNull;

@Schema(name = "Registration Request", description = "The registration request payload")
public class RegistrationRequest {

    @NullOrNotBlank(message = "Registration username can be null but not blank")
    @Schema(name = "A valid username", allowableValues = "NonEmpty String")
    private @NonConfidential String username;

    @NullOrNotBlank(message = "Registration email can be null but not blank")
    @Schema(name = "A valid email", required = true, allowableValues = "NonEmpty String")
    private @NonConfidential String email;

    @NotNull(message = "Registration password cannot be null")
    @Schema(name = "A valid password string", required = true, allowableValues = "NonEmpty String")
    private @Confidential String password;

    @NotNull(message = "Specify whether the user has to be registered as an admin or not")
    @Schema(name = "Flag denoting whether the user is an admin or not", required = true,
            type = "boolean", allowableValues = "true, false")
    private @Confidential Boolean registerAsAdmin;

    public RegistrationRequest(@NonConfidential String username, @NonConfidential String email,
                               @Confidential String password, @Confidential Boolean registerAsAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registerAsAdmin = registerAsAdmin;
    }

    public RegistrationRequest() {
    }

    public @NonConfidential String getUsername() {
        return username;
    }

    public void setUsername(@NonConfidential String username) {
        this.username = username;
    }

    public @NonConfidential String getEmail() {
        return email;
    }

    public void setEmail(@NonConfidential String email) {
        this.email = email;
    }

    public @Confidential String getPassword() {
        return password;
    }

    public void setPassword(@Confidential String password) {
        this.password = password;
    }

    public @Confidential Boolean getRegisterAsAdmin() {
        return registerAsAdmin;
    }

    public void setRegisterAsAdmin(@Confidential Boolean registerAsAdmin) {
        this.registerAsAdmin = registerAsAdmin;
    }
}
