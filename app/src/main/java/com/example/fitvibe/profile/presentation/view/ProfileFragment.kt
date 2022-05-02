package com.example.fitvibe.profile.presentation.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
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

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
        initResultListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        setProfileImage()
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
            if (requestKey != ProfileEditFragment.PROFILE_EDIT_FRAGMENT_FLAG) return@setFragmentResultListener
            val name = bundle.getString(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_NAME)
            if (!name.isNullOrBlank()) binding.userNameTextView.text = name
        }
    }


    private fun setProfileImage() {
        val encodedImage: String? =
            sharedPref.getString(ProfileEditFragment.PROFILE_PHOTO_CODE_KEY, "")
        if (!encodedImage.isNullOrBlank()) {
            val image = decodeImage(encodedImage)
            if (image != null) binding.profileImageView.setImageBitmap(image)
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

}