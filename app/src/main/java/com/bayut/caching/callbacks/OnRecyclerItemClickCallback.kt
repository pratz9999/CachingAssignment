package com.bayut.caching.callbacks

import android.view.View

interface OnRecyclerItemClickCallback<T> {
    fun onRecyclerItemClicked(position:Int, view:View, data:T)
}