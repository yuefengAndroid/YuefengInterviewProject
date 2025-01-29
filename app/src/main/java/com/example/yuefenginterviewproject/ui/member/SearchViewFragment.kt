package com.example.yuefenginterviewproject.ui.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yuefenginterviewproject.ColorFactory
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.databinding.FragmentSearchViewBinding


class SearchViewFragment : Fragment() {
    private lateinit var binding: FragmentSearchViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者

        binding.tcTagMore.maxLines = 3
        binding.tcTagMore.theme = ColorFactory.NONE
        binding.tcTagMore.tagMaxLength = 5

        binding.tcTagMore2.maxLines = 3
        binding.tcTagMore2.theme = ColorFactory.NONE
        binding.tcTagMore2.tagMaxLength = 5

        context?.let { ctx ->
            binding.tcTagMore.tagBackgroundColor = ContextCompat.getColor(ctx, R.color.color_ededed)
            binding.tcTagMore.tagTextColor = ContextCompat.getColor(ctx, R.color.color_272727)
            binding.tcTagMore.tagBorderColor = ContextCompat.getColor(ctx, R.color.color_ededed)

            binding.tcTagMore2.tagBackgroundColor = ContextCompat.getColor(ctx, R.color.color_ededed)
            binding.tcTagMore2.tagTextColor = ContextCompat.getColor(ctx, R.color.color_272727)
            binding.tcTagMore2.tagBorderColor = ContextCompat.getColor(ctx, R.color.color_ededed)
        }
        val list = arrayListOf<String?>("日安玩美","資生堂","禮盒","衛生紙","貓砂","溫泉泡湯券","Timberland")
        val list2 = arrayListOf<String?>("衛生紙","資生堂")
        binding.tcTagMore.tags = list
        binding.tcTagMore2.tags = list2
        return binding.root
    }

}