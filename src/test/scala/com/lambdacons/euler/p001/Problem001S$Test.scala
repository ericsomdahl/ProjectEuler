package com.lambdacons.euler.p001

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by eric on 3/30/16.
  */
class Problem001S$Test extends FlatSpec with Matchers {
  "Problem001S" should "sum multiples of 3 and 5 below 10" in {
    Problem001S.sumOfMultiplesBelow(10, 3, 5) should be (23)
  }

  it should "sum multiples of 3 and 5 below 1000" in {
    Problem001S.sumOfMultiplesBelow(1000, 3, 5) should be (233168)
  }
}
