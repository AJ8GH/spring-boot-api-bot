package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    @Test
    void testRunner() {
        Runner runner = new Runner(9L, 22L, "name", 0.0);

        assertEquals(9L, runner.getId());
        assertEquals(22L, runner.getSelectionId());
        assertEquals("name", runner.getRunnerName());
        assertEquals(0.0, runner.getHandicap());
    }

    @Test
    void testToString() {
        Runner runner = new Runner(9L, 22L, "name", 0.0);
        String toString = runner.toString();

        assertTrue(toString.contains(runner.getRunnerName()));
        assertTrue(toString.contains(runner.getHandicap().toString()));
        assertTrue(toString.contains(runner.getId().toString()));
        assertTrue(toString.contains(runner.getSelectionId().toString()));
    }
}
