package com.android.multiplescreens

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * The first activity is displayed.
 * You can display the second screen by tapping the button
 */
class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSecondActivityOnSecondaryDisplay()

        // load app 1
        val webview = findViewById<WebView>(R.id.webView1)
        webview.settings.javaScriptEnabled = true
        webview.loadUrl("file:///android_asset/app1.html")
    }

    /**
     * Navigate to the second screen.
     *
     * See more at https://developer.android.com/guide/topics/ui/foldables?#using_secondary_screens
     */
    private fun showSecondActivityOnSecondaryDisplay() {
        // DisplayManager manages the properties of attached displays.
        val displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

        // List displays was attached
        val displays = displayManager.displays

        if (displays.size > 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Activity options are used to select the display screen.
                val options = ActivityOptions.makeBasic()
                // Select the display screen that you want to show the second activity
                options.launchDisplayId = displays[1].displayId
                // To display on the second screen that your intent must be set flag to make
                // single task (combine FLAG_ACTIVITY_CLEAR_TOP and FLAG_ACTIVITY_NEW_TASK)
                // or you also set it in the manifest (see more at the manifest file)
                startActivity(
                    Intent(this@MainActivity, SecondActivity::class.java),
                    options.toBundle()
                )
            }
        } else {
            Toast.makeText(this, "Not found the second screen", Toast.LENGTH_SHORT).show()
        }
    }
}
