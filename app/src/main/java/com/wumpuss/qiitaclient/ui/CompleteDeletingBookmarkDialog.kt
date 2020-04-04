package com.wumpuss.qiitaclient.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CompleteDeletingBookmarkDialog : DialogFragment() {
    private val viewModel: MainViewModel by sharedViewModel()

    companion object {
        const val TAG = "CompleteDeletingBookmarkDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        this.isCancelable = false

        val simpleDialogView = View.inflate(context, R.layout.fragment_complete_deleting_bookmark_dialog, null)
        val okButton = simpleDialogView.findViewById(R.id.ok_button) as Button
        okButton.setOnClickListener {
//            viewModel.deleteBookmark(viewModel.id)
            dismissAllowingStateLoss()
        }

        return AlertDialog.Builder(requireContext())
            .apply {
                setView(simpleDialogView)
            }.create()
    }

}
