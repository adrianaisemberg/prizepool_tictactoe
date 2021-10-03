package com.adrianaisemberg.tictactoe.mvvm

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.adrianaisemberg.tictactoe.BR
import com.adrianaisemberg.tictactoe.utils.activity
import com.adrianaisemberg.tictactoe.utils.layoutInflater
import dagger.hilt.android.migration.OptionalInject
import javax.inject.Inject

@OptionalInject
abstract class ViewModelView<
        TViewModel : ViewViewModel,
        TViewBinding : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var viewModel: TViewModel

    @Suppress("LeakingThis")
    protected val binding: TViewBinding = DataBindingUtil.inflate<TViewBinding>(
        context.layoutInflater(),
        layoutId,
        this,
        true
    ).apply {
        lifecycleOwner = activity() as LifecycleOwner
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.setVariable(BR.viewModel, viewModel)

        viewModel.onFinishInflate()
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        super.setOnClickListener(listener)

        viewModel.setOnClickListener(listener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        viewModel.onDetachedFromWindow()
    }
}
