package com.negate.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.negate.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val list: List<User>
        get() {
            val name = resources.getStringArray(R.array.name)
            val username = resources.getStringArray(R.array.username)
            val location = resources.getStringArray(R.array.location)
            val repository = resources.getStringArray(R.array.repository)
            val company = resources.getStringArray(R.array.company)
            val followers = resources.getStringArray(R.array.followers)
            val following = resources.getStringArray(R.array.following)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val mutableList = mutableListOf<User>()
            for (i in name.indices) {
                mutableList.add(
                    User(
                        username[i],
                        name[i],
                        location[i],
                        repository[i],
                        company[i],
                        followers[i],
                        following[i],
                        avatar.getResourceId(i, -1)
                    )
                )
            }
            return mutableList
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)
        binding.recyclerView.setHasFixedSize(true)
        showData()
    }

    private fun showData() {
        binding.recyclerView.apply {
            val adapters = UserAdapter(list)
            adapter = adapters
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapters.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(item: User) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java)
                            .putExtra("bundle", item)
                    )
                }
            })
        }
    }
}