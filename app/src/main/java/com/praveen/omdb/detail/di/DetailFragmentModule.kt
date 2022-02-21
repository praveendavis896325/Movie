package com.praveen.omdb.detail.di

import androidx.recyclerview.widget.LinearLayoutManager
import com.praveen.omdb.detail.presentation.DetailFragment
import com.praveen.omdb.detail.presentation.RatingAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailFragmentModule {

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    internal fun provideRatingAdapter(): RatingAdapter {
        return RatingAdapter(ArrayList())
    }

}