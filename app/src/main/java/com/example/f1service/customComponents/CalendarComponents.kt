package com.example.f1service.customComponents

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.f1service.R
import com.example.f1service.databinding.CustomCountDownComponentBinding
import kotlinx.coroutines.DelicateCoroutinesApi

@SuppressLint("Recycle")
class CalendarComponents(
    context: Context,
    attrs: AttributeSet
    ):ConstraintLayout(context,attrs){

    private lateinit var mBinding:CustomCountDownComponentBinding

        init {
            inflate(context, R.layout.custom_count_down_component, this)
            val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.CalendarComponents, 0, 0)
            mBinding = CustomCountDownComponentBinding.inflate(LayoutInflater.from(context),this,false)
            addView(mBinding.root)

            mBinding.CountDownLayoutName.text  = customAttributesStyle.getString(R.styleable.CalendarComponents_text)

        }


    @OptIn(DelicateCoroutinesApi::class)
    fun setText(text:Int) {
        mBinding.countDownTimerTextView.text = text.toString()
    }
}