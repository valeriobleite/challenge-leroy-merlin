package pt.leite.valerio.challengeleroymerlin.di.useCases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pt.leite.valerio.challengeleroymerlin.di.repositories.RepositoriesModule
import pt.leite.valerio.domain.repositories.TaskRepository
import pt.leite.valerio.domain.useCases.tasks.DeleteTaskUseCase
import pt.leite.valerio.domain.useCases.tasks.GetTasksUseCase
import pt.leite.valerio.domain.useCases.tasks.InsertTaskUseCase

@Module(includes = [RepositoriesModule::class])
@InstallIn(ViewModelComponent::class)
class GetTasksUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetTasksUseCase(taskRepository: TaskRepository) = GetTasksUseCase(taskRepository)

    @ViewModelScoped
    @Provides
    fun provideInsertTaskUseCase(taskRepository: TaskRepository) = InsertTaskUseCase(taskRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteTaskUseCase(taskRepository: TaskRepository) = DeleteTaskUseCase(taskRepository)
}