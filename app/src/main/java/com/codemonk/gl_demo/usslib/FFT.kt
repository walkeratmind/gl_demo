package com.codemonk.gl_demo.usslib

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.FastFourierTransformer

class FFT(private var signal: DoubleArray) {

    private var output: ArrayList<Complex> = ArrayList()

    companion object {
        fun fft(signal: DoubleArray): ArrayList<Complex> {
            var out: ArrayList<Complex> = ArrayList()

            for (i in 0..signal.lastIndex) {
                var real: Double = 0.0
                var img: Double = 0.0
                for (j in 0..signal.lastIndex) {
                    val angle: Double = (2 * Math.PI * j * i) / signal.lastIndex
                    real += signal[j] * Math.cos(angle)
                    img += -signal[j] * Math.sin(angle)
                }
                out.add(Complex(real, img))
            }
            return out
        }
    }


}