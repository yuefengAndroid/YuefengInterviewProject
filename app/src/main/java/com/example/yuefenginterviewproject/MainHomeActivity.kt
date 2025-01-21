package com.example.yuefenginterviewproject

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.data.model.MainHomeViewModel
import com.example.yuefenginterviewproject.databinding.ActivityMainHomeBinding
import com.example.yuefenginterviewproject.ui.bestsellers.bestsellersFragment
import com.example.yuefenginterviewproject.ui.home.HomeFragment
import com.example.yuefenginterviewproject.ui.popularactivities.popularactivitiesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainHomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainHomeBinding
    lateinit var mainModel: MainHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyLog.setLogLevel(1)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_home)
        mainModel = ViewModelProvider(this)[MainHomeViewModel::class.java]

        binding.apply {
            mainHomeMadel = mainModel
            lifecycleOwner = this@MainHomeActivity
            navView.setOnNavigationItemSelectedListener(this@MainHomeActivity)
        }

        mainModel.navId.observe(this, Observer {
            onNavItemSelected(binding.navView.menu.findItem(it))
        })


        onNavigationItemSelected(binding.navView.menu.findItem(R.id.navigation_home))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        mainModel.navId.value = item.itemId
        return  true
    }

    private fun onNavItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.navigation_home -> {
                openNavFrag("Home", HomeFragment())
            }
            R.id.navigation_bestsellers -> {
                openNavFrag("bestsellers", bestsellersFragment())
            }
            R.id.navigation_popularActivities -> {
                openNavFrag("popularactivities", popularactivitiesFragment())
            }

        }
    }

    //開啟Fragment
    private fun openNavFrag(tag: String, frag: Fragment) {
        val fragTransaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) != null)
            fragTransaction.replace(
                R.id.nav_host_fragment_activity_main,
                supportFragmentManager.findFragmentByTag(tag)!!
            )
        else {
            val bundle = mainModel.navBundle.value
            frag.arguments = bundle
            fragTransaction.replace(R.id.nav_host_fragment_activity_main, frag, tag)
        }
        fragTransaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (onBackPressed(supportFragmentManager))
            return
        showAlertDialog(getString(R.string.alert), getString(R.string.sure_to_leave), true)
    }

    private fun onBackPressed(fragmentManager: FragmentManager): Boolean {
        val fragmentList = fragmentManager.fragments
        if (fragmentList.size > 0) {
            for (frag in fragmentList) {
                if (frag == null || !frag.isVisible) {
                    continue
                }

                if (frag is HomeFragment || frag is bestsellersFragment || frag is popularactivitiesFragment) {
                    return false
                }

                if (onBackPressed(frag.childFragmentManager)) {
                    return true
                }
            }
        }

        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            return true
        }

        return false
    }

    private fun showAlertDialog(title: String, message: String, showNegBtn: Boolean) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title).setMessage(message)
            .setPositiveButton(R.string.sure) { _, _ -> finish() }
            .setCancelable(false)

        if (showNegBtn) {
            builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
        }
        val dialog = builder.show()

        if (showNegBtn)
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
    }
}