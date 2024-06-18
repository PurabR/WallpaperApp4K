package com.homedrop.common.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.homedrop.common.R

abstract class BaseBottomSheetFragment<B : ViewBinding, VM : ViewModel> :
    BottomSheetDialogFragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected lateinit var viewModel: VM

    protected open fun getViewModelClass(): Class<VM>? {
        return null
    }

    protected abstract fun getViewBinding(): B

    protected open val useSharedViewModel = false
    protected open val state = BottomSheetBehavior.STATE_COLLAPSED
    protected open val cancellable = true
    protected open val style = R.style.BottomSheetStyle
    protected var dismissListener: DismissListener? = null

    protected open fun receiveExtras() {}
    protected open fun setUpViews() {}
    protected open fun observeData() {}

    open fun setOnDismissListener(listener: DismissListener) {
        this.dismissListener = listener
    }

    override fun getTheme(): Int {
        return style
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = cancellable
        init()
        receiveExtras()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            try {
                val bottomSheetDialog = dialog as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                ) as FrameLayout
                bottomSheet.setBackgroundColor(Color.TRANSPARENT)
                BottomSheetBehavior.from(bottomSheet).state = state
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun init() {
        if (getViewModelClass() == null) {
            return
        }
        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity())[getViewModelClass()!!]
        } else {
            ViewModelProvider(this)[getViewModelClass()!!]
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss()
    }

    protected fun activity(): BaseActivity<*,*> {
        return requireActivity() as BaseActivity<*, *>
    }

}