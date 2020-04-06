package com.rabeearkadan.sample.model

import com.rabeearkadan.sample.R
import com.rabeearkadan.sample.utils.IBinder

abstract class BaseModel: IBinder {

    open var layout: Int
    var item : Any

    constructor(item: Any){
        this.item = item
        this.layout = R.layout.layout_null
    }

    constructor(item: Any, layout: Int){
        this.item = item
        this.layout = layout
    }




}