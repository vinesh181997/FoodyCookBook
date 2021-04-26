package com.batman.foodycookbook.ui.search

import com.batman.foodycookbook.MVPView
import com.batman.foodycookbook.model.SearchResponse

interface ISearchView:MVPView {

    fun onSearchSuccess(res:SearchResponse)
    fun onSearchError()

}