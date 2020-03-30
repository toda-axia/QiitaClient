package com.wumpuss.qiitaclient.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.DialogFragment

import com.wumpuss.qiitaclient.R

class ConfirmDeleteDialog : DialogFragment() {
    private var confirmDeleteListener: ConfirmDeleteDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        confirmDeleteListener = context as? ConfirmDeleteDialog
    }

    override fun onDetach() {
        confirmDeleteListener = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)
        val confirmUpdateDialogView = View.inflate(context, R.layout.fragment_confirm_delete_dialog, null)

        return AlertDialog.Builder(requireContext())
            .apply {
                setView(confirmUpdateDialogView)
            }.create()
    }

}
