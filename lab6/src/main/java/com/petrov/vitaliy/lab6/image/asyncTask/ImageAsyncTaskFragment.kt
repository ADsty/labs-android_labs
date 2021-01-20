package com.petrov.vitaliy.lab6.image.asyncTask


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_image.*

class ImageAsyncTaskFragment : Fragment() {

    private var imageUrl =
        "https://memepedia.ru/wp-content/uploads/2019/12/kot-gruzitsja-9.jpg"
    
    private lateinit var asyncLoader: DownloadImage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onStart() {
        super.onStart()
        asyncLoader = DownloadImage(image)
        asyncLoader.execute(imageUrl)
    }

    override fun onStop() {
        super.onStop()
        asyncLoader.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    private inner class DownloadImage(private var imageView: ImageView):
        AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val currentUrl = urls[0]

            var icon: Bitmap? = null

            try {
                val inputStream = java.net.URL(currentUrl).openStream()
                icon = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                println("Error! Can't download file!")
            }
            return icon
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result == null){
                Toast.makeText(context, "Can't Download", Toast.LENGTH_SHORT).show()
            }
            imageView.setImageBitmap(result)
            load_bar.visibility = View.INVISIBLE
        }
    }
}
