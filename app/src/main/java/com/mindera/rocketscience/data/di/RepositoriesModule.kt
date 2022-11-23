package com.mindera.rocketscience.data.di

import com.mindera.rocketscience.data.Endpoints
import com.mindera.rocketscience.data.repositories.CompanyRepositoryImpl
import com.mindera.rocketscience.data.repositories.LaunchRepositoryImpl
import com.mindera.rocketscience.domain.repositories.CompanyRepository
import com.mindera.rocketscience.domain.repositories.LaunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

	@Singleton
	@Provides
	fun provideLaunchRemoteRepositoryImpl(endpoints: Endpoints): LaunchRepository =
		LaunchRepositoryImpl(endpoints)

	@Singleton
	@Provides
	fun provideCompanyRemoteRepositoryImpl(endpoints: Endpoints): CompanyRepository =
		CompanyRepositoryImpl(endpoints)

}