package com.aj.esa.models;

import com.aj.models.esa.ResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMessageTest {

    @Test
    void testToString() {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .op("op")
                .ct("ct")
                .heartbeatMs(5)
                .status(200)
                .build();

        assertTrue(responseMessage.toString().contains(responseMessage.getOp()));
        assertTrue(responseMessage.toString().contains(responseMessage.getCt()));
        assertTrue(responseMessage.toString().contains(String.valueOf(responseMessage.getStatus())));
        assertTrue(responseMessage.toString().contains(String.valueOf(responseMessage.getHeartbeatMs())));
    }
}
