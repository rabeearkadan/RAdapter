package com.rabeearkadan.sample.utils

import com.rabeearkadan.sample.model.BaseModel

interface IActions {
    fun onPositiveClick( item: BaseModel, position: Int)

    fun onNegativeClick( item: BaseModel, position: Int)
    
    fun onNeutralClick( item: BaseModel, position: Int)
}