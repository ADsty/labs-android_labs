package com.petrov.vitaliy.lab6.image.coroutine


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.coroutines.*
import java.net.URL

class ImageCoroutineFragment : Fragment() {

    private lateinit var job : Job
    private var imageUrl =
        "https://memepedia.ru/wp-content/uploads/2019/12/kot-gruzitsja-9.jpg"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        downloadImage()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    private fun downloadImage() {

        var icon: Bitmap?

        job = lifecycle.coroutineScope.launch{
            icon = async(Dispatchers.IO) {
                try {
                    URL(imageUrl).openStream().use {
                        return@async BitmapFactory.decodeStream(it)
                    }
                }catch (e: Exception){
                    return@async null
                }
            }.await()
            load_bar.visibility = View.INVISIBLE
            if(icon == null){
                Toast.makeText(context, "Can't Download", Toast.LENGTH_SHORT).show()
            }
            image.setImageBitmap(icon)
        }
    }
}
