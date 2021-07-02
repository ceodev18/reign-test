package com.ceodev18.listdata.ui.hits

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ceodev18.listdata.data.entities.Hit
import com.ceodev18.listdata.databinding.ItemHitBinding

class HitsAdapter(private val listener: HitItemListener) : RecyclerView.Adapter<HitViewHolder>() {

    interface HitItemListener {
        fun onClickedHit(hit: Hit)
    }

    private val items = ArrayList<Hit>()

    fun setItems(items: ArrayList<Hit>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding: ItemHitBinding =
            ItemHitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HitViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) =
        holder.bind(items[position])

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)

    }

    fun getHit(position: Int): Hit {
        return items[position]
    }
}

class HitViewHolder(
    private val itemBinding: ItemHitBinding,
    private val listener: HitsAdapter.HitItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var hit: Hit

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Hit) {
        this.hit = item
        itemBinding.name.text = item.story_title ?: item.title
        itemBinding.tvAuthorDate.text = """${item.author} - ${item.created_at}"""
    }

    override fun onClick(v: View?) {

        listener.onClickedHit(hit)
    }

}

