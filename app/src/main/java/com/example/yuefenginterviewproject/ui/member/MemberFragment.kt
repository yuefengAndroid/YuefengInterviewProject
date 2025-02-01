package com.example.yuefenginterviewproject.ui.member

import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.BaseFragment
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.databinding.FragmentMemberBinding

class MemberFragment : BaseFragment<FragmentMemberBinding>() {

    override val resId: Int = R.layout.fragment_member

    private val memberViewModel: MemberViewModel by lazy {
        ViewModelProvider(this)[MemberViewModel::class.java]
    }

    override fun initAfterBinding() {
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.memberModel = memberViewModel
    }
}