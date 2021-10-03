package com.adrianaisemberg.tictactoe

import com.adrianaisemberg.tictactoe.mvvm.ViewModelActivity
import com.adrianaisemberg.tictactoe.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    ViewModelActivity<MainActivityViewModel, ActivityMainBinding>(R.layout.activity_main)
