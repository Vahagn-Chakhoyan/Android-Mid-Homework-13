package com.vahagn.androidmidhomework13

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ContactsAdapter(private val clickListener: (Int) -> Unit, private val longClickListener: (Int) -> Boolean) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view
        val iconText: TextView = view.findViewById(R.id.iconText)
        val nameText: TextView = view.findViewById(R.id.nameText)
        val numberText: TextView = view.findViewById(R.id.numberText)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.contacts_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.iconText.text = ContactsFragment.contacts[position].name.substring(0,1)
        viewHolder.iconText.setBackgroundColor(Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256)))
        viewHolder.iconText.setTextColor(Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256)))
        viewHolder.nameText.text = ContactsFragment.contacts[position].name
        viewHolder.numberText.text = ContactsFragment.contacts[position].phoneNumber

        viewHolder.root.setOnClickListener { clickListener(position) }
        viewHolder.root.setOnLongClickListener { longClickListener(position) }
    }

    override fun getItemCount() = ContactsFragment.contacts.size
}