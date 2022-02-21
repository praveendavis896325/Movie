package com.praveen.omdb.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.praveen.omdb.R
import com.praveen.omdb.base.BaseFragment
import com.praveen.omdb.detail.data.DetailResponse
import com.praveen.omdb.movies.presentation.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment<MoviesViewModel>() {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mRatingAdapter: RatingAdapter

    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override val viewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        recycler_rating.setHasFixedSize(true)
        recycler_rating.layoutManager = mLinearLayoutManager
        recycler_rating.itemAnimator = DefaultItemAnimator()
        recycler_rating.adapter = mRatingAdapter
        renderMovieDetails()
    }

    private fun renderMovieDetails() {
        viewModel.getSelectedMovie()?.apply {
            tv_title.text = Title
            Glide.with(requireActivity())
                .load("${Poster}")
                .into(img_poster)
            viewModel.fetchMovieDetails(Title!!)
        }
    }

    override fun renderViewState(data: Any) {
        when (data) {
            is DetailViewState.MessageRes -> showMessage(getString(data.resId))
            is DetailViewState.DetailsFetchedSuccess -> renderDetailPage(data.details)
            is DetailViewState.DetailsFetchedError -> renderFetchingDetailsError()
        }
    }

    private fun renderFetchingDetailsError() {
        ratings_loading.visibility = View.GONE
        showMessage(getString(R.string.fetch_details_error))
    }

    private fun renderDetailPage(detials: DetailResponse) {
        ratings_loading.visibility = View.GONE
        detials.Ratings?.let { mRatingAdapter.addItems( it ) }
        tv_rating.text = detials.Rated
        tv_release_date.text = String.format(getString(R.string.released_in), detials.Released)
        tv_language.text = detials.Language
        genre.text = detials.Genre
        tv_plot.text = detials.Plot
        year.text = detials.Year
    }





}