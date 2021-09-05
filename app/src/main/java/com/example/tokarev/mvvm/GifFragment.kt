package com.example.tokarev.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tokarev.R

class GifFragment : Fragment() {

    companion object {
        fun newInstance(): GifFragment {
            return GifFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gifInflater = inflater.inflate(R.layout.fragment_gif, container, false)

        val gifViewModel by lazy { ViewModelProvider(this).get(GifViewModel::class.java) }
        gifViewModel.getDataFromApi(0)

        val gifView: ImageView = gifInflater.findViewById(R.id.gif_view)
        val progressBar: ProgressBar = gifInflater.findViewById(R.id.progress_bar_view)
        val buttonGo: Button = gifInflater.findViewById(R.id.button_go)
        val buttonBack: Button = gifInflater.findViewById(R.id.button_back)
        val textDescription: TextView = gifInflater.findViewById(R.id.description_textView)

        gifViewModel.getLatestData().observe(viewLifecycleOwner, {
            progressBar.visibility = View.VISIBLE
            Glide
                .with(this)
                .load(it.gifUrl)
                .centerCrop()
                .into(gifView)
            textDescription.text = it.description
            progressBar.visibility = View.INVISIBLE
        })

        buttonGo.setOnClickListener {
            gifViewModel.clickGo()
        }

        buttonBack.setOnClickListener {
            gifViewModel.clickBack()
        }

        return gifInflater
    }

}