package com.rachellimaa.features.channel.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rachellimaa.features.R
import com.rachellimaa.features.channel.presentation.adapter.ChannelAdapter
import com.rachellimaa.features.channel.presentation.adapter.LoadState
import com.rachellimaa.features.channel.presentation.adapter.LoadStateAdapter
import org.koin.android.ext.android.inject

class ChannelActivity : AppCompatActivity() {

    private val viewModel: ChannelViewModel by inject()

    private val catalogRv by lazy { findViewById<RecyclerView>(R.id.catalog_list) }
    private val errorContentView by lazy { findViewById<LinearLayoutCompat>(R.id.error_content) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.catalog_loading) }

    private lateinit var channelsAdapter: ChannelAdapter
    private lateinit var loadStateAdapter: LoadStateAdapter

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                loadStateAdapter.loadState = LoadState.Loading
                getChannels()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

        loadStateAdapter = LoadStateAdapter()
        channelsAdapter = ChannelAdapter()

        getChannels()

        catalogRv.apply {
            adapter = ConcatAdapter(
                channelsAdapter,
                loadStateAdapter
            )
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(onScrollListener)
        }

        errorContentView.setOnClickListener {
            progress.visibility = View.VISIBLE
            errorContentView.visibility = View.GONE
            getChannels()
        }

        setupObservables()
    }

    private fun setupObservables() {
        viewModel.channels.observe(this, { catalog ->
            progress.visibility = View.GONE
            errorContentView.visibility = View.GONE
            channelsAdapter.items = catalog.toMutableList()
        })

        viewModel.onError.observe(this, {
            errorContentView.visibility = View.VISIBLE
            progress.visibility = View.GONE
        })
    }

    private fun getChannels() {
        viewModel.getChannels()
    }
}