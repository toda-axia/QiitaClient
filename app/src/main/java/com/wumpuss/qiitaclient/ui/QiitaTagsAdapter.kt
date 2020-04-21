package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wumpuss.qiitaclient.data.QiitaTag
import com.wumpuss.qiitaclient.databinding.QiitaTagItemBinding

class QiitaTagsAdapter(
    private val context: Context,
    private val listener : (QiitaTag) -> Unit
): RecyclerView.Adapter<QiitaTagsAdapter.QiitaTagsViewHolder>() {
    var qiitaAllTags: List<QiitaTag> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaTagsViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val binding = QiitaTagItemBinding.inflate(layoutInflater, parent, false)
        return QiitaTagsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return qiitaAllTags.size
    }

    override fun onBindViewHolder(holder: QiitaTagsViewHolder, position: Int) {
        holder.binding.qiitaTag = qiitaAllTags[position]

        Glide.with(context).load(qiitaAllTags[position].icon_url).into(holder.binding.tagIcon)

        holder.itemView.setOnClickListener {
            listener.invoke(
                QiitaTag(
                    qiitaAllTags[position].id,
                    qiitaAllTags[position].icon_url,
                    qiitaAllTags[position].item_count
                )
            )
        }
    }

    inner class QiitaTagsViewHolder(var binding: QiitaTagItemBinding) : RecyclerView.ViewHolder(binding.root)
}