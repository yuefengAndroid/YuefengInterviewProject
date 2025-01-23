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
    private lateinit var memberViewModel: MemberViewModel

    override fun initAfterBinding() {
        val memberRepository = MemberRepository()
        memberViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(MemberViewModel::class.java).apply {
            setRepository(memberRepository) // 手动注入 Repository
        }
        binding.memberModel = memberViewModel
    }
}