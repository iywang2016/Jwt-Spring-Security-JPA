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
package com.accolite.pru.health.AuthApp.event;

import com.accolite.pru.health.AuthApp.model.payload.LogOutRequest;
import org.checkerframework.checker.confidential.qual.*;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.Date;

public class OnUserLogoutSuccessEvent extends ApplicationEvent {

    private final @NonConfidential String userEmail;
    private final @Confidential String token;
    private final transient LogOutRequest logOutRequest;
    private final @NonConfidential Date eventTime;

    public OnUserLogoutSuccessEvent(@NonConfidential String userEmail, @Confidential String token, LogOutRequest logOutRequest) {
        super(userEmail);
        this.userEmail = userEmail;
        this.token = token;
        this.logOutRequest = logOutRequest;
        @SuppressWarnings("confidential")
        @NonConfidential Date date = Date.from(Instant.now());
        this.eventTime = date;
    }

    public @NonConfidential String getUserEmail() {
        return userEmail;
    }

    public @Confidential String getToken() {
        return token;
    }

    public LogOutRequest getLogOutRequest() {
        return logOutRequest;
    }

    public @NonConfidential Date getEventTime() {
        return eventTime;
    }
}
