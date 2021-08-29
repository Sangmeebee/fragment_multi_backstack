package com.sangmee.myapplication

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            parentFragmentManager.beginTransaction().remove(this).commit()
            (activity as MainActivity).selectHome()

        }, 1000L)
    }
}
