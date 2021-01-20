package com.example.peacefirst.activities

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import coil.load
import com.example.peacefirst.R
import com.example.peacefirst.apputils.DialogUtil
import com.example.peacefirst.base.BaseActivity
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.base.Result
import com.example.peacefirst.databinding.ActivityChildDetailsBinding
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.response.ChildDetailsResponse
import com.example.peacefirst.viewmodles.ChildDetailsViewModel

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
                    if (childReportType == ModelEnums.ReportType.Founded) {
                        binding.tvChildPlaceHeader.text =
                            getString(R.string.str_place_where_you_found_child)
                    } else {
                        binding.tvChildPlaceHeader.text = getString(R.string.str_child_missed_place)
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
                            binding.tvChildGender.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_male,
                                0
                            )
                        }
                        ModelEnums.Gender.Female -> {
                            binding.tvChildGender.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_female,
                                0
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