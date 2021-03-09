package w2.jan29th.west2drive

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        val picName = intent.getStringExtra("pic_name")
        val uploadTime = intent.getStringExtra("upload_time")
        val picImage = intent.getIntExtra("pic_image", 1)
        val groupname = findViewById<View>(R.id.GroupName) as TextView
        val groupcreator = findViewById<View>(R.id.Creator) as TextView
        val grouphead = findViewById<View>(R.id.head) as ImageView
        val fab = findViewById<View>(R.id.floatingActionButton)
        groupname.text = picName
        groupcreator.text = uploadTime
        grouphead.setImageResource(picImage)
        fab.setOnClickListener {
            Toast.makeText(this, "开始下载", Toast.LENGTH_SHORT).show()
        }
    }
}