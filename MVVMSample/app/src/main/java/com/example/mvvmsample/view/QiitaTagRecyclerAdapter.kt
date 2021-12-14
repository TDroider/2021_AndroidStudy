package com.example.mvvmsample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.AdapterTagItemBinding
import com.example.mvvmsample.domain.QiitaTag

class QiitaTagRecyclerAdapter(tags: List<QiitaTag>) :
    RecyclerView.Adapter<QiitaTagRecyclerAdapter.VH>() {

    private val data = tags.toMutableList()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_tag_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    fun refresh(newData: List<QiitaTag>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: AdapterTagItemBinding = AdapterTagItemBinding.bind(itemView)
        fun bind(tag: QiitaTag) {
            binding.tag = tag
            binding.executePendingBindings()
        }
    }
}
