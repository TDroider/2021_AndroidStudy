package com.example.mvvmsample.controller

import com.example.mvvmsample.model.QiitaTagListRepository

class MainActivityController {

    private val repository = QiitaTagListRepository()

    suspend fun fetch(sortType: String) = repository.fetch(sortType)
}
