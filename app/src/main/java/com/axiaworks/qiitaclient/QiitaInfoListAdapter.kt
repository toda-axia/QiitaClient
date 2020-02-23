package com.axiaworks.qiitaclient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axiaworks.qiitaclient.data.QiitaInfo
import com.axiaworks.qiitaclient.databinding.QiitaItemBinding

class QiitaInfoListAdapter(
    private val context: Context
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
    }

    inner class QiitaInfoViewHolder(var binding: QiitaItemBinding) : RecyclerView.ViewHolder(binding.root)
}