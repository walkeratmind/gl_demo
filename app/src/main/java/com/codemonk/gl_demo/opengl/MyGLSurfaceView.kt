package com.codemonk.gl_demo.opengl

import android.content.Context
import android.opengl.GLSurfaceView

class MyGLSurfaceView(context: Context?) : GLSurfaceView(context) {
    private val renderer: GLRenderer

    init {
//        set openGL version
        setEGLContextClientVersion(3)

        renderer = GLRenderer()

        // render view only when there is change in drawing data
//        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

        setRenderer(renderer)
    }
}