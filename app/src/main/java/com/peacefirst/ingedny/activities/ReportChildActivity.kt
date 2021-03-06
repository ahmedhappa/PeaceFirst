package com.peacefirst.ingedny.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.peacefirst.ingedny.base.BaseActivity
import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.apputils.Utils
import com.peacefirst.ingedny.base.Result
import com.peacefirst.ingedny.databinding.ActivityReportChildBinding
import com.peacefirst.ingedny.models.ModelEnums
import com.peacefirst.ingedny.models.request.ReportChildRequest
import com.peacefirst.ingedny.viewmodles.ReportChildViewModel
import java.lang.Exception

class ReportChildActivity : BaseActivity() {
    companion object {
        const val EXTRA_REPORT_TYPE = "report_type"
    }

    private lateinit var binding: ActivityReportChildBinding
    private val viewModel: ReportChildViewModel by viewModels()
    private lateinit var childImg: Bitmap
    private lateinit var childImgName: String
    private var isChildNameFieldActive = false
    private var isChildAgeFieldActive = false
    private var isChildHeightFieldActive = false
    private var isChildSkinColorFieldActive = false
    private var isChildHairColorFieldActive = false
    private var isChildEyeColorFieldActive = false
    private var isImageAttached = false
    private var isChildMissedPlaceFieldActive = false
    private var isChildCurrentPlaceFieldActive = true
    private var isReporterNameFieldActive = false
    private var isReporterPhoneFieldActive = false
    private var isReporterAddressFieldActive = false
    private var isReporterNationalIDFieldActive = false
    private val attachFileRequestCode = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportChildBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.title_report_child_activity)
        val reportType: ModelEnums.ReportType =
            intent.getSerializableExtra(EXTRA_REPORT_TYPE) as ModelEnums.ReportType
        var reportTypeToSend = ""

        if (reportType == ModelEnums.ReportType.Missing) {
            binding.tilChildCurrentPlace.visibility = View.GONE
            reportTypeToSend = getString(R.string.str_missing)
        } else {
            binding.tilChildMissedPlace.hint =
                getString(R.string.str_place_where_you_found_child)
            binding.tilChildCurrentPlace.visibility = View.VISIBLE
            isChildCurrentPlaceFieldActive = false
            reportTypeToSend = getString(R.string.str_found)
        }

        validateFields()
        setAutoCompleteTextData()

        binding.btnAttachImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, attachFileRequestCode)
        }


        binding.btnSubmitReport.setOnClickListener {
            val checkedGenderRadio: RadioButton =
                findViewById(binding.rgGender.checkedRadioButtonId)
            val reporter = ReportChildRequest.Reporter(
                binding.etReporterName.text.toString(),
                binding.etReporterPhone.text.toString(),
                binding.etReporterAddress.text.toString(),
                binding.etReporterNationalId.text.toString()
            )
            val reportChildRequest = ReportChildRequest(
                binding.etChildNameAr.text.toString(),
                binding.etChildNameEn.text.toString(),
                checkedGenderRadio.text.toString(),
                binding.etAge.text.toString().toShort(),
                binding.etHeight.text.toString().toShort(),
                binding.actSkinColor.text.toString(),
                binding.actHairColor.text.toString(),
                binding.actEyeColor.text.toString(),
                childImg,
                childImgName,
                binding.etChildMissedPlace.text.toString(),
                binding.etChildCurrentPlace.text.toString(),
                reportTypeToSend,
                reporter
            )

            viewModel.reportChild(reportChildRequest)
        }

        viewModel.reportChildLiveData.observe(this, {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(
                        this,
                        getString(R.string.toast_success_child_report),
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK)
                    finish()
                }
                is Result.Error -> showError(it.exception.msg)
                Result.Loading -> showFullProgressDialog()
                Result.Complete -> hideFullProgressDialog()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == attachFileRequestCode) {
            val imgUri = data?.data
            imgUri?.let {
                try {
                    childImg = getBitmapFomUri(it)
                    childImgName = getImageNameFromUri(it)
                    binding.ivAttachedImage.setImageBitmap(childImg)
                    isImageAttached = true
                    changeButtonStatus()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getBitmapFomUri(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun getImageNameFromUri(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        var result = "imgName.jpg"
        if (cursor != null && cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }
        cursor?.close()
        return result
    }

    private fun setAutoCompleteTextData() {
        binding.actSkinColor.setAdapter(
            ArrayAdapter(
                this,
                R.layout.item_dropdown,
                Utils.AutoCompleteTextList.SKIN_COLOR_LIST
            )
        )
        binding.actHairColor.setAdapter(
            ArrayAdapter(
                this,
                R.layout.item_dropdown,
                Utils.AutoCompleteTextList.HAIR_COLOR_LIST
            )
        )
        binding.actEyeColor.setAdapter(
            ArrayAdapter(
                this,
                R.layout.item_dropdown,
                Utils.AutoCompleteTextList.EYE_COLOR_LIST
            )
        )
    }

    private fun validateFields() {
        binding.etChildNameAr.doAfterTextChanged {
            it?.let { isChildNameFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etAge.doAfterTextChanged {
            it?.let {
                if (it.toString().isNotEmpty() && it.toString().toInt() <= 20 && it.toString()
                        .toInt() != 0
                ) {
                    binding.tilAge.error = null
                    isChildAgeFieldActive = true
                } else {
                    binding.tilAge.error = getString(R.string.str_error_age)
                    isChildAgeFieldActive = false
                }
            }
            changeButtonStatus()
        }
        binding.etHeight.doAfterTextChanged {
            it?.let {
                if (it.toString().isNotEmpty() && it.toString().toInt() <= 150 && it.toString()
                        .toInt() >= 10
                ) {
                    binding.tilHeight.error = null
                    isChildHeightFieldActive = true
                } else {
                    binding.tilHeight.error = getString(R.string.str_error_height)
                    isChildHeightFieldActive = false
                }
            }
            changeButtonStatus()
        }
        binding.actSkinColor.doAfterTextChanged {
            it?.let { isChildSkinColorFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.actHairColor.doAfterTextChanged {
            it?.let { isChildHairColorFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.actEyeColor.doAfterTextChanged {
            it?.let { isChildEyeColorFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etChildMissedPlace.doAfterTextChanged {
            it?.let { isChildMissedPlaceFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etChildCurrentPlace.doAfterTextChanged {
            it?.let { isChildCurrentPlaceFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etReporterName.doAfterTextChanged {
            it?.let { isReporterNameFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etReporterPhone.doAfterTextChanged {
            it?.let {
                if (it.length < 11 || !it.startsWith("01")) {
                    binding.tilReporterPhone.error = getString(R.string.str_error_correct_phone)
                    isReporterPhoneFieldActive = false
                } else {
                    binding.tilReporterPhone.error = null
                    isReporterPhoneFieldActive = true
                }
            }
            changeButtonStatus()
        }
        binding.etReporterAddress.doAfterTextChanged {
            it?.let { isReporterAddressFieldActive = it.isNotEmpty() }
            changeButtonStatus()
        }
        binding.etReporterNationalId.doAfterTextChanged {
            it?.let {
                if (it.length < 14) {
                    binding.tilReporterNationalId.error = getString(R.string.str_error_national_id)
                    isReporterNationalIDFieldActive = false
                } else {
                    binding.tilReporterNationalId.error = null
                    isReporterNationalIDFieldActive = true
                }
            }
            changeButtonStatus()
        }
    }

    private fun changeButtonStatus() {
        binding.btnSubmitReport.isEnabled = isChildNameFieldActive && isChildAgeFieldActive &&
                isChildHeightFieldActive &&
                isChildSkinColorFieldActive &&
                isChildHairColorFieldActive &&
                isChildEyeColorFieldActive &&
                isImageAttached &&
                isChildMissedPlaceFieldActive &&
                isChildCurrentPlaceFieldActive &&
                isReporterNameFieldActive &&
                isReporterPhoneFieldActive &&
                isReporterAddressFieldActive &&
                isReporterNationalIDFieldActive
    }
}