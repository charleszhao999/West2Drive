package w2.jan29th.west2drive

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Picture
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import w2.jan29th.west2drive.ui.login.LoginActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private val folderList = ArrayList<Folder>()
    private val picList = ArrayList<Pic>()
    var adapter = PicAdapter(this, picList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val main_toolbar = findViewById<View>(R.id.main_toolbar)
        setSupportActionBar(main_toolbar as androidx.appcompat.widget.Toolbar?)
        val fab=findViewById<View>(R.id.mainfloatingActionButton)
        fab.setOnClickListener{

            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivity(intent)

        }
        if (!isDarkTheme(this@MainActivity)!!) {
            setDarkStatusIcon(true)
        }
        val layoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        recyclerView.layoutManager = layoutManager
        adapter = PicAdapter(this, picList)
        recyclerView.adapter = adapter
        picList.add(Pic("lighthouses.png", "2021.1.29", R.drawable.head3))
        picList.add(Folder("FOAM ARTS"))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.remove_all -> {
                picList.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "All removed", Toast.LENGTH_SHORT).show()
            }
            R.id.exit -> finish()
            else -> {
            }
        }
        return true
    }

    internal fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                picList.add(
                    0,
                    Pic(
                        data.getStringExtra("group_name")!!,
                        data.getStringExtra("group_creator")!!,
                        data.getIntExtra("group_head", 1)
                    )
                )
                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Added", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    fun setDarkStatusIcon(bDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (decorView != null) {
                var vis = decorView.systemUiVisibility
                vis = if (bDark) {
                    vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                decorView.systemUiVisibility = vis
            }
        }
    }

    fun isDarkTheme(context: Context): Boolean? {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }

}