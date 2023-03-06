package com.sta.dc.parent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;


@Service
public class WebClientHelperImpl implements WebClientHelper {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public <T> Mono<T> getForEntity(Class<T> clazz, Map<String, Set<String>> headers, String uri, Object... uriVariables) {
        addHeaders(headers);
        return webClientBuilder.build()
                .get()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToMono(clazz);

    }

    public <T> Flux<T> getForList(Class<T> clazz, Map<String, Set<String>> headers, String uri, Object... uriVariables) {
        addHeaders(headers);
        return webClientBuilder.build()
                .get()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToFlux(clazz);
    }

    public <T, R> Mono<T> postForEntity(Class<T> clazz, Map<String, Set<String>> headers, String uri, R body, Object... uriVariables) {
        addHeaders(headers);
        return webClientBuilder.build()
                .post()
                .uri(uri, uriVariables)
                .body(body, clazz)
                .retrieve()
                .bodyToMono(clazz);

    }

    public <T, R> Mono<T> putForEntity(Class<T> clazz, Map<String, Set<String>> headers, String uri, R body, Object... uriVariables) {
        addHeaders(headers);
        return webClientBuilder.build()
                .put()
                .uri(uri, uriVariables)
                .body(body, clazz)
                .retrieve()
                .bodyToMono(clazz);

    }

    public <T> Mono<T> delete(Class<T> clazz, Map<String, Set<String>> headers, String uri, Object... uriVariables) {
        addHeaders(headers);
        return webClientBuilder.build()
                .delete()
                .uri(uri, uriVariables)
                .retrieve()
                .bodyToMono(clazz);
    }
    private void addHeaders(Map<String, Set<String>> headers) {
        if (!ObjectUtils.isEmpty(headers))
            headers.keySet().forEach(key -> webClientBuilder.defaultHeader(key, headers.get(key).toArray(new String[0])));
    }

}
