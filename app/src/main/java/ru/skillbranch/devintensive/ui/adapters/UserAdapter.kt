package ru.skillbranch.devintensive.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user_list.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.visible
import ru.skillbranch.devintensive.models.data.UserItem

class UserAdapter(
    val listener: (UserItem) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserVH>() {

    private var items = listOf<UserItem>()

    fun updateData(data: List<UserItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                items[oldPos].id == data[newPos].id

            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                items[oldPos] == data[newPos]

            override fun getOldListSize() = items.size
            override fun getNewListSize() = data.size
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_user_list, parent, false)
        return UserVH(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.bind(items[position], listener)
    }

    class UserVH(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: UserItem, listener: (UserItem) -> Unit) {
            if (user.avatar != null) {
                Glide.with(itemView)
                    .load(user.avatar)
                    .into(iv_avatar_user)
            } else {
                Glide.with(itemView).clear(iv_avatar_user)
                iv_avatar_user.initials = user.initials
            }
            sv_indicator.visible = user.isOnline
            tv_user_name.text = user.fullName
            tv_last_activity.text = user.lastActivity
            iv_selected.visible = user.isSelected
            itemView.setOnClickListener { listener.invoke(user) }
        }
    }
}
