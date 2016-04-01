package com.lambdacons.euler
package p001

/**
  * Created by eric on 3/30/16.
  */
object Problem001S {

  def sumOfMultiplesBelow(below : Int, multiples : Int*): Int = {
    Stream.from(1, 1)
      .takeWhile(_ < below)
      .filter(value => {
        multiples.exists( multiple => {
          if (value % multiple == 0 ) true
          else false
        })
      })
      .sum
  }
}
