package w2.jan29th.west2drive

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PicAdapter(val context: Context, val picList: List<Pic>) :
    RecyclerView.Adapter<PicAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picName: TextView = view.findViewById(R.id.pic_name)
        val picImage: ImageView = view.findViewById(R.id.pic_image)
        val picUplpadTime: TextView = view.findViewById(R.id.pic_uploadtime)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pic = picList[position]
        holder.picImage.setImageResource(pic.imageId)
        holder.picName.text = pic.name
        holder.picUplpadTime.text = pic.uploadTime
        holder.cardView.setOnClickListener {
            holder.cardView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("pic_name", pic.name)
                intent.putExtra("upload_time", pic.uploadTime)
                intent.putExtra("pic_image", pic.imageId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = picList.size
}

