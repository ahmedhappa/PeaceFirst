package com.example.peacefirst.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.example.peacefirst.R
import com.example.peacefirst.apputils.Utils
import com.example.peacefirst.base.BaseActivity
import com.example.peacefirst.databinding.ActivityFilterBinding
import com.example.peacefirst.fragments.HomeFragment
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.request.ChildrenRequest
import com.example.peacefirst.viewmodles.FilterViewModel
import com.google.gson.Gson

class FilterActivity : BaseActivity() {
    private lateinit var binding: ActivityFilterBinding

    private lateinit var ageSliderDefaultValue: IntArray
    private lateinit var heightSliderDefaultValue: IntArray
    private val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.title_filter_activity)
        ageSliderDefaultValue =
            resources.getIntArray(R.array.initial_ranged_slider_values_age)
        heightSliderDefaultValue =
            resources.getIntArray(R.array.initial_ranged_slider_values_height)

        setAutoCompleteTextData()
        setDefaultFilterData()

        binding.rsFilterAge.addOnChangeListener { slider, _, _ ->
            viewModel.childrenRequest.minAge = slider.values[0].toInt().toShort()
            viewModel.childrenRequest.maxAge =
                if (slider.values[1].toInt() != 0) slider.values[1].toInt().toShort() else null
            binding.tvAgeHeader.text = getString(
                R.string.str_age_range,
                slider.values[0].toInt().toString(),
                slider.values[1].toInt().toString()
            )
        }

        binding.rsFilterHeight.addOnChangeListener { slider, _, _ ->
            viewModel.childrenRequest.minHeight = slider.values[0].toInt().toShort()
            viewModel.childrenRequest.maxHeight =
                if (slider.values[1].toInt() != 0) slider.values[1].toInt().toShort() else null
            binding.tvHeightHeader.text = getString(
                R.string.str_height_range,
                slider.values[0].toInt().toString(),
                slider.values[1].toInt().toString()
            )
        }

        binding.btnApplyFilters.setOnClickListener {
            if (binding.rgFilterGender.checkedRadioButtonId != -1) {
                val checkedGenderRadio: RadioButton =
                    findViewById(binding.rgFilterGender.checkedRadioButtonId)
                viewModel.childrenRequest.gender = checkedGenderRadio.text.toString()
            }

            if (binding.rgFilterReportType.checkedRadioButtonId != -1) {
                val checkedReportTypeRadio: RadioButton =
                    findViewById(binding.rgFilterReportType.checkedRadioButtonId)
                viewModel.childrenRequest.reportType = checkedReportTypeRadio.text.toString()
            }

            viewModel.childrenRequest.skinColor = if (binding.actSkinColor.text.isNotEmpty()) {
                binding.actSkinColor.text.toString()
            } else {
                null
            }
            viewModel.childrenRequest.hairColor = if (binding.actHairColor.text.isNotEmpty()) {
                binding.actHairColor.text.toString()
            } else {
                null
            }
            viewModel.childrenRequest.eyeColor = if (binding.actEyeColor.text.isNotEmpty()) {
                binding.actEyeColor.text.toString()
            } else {
                null
            }

            if (viewModel.isFilterInserted()) {
                val intent = Intent()
                intent.putExtra(HomeFragment.EXTRA_FILTER_DATA, viewModel.childrenRequest)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    this@FilterActivity,
                    getString(R.string.no_filter_selected),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnReset.setOnClickListener {
            setDefaultFilterData()
            binding.rsFilterAge.values = listOf(
                0f,
                0f
            )
            binding.rsFilterHeight.values = listOf(
                0f,
                0f
            )

            binding.rgFilterReportType.clearCheck()
            binding.rgFilterGender.clearCheck()
            binding.actSkinColor.text = null
            binding.actEyeColor.text = null
            binding.actHairColor.text = null
        }

    }

    private fun setDefaultFilterData() {
        binding.tvAgeHeader.text = getString(
            R.string.str_age_range,
            ageSliderDefaultValue[0].toString(),
            ageSliderDefaultValue[1].toString()
        )
        binding.tvHeightHeader.text = getString(
            R.string.str_height_range,
            heightSliderDefaultValue[0].toString(),
            heightSliderDefaultValue[1].toString()
        )
        viewModel.resetData()
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
}