package com.everis.cursoandroidbasicosample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.everis.cursoandroidbasicosample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.startButton.setOnClickListener { v ->
            val intent = Intent(this@MainActivity, CarActivity::class.java)
            startActivity(intent)
        }
        binding.title.text =""""""
    }
}