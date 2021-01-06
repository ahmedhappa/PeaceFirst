package com.example.peacefirst.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peacefirst.adapters.viewholders.SocialViewHolder
import com.example.peacefirst.databinding.ItemSocialBinding
import com.example.peacefirst.models.response.SocialResponse

class SocialAdapter(
    private val socialList: List<SocialResponse>,
    private val viewLinkAction: (link:String) -> Unit
) :
    RecyclerView.Adapter<SocialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder(
            ItemSocialBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(socialList[position], viewLinkAction)
    }

    override fun getItemCount(): Int {
        return socialList.size
    }
}