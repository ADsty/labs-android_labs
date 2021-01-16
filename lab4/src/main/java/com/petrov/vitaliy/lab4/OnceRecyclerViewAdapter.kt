package com.petrov.vitaliy.lab4


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*


class OnceRecyclerViewAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<OnceRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Item
            Log.d("onClick (once)", item.author + " " + item.title + " " + item.journal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.author.text = "author = ${item.author}"
        holder.title.text = "title = ${item.title}"
        holder.journal.text = "journal = ${item.journal}"
        holder.year.text = "year = ${item.year}"
        holder.volume.text = "volume = ${item.volume}"
        holder.number.text = "number = ${item.number}"
        holder.pages.text = "pages = ${item.pages}"


        with(holder.view) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        Log.d("getItemCount (once)", items.size.toString())
        return items.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.author
        val title: TextView = view.title
        val journal: TextView = view.journal
        val year: TextView = view.year
        val volume: TextView = view.volume
        val number: TextView = view.number
        val pages: TextView = view.pages
    }
}
