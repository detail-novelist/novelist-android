package dev.yjyoon.novelist.data.remote.api

import dev.yjyoon.novelist.data.remote.model.Novel
import dev.yjyoon.novelist.data.remote.model.NovelResult
import retrofit2.Response
import retrofit2.http.POST

interface NovelService {
    @POST("novel/generate")
    suspend fun generateNovel(novel: Novel): Response<NovelResult>
}
