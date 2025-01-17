package dev.yjyoon.novelist.ui.cover.generation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.novelist.data.db.entity.BookEntity
import dev.yjyoon.novelist.data.remote.model.Cover
import dev.yjyoon.novelist.data.remote.model.Genre
import dev.yjyoon.novelist.exception.NonexistentTagException
import dev.yjyoon.novelist.exception.TagAlreadyExistsException
import dev.yjyoon.novelist.repository.BookRepository
import dev.yjyoon.novelist.repository.CoverRepository
import dev.yjyoon.novelist.repository.GenreRepository
import dev.yjyoon.novelist.repository.NovelRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateCoverViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val coverRepository: CoverRepository,
    private val bookRepository: BookRepository,
    private val novelRepository: NovelRepository
) : ViewModel() {

    private var _uiState by mutableStateOf<GenerateCoverUiState>(GenerateCoverUiState.Waiting)
    val uiState: GenerateCoverUiState get() = _uiState

    val genres: LiveData<List<Genre>> = liveData {
        emit(genreRepository.getGenres())
    }

    var subGenres: LiveData<List<Genre>>? = null

    var bookTitle by mutableStateOf("")
    var bookAuthor by mutableStateOf("")
    var bookGenre by mutableStateOf<Genre?>(null)
    var bookSubGenre by mutableStateOf<Genre?>(null)
    var bookTags = mutableStateListOf<String>()
    var bookPublisher by mutableStateOf("")

    lateinit var covers: List<Cover>

    var novelResult by mutableStateOf("")

    fun editTitle(title: String) {
        bookTitle = title
    }

    fun editAuthor(author: String) {
        bookAuthor = author
    }

    fun editGenre(genre: Genre) {
        bookGenre = genre
        bookSubGenre = null
        subGenres = null
    }

    fun editSubGenre(subGenre: Genre) {
        bookSubGenre = subGenre
    }

    fun loadSubGenres(): LiveData<List<Genre>> {
        if (subGenres != null) return subGenres!!

        viewModelScope.launch {
            subGenres = genreRepository.getSubGenres(bookGenre!!.id).asLiveData()
        }

        return subGenres!!
    }

    fun addTag(tag: String) {
        if (bookTags.contains(tag.trim())) throw TagAlreadyExistsException()
        bookTags.add(tag.trim())
    }

    fun deleteTag(tag: String) {
        if (!bookTags.contains(tag.trim())) throw NonexistentTagException()
        bookTags.remove(tag.trim())
    }

    fun isInvalidTag(tag: String): Boolean {
        if (tag.trim().isEmpty()) return true
        if (bookTags.contains(tag.trim())) return true
        return false
    }

    fun isNotFullTags(): Boolean {
        return bookTags.size < 5
    }

    fun editPublisher(publisher: String) {
        bookPublisher = publisher
    }

    fun isValidInput(step: Int): Boolean =
        when (step) {
            0 -> bookTitle.trim() != ""
            1 -> bookAuthor.trim() != ""
            2 -> true
            else -> false
        }

    fun generateCover(context: Context) {
        _uiState = GenerateCoverUiState.Done
        /*
        _uiState = GenerateCoverUiState.Generating

        val novel = Novel(bookTitle, bookAuthor, bookPublisher)

        viewModelScope.launch {
            val response = novelRepository.generateNovel(novel)
            if (response.isSuccessful && response.body() != null) {
                novelResult = response.body()!!.novel
                withContext(Dispatchers.Main) {
                    _uiState = GenerateCoverUiState.Done
                }
            } else {
                withContext(Dispatchers.Main) {
                    _uiState = GenerateCoverUiState.Fail
                }
            }
        }
         */
    }

    private fun coversToBookEntity(): BookEntity {
        return covers[0].let {
            BookEntity(
                bookId = it.bookId,
                title = it.title,
                author = it.author,
                genre = it.genre,
                subGenre = it.subGenre,
                tags = it.tags,
                coverUrls = covers.map { cover -> cover.url },
                createdDate = it.createdDate
            )
        }
    }
}


