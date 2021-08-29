package com.sangmee.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.sangmee.myapplication.databinding.FragmentTrainerDetailPageBinding


class TrainerDetailPageFragment : BaseFragment<FragmentTrainerDetailPageBinding>(), OnBackPressedListener {

    override val onBackPressedListener: OnBackPressedListener
        get() = this

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTrainerDetailPageBinding {
        return FragmentTrainerDetailPageBinding.inflate(inflater, container, false)
    }

    override fun init() {
        mainVm.currentFragment.value = this
    }

    override fun onBackPressed() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            remove(this@TrainerDetailPageFragment)
            val showFragment = parentFragmentManager.fragments[parentFragmentManager.fragments.size - 2]
            show(showFragment)
            mainVm.currentFragment.value = showFragment
        }
    }
}
