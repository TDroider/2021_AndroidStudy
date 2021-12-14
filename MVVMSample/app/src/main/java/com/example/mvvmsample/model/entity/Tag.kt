package com.example.mvvmsample.model.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Tag(
    @JsonProperty("followers_count")
    val followersCount: Int,
    @JsonProperty("icon_url")
    val imageUrl: String?,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("items_count")
    val itemsCount: Int
)
