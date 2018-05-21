package tech.ascendio.mvvm.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import tech.ascendio.mvvm.R
import tech.ascendio.mvvm.databinding.SubredditImagesFragmentBinding
import tech.ascendio.mvvm.di.Injectable
import tech.ascendio.mvvm.testing.OpenForTesting
import tech.ascendio.mvvm.ui.adapters.ImageAdapter
import tech.ascendio.mvvm.util.AppExecutors
import tech.ascendio.mvvm.viewmodel.ImageViewModel
import javax.inject.Inject

@OpenForTesting
class SubredditImagesFragment : BaseFragment<SubredditImagesFragmentBinding>(), Injectable {
    override val layoutResource: Int
        get() = R.layout.subreddit_images_fragment
    override val tag: String
        get() = "SubredditImagesFragment"
    @Inject
    lateinit var appExecutors: AppExecutors
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var imagesViewModel: ImageViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imagesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ImageViewModel::class.java)
        val adapter = ImageAdapter(dataBindingComponent, appExecutors) { image ->
        }
        imagesViewModel.subredditImages.observe(this, Observer { imagesResource ->
            viewDataBinding.imagesResource = imagesResource
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (imagesResource?.data != null) {
                adapter.submitList(imagesResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })

        viewDataBinding.rvImages.adapter = adapter

        imagesViewModel.setSubreddit("aww")
    }

    override fun onBoundViews() {
    }
}