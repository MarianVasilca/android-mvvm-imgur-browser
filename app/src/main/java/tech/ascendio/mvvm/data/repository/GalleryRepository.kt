package tech.ascendio.mvvm.data.repository

import android.arch.lifecycle.LiveData
import tech.ascendio.mvvm.data.api.ApiResponse
import tech.ascendio.mvvm.data.api.AppService
import tech.ascendio.mvvm.data.api.Response
import tech.ascendio.mvvm.data.db.ImageDao
import tech.ascendio.mvvm.data.vo.Image
import tech.ascendio.mvvm.data.vo.Resource
import tech.ascendio.mvvm.testing.OpenForTesting
import tech.ascendio.mvvm.util.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles User objects.
 */
@OpenForTesting
@Singleton
class GalleryRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val imageDao: ImageDao,
        private val appService: AppService
) {

    fun getSubredditGalleries(
            subreddit: String,
            page: Int = 1,
            sort: String = "hot",
            window: String = "day"
    ): LiveData<Resource<List<Image>>> {
        return object : NetworkBoundResource<List<Image>, Response<List<Image>>>(appExecutors) {
            override fun saveCallResult(items: Response<List<Image>>) = imageDao.insertImages(items.data)
            override fun shouldFetch(data: List<Image>?): Boolean = true
            override fun loadFromDb(): LiveData<List<Image>> = imageDao.loadBySubreddit(subreddit)
            override fun createCall(): LiveData<ApiResponse<Response<List<Image>>>> =
                    appService.getSubredditGalleries(subreddit, sort, window, page)
        }.asLiveData()
    }
}