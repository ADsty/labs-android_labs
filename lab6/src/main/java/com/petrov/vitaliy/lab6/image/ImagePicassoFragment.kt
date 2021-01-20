package com.petrov.vitaliy.lab6.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.petrov.vitaliy.lab6.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*
import java.lang.Exception

class ImagePicassoFragment : Fragment() {

    private var imageUrl =
        "https://memepedia.ru/wp-content/uploads/2019/12/kot-gruzitsja-9.jpg"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadImage(imageUrl)
    }

    private fun loadImage(url: String) {
        Picasso.get().load(url).into(image, object : Callback {

            override fun onSuccess() {
                load_bar.visibility = View.INVISIBLE
            }

            override fun onError(e: Exception) {
                load_bar.visibility = View.INVISIBLE
                Toast.makeText(context, "Can't Download", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
