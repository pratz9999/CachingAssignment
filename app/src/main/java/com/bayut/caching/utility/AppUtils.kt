package com.bayut.caching.utility

import android.content.Context
import android.widget.Toast


class AppUtils {

    companion object {

        fun shortToast(context: Context, msg: String?) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

    }
}