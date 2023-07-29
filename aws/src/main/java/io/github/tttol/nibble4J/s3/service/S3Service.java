package io.github.tttol.nibble4J.s3.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.stream.Collectors;

@Service
@Slf4j
public class S3Service {
    @Value("${app.aws.accessKey}")
    private String accessKey;
    @Value("${app.aws.accessSecret}")
    private String accessSecret;
    @Value("${app.aws.s3.bucketName}")
    private String bucketName;

    public String listObjects() {
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", accessSecret);

        final var credentialsProvider = ProfileCredentialsProvider.create();
        final var region = Region.AP_NORTHEAST_1;
        final var s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
        try (s3Client) {
            final ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();
            final ListObjectsResponse res = s3Client.listObjects(listObjects);

            final var objects = res.contents();
            return objects.stream().map(S3Object::key).collect(Collectors.joining(", "));
        } catch (final S3Exception e) {
            log.error("S3 list objects error. ", e);
            throw new RuntimeException(e);
        }
    }
}