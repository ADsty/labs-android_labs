package com.petrov.vitaliy.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfinityFragment : Fragment() {

       override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                val mLayoutManager = (layoutManager as LinearLayoutManager)
                adapter = InfinityRecyclerViewAdapter(GetData.items)
                var pastVisiblesItems: Int
                var totalItemCount: Int
                view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0) {
                            totalItemCount = mLayoutManager.itemCount
                            pastVisiblesItems = mLayoutManager.findLastCompletelyVisibleItemPosition()
                            if (pastVisiblesItems == totalItemCount - 1) {
                                    mLayoutManager.scrollToPosition(0)
                            }
                        }
                    }
                })
                }
        }
        return view
    }
}