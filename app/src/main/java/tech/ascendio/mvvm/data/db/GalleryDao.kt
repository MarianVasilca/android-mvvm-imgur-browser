package tech.ascendio.mvvm.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import tech.ascendio.mvvm.data.vo.Gallery

/**
 * Interface for database access for User related operations.
 */
@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gallery: Gallery)
}