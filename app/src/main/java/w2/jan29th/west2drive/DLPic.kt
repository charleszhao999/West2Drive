package w2.jan29th.west2drive

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DLPic(var name: String, var time: String, var path: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
