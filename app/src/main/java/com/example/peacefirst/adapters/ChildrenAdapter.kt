package com.example.peacefirst.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.peacefirst.R
import com.example.peacefirst.adapters.viewholders.ChildrenViewHolder
import com.example.peacefirst.models.ChildrenResponse

class ChildrenAdapter(private val children: List<ChildrenResponse.Child>) :
    RecyclerView.Adapter<ChildrenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        return ChildrenViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        holder.bind(children[position])
    }

    override fun getItemCount(): Int {
        return children.size
    }
}