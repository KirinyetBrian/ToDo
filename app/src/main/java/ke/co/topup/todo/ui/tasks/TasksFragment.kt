package ke.co.topup.todo.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ke.co.topup.todo.R

@AndroidEntryPoint
class TasksFragment: Fragment(R.layout.fragment_tasks) {

    private val viewModel: TasksViewModel by viewModels()
}