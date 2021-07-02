package com.ceodev18.listdata.ui.hits

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ceodev18.listdata.R
import com.ceodev18.listdata.data.entities.Hit
import com.ceodev18.listdata.databinding.HitsFragmentBinding
import com.ceodev18.listdata.utils.Resource
import com.ceodev18.listdata.utils.animation.SwipeToDeleteCallback
import com.example.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync


@AndroidEntryPoint
class HitsFragment : Fragment(), HitsAdapter.HitItemListener {

    private var binding: HitsFragmentBinding by autoCleared()
    private val viewModel: HitViewModel by viewModels()
    private lateinit var adapter: HitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HitsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setActions()
    }

    private fun setupRecyclerView() {
        adapter = HitsAdapter(this)
        binding.hitsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.hitsRv.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(this.requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.hitsRv.adapter as HitsAdapter
                val hit = adapter.getHit(viewHolder.adapterPosition)
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteHit(hit)

                }
                adapter.removeAt(viewHolder.adapterPosition)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.hitsRv)

    }

    private fun setupObservers() {
        viewModel.hits.observe(viewLifecycleOwner, Observer { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedHit(hit: Hit) {
        if (hit.story_url != null) {
            findNavController().navigate(
                R.id.action_hitsFragment_to_hitDetailFragment,
                bundleOf("story_url" to hit.story_url)
            )
        } else {
            val toast = Toast.makeText(context, R.string.not_story_url, Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    private fun setActions() {
        binding.swipe.setOnRefreshListener { clear() }
    }

    private fun clear() {
        binding.swipe.isRefreshing = false
    }
}