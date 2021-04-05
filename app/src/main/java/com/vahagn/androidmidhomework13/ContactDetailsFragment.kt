package com.vahagn.androidmidhomework13

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vahagn.androidmidhomework13.ContactsFragment.Companion.chosenContactIndex
import com.vahagn.androidmidhomework13.databinding.FragmentContactDetailsBinding
import java.util.*

class ContactDetailsFragment : Fragment() {
    private var _binding: FragmentContactDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iconText.text = ContactsFragment.contacts[chosenContactIndex].name.substring(0,1)
        binding.iconText.setBackgroundColor(Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256)))
        binding.iconText.setTextColor(Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256)))
        binding.nameText.text = ContactsFragment.contacts[chosenContactIndex].name
        binding.numberText.text = ContactsFragment.contacts[chosenContactIndex].phoneNumber
        binding.type.text = ContactsFragment.contacts[chosenContactIndex].type.name
        binding.group.text = ContactsFragment.contacts[chosenContactIndex].group
        binding.lastCall.text = ContactsFragment.contacts[chosenContactIndex].lastCall?.toString() ?: "No last call."
    }
}