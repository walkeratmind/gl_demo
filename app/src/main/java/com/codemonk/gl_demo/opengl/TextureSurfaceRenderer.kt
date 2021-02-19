package com.codemonk.gl_demo.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import com.codemonk.gl_demo.opengl.renderer.TextureRenderer

class TextureSurfaceRenderer(context: Context?) : GLSurfaceView(context) {
    private val renderer: TextureRenderer

    init {
//        set openGL version
        setEGLContextClientVersion(3)

        renderer = TextureRenderer(context)

        // render view only when there is change in drawing data
//        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

    }

}