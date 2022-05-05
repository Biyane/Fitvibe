package com.example.fitvibe.main.presentation.view

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitvibe.databinding.MainFragmentBinding
import com.example.fitvibe.main.presentation.adapter.FitnessListAdapter
import com.example.fitvibe.profile.presentation.view.ProfileEditFragment
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedPref: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = FitnessListAdapter()
        with (binding) {
            fitnessRecyclerView.adapter = adapter
            val encodedImage = sharedPref.getString(ProfileEditFragment.PROFILE_PHOTO_CODE_KEY, null)
                ?: return@with
            val image = decodeImage(encodedImage)
            headerSectionView.profileImageView.setImageBitmap(image)
        }
    }

    private fun decodeImage(encodedImage: String): Bitmap? {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        val imageBitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val emptyBitmap =
            Bitmap.createBitmap(imageBitmap.width, imageBitmap.height, imageBitmap.config)
        if (!imageBitmap.sameAs(emptyBitmap)) return imageBitmap
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}