package com.batman.foodycookbook.ui.randomFood

import com.batman.foodycookbook.MVPView
import com.batman.foodycookbook.model.RandomResponse
import com.batman.foodycookbook.model.SearchResponse

interface IRandomView:MVPView {

 fun onRandomSuccess(res:RandomResponse)


}