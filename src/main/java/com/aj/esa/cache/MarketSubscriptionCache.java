package com.aj.esa.cache;

import com.aj.esa.models.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MarketSubscriptionCache {
    private final Map<Long, ResponseMessage> cache = new HashMap<>();

    public void addMessage(ResponseMessage message) {
        cache.put(message.getId(), message);
    }

    public ResponseMessage getMessage(Long id) {
        return cache.get(id);
    }
}
