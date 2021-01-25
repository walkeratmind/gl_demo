package com.codemonk.gl_demo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codemonk.gl_demo.opengl.MyGLSurfaceView

class DashboardFragment : Fragment() {

    private lateinit var GLView: MyGLSurfaceView
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        GLView = MyGLSurfaceView(context)
        return GLView
    }
}