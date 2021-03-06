package com.peacefirst.ingedny.adapters.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.adapters.ChildrenAdapter
import com.peacefirst.ingedny.databinding.ItemHomeListBinding
import com.peacefirst.ingedny.models.ModelEnums
import com.peacefirst.ingedny.models.response.ChildrenResponse

class ChildrenViewHolder(itemView: ItemHomeListBinding) : RecyclerView.ViewHolder(itemView.root) {
    private val context = itemView.root.context
    private val childImageView = itemView.ivMainChildImg
    private val childNameArTextView = itemView.tvMainChildNameAr
    private val childNameEnTextView = itemView.tvMainChildNameEn
    private val childAgeTextView = itemView.tvMainChildAge
    private val reportTypeTextView = itemView.tvMainReportType
    private val viewDetailsButton = itemView.btnViewDetails

    fun bind(child: ChildrenResponse, listener: ChildrenAdapter.OnClickCardListener) {
        childImageView.load(child.img) {
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
        }
        childNameArTextView.text = child.nameAr
        child.nameEn?.let {
            if (it.isNotEmpty()) {
                childNameEnTextView.visibility = View.VISIBLE
                childNameEnTextView.text = child.nameEn
            } else {
                childNameEnTextView.visibility = View.GONE
            }
        }
        if (child.age <=10){
            childAgeTextView.text = context.getString(R.string.child_age, child.age.toString())
        }else{
            childAgeTextView.text = context.getString(R.string.child_age_more_than_10, child.age.toString())
        }

        reportTypeTextView.text = child.reportType.name

        if (child.reportType == ModelEnums.ReportType.Missing) {
            reportTypeTextView.background =
                ContextCompat.getDrawable(context, R.drawable.shape_triangle_opacity_red)
        } else {
            reportTypeTextView.background =
                ContextCompat.getDrawable(context, R.drawable.shape_triangle_opacity_blue)
        }
        viewDetailsButton.setOnClickListener {
            listener.viewDetails(child.childId)
        }
    }
}