package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_group.*
import kotlinx.android.synthetic.main.item_chat_single.*
import kotlinx.android.synthetic.main.item_chat_single.sv_indicator
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.visible
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.models.data.ChatType

class ChatAdapter(
    val listener: (ChatItem) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatItemVH>() {

    companion object {
        private const val ARCHIVE_TYPE = 0
        private const val SINGLE_TYPE = 1
        private const val GROUP_TYPE = 2
    }

    var items: List<ChatItem> = listOf()

    fun updateData(data: List<ChatItem>) {
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

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemVH {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SINGLE_TYPE -> SingleVH(inflater.inflate(R.layout.item_chat_single, parent, false))
            GROUP_TYPE -> GroupVH(inflater.inflate(R.layout.item_chat_group, parent, false))
            else -> GroupVH(inflater.inflate(R.layout.item_chat_group, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ChatItemVH, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemViewType(position: Int) = when (items[position].chatType) {
        ChatType.ARCHIVE -> ARCHIVE_TYPE
        ChatType.SINGLE -> SINGLE_TYPE
        ChatType.GROUP -> GROUP_TYPE
    }

    abstract inner class ChatItemVH(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: ChatItem, listener: (ChatItem) -> Unit)
    }

    inner class SingleVH(
        override val containerView: View
    ) : ChatItemVH(containerView), ItemTouchViewHolder {

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            if (item.avatar == null) {
                Glide.with(itemView).clear(iv_avatar_single)
                iv_avatar_single.initials = item.initials
            } else {
                Glide.with(itemView)
                    .load(item.avatar)
                    .into(iv_avatar_single)
            }

            sv_indicator.visible = item.isOnline
            with(tv_date_single) {
                visible = item.lastMessageDate != null
                text = item.lastMessageDate
            }

            with(tv_counter_single) {
                visible = item.messageCount > 0
                text = item.messageCount.toString()
            }

            tv_title_single.text = item.title
            tv_message_single.text = item.shortDescription

            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    inner class GroupVH(
        override val containerView: View
    ) : ChatItemVH(containerView), ItemTouchViewHolder {

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            iv_avatar_group.initials = item.initials

            with(tv_date_group) {
                visible = item.lastMessageDate != null
                text = item.lastMessageDate
            }

            with(tv_counter_group) {
                visible = item.messageCount > 0
                text = item.messageCount.toString()
            }

            tv_title_group.text = item.title
            tv_message_group.text = item.shortDescription

            with(tv_message_author) {
                visible = item.messageCount > 0
                text = item.author
            }

            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}