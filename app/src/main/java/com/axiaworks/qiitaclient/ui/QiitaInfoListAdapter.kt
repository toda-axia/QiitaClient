package com.axiaworks.qiitaclient.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axiaworks.qiitaclient.data.QiitaInfo
import com.axiaworks.qiitaclient.databinding.QiitaItemBinding
import com.axiaworks.qiitaclient.viewmodel.MainViewModel

class QiitaInfoListAdapter(
    private val context: Context,
    private val viewModel: MainViewModel
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

        holder.itemView.setOnClickListener {
            viewModel.setArticleUrl(qiitaInfoList[position].url)
        }
    }

    inner class QiitaInfoViewHolder(var binding: QiitaItemBinding) : RecyclerView.ViewHolder(binding.root)
}