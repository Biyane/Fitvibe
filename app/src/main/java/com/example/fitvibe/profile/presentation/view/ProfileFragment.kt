package com.example.fitvibe.profile.presentation.view

import android.content.Intent
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
import com.example.fitvibe.base.presentation.MainActivity
import com.example.fitvibe.databinding.ProfileFragmentBinding
import com.example.fitvibe.launch.presentation.LaunchActivity
import com.example.fitvibe.registration.presentation.SmsCodeFragment
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedPref: SharedPreferences by inject()


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
        setUserName()
    }

    private fun initListener() {
        binding.myDataTextView.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ProfileEditFragment>(R.id.container)
                addToBackStack(ProfileEditFragment.TAG)
            }
        }
        binding.myBonusTextView.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ProfileBonusFragment>(R.id.container)
                addToBackStack(ProfileBonusFragment.TAG)
            }
        }
        binding.favouritesTextView.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ProfileFavouritesFragment>(R.id.container)
                addToBackStack(ProfileFavouritesFragment.TAG)
            }
        }
        binding.logOutTextView.setOnClickListener {
            sharedPref.edit().remove(SmsCodeFragment.AUTH_KEY).apply()
            val intent = Intent(context, LaunchActivity::class.java)
            startActivity(intent)
            (requireActivity() as MainActivity).finish()
        }
    }

    private fun initResultListener() {
        setFragmentResultListener(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_FLAG) { _: String, bundle: Bundle ->
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


    private fun setUserName() {
        val name = sharedPref.getString(ProfileEditFragment.PROFILE_EDIT_FRAGMENT_NAME, "")
        if (!name.isNullOrBlank()) binding.userNameTextView.text = name
    }

}