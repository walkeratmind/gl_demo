package com.codemonk.gl_demo.opengl

import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import com.codemonk.gl_demo.opengl.layers.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLRenderer : GLSurfaceView.Renderer {
    private lateinit var triangle: Triangle

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f, 0f, 0f, 1f)

        triangle = Triangle()

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        triangle.draw()
    }
}