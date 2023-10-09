package com.tech.trove.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

/**
 * The type Cognito config.
 */
@Configuration
public class CognitoConfig {

    @Value("${cognito.clientId}")
    private String clientId;

    @Value("${aws.region}")
    private String region;

    /**
     * Cognito client cognito identity provider client.
     *
     * @return the cognito identity provider client
     */
    @Bean
    public CognitoIdentityProviderClient cognitoClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .build();
    }

    /**
     * Cognito main credentials cognito credentials.
     *
     * @param cognitoUserPoolId the cognito user pool id
     * @param cognitoClientId   the cognito client id
     * @param userPoolId        the user pool id
     * @return the cognito credentials
     */
    @Bean("cognitoMainCredentials")
    @Primary
    public CognitoCredentials cognitoMainCredentials(@Value("${cognito.userPoolId}") String cognitoUserPoolId,
                                                     @Value("${cognito.clientId}")  String cognitoClientId,
                                                     @Value("${cognito.userPoolId}")  String userPoolId) {
        return CognitoCredentials.builder()
                .userPoolId(cognitoUserPoolId)
                .clientId(cognitoClientId)
                .userPoolId(userPoolId)
                .build();
    }
}
