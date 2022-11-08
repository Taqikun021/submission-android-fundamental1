package com.negate.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.negate.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = when {
            SDK_INT >= 33 -> intent?.getParcelableExtra("bundle", User::class.java)
            else -> @Suppress("DEPRECATION") intent?.getParcelableExtra<User>("bundle") as User
        }

        supportActionBar?.title = user?.name

        binding.apply {
            username.text = user?.username
            name.text = user?.name
            location.text = user?.location
            repository.text = user?.repository
            company.text = user?.company
            follow.text = getString(R.string.follow, user?.followers, user?.following)
            avatar.load(user?.avatar) {
                crossfade(400)
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            shareButton.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/${user?.username}")
                    )
                )
            }
        }
    }
}