package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.databinding.QiitaItemBinding

class MyPostArticleAdapter(
    private val context: Context,
    private val listener : (QiitaBookmark) -> Unit
): RecyclerView.Adapter<MyPostArticleAdapter.MyPostArticleViewHolder>() {
    companion object {
        private const val CLICKABLE_DELAY_TIME = 100L
    }
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

        holder.itemView.setSafeClickListener {
            listener.invoke(
                QiitaBookmark(
                    myPostArticleList[position].id,
                    myPostArticleList[position].title,
                    myPostArticleList[position].url,
                    myPostArticleList[position].qiitaUser.profile_image_url
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: View> T.setSafeClickListener(listener: (it: T) -> Unit) {
        setOnClickListener { view ->
            if (view == null) return@setOnClickListener
            view.isEnabled = false

            Handler().postDelayed(
                { view.isEnabled = true },
                CLICKABLE_DELAY_TIME
            )

            listener.invoke(view as T)
        }
    }

    inner class MyPostArticleViewHolder(var binding: QiitaItemBinding): RecyclerView.ViewHolder(binding.root)
}