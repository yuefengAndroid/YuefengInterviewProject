package com.example.yuefenginterviewproject.ui.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yuefenginterviewproject.BaseFragment
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.databinding.FragmentMemberBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MemberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemberFragment : BaseFragment<FragmentMemberBinding>() {

    override val resId: Int = R.layout.fragment_member
    override fun initAfterBinding() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member, container, false)
    }

}