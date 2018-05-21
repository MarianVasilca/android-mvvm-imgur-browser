package tech.ascendio.mvvm.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import tech.ascendio.mvvm.data.vo.Image
import tech.ascendio.mvvm.testing.OpenForTesting

/**
 * Interface for database access for Image related operations.
 */
@Dao
@OpenForTesting
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(repositories: List<Image>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image)

    @Query("SELECT * FROM Image WHERE section in (:subreddit)")
    fun loadBySubreddit(subreddit: String): LiveData<List<Image>>
}