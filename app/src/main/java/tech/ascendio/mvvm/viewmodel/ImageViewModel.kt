package tech.ascendio.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import tech.ascendio.mvvm.data.repository.GalleryRepository
import tech.ascendio.mvvm.data.vo.Image
import tech.ascendio.mvvm.data.vo.Resource
import tech.ascendio.mvvm.testing.OpenForTesting
import tech.ascendio.mvvm.util.AbsentLiveData
import javax.inject.Inject

@OpenForTesting
class ImageViewModel
@Inject constructor(galleryRepository: GalleryRepository) : ViewModel() {
    private val _subreddit = MutableLiveData<String>()
    val subreddit: LiveData<String>
        get() = _subreddit
    val subredditImages: LiveData<Resource<List<Image>>> = Transformations
            .switchMap(_subreddit) { subreddit ->
                if (subreddit == null) {
                    AbsentLiveData.create()
                } else {
                    galleryRepository.getSubredditGalleries(subreddit)
                }
            }

    fun setSubreddit(subreddit: String?) {
        if (_subreddit.value != subreddit) {
            _subreddit.value = subreddit
        }
    }
}