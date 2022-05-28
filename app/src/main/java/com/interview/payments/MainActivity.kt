package com.interview.payments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.interview.payments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findParentNavController(R.id.navHostFragment) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.furnitureFragment -> updateToolbar(R.string.toolbar_furniture_title, false)
                R.id.furnitureDetailsFragment -> updateToolbar(R.string.toolbar_furniture_details_title, true)
                R.id.paymentFragment -> updateToolbar(R.string.toolbar_furniture_payment_title, true)
                else -> { }
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }

    private fun updateToolbar(@StringRes title: Int, isArrowBack: Boolean) = with(binding) {
        toolbar.setTitle(title)
        toolbar.navigationIcon = if (isArrowBack)
            ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_start_arrow) else null
    }
}

fun AppCompatActivity.findParentNavController(@IdRes navHostFragmentResId: Int): NavHostController {
    val navHostFragment = supportFragmentManager.findFragmentById(navHostFragmentResId)
    return (NavHostFragment.findNavController(navHostFragment!!) as NavHostController)
}