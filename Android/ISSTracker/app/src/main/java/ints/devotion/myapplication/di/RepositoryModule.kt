package ints.devotion.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ints.devotion.myapplication.data.network.IssService
import ints.devotion.myapplication.data.repository.IssRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMapRepository(
        issService: IssService
    ): IssRepository {
        return IssRepository(issService = issService)
    }
}