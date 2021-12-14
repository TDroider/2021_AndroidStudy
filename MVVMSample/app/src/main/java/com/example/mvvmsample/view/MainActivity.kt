package com.example.mvvmsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mvvmsample.R
import com.example.mvvmsample.controller.MainActivityController
import com.example.mvvmsample.databinding.ActivityMainBinding
import com.example.mvvmsample.model.QiitaTagListRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding: ActivityMainBinding
    private var adapter: QiitaTagRecyclerAdapter? = null

    private val controller = MainActivityController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = SupervisorJob()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val sortType = when (checkedId) {
                binding.radioName.id -> binding.radioName.text.toString()
                binding.radioCount.id -> binding.radioCount.text.toString()
                else -> ""
            }
            fetch(sortType)
        }
    }

    private fun fetch(sortType: String) {
        launch {
            val result = controller.fetch(sortType)
            if (binding.recycler.adapter == null) {
                adapter = QiitaTagRecyclerAdapter(result)
                binding.recycler.adapter = adapter
            } else {
                adapter?.refresh(result)
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
