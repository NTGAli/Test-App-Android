package com.ntg.testapp

import android.content.Context

object ResourceComperor {

    fun isEqual(ctx: Context, resId: Int, str: String): Boolean{
        return ctx.getString(resId) == str
    }

}