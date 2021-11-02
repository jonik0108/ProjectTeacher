package com.zadira.test3.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zadira.projectteacher.R
import com.zadira.projectteacher.databinding.ItemRvBinding
import com.zadira.test3.Model.User




class RvAdapter (val context: Context, val list: List<User>, val rvClick: RvClick) : RecyclerView.Adapter<RvAdapter.Vh>(){
    inner class Vh(var itemRv: ItemRvBinding):RecyclerView.ViewHolder(itemRv.root){
        fun onBind( user: User, position:Int){
            itemRv.lessonName.text = user.lessonName1.toString()
            itemRv.grop.text = user.group1.toString()
            itemRv.room.text = user.room1.toString()
            itemRv.time1.text = user.time1.toString()
            itemRv.root.animation= AnimationUtils.loadAnimation(context, R.anim.anim1)
            itemRv.imageMore.setOnClickListener { it as ImageView
                rvClick.onCLick(user, position,it)
            }




        }
    }


    interface RvClick{
        fun onCLick(user: User, position: Int,imageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
       holder.onBind(list[position], position)


    }

    override fun getItemCount(): Int = list.size

}