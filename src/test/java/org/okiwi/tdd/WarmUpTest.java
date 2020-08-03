package org.okiwi.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WarmUp Test Class as example")
public class WarmUpTest {
    @Nested
    @DisplayName("Nested WarmUp")
    class p {

        @Test
        @DisplayName("warm up test is OK")
        void warmUp() {
            assertThat(true).isTrue();
        }
    }
}
