package com.homedrop.common.base

import android.util.Log
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.homedrop.common.ITEM_NONE
import java.util.Locale

abstract class BaseRecyclerAdapter<T : BaseViewType> :
    RecyclerView.Adapter<DataBoundViewHolder<ViewBinding>>() {

    companion object {
        private const val TAG = "BaseRecyclerAdapter"
    }

    var dataList = ArrayList<T>()
        private set

    private val selectedItems = SparseBooleanArray()
    private val supportedViewBinderResolverMap = SparseArray<ViewDataBinder<ViewBinding, T>>()

    protected abstract fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, T>>

    protected fun initViewDataBinders() {
        getSupportedViewDataBinder().forEach { viewDataBinder ->
            supportedViewBinderResolverMap.put(viewDataBinder.viewType, viewDataBinder)
        }
    }

    fun isSelected(position: Int): Boolean {
        return selectedItems.get(position, false)
    }

    fun toggleSelection(position: Int) {
        if (isSelected(position)) {
            selectedItems.delete(position)
        } else {
            selectedItems.put(position, true)
        }
        notifyItemChanged(position)
    }

    open fun selectAll() {
        for (i in 0 until itemCount) {
            selectedItems.put(i, true)
        }
        notifyDataSetChanged()
    }

    open fun clearSelection(areItemsDeleted: Boolean = false) {
        val selection = getSelectedItems()
        selectedItems.clear()
        if (!areItemsDeleted) {
            for (i in selection) {
                notifyItemChanged(i, dataList[i])
            }
        }
    }

    fun getSelectedItems(): List<Int> {
        val items = arrayListOf<Int>()
        selectedItems.forEach { key, value ->
            if (value) {
                items.add(key)
            }
        }
        return items
    }

    fun getSelectedItemCount(): Int {
        return selectedItems.size()
    }

    fun isSelectionMode(): Boolean {
        return getSelectedItemCount() > 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBoundViewHolder<ViewBinding> {
        val viewDataBinder = supportedViewBinderResolverMap.get(viewType)
        if (viewDataBinder == null) {
            val message =
                String.format(Locale.US, "No view binder found for viewType: %d", viewType)
            throw IllegalArgumentException(message)
        }
        val binder = viewDataBinder.createBinder(parent)
        return DataBoundViewHolder(binder)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ViewBinding>, position: Int) {
        val viewDataBinder = supportedViewBinderResolverMap.get(getItemViewType(position))
        if (viewDataBinder == null) {
            Log.e(TAG, "Please add the supported view binder to view binders list in adapter")
            return
        }
        viewDataBinder.bindData(holder.binding, dataList[position], position)
    }

    override fun onBindViewHolder(
        holder: DataBoundViewHolder<ViewBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val viewDataBinder = supportedViewBinderResolverMap.get(getItemViewType(position))
            if (viewDataBinder == null) {
                Log.e(TAG, "Please add the supported view binder to view binders list in adapter")
                return
            }
            val item = payloads[0] as? T
            viewDataBinder.bindDataWithPayload(holder.binding, item, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position >= 0 && position < dataList.size) {
            return dataList[position].viewType
        }
        return ITEM_NONE
    }

    override fun getItemCount() = dataList.size

    operator fun get(position: Int): BaseViewType {
        return dataList[position]
    }

    operator fun <T> get(position: Int): T? {
        return dataList[position] as? T
    }

    fun setItems(items: List<T>) {
        if (items is ArrayList<T>) {
            dataList = items
        } else {
            dataList = ArrayList()
            dataList.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun addItem(item: T, position: Int) {
        dataList.add(position, item)
        notifyItemInserted(position)
    }

    fun addItem(item: T): Int {
        dataList.add(item)
        val i = dataList.size - 1
        notifyItemInserted(i)
        return i
    }

    fun updateItem(item: T, position: Int) {
        dataList[position] = item
        notifyItemChanged(position)
    }

    fun removeItem(position: Int, updateAllTrailing: Boolean = false) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        if (updateAllTrailing) {
            notifyItemRangeChanged(position, dataList.size - position)
        }
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

}