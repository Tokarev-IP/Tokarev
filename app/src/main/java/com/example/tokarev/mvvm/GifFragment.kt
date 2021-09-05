package com.example.tokarev.mvvm

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tokarev.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class GifFragment : Fragment() {

    companion object {
        fun newInstance(): GifFragment {
            return GifFragment()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gifInflater = inflater.inflate(R.layout.fragment_gif, container, false)

        val gifViewModel by lazy { ViewModelProvider(this).get(GifViewModel::class.java) }

        gifViewModel.getAllFromLatestRoom()

        val gifView: ImageView = gifInflater.findViewById(R.id.gif_view)
        val buttonGo: Button = gifInflater.findViewById(R.id.button_go)
        val buttonBack: Button = gifInflater.findViewById(R.id.button_back)
        val textDescription: TextView = gifInflater.findViewById(R.id.description_textView)
        val bottomNav: BottomNavigationView = gifInflater.findViewById(R.id.nav_bar)

        gifViewModel.getLatestData().observe(viewLifecycleOwner, {
            Glide
                .with(this)
                .load(it.gifUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_search_24_blue)
                .error(R.drawable.ic_baseline_error_24)
                .into(gifView)
            textDescription.text = it.description
        })

        buttonGo.setOnClickListener {
            gifViewModel.clickGo()
        }

        buttonBack.setOnClickListener {
            gifViewModel.clickBack()
        }

        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.action_one ->{}
                R.id.action_two->{}
                R.id.action_three->{}
            }
        }

        return gifInflater
    }

}