package com.mindera.rocketscience.presentation.container

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mindera.rocketscience.R
import com.mindera.rocketscience.databinding.ActivityContainerBinding
import com.mindera.rocketscience.presentation.base.BaseActivity

class ContainerActivity : BaseActivity() {

	private lateinit var binding: ActivityContainerBinding
	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityContainerBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setupUI()
	}

	override fun setupUI() {
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

		navController = navHostFragment.findNavController()

		// Up button won't be displayed on these fragments.
		appBarConfiguration = AppBarConfiguration(setOf(R.id.launchesFragment))

		setupActionBarWithNavController(navController, appBarConfiguration)
	}

}