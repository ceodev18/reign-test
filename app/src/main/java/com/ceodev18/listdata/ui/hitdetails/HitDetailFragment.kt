package com.ceodev18.listdata.ui.hitdetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ceodev18.listdata.R
import com.ceodev18.listdata.databinding.HitDetailFragmentBinding
import com.ceodev18.listdata.utils.Resource
import com.example.rickandmorty.utils.autoCleared


class HitDetailFragment : Fragment() {

    private var binding: HitDetailFragmentBinding by autoCleared()
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HitDetailFragmentBinding.inflate(inflater, container, false)
        webView = binding.wvMain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("story_url")?.let { /*viewModel.start(it)*/
            loadPage(it)
        }
    }

    private fun loadPage(url: String) {
        val settings = webView.settings

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true

        webView.settings.javaScriptEnabled = true
        // WebView settings
        webView.fitsSystemWindows = true

        /*
            if SDK version is greater of 19 then activate hardware acceleration
            otherwise activate software acceleration
        */
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)


        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.pbMain.visibility = GONE
                webView.visibility = VISIBLE
            }
        }
        webView.loadUrl(url)
    }

}