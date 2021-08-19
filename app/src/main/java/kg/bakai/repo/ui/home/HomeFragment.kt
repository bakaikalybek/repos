package kg.bakai.repo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kg.bakai.repo.databinding.FragmentHomeBinding
import kg.bakai.repo.domain.model.Repository
import kg.bakai.repo.ui.adapter.RepositoryAdapter
import kg.bakai.repo.ui.viewmodel.MainViewModel
import kg.bakai.repo.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RepositoryAdapter { view, repo ->  onItemClick(view, repo) }
        binding.apply {
            recyclerView.adapter = adapter
            btnSubmit.setOnClickListener {
                mainViewModel.getRepo(etSearch.text.toString())
            }
            mainViewModel.repositories.observe(viewLifecycleOwner) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        adapter.submit(resource.data)
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    private fun onItemClick(view: View, repository: Repository) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(repository)
        view.findNavController().navigate(action)
    }

}