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

import com.accolite.pru.health.AuthApp.model.DeviceType;
import com.accolite.pru.health.AuthApp.validation.annotation.NullOrNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import org.checkerframework.checker.confidential.qual.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeviceInfo {

    @NotBlank(message = "Device id cannot be blank")
    @Schema(name = "Device Id", required = true, type = "string", allowableValues = "Non empty string")
    private @NonConfidential String deviceId;

    @NotNull(message = "Device type cannot be null")
    @Schema(name = "Device type Android/iOS", required = true, type = "string", allowableValues =
            "DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS")
    private @NonConfidential DeviceType deviceType;

    @NullOrNotBlank(message = "Device notification token can be null but not blank")
    @Schema(name = "Device notification id", type = "string", allowableValues = "Non empty string")
    private @Confidential String notificationToken;

    public DeviceInfo() {
    }

    public DeviceInfo(@NonConfidential String deviceId, @NonConfidential DeviceType deviceType, @Confidential String notificationToken) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.notificationToken = notificationToken;
    }

    public @NonConfidential String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NonConfidential String deviceId) {
        this.deviceId = deviceId;
    }

    public @NonConfidential DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(@NonConfidential DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public @Confidential String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(@Confidential String notificationToken) {
        this.notificationToken = notificationToken;
    }

    @Override
    public @Confidential String toString() {
        return "DeviceInfo{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceType=" + deviceType +
                ", notificationToken='" + notificationToken + '\'' +
                '}';
    }
}
