package com.kartik.grevocab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kartik.grevocab.adapters.display.ReservationDisplay
import com.kartik.grevocab.adapters.vh.ReservationViewHolder
import com.kartik.grevocab.databinding.InflateReservationBinding

class ReservationsAdapter(private val clickListener: OnBindClickListener? = null) : RecyclerView.Adapter<BaseBindingViewHolder>() {
    private var items: List<ReservationDisplay> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        holder as ReservationViewHolder
        holder.binding.rd = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return ReservationViewHolder(
            InflateReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    fun replaceData(items: List<ReservationDisplay>) {
        this.items = items
        notifyDataSetChanged()
    }
}
