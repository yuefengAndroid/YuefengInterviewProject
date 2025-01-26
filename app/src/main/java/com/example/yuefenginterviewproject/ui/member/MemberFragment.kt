package com.example.yuefenginterviewproject.ui.member

import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.BaseFragment
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.data.repository.MemberRepository
import com.example.yuefenginterviewproject.databinding.FragmentMemberBinding
import com.example.yuefenginterviewproject.databinding.LayoutLoginButtonBinding

class MemberFragment : BaseFragment<FragmentMemberBinding>() {

    override val resId: Int = R.layout.fragment_member
    private lateinit var loginButtonBinding: LayoutLoginButtonBinding

    private val memberViewModel: MemberViewModel by lazy {
        ViewModelProvider(this)[MemberViewModel::class.java]
    }

    override fun initAfterBinding() {
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.memberModel = memberViewModel
    }
}