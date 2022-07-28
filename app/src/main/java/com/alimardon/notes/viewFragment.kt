package com.alimardon.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alimardon.notes.Activity.recyclerAdapter
import com.alimardon.notes.Activity.room.Note
import com.alimardon.notes.Activity.room.NoteDataBase
import com.alimardon.notes.databinding.FragmentViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class viewFragment : Fragment() {
    lateinit var binding: FragmentViewBinding
    lateinit var adapter: recyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = recyclerAdapter()
        adapter.setOnItemClickListener(object : recyclerAdapter.OnItemClickListener {
            override fun delete(note: Note) {
                GlobalScope.launch(Dispatchers.IO) {
                    delete(note)
                }
            }

            override fun update(note: Note) {
                val action = viewFragmentDirections.action_viewFragment_to_addFragment(note)
                findNavController().navigate(action)
            }
        })
        GlobalScope.launch(Dispatchers.IO) { setlist()}
        binding.recyclerview.adapter = adapter
        binding.btnplus.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_addFragment)

        }
    }

    fun setlist() {
        val notes =
            NoteDataBase.DatabaseBuilder.getdatabase(requireContext()).noteDao().getAllNotes()
        adapter.submitList(notes)
    }

    suspend fun deleteNote(note: Note) {
        NoteDataBase.DatabaseBuilder.getdatabase(requireContext()).noteDao().deleteNote(note)
    }
}