package com.example.peacefirst.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.peacefirst.R
import com.example.peacefirst.databinding.ItemHomeListBinding
import com.example.peacefirst.models.ChildrenResponse

class ChildrenViewHolder(itemView: ItemHomeListBinding) : RecyclerView.ViewHolder(itemView.root) {
    private val context = itemView.root.context
    private val childImageView = itemView.ivMainChildImg
    private val childNameTextView = itemView.tvMainChildName
    private val childAgeTextView = itemView.tvMainChildAge
    private val reportTypeTextView = itemView.tvMainReportType

    fun bind(child: ChildrenResponse.Child) {
        childImageView.load(child.img)
        childNameTextView.text = context.getString(R.string.child_name, child.name)
        childAgeTextView.text = context.getString(R.string.child_age, child.age.toString())
        reportTypeTextView.text = context.getString(R.string.report_type, child.reportType)
        when (child.gender){
            ChildrenResponse.Child.Gender.MALE -> childNameTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_male,0)
            ChildrenResponse.Child.Gender.FEMALE -> childNameTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_female,0)
        }
    }
}