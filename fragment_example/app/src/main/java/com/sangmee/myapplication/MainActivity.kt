package com.sangmee.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sangmee.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
    }

    private fun initView() {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SplashFragment>(R.id.container)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    clickBottomNav("home")
                    true
                }
                R.id.my -> {
                    clickBottomNav("my")
                    true
                }

                else -> false
            }
        }
    }

    private fun initObserver() {
        vm.currentFragment.observe(this) {
            when (it) {
                is HomeFragment  -> binding.bottomNavigation.isVisible = true
                is MyFragment -> binding.bottomNavigation.isVisible = true
                else -> binding.bottomNavigation.isVisible = true
            }
        }
    }

    private fun clickBottomNav(tag: String) {
        vm.backStackTagList.run {
            if (contains(tag)) {
                // saved State에서 home backstack 불러오기
                supportFragmentManager.saveBackStack(this[0])
                supportFragmentManager.restoreBackStack(tag)

                removeAt(indexOf(tag))

            } else {
                when (tag) {

                    "home" -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<HomeFragment>(R.id.container)
                            addToBackStack(tag)
                        }
                    }

                    "my" -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<MyFragment>(R.id.container)
                            addToBackStack(tag)
                        }
                    }
                }

            }
            add(0, tag)
        }
    }

    fun selectHome() {
        binding.bottomNavigation.selectedItemId = R.id.home
    }
}
