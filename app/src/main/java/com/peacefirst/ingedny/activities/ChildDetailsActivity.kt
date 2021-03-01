package com.peacefirst.ingedny.activities

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import coil.load
import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.apputils.DialogUtil
import com.peacefirst.ingedny.base.BaseActivity
import com.peacefirst.ingedny.databinding.ActivityChildDetailsBinding
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.models.ModelEnums
import com.peacefirst.ingedny.models.response.ChildDetailsResponse
import com.peacefirst.ingedny.viewmodles.ChildDetailsViewModel
import com.peacefirst.ingedny.base.Result

class ChildDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityChildDetailsBinding
    private val viewModel: ChildDetailsViewModel by viewModels()
    private val args: ChildDetailsActivityArgs by navArgs()
    private lateinit var imgDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.title_details_activity)
        viewModel.getChildDetails(args.childId)
        viewModel.childDetailsResponseLiveData.observe(this, childDetailsObserver)

    }

    private val childDetailsObserver = Observer<Result<BaseResponse<ChildDetailsResponse>>> {
        when (it) {
            is Result.Success -> {
                val childDetailsResponse = it.response.data
                childDetailsResponse?.apply {
                    if (childReportType == ModelEnums.ReportType.Missing) {
                        binding.tvChildPlaceHeader.text = getString(R.string.str_child_missed_place)
                    } else {
                        binding.tvChildPlaceHeader.text =
                            getString(R.string.str_place_where_you_found_child)
                    }
                    binding.tvChildPlace.text = place
                    binding.ivChildImg.load(image) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.placeholder)

                    }
                    imgDialog =
                        DialogUtil.createFullScreenImageDialog(this@ChildDetailsActivity, image)
                    binding.ivChildImg.setOnClickListener {
                        imgDialog.show()
                    }
                    binding.tvChildNameAr.text = nameAr
                    if (nameEn?.isNotEmpty() == true) {
                        binding.tvChildNameEn.visibility = View.VISIBLE
                        binding.tvChildNameEn.text = nameEn
                    }
                    binding.tvChildReportType.text =
                        childReportType.name
                    binding.tvChildAge.text = age
                    binding.tvChildHeight.text = height
                    binding.tvChildGender.text = gender.name
                    when (gender) {
                        ModelEnums.Gender.Male -> {
                            binding.ivGender.setImageDrawable(
                                ContextCompat.getDrawable(
                                    this@ChildDetailsActivity,
                                    R.drawable.ic_male
                                )
                            )
                        }
                        ModelEnums.Gender.Female -> {
                            binding.ivGender.setImageDrawable(
                                ContextCompat.getDrawable(
                                    this@ChildDetailsActivity,
                                    R.drawable.ic_female
                                )
                            )
                        }
                    }
                    binding.tvChildSkinColor.text = skinColor
                    binding.tvChildHairColor.text = hairColor
                    binding.tvChildEyeColor.text = eyeColor

                    binding.tvReporterName.text = reporterName
                    binding.tvReporterPhone.text = getString(
                        R.string.str_show_three_numbers,
                        reporterPhone.substring(0, 3)
                    )
                    binding.tvReporterAddress.text = reporterAddress

                    binding.btnCall.setOnClickListener {
                        viewModel.callNumberAnalytics(args.childId)
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data =
                            Uri.parse("tel:${reporterPhone}")
                        startActivity(intent)
                    }
                }
            }
            is Result.Error -> showError(it.exception.msg)
            Result.Loading -> showFullProgressDialog()
            Result.Complete -> hideFullProgressDialog()
        }
    }
}