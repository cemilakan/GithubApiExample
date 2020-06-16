package com.example.githubapiexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiexample.R
import com.example.githubapiexample.models.ReposItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_item.view.*
import java.lang.Exception


class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ReposItem>() {
        override fun areItemsTheSame(oldItem: ReposItem, newItem: ReposItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReposItem, newItem: ReposItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo= differ.currentList[position]
        holder.itemView.apply {
            Picasso.get().load(repo.owner.avatarUrl).fit().centerCrop().into(user_image, object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    Toast.makeText(holder.itemView.context, "Error For Picasso", Toast.LENGTH_SHORT).show()
                }

            })
            row_parent.setOnClickListener {
                Toast.makeText(holder.itemView.context, "This Repo's Id is: "+ repo.id, Toast.LENGTH_SHORT).show()
            }
            user_nick.text=repo.owner.login
            repo_name.text=repo.name
            repo_description.text=repo.description
            repo_lang.text=repo.language
        }

    }
}