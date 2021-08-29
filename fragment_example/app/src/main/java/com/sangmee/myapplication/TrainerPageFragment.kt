package com.sangmee.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.sangmee.myapplication.databinding.FragmentTrainerPageBinding


class TrainerPageFragment : BaseFragment<FragmentTrainerPageBinding>(), OnBackPressedListener {

    override val onBackPressedListener: OnBackPressedListener
        get() = this

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTrainerPageBinding {
        return FragmentTrainerPageBinding.inflate(inflater, container, false)
    }

    override fun init() {
        mainVm.currentFragment.value = this

        initListener()
    }

    private fun initListener() {
        binding.btnGoNext.setOnClickListener {
            binding.tvClickNum.apply {
                text = (text.toString().toInt() + 1).toString()
            }

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add<TrainerDetailPageFragment>(R.id.container)
                hide(this@TrainerPageFragment)
                addToBackStack("trainerDetailPage")
            }
        }
    }

    override fun onBackPressed() {

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            remove(this@TrainerPageFragment)
            val showFragment = parentFragmentManager.fragments[parentFragmentManager.fragments.size - 2]
            show(showFragment)
            mainVm.currentFragment.value = showFragment
        }
    }
}
