package com.shapee.android.ui.fragment.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shapee.android.R
import com.shapee.android.model.Child
import kotlinx.android.synthetic.main.item_child.view.*

class ChildAdapter(var data: List<Child>, val listener: (item: Child, position:Int) -> Unit) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = data[position]
        holder.itemView.tvName.text = child.name
        holder.itemView.tvEmail.text = child.email
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                listener(data[layoutPosition], layoutPosition)
            }
        }
    }
}