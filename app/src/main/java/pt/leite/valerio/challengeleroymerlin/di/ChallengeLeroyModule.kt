package pt.leite.valerio.challengeleroymerlin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pt.leite.valerio.data.local.ChallengeLeroyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ChallengeLeroyModule {
    companion object {
        @Provides
        @Singleton
        fun provideChallengeLeroyDatabase(
            @ApplicationContext context: Context
        ): ChallengeLeroyDatabase = ChallengeLeroyDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesTaskDao(database: ChallengeLeroyDatabase) = database.taskDAO
}