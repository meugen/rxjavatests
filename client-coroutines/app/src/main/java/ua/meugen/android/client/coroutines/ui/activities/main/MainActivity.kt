package ua.meugen.android.client.coroutines.ui.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ua.meugen.android.client.coroutines.R
import ua.meugen.android.client.coroutines.ui.activities.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
