package pt.leite.valerio.challengeleroymerlin.di.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pt.leite.valerio.challengeleroymerlin.repositories.TaskRepositoryImpl
import pt.leite.valerio.domain.repositories.TaskRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @ViewModelScoped
    @Binds
    abstract fun bindTaskRepository(taskRepository: TaskRepositoryImpl): TaskRepository
}