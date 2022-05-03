package com.example.fitvibe.profile.presentation.view

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.fitvibe.R
import com.example.fitvibe.databinding.BottomSheetProfileEditBinding
import com.example.fitvibe.databinding.ProfileEditFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayOutputStream

class ProfileEditFragment : Fragment() {

    private var _binding: ProfileEditFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileEditFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListener() {
        saveButtonPressed()
        onChangePhotoClick()
        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        binding.myToolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get(CAMERA_DATA_FLAG) as? Bitmap
            if (imageBitmap != null) {
                binding.profileImageView.setImageBitmap(imageBitmap)
                savePhotoInsideSharedPreference(imageBitmap)
            }

        }
    }

    private fun savePhotoInsideSharedPreference(imageBitmap: Bitmap) {
        val photoStringBase: String = getPhotoStringBase(imageBitmap)
        with(sharedPref.edit()) {
            putString(PROFILE_PHOTO_CODE_KEY, photoStringBase)
            apply()
        }
    }

    private fun getPhotoStringBase(imageBitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val bytes = baos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private fun initViews() {
        val decodedImageString: String? = sharedPref.getString(PROFILE_PHOTO_CODE_KEY, "")
        if (decodedImageString.isNullOrBlank()) return
        val bytes = Base64.decode(decodedImageString, Base64.DEFAULT)
        val imageBitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val emptyBitmap =
            Bitmap.createBitmap(imageBitmap.width, imageBitmap.height, imageBitmap.config)
        if (!imageBitmap.sameAs(emptyBitmap)) binding.profileImageView.setImageBitmap(
            imageBitmap
        )
    }

    private fun saveButtonPressed() {
        binding.saveDataButton.setOnClickListener {
            saveName()
            val bundle = gatherEditedData()
            setFragmentResult(PROFILE_EDIT_FRAGMENT_FLAG, bundle)
            parentFragmentManager.popBackStack()
        }
    }


    private fun saveName() {
        with (sharedPref.edit()) {
            putString(PROFILE_EDIT_FRAGMENT_NAME, binding.nameEditText.text.toString())
            apply()
        }
    }

    private fun gatherEditedData(): Bundle = bundleOf(
            PROFILE_EDIT_FRAGMENT_NAME to binding.nameEditText.text.toString()
        )

    private fun onChangePhotoClick() {
        binding.changeProfilePhoto.setOnClickListener {
            val dialogBinding =
                BottomSheetProfileEditBinding.inflate(LayoutInflater.from(requireContext()))
            val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
            dialog.setContentView(dialogBinding.root)
            dialog.show()
            dialogBinding.closeImageView.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.takePhotoTextView.setOnClickListener {
                handlePhotoTake()
                dialog.dismiss()
            }

            dialogBinding.chooseFromGalleryTextView.setOnClickListener {

            }
        }
    }

    private fun handlePhotoTake() {
        if (isPermissionGranted()) {
            dispatchTakePictureIntent()
        } else {
            requestPermission()
        }
    }

    private fun isPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Camera not available", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
    }

    companion object {
        const val TAG = "profile_edit_fragment"
        const val PROFILE_EDIT_FRAGMENT_FLAG = "profile_edit_fragment_flag"
        const val PROFILE_EDIT_FRAGMENT_NAME = "profile_edit_fragment_name"
        const val PROFILE_PHOTO_CODE_KEY = "profile_photo_code"

        private const val CAMERA_DATA_FLAG = "data"
        private const val CAMERA_PERMISSION_CODE = 1000
        private const val REQUEST_IMAGE_CAPTURE = 1

    }

}
