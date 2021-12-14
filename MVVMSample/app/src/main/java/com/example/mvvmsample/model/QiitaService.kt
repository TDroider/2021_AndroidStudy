package com.example.mvvmsample.model

import com.example.mvvmsample.model.entity.Tag
import retrofit2.http.GET

interface QiitaService {
    @GET("/api/v2/tags")
    open suspend fun getTags(): List<Tag>
}
