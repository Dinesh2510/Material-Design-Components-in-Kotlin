package com.example.kotlindemoapp.Helper

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.SwipeToDismissListWithConfirmAdapter

class RecyclerItemTouchHelper(dragDirs: Int, swipeDirs: Int, private val listener: RecyclerItemTouchHelperListener?) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView = (viewHolder as SwipeToDismissListWithConfirmAdapter.GeneralListViewHolder).viewForeground

            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView,
                                 viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                                 actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as SwipeToDismissListWithConfirmAdapter.GeneralListViewHolder).viewForeground
        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView = (viewHolder as SwipeToDismissListWithConfirmAdapter.GeneralListViewHolder).viewForeground
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                             actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as SwipeToDismissListWithConfirmAdapter.GeneralListViewHolder).viewForeground

        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener?.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
    }
}