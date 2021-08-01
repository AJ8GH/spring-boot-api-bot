package com.aj.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestBodyBuilderTest {

    @Test
    void eventTypesBody() {
        var requestBodyBuilder = new RequestBodyBuilder();
        String body = "{\"filter\":{}}";

        assertEquals(body, requestBodyBuilder.listEventTypesBody());
    }

    @Test
    void eventsBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();

        long eventTypeId = 1L;
        String body = "{\"filter\":{\"eventTypeIds\":[" + eventTypeId + "]}}";

        assertEquals(body, requestBodyBuilder.listEventsBody(eventTypeId));
    }
}
