package com.example.fitvibe.main.presentation.main.view

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
import com.example.fitvibe.R
import com.example.fitvibe.databinding.MainFragmentBinding
import com.example.fitvibe.main.presentation.main.adapter.FitnessClickListener
import com.example.fitvibe.main.presentation.main.adapter.FitnessListAdapter
import com.example.fitvibe.main.presentation.trainers_list.view.MainTrainersListFragment
import com.example.fitvibe.profile.presentation.view.ProfileEditFragment
import com.example.fitvibe.utils.fitnessList
import org.koin.android.ext.android.inject

class MainFragment : Fragment(), FitnessClickListener {

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


    override fun onFitnessClick(fitnessValue: String) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MainTrainersListFragment>(R.id.fragment_main_container, MainTrainersListFragment.TAG)
            addToBackStack(MainTrainersListFragment.TAG)
        }
    }


    private fun initViews() {
        val adapter = FitnessListAdapter(this)
        adapter.setList(fitnessList)
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

    companion object {
        const val TAG = "main_fragment"
    }
}