package com.peacefirst.ingedny.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.peacefirst.ingedny.adapters.viewholders.ChildrenViewHolder
import com.peacefirst.ingedny.databinding.ItemHomeListBinding
import com.peacefirst.ingedny.models.response.ChildrenResponse

class ChildrenAdapter(
    private val listener: OnClickCardListener
) :
    ListAdapter<ChildrenResponse, ChildrenViewHolder>(ChildrenDiffUtils()) {
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
        holder.bind(getItem(position), listener)
    }

    interface OnClickCardListener {
        fun viewDetails(childId: Int)
    }

    class ChildrenDiffUtils : DiffUtil.ItemCallback<ChildrenResponse>() {
        override fun areItemsTheSame(
            oldItem: ChildrenResponse,
            newItem: ChildrenResponse
        ): Boolean {
            return oldItem.childId == newItem.childId
        }

        override fun areContentsTheSame(
            oldItem: ChildrenResponse,
            newItem: ChildrenResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
}