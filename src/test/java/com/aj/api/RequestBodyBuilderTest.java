package com.aj.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestBodyBuilderTest {

    @Test
    void eventTypesBody() {
        var requestBodyBuilder = new RequestBodyBuilder();
        String body = "{\"filter\":{}}";

        assertEquals(body, requestBodyBuilder.eventTypesBody());
    }
}
