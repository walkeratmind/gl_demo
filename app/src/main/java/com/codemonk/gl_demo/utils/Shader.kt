package com.codemonk.gl_demo.utils

import android.opengl.GLES30

class Shader {
    companion object {
        fun loadShader(type: Int, shaderCode: String): Int {
            return GLES30.glCreateShader(type).also { shader ->
                GLES30.glShaderSource(shader, shaderCode)
                GLES30.glCompileShader(shader)
            }
        }
    }

}