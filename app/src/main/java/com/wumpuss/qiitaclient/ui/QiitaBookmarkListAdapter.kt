package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wumpuss.qiitaclient.data.QiitaBookmark
import com.wumpuss.qiitaclient.databinding.QiitaBookmarkItemBinding

class QiitaBookmarkListAdapter(
    private val context: Context,
    private val listener : (QiitaBookmark) -> Unit
): RecyclerView.Adapter<QiitaBookmarkListAdapter.QiitaBookmarkViewHolder>() {
    companion object {
        private const val CLICKABLE_DELAY_TIME = 100L
    }
    var qiitaBookmarkList: List<QiitaBookmark> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaBookmarkViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val binding = QiitaBookmarkItemBinding.inflate(layoutInflater, parent, false)
        return QiitaBookmarkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return qiitaBookmarkList.size
    }

    override fun onBindViewHolder(holder: QiitaBookmarkViewHolder, position: Int) {
        holder.binding.qiitabookmark = qiitaBookmarkList[position]

        Glide.with(context).load(qiitaBookmarkList[position].profileImage).into(holder.binding.userImageView)

        holder.itemView.setSafeClickListener {
            listener.invoke(
                qiitaBookmarkList[position]
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


    inner class QiitaBookmarkViewHolder(var binding: QiitaBookmarkItemBinding) : RecyclerView.ViewHolder(binding.root)
}