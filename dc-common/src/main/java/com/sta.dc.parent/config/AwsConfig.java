package com.sta.dc.parent.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(value="sta.aws.enabled", havingValue = "true", matchIfMissing = false)
public class AwsConfig {

    @Value("${sta.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${sta.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Primary
    @Bean
    public AmazonS3 amazonSQSAsync() {
        return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
}