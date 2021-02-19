package com.codemonk.gl_demo.opengl.layers

import android.content.Context
import android.opengl.GLES30
import com.codemonk.gl_demo.R
import com.codemonk.gl_demo.utils.Shader
import com.codemonk.gl_demo.utils.rawResToString
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class Square(val context: Context) {

    private val squareVertices = floatArrayOf(
        -1f, -1f,
        1f, -1f,
        -1f, 1f,
        1f, 1f,
    )

    private val textureVertices = floatArrayOf(
        0f, 1f,
        1f, 1f,
        0f, 0f,
        1f, 0f
    )

    private val squareBuffer: FloatBuffer =
        ByteBuffer.allocateDirect(squareVertices.size * 4).run {
            order(ByteOrder.nativeOrder())

            asFloatBuffer().apply {
                put(squareVertices)
                position(0)
            }
        }

    private val textureBuffer: FloatBuffer =
        ByteBuffer.allocateDirect(textureVertices.size * 4).run {
            order(ByteOrder.nativeOrder())

            asFloatBuffer().apply {
                put(textureVertices)
                position(0)
            }
        }

    private var positionHandle: Int = 0
    private var textureHandle: Int = 0
    private var texturePositionHandle: Int = 0

    private var mProgram: Int

    init {
        val vertexShader: Int =
            Shader.loadShader(GLES30.GL_VERTEX_SHADER, context.rawResToString(R.raw.vertex_shader))
        val fragmentShader: Int = Shader.loadShader(
            GLES30.GL_FRAGMENT_SHADER,
            context.rawResToString(R.raw.fragment_shader)
        )

        mProgram = GLES30.glCreateProgram().also {
            GLES30.glAttachShader(it, vertexShader)
            GLES30.glAttachShader(it, fragmentShader)

            GLES30.glLinkProgram(it)
        }
    }


    fun draw(texture: Int) {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
        GLES30.glUseProgram(mProgram)

        positionHandle = GLES30.glGetAttribLocation(mProgram, "aPosition")
        textureHandle = GLES30.glGetUniformLocation(mProgram, "uTexture")
        texturePositionHandle = GLES30.glGetAttribLocation(mProgram, "aTexPosition")

        GLES30.glVertexAttribPointer(
            texturePositionHandle,
            2,
            GLES30.GL_FLOAT,
            false,
            0,
            textureBuffer
        )
        GLES30.glEnableVertexAttribArray(texturePositionHandle)

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0)
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture)
        GLES30.glUniform1i(textureHandle, 0)

        GLES30.glVertexAttribPointer(positionHandle, 2, GLES30.GL_FLOAT, false, 0, squareBuffer)
        GLES30.glEnableVertexAttribArray(positionHandle)

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4)

    }
}