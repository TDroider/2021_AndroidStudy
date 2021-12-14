package com.example.mvvmsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvvmsample.R
import com.example.mvvmsample.model.QiitaTagListRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = SupervisorJob()
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
