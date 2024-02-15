package ints.devotion.navigator.di

import ints.devotion.navigator.ComposeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ints.devotion.navigator.IssTrackerComposeNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun provideComposeNavigator(issTrackerNavigator: IssTrackerComposeNavigator): ComposeNavigator
}
