package tech.ascendio.mvvm.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.subreddit_images_fragment.*
import tech.ascendio.mvvm.R
import tech.ascendio.mvvm.databinding.SubredditImagesFragmentBinding
import tech.ascendio.mvvm.di.Injectable
import tech.ascendio.mvvm.testing.OpenForTesting
import tech.ascendio.mvvm.ui.adapters.ImageAdapter
import tech.ascendio.mvvm.util.AppExecutors
import tech.ascendio.mvvm.util.Constants.DEFAULT_SUBREDDIT
import tech.ascendio.mvvm.util.onSearch
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

        val adapter = ImageAdapter(dataBindingComponent, appExecutors) {}

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

        imagesViewModel.setSubreddit(DEFAULT_SUBREDDIT)

        getMainActivity().setSupportActionBar(tbMain)
        getMainActivity().supportActionBar?.title = DEFAULT_SUBREDDIT
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Configure the search info and add any event listeners...
        searchView.queryHint = getString(R.string.search_hint)
        searchView.onSearch {
            imagesViewModel.setSubreddit(it?.trim())
            getMainActivity().supportActionBar?.title = it
            hideKeyboard(activity!!)
            searchItem.collapseActionView()
        }

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onBoundViews() {
        setHasOptionsMenu(true)
    }
}