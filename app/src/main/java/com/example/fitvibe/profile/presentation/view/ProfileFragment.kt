package com.example.fitvibe.profile.presentation.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResultListener
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ProfileFragmentBinding
import com.example.fitvibe.profile.presentation.viewmodel.ProfileViewModel
import timber.log.Timber

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initResultListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("OnDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("hello", "ProfileFragmentDestroyed")
    }

    private fun initListener() {
        binding.myDataTextView.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ProfileEditFragment>(R.id.container)
                addToBackStack(ProfileEditFragment.TAG)
            }
        }
    }

    private fun initResultListener() {
        setFragmentResultListener(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_FLAG) { requestKey: String, bundle: Bundle ->
            val decodedImage: String? = bundle.getString(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_IMAGE)
            if (decodedImage != null) {
                val image = decodeImage(decodedImage)
                if (image != null) binding.profileEditImageView.setImageBitmap(image)
            }
            val name = bundle.getString(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_NAME)
            if (name != null) {
                binding.userNameTextView.text = name
            }
        }
    }

    private fun decodeImage(decodedImage: String): Bitmap? {
        val bytes = Base64.decode(decodedImage, Base64.DEFAULT)
        val imageBitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val emptyBitmap =
            Bitmap.createBitmap(imageBitmap.width, imageBitmap.height, imageBitmap.config)
        if (!imageBitmap.sameAs(emptyBitmap)) return imageBitmap
        return null
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

}