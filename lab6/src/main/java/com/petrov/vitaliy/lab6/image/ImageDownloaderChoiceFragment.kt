package com.petrov.vitaliy.lab6.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_menu_download_image.*

class ImageDownloaderChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_download_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        download_from_async_task.setOnClickListener {
            this.findNavController().navigate(R.id.action_download_from_async_task)
        }

        download_from_coroutines.setOnClickListener {
            this.findNavController().navigate(R.id.action_download_from_coroutines)
        }

        download_from_library.setOnClickListener {
            this.findNavController().navigate(R.id.action_download_from_library)
        }
    }


}
