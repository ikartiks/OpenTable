package com.kartik.grevocab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kartik.grevocab.adapters.display.TimeDisplay
import com.kartik.grevocab.adapters.vh.TimeViewHolder
import com.kartik.grevocab.databinding.InflateTimeBinding

class TimeAdapter(private val clickListener: OnBindClickListener? = null) : RecyclerView.Adapter<BaseBindingViewHolder>() {
    private var items: List<TimeDisplay> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        holder as TimeViewHolder
        holder.binding.td = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return TimeViewHolder(
            InflateTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    fun replaceData(items: List<TimeDisplay>) {
        this.items = items
        notifyDataSetChanged()
    }
}
