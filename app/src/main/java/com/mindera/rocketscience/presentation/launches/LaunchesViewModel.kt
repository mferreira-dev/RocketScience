package com.mindera.rocketscience.presentation.launches

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.mindera.rocketscience.R
import com.mindera.rocketscience.data.utils.Event
import com.mindera.rocketscience.domain.entities.Company
import com.mindera.rocketscience.domain.entities.Launch
import com.mindera.rocketscience.domain.entities.LaunchOrder
import com.mindera.rocketscience.domain.usecases.CompanyUseCase
import com.mindera.rocketscience.domain.usecases.LaunchUseCase
import com.mindera.rocketscience.utils.displayToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel
@Inject
constructor(
	private val app: Application,
	private val launchUseCase: LaunchUseCase,
	private val companyUseCase: CompanyUseCase
) : AndroidViewModel(app) {

	private var _order = MutableLiveData<LaunchOrder>()
	val launches = _order.switchMap {
		launchUseCase.getLaunches(order = _order.value).cachedIn(viewModelScope)
	}

	private val _companyInfo = MutableLiveData<Event<Company>>()
	val companyInfo: LiveData<Event<Company>>
		get() = _companyInfo

	init {
		// Start observer.
		sortLaunches(LaunchOrder.DESC)
		getCompanyInfo()
	}

	private fun getCompanyInfo() {
		viewModelScope.launch {
			companyUseCase.getCompanyInfo().either(
				success = {
					_companyInfo.value = Event(it)
				},
				failure = {
					displayToast(
						app.applicationContext,
						app.applicationContext.getString(R.string.generic_error)
					)
				}
			)
		}
	}

	fun sortLaunches(order: LaunchOrder) {
		_order.value = order
	}

}