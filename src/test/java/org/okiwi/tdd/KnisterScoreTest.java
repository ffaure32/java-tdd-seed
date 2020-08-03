package org.okiwi.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KnisterScoreTest {
    @Nested
    class LineScoreTest {

        @Test
        void onePairShouldScoreOnePoint() {
            // ARRANGE
            Line line = new Line(Arrays.asList(4, 5, 6, 7, 5));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(1);
        }

        @Test
        void threeOfAKindShouldScoreThreePoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(7, 8, 7, 7, 4));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(3);
        }


        @Test
        void fourOfAKindShouldScoreSixPoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(6, 3, 6, 6, 6));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(6);
        }
        @Test
        void yamShouldScoreTenPoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(6, 6, 6, 6, 6));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(10);
        }

        @Test
        void dummyLineShouldScoreZeroPoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(4, 5, 7, 9, 11));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(0);
        }

        @Test
        void twoPairsShouldScoreThreePoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(4, 4, 6, 6, 11));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(3);
        }

        @Test
        void twoPairsUnorderedShouldScoreThreePoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(4, 4, 2, 6, 6));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(3);
        }

        @Test
        void fullHouseShouldScoreEightPoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(4, 4, 4, 6, 6));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(8);
        }

        @Test
        void straightContaining7ShouldScoreEightPoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(3, 4, 5, 6, 7));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(8);
        }

        @Test
        void straightNotContaining7ShouldScoreTwelvePoints() {
            // ARRANGE
            Line line = new Line(Arrays.asList(2, 3, 4, 5, 6));
            // ACT
            int score = line.computeScore();
            // ASSERT
            assertThat(score).isEqualTo(12);
        }

    }

    @Nested
    class CombinationTest {

        @Test
        void twoFoursTwoSixAndAnElevenAreADoublePair() {
            Line.Combination combination = Line.Combination.getCombination(Arrays.asList(4, 4, 6, 6, 11));

            assertThat(combination).isEqualTo(Line.Combination.TWO_PAIRS);
            assertThat(combination.score).isEqualTo(3);
        }
    }

    @Nested
    class GroupByTest {
        @Test
        void groupIntsByCount() {
            Map<Integer, List<Integer>> collect = Arrays.asList(4, 4, 6, 6, 11)
                    .stream()
                    .collect(Collectors.groupingBy(Integer::intValue));
            List<Integer> integerStream = collect.values().stream().map(l -> l.size()).collect(Collectors.toList());
            assertThat(integerStream).hasSize(3).containsExactly(2, 2, 1);

        }
    }
}
