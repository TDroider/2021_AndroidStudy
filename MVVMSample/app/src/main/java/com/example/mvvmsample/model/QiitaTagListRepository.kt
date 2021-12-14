package com.example.mvvmsample.model

import com.example.mvvmsample.domain.QiitaTag

class QiitaTagListRepository {
    suspend fun fetch(): List<QiitaTag> {
        return HttpClient.buildService().getTags().map {
            QiitaTag(
                it.id,
                it.imageUrl.orEmpty(),
                it.itemsCount
            )
        }
    }
}
