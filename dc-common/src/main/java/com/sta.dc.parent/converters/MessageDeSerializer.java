package com.sta.dc.parent.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.io.IOException;

@ConditionalOnProperty(value="sta.messaging.enabled", havingValue = "true", matchIfMissing = false)
public class MessageDeSerializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data), Object.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
