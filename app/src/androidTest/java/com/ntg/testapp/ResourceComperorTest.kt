package com.ntg.testapp

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceComperorTest{

    private lateinit var resourceComperor: ResourceComperor

    @Before
    fun setup(){
        resourceComperor = ResourceComperor
    }

    @Test
    fun stringResSameAsGivenString_returnsTrue(){
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        assertThat(resourceComperor.isEqual(ctx, R.string.app_name, "Test App")).isTrue()
    }

    @Test
    fun stringResDifferentAsGivenString_returnsFalse(){
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        assertThat(resourceComperor.isEqual(ctx, R.string.app_name, "Test")).isFalse()
    }


}