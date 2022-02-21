package com.praveen.omdb.main.presentation

import android.content.Intent
import android.os.SystemClock
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.praveen.omdb.OmdbMovieApp
import com.praveen.omdb.R
import com.praveen.omdb.movies.presentation.MovieAdapter
import com.praveen.omdb.utils.di.DaggerTestAppComponent
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var mockServer: MockWebServer

    private lateinit var app: OmdbMovieApp

    private lateinit var intent: Intent

    @Before
    fun setup() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        app = instrumentation.targetContext.applicationContext as OmdbMovieApp

        val appInjector = DaggerTestAppComponent.builder()
                .application(app)
                .build()
        appInjector.inject(app)
        mockServer = appInjector.getMockWebServer()

        intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(
                "{\"Search\":[{\"Title\":\"Guardians of the Galaxy\",\"Year\":\"2014\",\"imdbID\":\"tt2015381\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTAwMjU5OTgxNjZeQTJeQWpwZ15BbWU4MDUxNDYxODEx._V1_SX300.jpg\"},{\"Title\":\"Guardians of the Galaxy Vol. 2\",\"Year\":\"2017\",\"imdbID\":\"tt3896198\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg\"},{\"Title\":\"Rise of the Guardians\",\"Year\":\"2012\",\"imdbID\":\"tt1446192\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTkzMjgwMDg1M15BMl5BanBnXkFtZTcwMTgzNTI1OA@@._V1_SX300.jpg\"},{\"Title\":\"Legend of the Guardians: The Owls of Ga'Hoole\",\"Year\":\"2010\",\"imdbID\":\"tt1219342\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjE0NjA5OTA4N15BMl5BanBnXkFtZTcwODA3MTA3Mw@@._V1_SX300.jpg\"},{\"Title\":\"The Guardians\",\"Year\":\"2017\",\"imdbID\":\"tt4600952\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BYzgxY2NkZGYtOGI4NC00MTc4LTlkY2QtNmRjOTU1NDI0NGQ1XkEyXkFqcGdeQXVyNTc4MjczMTM@._V1_SX300.jpg\"},{\"Title\":\"7 Guardians of the Tomb\",\"Year\":\"2018\",\"imdbID\":\"tt4915672\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZjMzZDI4YmEtZDIwNS00YWQ5LTkzN2UtMzJiMTliNjZiMzRjXkEyXkFqcGdeQXVyMzQwMTY2Nzk@._V1_SX300.jpg\"},{\"Title\":\"Naruto the Movie 3: Guardians of the Crescent Moon Kingdom\",\"Year\":\"2006\",\"imdbID\":\"tt1071815\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjk2ZWIzOTYtMGUxMC00MzdhLWI3ZGMtZGJhNzZmMDYxYjJlXkEyXkFqcGdeQXVyMjQ3NTQ4MjQ@._V1_SX300.jpg\"},{\"Title\":\"The Guardians\",\"Year\":\"2017\",\"imdbID\":\"tt6213362\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjdkZmU1MzEtNjQ4OC00ZWM3LWIwNGUtZmM1MjBjYTI2M2E0XkEyXkFqcGdeQXVyNDU0NjMyNTQ@._V1_SX300.jpg\"},{\"Title\":\"Ice Guardians\",\"Year\":\"2016\",\"imdbID\":\"tt4431208\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjM5MDQyODAyNF5BMl5BanBnXkFtZTgwMDgzNjA4OTE@._V1_SX300.jpg\"},{\"Title\":\"Guardians of the Galaxy: Inferno\",\"Year\":\"2017\",\"imdbID\":\"tt7131308\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZGQ0YzEyNWQtNGJiMi00NTAxLThkNDctNGY2ODkzYWMxZmZkXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg\"}],\"totalResults\":\"160\",\"Response\":\"True\"}"
        ))
        activityTestRule.launchActivity(intent)
        SystemClock.sleep(1000)
    }

    @Test
    fun onCreate() {
        Espresso.onView(withId(R.id.moviesRecycler)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onMovieSelected() {
        Espresso.onView(withId(R.id.moviesRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(1, ViewActions.click()))
        Espresso.onView(withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycler_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}