package com.sangmee.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.sangmee.myapplication.databinding.FragmentMyBinding


class MyFragment : BaseFragment<FragmentMyBinding>(), OnBackPressedListener {

    override val onBackPressedListener: OnBackPressedListener
        get() = this

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMyBinding {
        return FragmentMyBinding.inflate(inflater, container, false)
    }

    override fun init() {
        mainVm.currentFragment.value = this
        initListener()

    }

    private fun initListener(){
        binding.btnGoNext.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add<TrainerPageFragment>(R.id.container)
                hide(this@MyFragment)
                addToBackStack("home")
            }
        }
    }
    override fun onBackPressed() {
        (activity as MainActivity).selectHome()
    }
}
