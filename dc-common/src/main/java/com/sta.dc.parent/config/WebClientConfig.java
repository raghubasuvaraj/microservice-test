package com.sta.dc.parent.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@ConditionalOnProperty(value="sta.webclient.enabled", havingValue = "true", matchIfMissing = false)
public class WebClientConfig {

    @Value("${api.timeout:5000}")
    public int TIMEOUT;

    private ObjectMapper jacksonDecoderMapper;
    private ObjectMapper jacksonEncoderMapper;

    @Bean
    public WebClient.Builder buildClient() {
        final ExchangeStrategies strategies = getExchangeStrategies();

        final HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .responseTimeout(Duration.ofMillis(TIMEOUT))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)));

        WebClient.Builder builder = WebClient
                .builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient));
        return builder;
    }

    private ExchangeStrategies getExchangeStrategies() {
            if (jacksonEncoderMapper == null) {
                jacksonEncoderMapper = new ObjectMapper();
                jacksonEncoderMapper.registerModule(new Jdk8Module());
            }

            if (jacksonDecoderMapper == null) {
                jacksonDecoderMapper = new ObjectMapper();
                jacksonDecoderMapper.registerModule(new Jdk8Module());
            }

            return ExchangeStrategies
                    .builder()
                    .codecs(codecConfigure -> {
                        final ClientCodecConfigurer.ClientDefaultCodecs defaultCodecs = codecConfigure.defaultCodecs();
                        defaultCodecs.jackson2JsonEncoder(new Jackson2JsonEncoder(jacksonEncoderMapper, APPLICATION_JSON));
                        defaultCodecs.jackson2JsonDecoder(new Jackson2JsonDecoder(jacksonDecoderMapper, APPLICATION_JSON));
                    }).build();
        }

}
