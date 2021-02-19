package com.codemonk.gl_demo.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.RawRes
import java.io.InputStream
import java.nio.ByteBuffer

fun Context.rawResToString(@RawRes resource: Int): String {
    val inputString = resources.openRawResource(resource)

    return inputString.readBytes().toString(Charsets.UTF_8)
}

fun Context.rawResToInputStream(@RawRes resource: Int) : InputStream {
    return resources.openRawResource(resource)
}

fun Bitmap.convertToByteArray(): ByteArray = ByteBuffer.allocate(byteCount).apply {
    copyPixelsToBuffer(this)
    rewind()
}.array()