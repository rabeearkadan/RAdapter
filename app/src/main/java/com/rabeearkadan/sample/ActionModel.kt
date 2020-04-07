package com.rabeearkadan.sample

import android.content.Context
import com.rabeearkadan.sample.model.BaseModel
import com.rabeearkadan.sample.utils.BaseHolder
import com.rabeearkadan.sample.utils.IActions
import kotlinx.android.synthetic.main.layout_basic.view.*

class ActionModel(item : Model) : BaseModel( item ) {

    override var layout: Int = R.layout.layout_basic

    override fun bind(context: Context, holder: BaseHolder, position: Int, iActions: IActions?) {
        holder.itemView.txt_name.text = (item as Model).name
        holder.itemView.txt_age.text = (item as Model).age.toString()
        holder.itemView.txt_address.text = (item as Model).address

        holder.itemView.setOnClickListener {
            iActions!!.onPositiveClick( this , position )
        }
    }
}