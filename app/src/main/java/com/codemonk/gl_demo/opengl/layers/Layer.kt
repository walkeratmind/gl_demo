package com.codemonk.gl_demo.opengl.layers

interface Layer {
    fun prepare()
    fun draw(mvpMatrix: FloatArray)
}