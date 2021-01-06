package com.example.peacefirst.fragments

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peacefirst.R
import com.example.peacefirst.activities.FilterActivity
import com.example.peacefirst.activities.ReportChildActivity
import com.example.peacefirst.adapters.ChildrenAdapter
import com.example.peacefirst.apputils.DialogUtil
import com.example.peacefirst.base.BaseFragment
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.base.Result
import com.example.peacefirst.databinding.FragmentHomeBinding
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.request.ChildrenRequest
import com.example.peacefirst.models.response.ChildrenResponse
import com.example.peacefirst.viewmodles.HomeViewModel
import com.google.gson.Gson

class HomeFragment : BaseFragment() {
    companion object {
        const val EXTRA_FILTER_DATA = "filter_data"
    }

    private lateinit var binding: FragmentHomeBinding
    private val filterRequestCode = 20
    private val reportChildRequestCode = 30
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        requireActivity().title = getString(R.string.title_home_fragment)
        binding.rvHomeList.layoutManager = GridLayoutManager(activity, 2)

        val childrenAdapter = ChildrenAdapter(viewModel.childrenList,
            object : ChildrenAdapter.OnClickCardListener {
                override fun viewDetails(childId: Int) {
                    requireView().findNavController()
                        .navigate(
                            HomeFragmentDirections.actionHomeFragmentToChildDetailsActivity(
                                childId
                            )
                        )
                }
            })
        binding.rvHomeList.adapter = childrenAdapter
        binding.rvHomeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (viewModel.childrenList.size != 0 && ((viewModel.childrenList.size % 20) == 0))
                        viewModel.getAllChildren()
                    else
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.toast_no_more_data),
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        })

        if (viewModel.childrenList.isEmpty())
            viewModel.getAllChildren()
        viewModel.childrenLiveData.observe(viewLifecycleOwner, childrenResponseObserver)

        binding.fabReportChild.setOnClickListener {
            DialogUtil.createSimpleFlexibleMaterialDialog(
                requireActivity(), getString(R.string.str_report_type),
                getString(
                    R.string.alert_report_type_message
                ),
                ModelEnums.ReportType.Missing.name,
                { _, _ ->
                    val intent = Intent(requireActivity(), ReportChildActivity::class.java)
                    intent.putExtra(
                        ReportChildActivity.EXTRA_REPORT_TYPE,
                        ModelEnums.ReportType.Missing
                    )
                    startActivityForResult(intent, reportChildRequestCode)
                },
                ModelEnums.ReportType.Founded.name,
                { _, _ ->
                    val intent = Intent(requireActivity(), ReportChildActivity::class.java)
                    intent.putExtra(
                        ReportChildActivity.EXTRA_REPORT_TYPE,
                        ModelEnums.ReportType.Founded
                    )
                    startActivityForResult(intent, reportChildRequestCode)
                },
                getString(R.string.str_cancel),
                { dialog, _ ->
                    dialog.dismiss()
                }, false
            ).show()

        }

        viewModel.childrenRequestLD.observe(viewLifecycleOwner, {
            handleFiltersStatus(it)
        })

//        binding.cgFilters.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
        binding.chipFilterReportType.setOnClickListener { viewModel.clearReportTypeFilter() }
        binding.chipFilterGender.setOnClickListener { viewModel.clearGenderFilter() }
        binding.chipFilterAge.setOnClickListener { viewModel.clearAgeFilter() }
        binding.chipFilterHeight.setOnClickListener { viewModel.clearHeightFilter() }
        binding.chipFilterSkinColor.setOnClickListener { viewModel.clearSkinColorFilter() }
        binding.chipFilterHairColor.setOnClickListener { viewModel.clearHairColorFilter() }
        binding.chipFilterEyeColor.setOnClickListener { viewModel.clearEyeColorFilter() }
//            }
//        }

        return binding.root
    }

    private val childrenResponseObserver =
        Observer<Result<BaseResponse<MutableList<ChildrenResponse>>>> {
            when (it) {
                is Result.Success -> {
                    if (it.response.data?.isEmpty() == true) {
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.toast_no_more_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    binding.rvHomeList.adapter?.notifyDataSetChanged()

                }
                is Result.Error -> showError(it.exception.msg)
                Result.Loading -> showFullProgressDialog()
                Result.Complete -> hideFullProgressDialog()
            }
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_filter -> {
                val intent = Intent(activity, FilterActivity::class.java)
                startActivityForResult(intent, filterRequestCode)
            }
            R.id.menu_item_reset_filter -> {
                viewModel.resetFilters()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == filterRequestCode) {
                val childrenRequest: ChildrenRequest? = data?.getParcelableExtra(EXTRA_FILTER_DATA)
                childrenRequest?.apply {
                    viewModel.setNewFilters(this)
                }
            } else if (requestCode == reportChildRequestCode) {
                viewModel.resetFilters()
            }
        }
    }

    private fun handleFiltersStatus(childrenRequest: ChildrenRequest) {
        if (childrenRequest.reportType == null &&
            childrenRequest.gender == null &&
            childrenRequest.minAge == null &&
            childrenRequest.minHeight == null &&
            childrenRequest.skinColor == null &&
            childrenRequest.hairColor == null &&
            childrenRequest.eyeColor == null
        ) {
            binding.llFiltersHolder.visibility = View.GONE
        } else {
            binding.llFiltersHolder.visibility = View.VISIBLE

            if (childrenRequest.reportType == null) {
                binding.chipFilterReportType.visibility = View.GONE
            } else {
                binding.chipFilterReportType.visibility = View.VISIBLE
                binding.chipFilterReportType.text = childrenRequest.reportType
            }

            if (childrenRequest.gender == null) {
                binding.chipFilterGender.visibility = View.GONE
            } else {
                binding.chipFilterGender.visibility = View.VISIBLE
                binding.chipFilterGender.text = childrenRequest.gender
            }

            if (childrenRequest.minAge == null) {
                binding.chipFilterAge.visibility = View.GONE
            } else {
                binding.chipFilterAge.visibility = View.VISIBLE
                binding.chipFilterAge.text = getString(
                    R.string.str_age_range,
                    childrenRequest.minAge.toString(),
                    childrenRequest.maxAge.toString()
                )
            }

            if (childrenRequest.minHeight == null) {
                binding.chipFilterHeight.visibility = View.GONE
            } else {
                binding.chipFilterHeight.visibility = View.VISIBLE
                binding.chipFilterHeight.text = getString(
                    R.string.str_height_range,
                    childrenRequest.minHeight.toString(),
                    childrenRequest.maxHeight.toString()
                )
            }

            if (childrenRequest.skinColor == null) {
                binding.chipFilterSkinColor.visibility = View.GONE
            } else {
                binding.chipFilterSkinColor.visibility = View.VISIBLE
                binding.chipFilterSkinColor.text =
                    getString(R.string.skin_color_value, childrenRequest.skinColor)
            }

            if (childrenRequest.hairColor == null) {
                binding.chipFilterHairColor.visibility = View.GONE
            } else {
                binding.chipFilterHairColor.visibility = View.VISIBLE
                binding.chipFilterHairColor.text =
                    getString(R.string.hair_color_value, childrenRequest.hairColor)
            }

            if (childrenRequest.eyeColor == null) {
                binding.chipFilterEyeColor.visibility = View.GONE
            } else {
                binding.chipFilterEyeColor.visibility = View.VISIBLE
                binding.chipFilterEyeColor.text =
                    getString(R.string.eye_color_value, childrenRequest.eyeColor)
            }
        }
    }
}