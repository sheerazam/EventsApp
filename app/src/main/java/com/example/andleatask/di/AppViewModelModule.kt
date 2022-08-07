package com.example.andleatask.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.example.andleatask.ui.viewmodel.ListScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface AppViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListScreenViewModel::class)
    fun listScreenViewModelFactory(factory: ListScreenViewModel.Factory): AssistedViewModelFactory<*, *>
}