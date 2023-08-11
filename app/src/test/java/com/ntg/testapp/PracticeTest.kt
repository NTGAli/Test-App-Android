package com.ntg.testapp

import android.util.Log
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class PracticeTest{

    @Test
    fun `miscalculation fib`(){

        for (i in 0..10){
            val rnd = (0..50).random()
            assertThat(Practice.fib(rnd)).isEqualTo(Practice.fib(rnd-2) + Practice.fib(rnd-1))
        }

    }
    @Test
    fun `miscalculation fib for 1`(){
        assertThat(Practice.fib(1)).isEqualTo(1L)
    }

    @Test
    fun `miscalculation fib for 0`(){
        assertThat(Practice.fib(0)).isEqualTo(0L)
    }

}