package com.adrianaisemberg.tictactoe.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adrianaisemberg.tictactoe.BR
import javax.inject.Inject

abstract class ViewModelActivity<
        TViewModel : ActivityViewModel,
        TViewBinding : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
) : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TViewModel

    @Inject
    lateinit var common: Common

    protected lateinit var binding: TViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        common.logger.d("${javaClass.simpleName}.onCreate")

        viewModel.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<TViewBinding>(
            this,
            layoutId
        ).apply {
            lifecycleOwner = this@ViewModelActivity
            setVariable(BR.viewModel, viewModel)
        }

        onViewModelCreated()
    }

    protected open fun onViewModelCreated() {

    }

    override fun onStart() {
        super.onStart()

        common.logger.d("${javaClass.simpleName}.onStart")

        viewModel.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()

        common.logger.d("${javaClass.simpleName}.onDestroy")

        viewModel.onDestroy()
    }

    override fun onBackPressed() {
        if (viewModel.onBack()) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()

        common.logger.d("${javaClass.simpleName}.onResume")

        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()

        common.logger.d("${javaClass.simpleName}.onPause")

        viewModel.onPause()
    }
}
