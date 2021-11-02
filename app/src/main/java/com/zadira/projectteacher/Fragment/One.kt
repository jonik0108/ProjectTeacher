package com.zadira.test3.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TimePicker
import android.widget.Toast
import com.zadira.projectteacher.DB.MyDbHelper
import com.zadira.projectteacher.R
import com.zadira.projectteacher.Service.BackSer
import com.zadira.projectteacher.Service.BackgroundService
import com.zadira.projectteacher.Service.NotificationHelper
import com.zadira.projectteacher.databinding.FragmentOneBinding
import com.zadira.test3.Adapter.RvAdapter
import com.zadira.test3.Model.User
import com.zadira.projectteacher.SharedPref.MyShafePreferens
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.timepicker.view.*
import java.sql.SQLData
import java.sql.Time


class One : Fragment() {
    lateinit var binding:FragmentOneBinding
    lateinit var rvAdapter: RvAdapter
    var list = ArrayList<User>()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOneBinding.inflate(LayoutInflater.from(context))

        context?.let { MyShafePreferens.init(it) }
        binding.a.setOnClickListener {
            context?.startService(Intent(context,BackSer::class.java))
        }
        binding.add.setOnClickListener {
            val alertDialog = context?.let { it1 -> androidx.appcompat.app.AlertDialog.Builder(it1) }
            val dialog = alertDialog?.create()
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null, false)
            dialog?.setView(dialogView)
            dialogView.mondayTime.setOnClickListener {
                val alertDialog1 = AlertDialog.Builder(context)
                val dialog2 = alertDialog1.create()
                val dialogView2 = layoutInflater.inflate(R.layout.timepicker, null, false)
                dialog2.setView(dialogView2)
                dialogView2.simpleTimePicker.setIs24HourView(true)
                dialogView2.simpleTimePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
                    dialogView.mondayTime.setText("$hourOfDay:$minute")
                }
                dialog2.show()
                dialogView2.timesave.setOnClickListener {
                    dialog2.dismiss()
                }

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
                MyShafePreferens.objectsString=list
                dialog?.dismiss()
                onResume()
            }
            dialog?.show()
        }

      return binding.root
    }


    override fun onResume() {

        context?.let { MyShafePreferens.init(it) }
        list=MyShafePreferens.objectsString
        rvAdapter = RvAdapter(requireContext(),list, object : RvAdapter.RvClick{
            override fun onCLick(user: User, position: Int, imageView: ImageView) {
                showPopUp(imageView,position)
            }

        })
        binding.rv.adapter=rvAdapter



        super.onResume()

    }



    fun showPopUp(imageView: ImageView, position: Int) {
        context?.let { MyShafePreferens.init(it) }
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
                        MyShafePreferens.objectsString=list
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