package com.bayut.caching.component.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bayut.caching.component.image.BitmapUtils
import com.bayut.caching.databinding.ContentBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * [Content]
 */
@AndroidEntryPoint
class Content : Fragment() {

    private lateinit var binding: ContentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        arguments.let {
            val args =
                ContentArgs.fromBundle(
                    requireArguments()
                )
            binding.image = args.item
            BitmapUtils.display(
                requireContext(), binding.ivImage,
                args.item.imageUrlsThumbnails?.get(0)
            )
        }
    }
}