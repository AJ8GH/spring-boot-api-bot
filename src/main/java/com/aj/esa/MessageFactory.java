package com.aj.esa;

import com.aj.domain.esa.AuthenticationMessage;
import com.aj.domain.esa.SubscriptionMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageFactory {
    private final String authentication = "authentication";
    private final String marketSubscription = "marketSubscription";
    private final String marketIdFilter = "marketIds";
    private final String marketDataFieldsFilter = "fields";
    private final int DEFAULT_HEARTBEAT = 500;

    public enum MarketDataFilter {
        EX_BEST_OFFERS_DISP,
        EX_BEST_OFFERS,
        EX_MARKET_DEF
    }

    public AuthenticationMessage authenticationMessage(String appKey, String token) {
        return AuthenticationMessage.builder()
                .op(authentication)
                .appKey(appKey)
                .session(token).build();
    }

    public SubscriptionMessage marketSubscriptionMessage(String marketId) {
        Map<String, List<String>> marketFilter = new HashMap<>();

        List<String> marketIds = List.of(marketId);
        marketFilter.put(marketIdFilter, marketIds);

        List<String> fields = Stream.of(MarketDataFilter.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        Map<String, List<String>> marketDataFilter = new HashMap<>();
        marketDataFilter.put(marketDataFieldsFilter, fields);

        return SubscriptionMessage.builder()
                .op(marketSubscription)
                .marketFilter(marketFilter)
                .marketDataFilter(marketDataFilter)
                .heartbeatMs(DEFAULT_HEARTBEAT)
                .build();
    }
}
