package com.everis.cursoandroidbasicosample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.everis.cursoandroidbasicosample.R
import com.everis.cursoandroidbasicosample.databinding.ActivityCarBinding

class CarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(R.id.container_frame, CarListFragment())
    }

    fun replaceFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(frameId, fragment)
            .addToBackStack("Cars")
            .commit()
    }

    fun addFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(frameId, fragment)
            .addToBackStack("Cars")
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            this.finish()
        }
    }
}