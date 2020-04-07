package com.rabeearkadan.sample.utils

import android.content.Context

interface IBinder {
    fun bind(context : Context , holder: BaseHolder, position: Int, iActions: IActions?)
}