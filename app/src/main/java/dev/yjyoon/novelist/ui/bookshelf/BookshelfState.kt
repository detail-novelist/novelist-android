package dev.yjyoon.novelist.ui.bookshelf

import dev.yjyoon.novelist.data.db.entity.BookEntity

sealed class BookshelfState {
    object Loading : BookshelfState()
    object Empty : BookshelfState()
    data class Success(val data: List<BookEntity>) : BookshelfState()
    data class Error(val error: Throwable?) : BookshelfState()
}