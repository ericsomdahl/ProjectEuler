package com.lambdacons.euler.p001;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by eric on 3/30/16.
 */
public class Problem001Test {
    Problem001 sut;

    @Before
    public void setup() {
        sut = new Problem001();
    }

    @Test
    public void sumOfMultiplesBelow_isCorrect_test1() {
        //Multiples of 3 and 5 below 10 are 3, 5, 6, 9.
        int actual = sut.sumOfMultiplesBelow(10, 3, 5);
        assertThat(actual, equalTo(23));
    }

    @Test
    public void sumOfMultiplesBelow_isCorrect_test2() {
        int actual = sut.sumOfMultiplesBelow(1000, 3, 5);
        assertThat(actual, equalTo(233168));
    }
}