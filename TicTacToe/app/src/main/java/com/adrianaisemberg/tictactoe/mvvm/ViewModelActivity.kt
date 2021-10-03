package com.adrianaisemberg.tictactoe.mvvm

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

    protected lateinit var binding: TViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<TViewBinding>(
            this,
            layoutId
        ).apply {
            lifecycleOwner = this@ViewModelActivity
            setVariable(BR.viewModel, viewModel)
        }
    }
}
