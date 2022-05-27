package server.utils;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;
import java.time.Duration;

public class DurationDeserialize extends KeyDeserializer {
    @Override
    public Duration deserializeKey(String s, DeserializationContext deserializationContext) throws IOException {
        return Duration.ofSeconds(Long.parseLong(s));
    }
}
