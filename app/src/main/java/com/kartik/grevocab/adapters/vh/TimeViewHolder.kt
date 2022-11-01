package com.kartik.grevocab.adapters.vh

import android.view.View
import com.kartik.grevocab.adapters.BaseBindingViewHolder
import com.kartik.grevocab.adapters.OnBindClickListener
import com.kartik.grevocab.databinding.InflateTimeBinding

class TimeViewHolder(val binding: InflateTimeBinding, private val clickListener: OnBindClickListener? = null) : BaseBindingViewHolder(binding), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        clickListener?.onItemClick(v, this.adapterPosition, binding.td as Any)
    }
}
