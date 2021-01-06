package com.example.peacefirst.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.peacefirst.adapters.viewholders.ChildrenViewHolder
import com.example.peacefirst.databinding.ItemHomeListBinding
import com.example.peacefirst.models.response.ChildrenResponse

class ChildrenAdapter(
    private val children: MutableList<ChildrenResponse>,
    private val listener: OnClickCardListener
) :
    RecyclerView.Adapter<ChildrenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        return ChildrenViewHolder(
            ItemHomeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        holder.bind(children[position], listener)
    }

    override fun getItemCount(): Int {
        return children.size
    }

    interface OnClickCardListener {
        fun viewDetails(childId: Int)
    }
}