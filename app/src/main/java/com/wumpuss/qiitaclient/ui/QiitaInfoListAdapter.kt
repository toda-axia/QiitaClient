package com.wumpuss.qiitaclient.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wumpuss.qiitaclient.data.QiitaInfo
import com.wumpuss.qiitaclient.databinding.QiitaItemBinding
import com.bumptech.glide.Glide

class QiitaInfoListAdapter(
    private val context: Context,
    private val listener : (String) -> Unit
): RecyclerView.Adapter<QiitaInfoListAdapter.QiitaInfoViewHolder>() {
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

        qiitaInfoList[position].qiitaUser.profile_image_url?.let {
            Glide.with(context).load(qiitaInfoList[position].qiitaUser.profile_image_url).into(holder.binding.userImageView)
        }

        holder.itemView.setOnClickListener {
            listener.invoke(qiitaInfoList[position].url)
        }
    }

    inner class QiitaInfoViewHolder(var binding: QiitaItemBinding) : RecyclerView.ViewHolder(binding.root)
}