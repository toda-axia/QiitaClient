package com.wumpuss.qiitaclient.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.wumpuss.qiitaclient.R
import com.wumpuss.qiitaclient.databinding.FragmentConfirmDeleteDialogBinding
import com.wumpuss.qiitaclient.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ConfirmDeleteDialog : DialogFragment() {
    private lateinit var binding: FragmentConfirmDeleteDialogBinding
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_confirm_delete_dialog,
            null,
            false
        )
        return Dialog(requireContext()).apply {
            setContentView(binding.root)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.deleteCancelButton.setOnClickListener {
            dismissAllowingStateLoss()
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteBookmark(viewModel.id)
            CompleteDeletingBookmarkDialog().show(parentFragmentManager, CompleteDeletingBookmarkDialog.TAG)
            dismissAllowingStateLoss()
        }
    }
}
