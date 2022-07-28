package com.alimardon.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alimardon.notes.Activity.recyclerAdapter
import com.alimardon.notes.Activity.room.Note
import com.alimardon.notes.Activity.room.NoteDataBase
import com.alimardon.notes.databinding.FragmentAddBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class addFragment : Fragment() {
    val args: addFragmentArgs by navArgs()
    lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.note == !null) {
            binding.btn.setText("Update")
            binding.ed1.setText(args.note!!.title)
            binding.ed2.setText(args.note!!.description)
        }
        binding.btn.setOnClickListener {
            if (args.note !== null) {
                val note =
                    Note(args.note!!.id, binding.ed1.text.toString(), binding.ed2.text.toString())
                GlobalScope.launch(Dispatchers.IO) {
                    NoteDataBase.DatabaseBuilder.getdatabase(requireContext()).noteDao()
                        .updateNote(note)
                }
            } else {
                val note = Note(0, binding.ed1.text.toString(), binding.ed2.text.toString())
                NoteDataBase.DatabaseBuilder.getdatabase(requireContext()).noteDao()
                    .insertNote(note)
            }
        }
            findNavController().popBackStack(R.id.addFragment,true)
    }
}