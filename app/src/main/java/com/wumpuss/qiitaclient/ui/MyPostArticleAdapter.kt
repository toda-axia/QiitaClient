package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.databinding.QiitaItemBinding

class MyPostArticleAdapter(private val context: Context): RecyclerView.Adapter<MyPostArticleAdapter.MyPostArticleViewHolder>() {
    var myPostArticleList: List<QiitaInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostArticleViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val binding = QiitaItemBinding.inflate(layoutInflater, parent, false)
        return MyPostArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myPostArticleList.size
    }

    override fun onBindViewHolder(holder: MyPostArticleViewHolder, position: Int) {
        holder.binding.qiitainfo = myPostArticleList[position]

        Glide.with(context).load(myPostArticleList[position].qiitaUser.profile_image_url).into(holder.binding.userImageView)
    }

    inner class MyPostArticleViewHolder(var binding: QiitaItemBinding): RecyclerView.ViewHolder(binding.root)
}