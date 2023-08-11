package com.ntg.testapp

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()

    }


    @Test
    fun `validate username and password returns true`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "testUsername",
            "ABcde123",
            "ABcde123"
        )

        assertThat(result).isTrue()

    }

    @Test
    fun `username already exist returns false`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "John",
            "ABcde123",
            "ABcde123"
        )

        assertThat(result).isFalse()


    }


    @Test
    fun `password repeated incorrectly returns false`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "testUser",
            "ABcde1234",
            "ABcde1235"
        )

        assertThat(result).isFalse()


    }

    @Test
    fun `password contains less than 2 digits returns false`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "testUser",
            "ABcde1aa",
            "ABcde1aa"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `password contains less than 1 capital char returns false`() {

        val result = RegistrationUtil.validateRegistrationInput(
            "testUser",
            "Abcde123",
            "Abcde123"
        )
        assertThat(result).isFalse()
    }
}
