package com.codemonk.gl_demo.ui.notifications

import android.graphics.SurfaceTexture
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.fragment.app.Fragment

class NotificationsFragment : Fragment(), TextureView.SurfaceTextureListener {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var textureView: TextureView
    private lateinit var mSurfaceTexture: SurfaceTexture
    private lateinit var mCamera: CameraX

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        textureView = TextureView(requireContext())
        textureView.surfaceTextureListener = this

        return textureView
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
        TODO("Not yet implemented")
    }

    fun startPreview() {
        val cameraProviderFeature = ProcessCameraProvider.getInstance(requireContext())
    }

    fun bindCameraPreview(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder().build()

        val cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()


//        preview.(textureView.surfaceTexture)
    }


}