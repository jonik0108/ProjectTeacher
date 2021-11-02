package com.zadira.test3.Fragment

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import com.zadira.projectteacher.R
import com.zadira.projectteacher.databinding.FragmentFourBinding
import com.zadira.test3.Adapter.RvAdapter
import com.zadira.test3.Model.User
import com.zadira.projectteacher.SharedPref.MyShafePreferens4
import kotlinx.android.synthetic.main.custom_dialog.view.*


class Four : Fragment() {


    lateinit var binding:FragmentFourBinding
    lateinit var rvAdapter: RvAdapter
    val list = ArrayList<User>()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFourBinding.inflate(LayoutInflater.from(context))
        context?.let { MyShafePreferens4.init(it) }
        val list = MyShafePreferens4.objectsString
        binding.add.setOnClickListener {
            val alertDialog = context?.let { it1 -> androidx.appcompat.app.AlertDialog.Builder(it1) }
            val dialog = alertDialog?.create()
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null, false)
            dialog?.setView(dialogView)
            dialogView.mondayTime.setOnClickListener {
                val timePickerDialog = TimePickerDialog(
                    context,
                    { view, hourOfDay, minute ->
                        dialogView.mondayTime.setText("$hourOfDay:$minute")
                    },
                    24,
                    60,
                    true
                )
                timePickerDialog.updateTime(12, 50)
                timePickerDialog.show()

            }
            dialogView.btn_close.setOnClickListener {
                val lessonName=dialogView.mondayLessonName.text.toString().trim()
                val group=dialogView.mondayGroup.text.toString().trim()
                val room=dialogView.mondayRoom.text.toString().trim()
                val time=dialogView.mondayTime.text.toString().trim()
                if (lessonName!="" && group!="" && room!="" && time!=""){
                    list.add(User(lessonName,group,room,time))
                }else{
                    Toast.makeText(context, "Чтобы добавить уведомление введите все данные!!", Toast.LENGTH_SHORT).show()
                }
                MyShafePreferens4.objectsString=list



                dialog?.dismiss()
                onResume()
            }
            dialog?.show()
        }
        return binding.root
    }
   /* override fun onResume() {

        context?.let { MyShafePreferens4.init(it) }
        val list = MyShafePreferens4.objectsString
        rvAdapter = RvAdapter(requireContext(),list, object : RvAdapter.RvClick{
            override fun onCLick(user: User, position: Int, imageView: ImageView) {
                showPopUp(imageView,position)
            }

        })
       binding.rv4.adapter=rvAdapter
        super.onResume()

    }*/
    fun showPopUp(imageView: ImageView, position: Int) {
        context?.let { MyShafePreferens4.init(it) }
        val list = MyShafePreferens4.objectsString
        val popupMenu = PopupMenu(context,imageView)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.header_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.delete -> {
                    val alertDialog = context?.let { it1 ->
                        androidx.appcompat.app.AlertDialog.Builder(
                            it1
                        )
                    }
                    alertDialog?.setTitle("Удалить безвозвратно?")
                    alertDialog?.setCancelable(false)
                    alertDialog?.setPositiveButton("yes"
                    ) { p0, p1 ->
                        Toast.makeText(context, "Yes", Toast.LENGTH_SHORT).show()
                        list.removeAt(position)
                        MyShafePreferens4.objectsString=list
                        onResume()
                    }
                    alertDialog?.setNegativeButton("no"
                    ) { p0, p1 ->
                        Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
                        onResume()
                    }

                    alertDialog?.show()

                }
                /*     R.id.refactor -> {
                         val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
                         val dialog = alertDialog.create()
                         val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null, false)
                         dialog.setView(dialogView)
                         myDbHelper.deleteUser(list[position])
                         dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                         dialogView.save.setOnClickListener {
                             val name = dialogView.name.text.toString().trim()
                             val number = dialogView.number.text.toString().trim()
                             val user = User(name,number)
                             myDbHelper.addUser(user)
                             binding.rv.animation= AnimationUtils.loadAnimation(this, R.anim.anim1)
                             onResume()
                             dialog.dismiss()

                         }
                         dialog.show()

                     }*/


            }
            true
        }
    }


}