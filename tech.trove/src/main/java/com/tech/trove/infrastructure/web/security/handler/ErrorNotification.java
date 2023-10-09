package com.tech.trove.infrastructure.web.security.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Error notification.
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorNotification {

    private int status;

    private String error;

    private String reason;

    private String path;

}
