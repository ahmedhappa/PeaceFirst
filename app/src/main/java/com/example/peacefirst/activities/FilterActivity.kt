package com.example.peacefirst.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.example.peacefirst.R
import com.example.peacefirst.apputils.Utils
import com.example.peacefirst.base.BaseActivity
import com.example.peacefirst.databinding.ActivityFilterBinding
import com.example.peacefirst.fragments.HomeFragment
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.request.ChildrenRequest
import com.google.gson.Gson

class FilterActivity : BaseActivity() {
    private lateinit var binding: ActivityFilterBinding
    private lateinit var childrenRequest: ChildrenRequest
    private lateinit var ageSliderDefaultValue: IntArray
    private lateinit var heightSliderDefaultValue: IntArray

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
            childrenRequest.minAge = slider.values[0].toInt().toShort()
            childrenRequest.maxAge = slider.values[1].toInt().toShort()
            binding.tvAgeHeader.text = getString(
                R.string.str_age_range,
                slider.values[0].toInt().toString(),
                slider.values[1].toInt().toString()
            )
        }

        binding.rsFilterHeight.addOnChangeListener { slider, _, _ ->
            childrenRequest.minHeight = slider.values[0].toInt().toShort()
            childrenRequest.maxHeight = slider.values[1].toInt().toShort()
            binding.tvHeightHeader.text = getString(
                R.string.str_height_range,
                slider.values[0].toInt().toString(),
                slider.values[1].toInt().toString()
            )
        }

        binding.btnApplyFilters.setOnClickListener {
            val checkedGenderRadio: RadioButton =
                findViewById(binding.rgFilterGender.checkedRadioButtonId)
            childrenRequest.gender = checkedGenderRadio.text.toString()

            val checkedReportTypeRadio: RadioButton =
                findViewById(binding.rgFilterReportType.checkedRadioButtonId)
            childrenRequest.reportType = checkedReportTypeRadio.text.toString()

            childrenRequest.skinColor = if (binding.actSkinColor.text.isNotEmpty()) {
                binding.actSkinColor.text.toString()
            } else {
                null
            }
            childrenRequest.hairColor = if (binding.actHairColor.text.isNotEmpty()) {
                binding.actHairColor.text.toString()
            } else {
                null
            }
            childrenRequest.eyeColor = if (binding.actEyeColor.text.isNotEmpty()) {
                binding.actEyeColor.text.toString()
            } else {
                null
            }

            val intent = Intent()
            intent.putExtra(HomeFragment.EXTRA_FILTER_DATA, childrenRequest)
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.btnReset.setOnClickListener {
            setDefaultFilterData()
            binding.rsFilterAge.values = listOf(
                ageSliderDefaultValue[0].toFloat(),
                ageSliderDefaultValue[1].toFloat()
            )
            binding.rsFilterHeight.values = listOf(
                heightSliderDefaultValue[0].toFloat(),
                heightSliderDefaultValue[1].toFloat()
            )

            binding.rgFilterReportType.check(binding.rbFilterMissing.id)
            binding.rgFilterGender.check(binding.rbFilterMale.id)
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

        childrenRequest = ChildrenRequest(
            getString(R.string.str_missing),
            getString(R.string.str_male),
            ageSliderDefaultValue[0].toShort(), ageSliderDefaultValue[1].toShort(),
            heightSliderDefaultValue[0].toShort(), heightSliderDefaultValue[1].toShort(),
            null, null, null
        )
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