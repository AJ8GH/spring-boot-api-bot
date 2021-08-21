package com.aj.esa.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunnerChangeTest {

    @Test
    void testToString() {
        RunnerChange runnerChange = RunnerChange.builder()
                .batb(List.of(List.of(0.0)))
                .batl(List.of(List.of(1.1)))
                .bdatb(List.of(List.of(2.2)))
                .bdatl(List.of(List.of(3.3)))
                .build();

        assertTrue(runnerChange.toString().contains(runnerChange.getBatb().toString()));
        assertTrue(runnerChange.toString().contains(runnerChange.getBatl().toString()));
        assertTrue(runnerChange.toString().contains(runnerChange.getBdatl().toString()));
        assertTrue(runnerChange.toString().contains(runnerChange.getBdatb().toString()));
    }
}
