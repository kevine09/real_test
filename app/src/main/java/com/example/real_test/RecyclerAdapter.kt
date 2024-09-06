package com.example.real_test

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private var listDataHeader: MutableList<String>,
    private var listDataChild: HashMap<String, MutableList<String>>,
    private var items: MutableList<Item>  // Define the 'items' list here
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val contactTextView: TextView = itemView.findViewById(R.id.contactTextView)
        val saveButton: Button = itemView.findViewById(R.id.saveButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the header text
        holder.textView.text = listDataHeader[position]

        // Get the item corresponding to the current position
        val item = items[position]

        // Bind the data to the views
        holder.imageView.setImageResource(item.imageResId)
        holder.descriptionTextView.text = item.description
        holder.contactTextView.text = item.contact

        // Handle the save button click
        holder.saveButton.setOnClickListener {
            // Handle save action
        }
    }

    override fun getItemCount(): Int {
        // Ensure to return the correct count based on the items list
        return items.size
    }

    // Function to update data in the adapter
    fun updateData(newItems: MutableList<Item>, newDataHeader: MutableList<String>, newDataChild: HashMap<String, MutableList<String>>) {
        this.items = newItems
        this.listDataHeader = newDataHeader
        this.listDataChild = newDataChild
        notifyDataSetChanged()
    }
}
