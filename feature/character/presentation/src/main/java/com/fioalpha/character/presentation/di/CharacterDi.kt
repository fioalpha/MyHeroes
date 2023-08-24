package com.fioalpha.character.presentation.di

import com.fioalpha.character.presentation.CharacterViewModel
import com.fioalpha.feature.character.data.CharacterRepositoryFactory
import com.fioalpha.feature.character.data.CharacterRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    viewModel {
        CharacterViewModel(
            repository = CharacterRepositoryFactory.create()
        )
    }
}