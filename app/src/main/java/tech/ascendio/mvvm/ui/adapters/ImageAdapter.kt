package tech.ascendio.mvvm.ui.adapters

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import tech.ascendio.mvvm.R
import tech.ascendio.mvvm.data.vo.Image
import tech.ascendio.mvvm.databinding.ImageItemBinding
import tech.ascendio.mvvm.ui.common.DataBoundListAdapter
import tech.ascendio.mvvm.util.AppExecutors

class ImageAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val callback: ((Image) -> Unit)?
) : DataBoundListAdapter<Image, ImageItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.url == newItem.url
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ImageItemBinding {
        val binding = DataBindingUtil
                .inflate<ImageItemBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.image_item,
                        parent,
                        false,
                        dataBindingComponent
                )
        binding.root.setOnClickListener {
            binding.image?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ImageItemBinding, item: Image) {
        binding.image = item
    }
}