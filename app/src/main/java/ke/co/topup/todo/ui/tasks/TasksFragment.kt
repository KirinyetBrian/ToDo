package ke.co.topup.todo.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ke.co.topup.todo.R
import ke.co.topup.todo.databinding.FragmentTasksBinding
import ke.co.topup.todo.util.onQueryTextChanged

@AndroidEntryPoint
class TasksFragment: Fragment(R.layout.fragment_tasks) {

    private val viewModel: TasksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)

        val tasksAdapter = TasksAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter = tasksAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        viewModel.tasks.observe(viewLifecycleOwner){
            tasksAdapter.submitList(it)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks, menu)

        val  searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.action_sort_by_name -> {
                true
            }
            R.id.action_sort_by_date_created -> {
                true
            }
            R.id.action_hide_completed_tasks -> {
                true
            }
            R.id.action_delete_all_completed_tasks -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}