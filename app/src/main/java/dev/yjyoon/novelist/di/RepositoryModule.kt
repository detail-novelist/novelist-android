package dev.yjyoon.novelist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.novelist.repository.BookRepository
import dev.yjyoon.novelist.repository.BookRepositoryImpl
import dev.yjyoon.novelist.repository.CoverRepository
import dev.yjyoon.novelist.repository.CoverRepositoryImpl
import dev.yjyoon.novelist.repository.GenreRepository
import dev.yjyoon.novelist.repository.GenreRepositoryImpl
import dev.yjyoon.novelist.repository.NovelRepository
import dev.yjyoon.novelist.repository.NovelRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun genreRepository(repository: GenreRepositoryImpl): GenreRepository

    @Binds
    @Singleton
    abstract fun coverRepository(repository: CoverRepositoryImpl): CoverRepository

    @Binds
    @Singleton
    abstract fun bookRepository(repository: BookRepositoryImpl): BookRepository

    @Binds
    @Singleton
    abstract fun novelRepository(repository: NovelRepositoryImpl): NovelRepository
}
