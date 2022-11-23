package com.mindera.rocketscience.presentation.base

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
	abstract fun setupUI()
	abstract fun setupButtons()
	abstract fun setupObservers()
}