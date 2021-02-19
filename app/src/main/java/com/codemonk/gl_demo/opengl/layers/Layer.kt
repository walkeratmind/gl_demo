package com.codemonk.gl_demo.opengl.layers

interface Layer {
    fun draw(mvpMatrix: FloatArray)
}