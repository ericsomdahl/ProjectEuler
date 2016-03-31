package com.lambdacons.euler.p001;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Created by eric on 3/30/16.
 */
public class Problem001 {
    public int sumOfMultiplesBelow(final int below, final int... multiples) {
        final IntPredicate isMultiple = getIntPredicate(multiples);
        return IntStream
                .range(1, below)
                .filter( isMultiple )
                .sum();
    }

    private IntPredicate getIntPredicate(final int[] multiples) {
        return i -> {
            for (int v : multiples) {
                if (i % v == 0) {
                    return true;
                }
            }
            return false;
        };
    }
}
