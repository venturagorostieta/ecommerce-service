package com.tech.trove.infrastructure.configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Cognito credentials.
 */
@Getter
@Setter
@Builder
public class CognitoCredentials {

    private String clientId;

    private String userPoolId;

    private String channelClientId;

    private String channelUserPoolId;

}
