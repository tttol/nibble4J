package io.github.tttol.nibble4J.s3.service;

import io.github.tttol.nibble4J.s3.client.S3ClientSetup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    @Value("${app.aws.s3.bucketName}")
    private String bucketName;

    private final S3ClientSetup s3ClientSetup;

    public String listObjects() {
        final var s3Client = s3ClientSetup.build();
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