package com.mindera.rocketscience.presentation.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mindera.rocketscience.R
import com.mindera.rocketscience.databinding.FragmentLaunchesBinding
import com.mindera.rocketscience.domain.entities.LaunchOrder
import com.mindera.rocketscience.presentation.base.BaseFragment
import com.mindera.rocketscience.utils.convertMoney

class LaunchesFragment : BaseFragment() {

	private var _binding: FragmentLaunchesBinding? = null
	private val binding get() = _binding!!

	private lateinit var fragmentViewModel: LaunchesViewModel
	private lateinit var launchPagingAdapter: LaunchPagingAdapter

	private var fabsVisible = false

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentLaunchesBinding.inflate(inflater, container, false)
		fragmentViewModel = ViewModelProvider(requireActivity())[LaunchesViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupUI()
		setupButtons()
		setupObservers()
	}

	override fun setupUI() {
		launchPagingAdapter = LaunchPagingAdapter(binding.launchesWebView)

		binding.launchesList.apply {
			setHasFixedSize(true)
			adapter = launchPagingAdapter
		}
	}

	override fun setupButtons() {
		binding.apply {
			launchesMasterFab.shrink()

			launchesMasterFab.setOnClickListener {
				fabsVisible = if (fabsVisible) hideFabs() else showFabs()
			}
		}

		binding.launchesScrollTop.setOnClickListener { binding.launchesList.smoothScrollToPosition(0) }
		binding.launchesAscFab.setOnClickListener { fragmentViewModel.sortLaunches(LaunchOrder.ASC) }
		binding.launchesDescFab.setOnClickListener { fragmentViewModel.sortLaunches(LaunchOrder.DESC) }
	}

	override fun setupObservers() {
		fragmentViewModel.companyInfo.observe(viewLifecycleOwner) {
			if (it.hasBeenHandled)
				return@observe

			it.peekContent().apply {
				binding.lblCompanyDesc.text =
					getString(
						R.string.company_desc,
						name,
						founder,
						founded,
						employees,
						launchSites,
						convertMoney(valuation)
					)
			}
		}

		fragmentViewModel.launches.observe(viewLifecycleOwner) {
			launchPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun hideFabs(): Boolean {
		binding.apply {
			launchesMasterFab.shrink()
			launchesScrollTop.hide()
			launchesAscFab.hide()
			launchesDescFab.hide()
		}
		return false
	}

	private fun showFabs(): Boolean {
		binding.apply {
			launchesMasterFab.extend()
			launchesScrollTop.show()
			launchesAscFab.show()
			launchesDescFab.show()
		}
		return true
	}

}