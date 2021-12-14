package com.example.mvvmsample.model

import com.example.mvvmsample.domain.QiitaTag

class QiitaTagListRepository {
    suspend fun fetch(sortType: String): List<QiitaTag> {
        return HttpClient.buildService().getTags(sortType).map {
            QiitaTag(
                it.id,
                it.imageUrl.orEmpty(),
                it.itemsCount
            )
        }
    }
}
