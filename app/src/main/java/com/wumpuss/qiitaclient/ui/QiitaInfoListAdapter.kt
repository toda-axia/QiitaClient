package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.databinding.QiitaItemBinding
import com.bumptech.glide.Glide
import com.wumpuss.qiitaclient.model.Bookmark

class QiitaInfoListAdapter(
    private val context: Context,
    private val listener : (Bookmark) -> Unit
): RecyclerView.Adapter<QiitaInfoListAdapter.QiitaInfoViewHolder>() {
    companion object {
        private const val CLICKABLE_DELAY_TIME = 100L
    }
    var qiitaInfoList: List<QiitaInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaInfoViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val binding = QiitaItemBinding.inflate(layoutInflater, parent, false)
        return QiitaInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return qiitaInfoList.size
    }

    override fun onBindViewHolder(holder: QiitaInfoViewHolder, position: Int) {
        holder.binding.qiitainfo = qiitaInfoList[position]

        Glide.with(context).load(qiitaInfoList[position].qiitaUser.profile_image_url).into(holder.binding.userImageView)

        holder.itemView.setSafeClickListener {
            listener.invoke(
                Bookmark.createBookmarkModel(
                    qiitaInfoList[position].id,
                    qiitaInfoList[position].title,
                    qiitaInfoList[position].url,
                    qiitaInfoList[position].qiitaUser.profile_image_url)
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


    inner class QiitaInfoViewHolder(var binding: QiitaItemBinding) : RecyclerView.ViewHolder(binding.root)
}