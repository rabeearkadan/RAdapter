package com.rabeearkadan.sample

import android.content.Context
import android.widget.TextView
import com.rabeearkadan.sample.model.BaseModel
import com.rabeearkadan.sample.utils.BaseHolder
import com.rabeearkadan.sample.utils.IActions

class R2Model(model: Model, layout: Int): BaseModel(model, layout) {


    override fun bind(context: Context , holder: BaseHolder, position: Int, iActions: IActions?) {

        holder.itemView.findViewById<TextView>(R.id.txt_name).text = (this.item as Model).name
        holder.itemView.findViewById<TextView>(R.id.txt_age).text = (this.item as Model).age.toString()
        holder.itemView.findViewById<TextView>(R.id.txt_address).text = (this.item as Model).address
    }


}