package com.codemonk.gl_demo.opengl.renderer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.*
import com.codemonk.gl_demo.R
import com.codemonk.gl_demo.opengl.layers.Square
import com.codemonk.gl_demo.utils.convertToByteArray
import timber.log.Timber
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TextureRenderer(context: Context?) : GLSurfaceView.Renderer {

    private lateinit var mModelMatrix: Matrix
    private lateinit var mViewMatrix: Matrix
    private lateinit var mProjectionMatrix: Matrix
    private lateinit var mMVPMatrix: Matrix

    private lateinit var mLightModelMatrix: Matrix

    private lateinit var mCubePositions: FloatBuffer
    private lateinit var mCubeColors: FloatBuffer
    private lateinit var mCubeNormals: FloatBuffer
    private lateinit var mCubeTextureCoordinates: FloatBuffer

    private lateinit var square: Square
    private var textures: IntArray = IntArray(2)

    private lateinit var photo: Bitmap
    private var photoWidth: Int
    private var photoHeight: Int

    private var context: Context = context!!

    init {
//            var options: BitmapFactory.Options =
//                BitmapFactory.Options().also { it.inScaled = false }

        photo = BitmapFactory.decodeResource(context?.resources, R.drawable.hobbit)
//            var img = Bi

//            BitmapFactory.decodeByteArray()
        photoWidth = photo.width
        photoHeight = photo.height


    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f, 0f, 0f, 1f)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)

        generateSquare(context)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        square.draw(textures[0])
    }

    private fun generateSquare(context: Context?) {
        GLES30.glGenTextures(2, textures, 0)
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textures[0]);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR)
        GLES30.glTexParameteri(
            GLES30.GL_TEXTURE_2D,
            GLES30.GL_TEXTURE_WRAP_S,
            GLES30.GL_CLAMP_TO_EDGE
        )
        GLES30.glTexParameteri(
            GLES30.GL_TEXTURE_2D,
            GLES30.GL_TEXTURE_WRAP_T,
            GLES30.GL_CLAMP_TO_EDGE
        )


        var options: BitmapFactory.Options =
            BitmapFactory.Options().also { it.inScaled = true }

        var pic = BitmapFactory.decodeResource(context?.resources, R.drawable.hobbit)

        var photoArray: ByteArray = pic.convertToByteArray()
        Timber.d("pic Array: ${photoArray.size}")

        var photoBytes = pic.byteCount
        var buffer: ByteBuffer = ByteBuffer.allocate(pic.byteCount)

        photo.copyPixelsToBuffer(buffer)
        buffer.rewind()

//        GLES30.glTexImage2D()

//        buffer.array()

        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, photo, 0)

        square = Square(context!!)

    }

}