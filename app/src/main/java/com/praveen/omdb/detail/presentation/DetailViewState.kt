package com.praveen.omdb.detail.presentation

import com.praveen.omdb.detail.data.DetailResponse

sealed class DetailViewState {
    object DetailsFetchedError : DetailViewState()
    class DetailsFetchedSuccess(val details: DetailResponse) : DetailViewState()
    class MessageRes(val resId: Int) : DetailViewState()
}
