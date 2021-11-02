package com.zadira.projectteacher

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.zadira.projectteacher.Fragment.Two
import com.zadira.test3.Adapter.MyFragmentAdapter
import com.zadira.test3.Animation.MyAnimaClass
import com.zadira.test3.Fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_tab.view.*

class MainActivity : AppCompatActivity() {
    lateinit var list:ArrayList<Fragment>
    lateinit var myFragmentAdapter: MyFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        loadData()
        myFragmentAdapter = MyFragmentAdapter(list, supportFragmentManager)
        view_pager.adapter = myFragmentAdapter
        view_pager.setPageTransformer(true, MyAnimaClass())
        tab_layout1.setupWithViewPager(view_pager)
        setTab()
        tab_layout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
           override fun onTabSelected(tab: TabLayout.Tab?) {
               tab?.customView?.item_txt_tab?.setTextColor(Color.GREEN)
               tab?.customView?.indicator_tab?.visibility = View.VISIBLE
           }
           override fun onTabUnselected(tab: TabLayout.Tab?) {
               tab?.customView?.item_txt_tab?.setTextColor(Color.WHITE)
               tab?.customView?.indicator_tab?.visibility = View.INVISIBLE
           }
           override fun onTabReselected(tab: TabLayout.Tab?) {

           }
       })
    }
    private fun loadData() {

        list = ArrayList()
        list.add(One())
        list.add(Two())
        list.add(Three())
        list.add(Four())
        list.add(Five())
        list.add(Six())


    }
    private fun setTab() {
        val tabCount = tab_layout1.tabCount
        val monday=getString(R.string.monday)
        val tuesday= getString(R.string.tuesday)
        val wednesday=getString(R.string.wednesday)
        val thursday=getString(R.string.thursday)
        val friday=getString(R.string.friday)
        val saturday=getString(R.string.saturday)
        val tabList= arrayListOf<String>(monday, tuesday,wednesday,thursday,friday,saturday)
        for (i in 0 until tabCount){
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null, false)
            val tab = tab_layout1.getTabAt(i)
            tab?.customView = view
            view.item_txt_tab.text = tabList[i]
            if (i == 0){
                view.item_txt_tab.setTextColor(Color.WHITE)
                view.indicator_tab.visibility = View.VISIBLE
            }else{
                view.item_txt_tab.setTextColor(Color.WHITE)
                view.indicator_tab.visibility = View.INVISIBLE
            }
        }
    }

}