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

@Schema(name = "Update password Request", description = "The update password request payload")
public class UpdatePasswordRequest {

    @NotBlank(message = "Old password must not be blank")
    @Schema(name = "Valid current user password", required = true, allowableValues = "NonEmpty String")
    private @Confidential String oldPassword;

    @NotBlank(message = "New password must not be blank")
    @Schema(name = "Valid new password string", required = true, allowableValues = "NonEmpty String")
    private @Confidential String newPassword;

    public UpdatePasswordRequest(@Confidential String oldPassword, @Confidential String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UpdatePasswordRequest() {
    }

    public @Confidential String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(@Confidential String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public @Confidential String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@Confidential String newPassword) {
        this.newPassword = newPassword;
    }
}
