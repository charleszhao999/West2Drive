package w2.jan29th.west2drive

import androidx.room.*

@Dao
interface DLPicDao {
    @Insert
    fun insertDLPic(dlPic: DLPic):Long

    @Update
    fun updateDLPic(newDLPic: DLPic)

    @Query("select * from DLPic")
    fun loadALLDLPics():List<DLPic>

    @Delete
    fun deleteDLPic(dlPic: DLPic)


}