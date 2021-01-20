package com.example.peacefirst.adapters.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.peacefirst.R
import com.example.peacefirst.adapters.ChildrenAdapter
import com.example.peacefirst.databinding.ItemHomeListBinding
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.response.ChildrenResponse

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
        childAgeTextView.text = context.getString(R.string.child_age, child.age.toString())
        reportTypeTextView.text = child.reportType.name

//        if (child.reportType == ModelEnums.ReportType.Missing) {
//            reportTypeTextView.setTextColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.color_secondary_dark
//                )
//            )
//        } else {
//            reportTypeTextView.setTextColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.black
//                )
//            )
//        }
        viewDetailsButton.setOnClickListener {
            listener.viewDetails(child.childId)
        }
    }
}