package com.wumpuss.qiitaclient.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.wumpuss.qiitaclient.R

class InputSearchTagDialog : DialogFragment() {
    companion object {
        const val TAG = "InputSearchTagDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inputSearchTagDialogView = View.inflate(context, R.layout.fragment_input_search_tag_dialog, null)
        val searchButton = inputSearchTagDialogView.findViewById(R.id.search_button) as Button
        val cancelButton = inputSearchTagDialogView.findViewById(R.id.cancel_button) as Button
        val tagText = inputSearchTagDialogView.findViewById(R.id.input_tag_text) as TextInputEditText

        cancelButton.setOnClickListener {
            dismissAllowingStateLoss()
        }

        searchButton.setOnClickListener {
            startActivity(SearchResultActivity.callingIntent(requireContext(), tagText.text.toString()))
            dismissAllowingStateLoss()
        }

        tagText.addTextChangedListener(
//            beforeTextChanged = { text, start, count, after ->
//            },
//            onTextChanged = { text, start, count, after ->
//            },
            afterTextChanged = { text ->
                searchButton.isEnabled = text.toString().isNotEmpty()
            }
        )

        return AlertDialog.Builder(requireContext())
            .apply {
                setView(inputSearchTagDialogView)
            }.create()
    }
}
