package kg.bakai.repo.ui.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import kg.bakai.repo.databinding.FragmentDetailsBinding
import kg.bakai.repo.domain.model.Repository

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)

        setupView(args.repository)
    }

    private fun setupView(repo: Repository) {
        binding.apply {
            toolbar.title = repo.name
            tvOwner.text = repo.owner.ownerName

            Glide
                .with(ivAvatar.context)
                .load(repo.owner.avatar)
                .into(ivAvatar)

            tvStars.text = repo.starCount.toString()
            tvIssues.text = repo.openIssueCount.toString()
        }
    }
}