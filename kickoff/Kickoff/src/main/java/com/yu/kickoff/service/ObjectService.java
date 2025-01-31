package com.yu.kickoff.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

@Service
public class ObjectService {
    public String getStringValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public Long getLongValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value != null) {
            try {
                return Long.parseLong(value.toString());
            }
            catch (Exception exception) {
                throw new IllegalStateException("key = " + key + " must be in integer format between 1 - " + Long. MAX_VALUE + ".");
            }
        }
        return 0L;
    }

    public LocalDate getLocalDateValue(Map<String, Object> map, String key, String format) {
        Object value = map.get(key);
        if (value instanceof String) {
            return LocalDate.parse((String) value, DateTimeFormatter.ofPattern(format));
        }
        return null;
    }

    private byte[] decodeBase64String(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }


    public Object getImageValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value;
    }

}
