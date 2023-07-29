package io.github.tttol.nibble4J.s3.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3ClientSetup {
    @Value("${app.aws.accessKey}")
    private String accessKey;
    @Value("${app.aws.accessSecret}")
    private String accessSecret;

    public S3Client build() {
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", accessSecret);

        final var credentialsProvider = ProfileCredentialsProvider.create();
        final var region = Region.AP_NORTHEAST_1;
        return S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
