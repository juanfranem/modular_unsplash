package es.jfechevarria.detail_pictures.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import es.jfechevarria.detail_pictures.common.ExifAdapter
import es.jfechevarria.detail_pictures.databinding.ErrorViewBinding
import es.jfechevarria.detail_pictures.databinding.ExifViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ErrorViewBinding

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ErrorViewBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
    }

    fun setMessage(message: String) {
        binding.message.text = message
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.retry.setOnClickListener(l)
    }
}