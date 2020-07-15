package com.saitejajanjirala.startoonlabstask.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.saitejajanjirala.startoonlabstask.R
import com.saitejajanjirala.startoonlabstask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        mainActivityViewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        FirebaseApp.initializeApp(this)
        binding.bottomnav.selectedItemId=R.id.Home
       // binding.lifecycleOwner=mainActivityViewModel
    }
}