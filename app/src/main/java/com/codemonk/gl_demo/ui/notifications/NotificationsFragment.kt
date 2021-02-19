package com.codemonk.gl_demo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codemonk.gl_demo.R
import com.codemonk.gl_demo.opengl.TextureSurfaceRenderer
import com.codemonk.gl_demo.opengl.renderer.TextureRenderer
import com.codemonk.gl_demo.usslib.CSVReader
import com.codemonk.gl_demo.usslib.SignalProcessor
import com.codemonk.gl_demo.utils.rawResToInputStream
import com.codemonk.gl_demo.utils.rawResToString
import timber.log.Timber
import java.io.InputStream

class NotificationsFragment : Fragment() {

    private lateinit var glView: TextureSurfaceRenderer
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        glView = TextureSurfaceRenderer(context)
        glView.setRenderer(TextureRenderer(context))

        loadRFSignals()
        return glView
    }

    fun loadRFSignals() {
        val inputStream: InputStream = requireContext().rawResToInputStream(R.raw.single_rf_data)
        Timber.d("InputStream : ${inputStream.bufferedReader(Charsets.UTF_8).toString()}")
        var rfSignals = CSVReader(inputStream).read()
        var signalProcessor = SignalProcessor()
        var hilbertMat = signalProcessor.hilbert2DTransform(rfSignals.toTypedArray())
        Timber.d("hilbertMat: $hilbertMat")

    }


}