package dev.yjyoon.novelist.repository

import dev.yjyoon.novelist.data.remote.api.NovelService
import dev.yjyoon.novelist.data.remote.model.Novel
import dev.yjyoon.novelist.data.remote.model.NovelResult
import retrofit2.Response
import javax.inject.Inject

interface NovelRepository {
    suspend fun generateNovel(novel: Novel): Response<NovelResult>
}

class NovelRepositoryImpl @Inject constructor(
    private val novelService: NovelService
) : NovelRepository {
    override suspend fun generateNovel(novel: Novel): Response<NovelResult> =
        novelService.generateNovel(novel)
}
