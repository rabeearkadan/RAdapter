package com.rabeearkadan.sample

import android.content.Context
import com.rabeearkadan.sample.model.BaseModel
import com.rabeearkadan.sample.utils.BaseHolder
import com.rabeearkadan.sample.utils.IActions
import kotlinx.android.synthetic.main.layout_basic.view.*

class RModel(model: Model): BaseModel(model) {

    override var layout: Int = R.layout.layout_basic

    override fun bind(context: Context, holder: BaseHolder, position: Int, iActions: IActions?) {
        holder.itemView.txt_name.text = (this.item as Model).name
        holder.itemView.txt_age.text = (this.item as Model).age.toString()
        holder.itemView.txt_address.text = (this.item as Model).address
    }
}