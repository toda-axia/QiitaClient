package com.wumpuss.qiitaclient.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText

import com.wumpuss.qiitaclient.R

class InputSearchTagDialog : DialogFragment() {
    companion object {
        const val TAG = "InputSearchTagDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inputSearchTagDialogView = View.inflate(context, R.layout.fragment_input_search_tag_dialog, null)
        val searchButton = inputSearchTagDialogView.findViewById(R.id.seach_button) as Button
        val cancelButton = inputSearchTagDialogView.findViewById(R.id.cancel_button) as Button
        val tagText = inputSearchTagDialogView.findViewById(R.id.tag_text) as TextInputEditText

        cancelButton.setOnClickListener {
            dismissAllowingStateLoss()
        }

        searchButton.setOnClickListener {
            startActivity(SearchResultActivity.callingIntent(requireContext(), tagText.text.toString()))
            dismissAllowingStateLoss()
        }

        return AlertDialog.Builder(requireContext())
            .apply {
                setView(inputSearchTagDialogView)
            }.create()
    }

}
