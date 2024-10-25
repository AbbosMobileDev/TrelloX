package com.abisoft.trellox.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.util.query
import com.abisoft.trellox.R
import com.abisoft.trellox.databinding.FragmentDetailBinding
import com.abisoft.trellox.model.TaskRow
import com.bumptech.glide.Glide

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = arguments?.getParcelable<TaskRow>("key")
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        task?.let {
            binding.apply {
                txtName.text = it.name
                txtTaskId.text = it.taskId.toString()
                txtIndex.text = it.index.toString()
                txtProjectId.text = it.projectId
                txtProjectName.text = it.projectName
                txtOwnerId.text = it.ownerId.toString()
                txtOwnerName.text = it.ownerName
                txtExcutorId.text = it.executorId.toString()
                txtExcutorName.text = it.executorName
                txtTaskData.text = it.taskData
                txtTermData.text = it.termData
                txtPriority.text = it.priority
                txtStatus.text = it.status

                Glide.with(requireContext())
                    .load(it.ownerAvatar)
                    .placeholder(R.drawable.circle_check)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imgOwnerAvatar)

                Glide.with(requireContext())
                    .load(it.executorAvatar)
                    .placeholder(R.drawable.circle_check)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imgExcutorAvatar)
            }

        }

    }
}
