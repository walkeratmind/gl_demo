package com.codemonk.gl_demo.usslib

import android.content.Context
import androidx.annotation.RawRes
import com.github.psambit9791.jdsp.io.Csv
import com.github.psambit9791.jdsp.transform.Hilbert
import java.io.InputStream

class SignalProcessor {

    private lateinit var rfSignals: Array<DoubleArray>

    fun hilbert2DTransform(rfSignals: Array<DoubleArray>): ArrayList<DoubleArray> {
        var hilbertMat: ArrayList<DoubleArray> = ArrayList()

        for ((index, value) in rfSignals.withIndex()) {
            val hilbert: Hilbert = Hilbert(value)
            hilbert.hilbertTransform()
            hilbert.amplitudeEnvelope.also { hilbertMat.add(it) }
        }
        return hilbertMat
//        rfSignals.iterator().forEach { signal ->
//            val hilbert: Hilbert = Hilbert(signal)
//            hilbert.hilbertTransform()
//            hilbertMat[i] = hilbert.amplitudeEnvelope
//            i++
//        }
    }
}