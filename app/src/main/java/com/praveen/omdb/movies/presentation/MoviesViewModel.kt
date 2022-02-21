package com.praveen.omdb.movies.presentation

import androidx.lifecycle.*
import androidx.paging.*
import com.praveen.omdb.base.BaseViewModel
import com.praveen.omdb.detail.presentation.DetailViewState
import com.praveen.omdb.movies.data.Movie
import com.praveen.omdb.movies.data.MovieRepo
import com.praveen.omdb.movies.domain.MovieSourceFactory
import com.praveen.omdb.utils.commons.CoroutineDispatcher
import com.praveen.omdb.utils.network.ResultType
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val sourceFactory: MovieSourceFactory,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
    }

    private var movie: Movie? = null
    private val searchTextLiveData: MutableLiveData<String> = MutableLiveData("")
    var movies: LiveData<PagingData<Movie>> = MediatorLiveData()

    init {
        movies =  Transformations.switchMap(searchTextLiveData) { input: String ->
            return@switchMap getMoviesStream(input)
        }
    }

    private fun getMoviesStream(input: String): LiveData<PagingData<Movie>> {
        val result = viewModelScope.async { Pager(PagingConfig(PAGE_SIZE)) { sourceFactory.getSource(input) } }
        return runBlocking { result.await().liveData.cachedIn(viewModelScope)}
    }


    fun fetchMovieDetails(movieTitle: String) {
        viewModelScope.launch(dispatcher.IO) {
            val ratingsList = movieRepo.fetchMovieDetails(movieTitle)
            withContext(dispatcher.Main) {
                if (ratingsList is ResultType.Success) updateViewState(DetailViewState.DetailsFetchedSuccess(ratingsList.data))
                else updateViewState(DetailViewState.DetailsFetchedError)
            }
        }
    }


    fun setSelectedMovie(movie: Movie) {
        this.movie = movie
    }

    fun getSelectedMovie(): Movie? {
        return movie
    }

    fun getSearchLiveData(): MutableLiveData<String> {
        return searchTextLiveData
    }

}