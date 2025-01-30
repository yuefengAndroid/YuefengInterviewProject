package com.example.yuefenginterviewproject.ui.messages.subview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.EventEntity

class MessageEventListAdapter (val frag: Fragment, var list: ArrayList<EventEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(frag.context).inflate(R.layout.item_message_events, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val eventData: TextView = pContentView.findViewById(R.id.eventData)
        private val eventTitle: TextView = pContentView.findViewById(R.id.eventTitle)
        private val eventSubTitle: TextView = pContentView.findViewById(R.id.eventSubTitle)

        fun bind(data: EventEntity) {
            eventData.text = data.dataStr
            eventTitle.text = data.Title
            eventSubTitle.text = data.SubTitle
            itemView.setOnClickListener {
                frag.context?.let {
                }
            }
        }
    }
}