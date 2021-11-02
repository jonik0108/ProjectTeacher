package com.zadira.test3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zadira.projectteacher.R
import com.zadira.test3.Model.User

import kotlinx.android.synthetic.main.item_avagers.view.*

class UserAdapter (val context: Context, val userList: List<User>) : RecyclerView.Adapter<UserAdapter.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv) {
        fun onBind(user: User) {
      /*      itemRv.namefilm.text = user.name
            itemRv.data.text=user.telefon*/

            itemRv.animation = AnimationUtils.loadAnimation(context, R.anim.anim1)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_avagers, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(userList[position])
        holder.itemRv.setOnClickListener {
        }

    }

    override fun getItemCount(): Int = userList.size
}