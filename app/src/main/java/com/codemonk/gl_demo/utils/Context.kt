package com.codemonk.gl_demo.utils

import android.content.Context
import androidx.annotation.RawRes

fun Context.rawResToString(@RawRes resource: Int): String {
    val inputString = resources.openRawResource(resource)

    return inputString.readBytes().toString(Charsets.UTF_8)
}