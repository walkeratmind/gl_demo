package com.codemonk.gl_demo.opengl.layers

import android.opengl.GLES30
import com.codemonk.gl_demo.utils.Shader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

const val COORDS_PER_VERTEX = 3
var triangleCoords = floatArrayOf(     // in counterclockwise order:
    0.0f, 0.622008459f, 0.0f,      // top
    -0.5f, -0.311004243f, 0.0f,    // bottom left
    0.5f, -0.311004243f, 0.0f      // bottom right
)

class Triangle {
    private val color: FloatArray = floatArrayOf(0.5f, 0.5f, 0.5f, 1.0f)

    private val vertexShaderCode =
        "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = vPosition;" +
                "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" + "}"

    private var mProgram: Int

    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {
        val vertexShader: Int = Shader.loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = Shader.loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode)

        mProgram = GLES30.glCreateProgram().also {
            GLES30.glAttachShader(it, vertexShader)
            GLES30.glAttachShader(it, fragmentShader)

            GLES30.glLinkProgram(it)
        }
    }

    private val vertexBuffer: FloatBuffer =
        // (number of coordinate values * 4 bytes per float)
        ByteBuffer.allocateDirect(triangleCoords.size * 4).run {
            order(ByteOrder.nativeOrder())

            asFloatBuffer().apply {
                put(triangleCoords)
                position(0)
            }
        }

    fun draw() {
        GLES30.glUseProgram(mProgram)

        positionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition").also {

            // Enable a handle to the triangle vertices
            GLES30.glEnableVertexAttribArray(it)

            // Prepare triangle co-ordinate data
            GLES30.glVertexAttribPointer(
                it, COORDS_PER_VERTEX, GLES30.GL_FLOAT, false, vertexStride, vertexBuffer
            )

            // get handle to fragment shader's vColor member
            mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->
                // color for drawing triangle
                GLES30.glUniform4fv(colorHandle, 1, color, 0)
            }

            // draw triangle vertex
            GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount)

            // disable vertex array
            GLES30.glDisableVertexAttribArray(it)
        }
    }
}