package com.peacefirst.ingedny.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.peacefirst.ingedny.databinding.ItemSocialBinding
import com.peacefirst.ingedny.models.response.SocialResponse

class SocialViewHolder(itemView: ItemSocialBinding) : RecyclerView.ViewHolder(itemView.root) {
    private val icon = itemView.ivSocialIcon
    private val name = itemView.tvSocialName
    private val viewLinkButton = itemView.btnViewSocial
    fun bind(social: SocialResponse, viewLinkAction: (link: String) -> Unit) {
        icon.load(social.icon)
        name.text = social.name
        viewLinkButton.setOnClickListener {
            viewLinkAction.invoke(social.link)
        }
    }
}