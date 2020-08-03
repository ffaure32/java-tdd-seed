package org.okiwi.tdd;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Line {
    public static final int MAX_DICE_ROLL = 12;
    public static final int MIN_DICE_ROLL = 2;
    public static final int MAX_MINUS_DIFF_FOR_STRAIGHT = 4;
    private List<Integer> diceRolls;

    public Line(List<Integer> diceRolls) {
        this.diceRolls = diceRolls;
    }

    public enum Combination {
        DUMMY(0, new int[]{1, 1, 1, 1, 1}),
        PAIR(1, new int[]{2, 1, 1, 1}),
        THREE_OF_A_KIND(3, new int[]{3, 1, 1}),
        FIVE_OF_A_KIND(10, new int[]{5}),
        FOUR_OF_A_KIND(6, new int[]{4, 1}),
        TWO_PAIRS(3, new int[]{2, 2, 1}),
        FULL_HOUSE(8, new int[]{3, 2});

        public final int score;
        private List<Integer> figure;

        Combination(int score, int[] figure) {
            this.score = score;
            this.figure = (Arrays.stream(figure).boxed().collect(Collectors.toList()));
        }

        public static Combination getCombination(List<Integer> diceRolls) {
            Map<Integer, List<Integer>> diceRollsToMap = diceRolls
                    .stream()
                    .collect(Collectors.groupingBy(Integer::intValue));
            List<Integer> numberOfFigures = diceRollsToMap
                    .values()
                    .stream()
                    .map(List::size)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            return findCombinationFromFigure(numberOfFigures);
        }

        private static Combination findCombinationFromFigure(List<Integer> numberOfFigures) {
            for (Combination value : Combination.values()) {
                if(value.figure.equals(numberOfFigures)) {
                    return value;
                }
            }
            return Combination.DUMMY;
        }

    }
    public int computeScore() {
        Combination combination = Combination.getCombination(diceRolls);
        if (combination == Combination.DUMMY && isStraight(diceRolls)) {
            return computeStraightScore();
        }
        return combination.score;
    }

    private int computeStraightScore() {
        if (straightContainsSeven(diceRolls)) {
            return 8;
        } else {
            return 12;
        }
    }

    private boolean straightContainsSeven(List<Integer> diceRolls) {
        return diceRolls.contains(7);
    }

    private boolean isStraight(List<Integer> diceRolls) {
        Optional<Integer> max = diceRolls.stream().max(Comparator.naturalOrder());
        Optional<Integer> min = diceRolls.stream().min(Comparator.naturalOrder());
        return(max.orElse(MAX_DICE_ROLL) - min.orElse(MIN_DICE_ROLL) == MAX_MINUS_DIFF_FOR_STRAIGHT);
    }


}
