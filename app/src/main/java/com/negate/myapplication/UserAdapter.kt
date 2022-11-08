package com.negate.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.negate.myapplication.databinding.UserRowLayoutBinding

class UserAdapter(private val items: List<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: User)
    }

    inner class MyViewHolder(private val binding: UserRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.apply {
                username.text = item.username
                name.text = item.name
                company.text = item.company
                location.text = item.location
                avatar.load(item.avatar) {
                    crossfade(400)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(item)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            UserRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}