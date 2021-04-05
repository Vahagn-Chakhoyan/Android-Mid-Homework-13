package com.vahagn.androidmidhomework13

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahagn.androidmidhomework13.databinding.FragmentContactsBinding
import java.text.SimpleDateFormat
import kotlin.properties.Delegates

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contacts.layoutManager = LinearLayoutManager(requireContext())
        binding.contacts.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.contacts.adapter = ContactsAdapter( {contactClicked(it)}, {deleteContactByIndex(it)})
        binding.cancelButton.setOnClickListener { binding.confirmDelete.visibility = View.GONE }
        binding.deleteButton.setOnClickListener { binding.confirmDelete.visibility = View.GONE; confirmDelete() }
        binding.createNewContact.setOnClickListener { findNavController().navigate(R.id.goToNewContact) }
    }

    override fun onResume() {
        super.onResume()
        if(contactAdded) binding.contacts.adapter?.notifyDataSetChanged()
        contactAdded = false
    }

    private fun contactClicked(index: Int) {
        chosenContactIndex = index

        findNavController().navigate(R.id.goToDetails)
    }

    private fun confirmDelete() {
        contacts.removeAt(indexToDelete)
        binding.contacts.adapter?.notifyDataSetChanged()
    }

    private fun deleteContactByIndex(index: Int): Boolean {
        indexToDelete = index
        binding.confirmDelete.visibility = View.VISIBLE

        return true
    }

    companion object {
        private var formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        val contacts = mutableListOf(
            Contact("C1", "098000001", "Work", formatter.parse("15/03/2021"), PhoneType.mobile),
            Contact("C2", "098000002", "Work", formatter.parse("15/03/2020"), PhoneType.mobile),
            Contact("My Contact", "098000003", "", formatter.parse("15/01/2021"), PhoneType.mobile),
            Contact("Name", "098000004", "", formatter.parse("17/03/2020"), PhoneType.mobile),
            Contact("Contact Name", "098000005", "", formatter.parse("24/03/2021"), PhoneType.mobile),
            Contact("Undefined", "098000006", "Unknowns", formatter.parse("25/03/2021"), PhoneType.mobile),
            Contact("Unnamed", "098000007", "Unknowns", formatter.parse("26/03/2021"), PhoneType.mobile),
            Contact("Some Contact", "098000008", "Unknowns", formatter.parse("26/03/2021"), PhoneType.mobile),
            Contact("Some Name", "098000009", "Unknowns", formatter.parse("01/03/2021"), PhoneType.mobile),
            Contact("Mr. Someone", "098000000", "Work", formatter.parse("02/03/2021"), PhoneType.mobile),
            Contact("Mrs. Someone", "098100000", "Work", formatter.parse("15/02/2021"), PhoneType.mobile),
            Contact("Contact Name2", "098200000", "", formatter.parse("16/02/2021"), PhoneType.mobile),
            Contact("098300000", "098300000", "Unknowns", formatter.parse("15/04/2020"), PhoneType.mobile),
            Contact("Name2", "098400000", "", formatter.parse("15/03/2020"), PhoneType.mobile),
            Contact("Name3", "010659565", "", formatter.parse("15/03/2019"), PhoneType.city),
            Contact("Name4", "010295664", "", formatter.parse("15/03/2019"), PhoneType.city),
            Contact("Name5", "010984611", "", formatter.parse("15/03/2019"), PhoneType.city),
        )

        var chosenContactIndex by Delegates.notNull<Int>()

        private var indexToDelete by Delegates.notNull<Int>()

        var contactAdded: Boolean = false
    }
}