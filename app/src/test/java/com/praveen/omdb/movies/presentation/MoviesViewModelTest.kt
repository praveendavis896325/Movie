package com.praveen.omdb.movies.presentation

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.IOException
import com.praveen.omdb.detail.data.DetailRating
import com.praveen.omdb.detail.data.DetailResponse
import com.praveen.omdb.movies.CoroutineTestingRule
import com.praveen.omdb.movies.data.Movie
import com.praveen.omdb.movies.data.MovieRepo
import com.praveen.omdb.movies.data.MovieResponse
import com.praveen.omdb.movies.domain.MovieSourceFactory
import com.praveen.omdb.utils.commons.TestDispatcher
import com.praveen.omdb.utils.network.ResultType
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MoviesViewModelTest
{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestingRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesRepo: MovieRepo
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var movieSourceFactory: MovieSourceFactory

    private val mockMovieEntity = Movie("tt0486592", "Guardians of the Galaxy", "https://m.media-amazon.com/images/M/MV5BMjgyOTkyOTgwMV5BMl5BanBnXkFtZTgwNTM1OTkwMzE@._V1_SX300.jpg", "movie","2009")
    private val mockMovieRemote =  Movie("tt0486592", "Guardians of the Galaxy", "https://m.media-amazon.com/images/M/MV5BMjgyOTkyOTgwMV5BMl5BanBnXkFtZTgwNTM1OTkwMzE@._V1_SX300.jpgjpg", "movie","2009")

    private val mockMovieEntityList = listOf(mockMovieEntity, mockMovieEntity)
    private val mockMovieResponse = MovieResponse(totalResults = "2",
        Search = arrayListOf(mockMovieRemote, mockMovieRemote))

    private val mockDetails = DetailRating("Internet Movie Database", "3.0/10")
    private val mockDetailsResponse = DetailResponse("Guardians","2009","24 Jun 2009","Horror","Drew Maxwell","Drew Maxwell","Chris Bell, Benjamin Budd, Tylan Canady","Twilight Cove, a small forgotten town, is besieged by hideous creatures summoned into our dimension. It's only a matter of time before the army of creatures attacks the rest of civilization and wreaks havoc upon the world. At dusk, a beat-up truck containing a rag-tag team of hardened mercenaries rolls into town, and this is not the first time they've been called in to exterminate; they are part of a secret society that has been operating outside of the government and the public eye for thousands of years. Their mission is to keep our world safe from evil-of our own creation and from beyond.","English","United States", "N/A","https://m.media-amazon.com/images/M/MV5BMjgyOTkyOTgwMV5BMl5BanBnXkFtZTgwNTM1OTkwMzE@._V1_SX300.jpg",Ratings = arrayListOf(mockDetails))

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesRepo = mock()
        movieSourceFactory = MovieSourceFactory()
        moviesViewModel = MoviesViewModel(
            moviesRepo,
            movieSourceFactory,
            TestDispatcher()
        )
    }


    @Test
    fun testingGetMoviesRemote_success() {
        val expected = mockMovieEntityList
        runBlockingTest {
            whenever(moviesRepo.getPopularMovies(0)).thenReturn(ResultType.Success(mockMovieResponse))
        }
        moviesViewModel.apply {
            uiState.observeForever {
                val actual = (it as MoviesViewState.FetchingMoviesSuccess).movies
                assert(expected == actual)
            }

        }
    }


    @Test
    fun testingGetMoviesRemote_failure() {
        runBlockingTest {
            whenever(moviesRepo.getPopularMovies(1)).thenReturn(ResultType.Error(IOException()))
            moviesViewModel.apply {
                uiState.observeForever {
                    assert(it is MoviesViewState.FetchingMoviesError)
                }
             //   verify(moviesRepo, times(1)).getPopularMovies(1)
            }
        }
    }

}