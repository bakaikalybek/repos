package kg.bakai.repo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.bakai.repo.R
import kg.bakai.repo.domain.model.Repository

class RepositoryAdapter(
    private val onItemClick: (view: View, repo: Repository) -> Unit
): RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private val list = mutableListOf<Repository>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submit(repositories: List<Repository>) {
        list.clear()
        list.addAll(repositories)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val mainView: CardView = view.findViewById(R.id.main_view)
        private val repoName: TextView = view.findViewById(R.id.tv_repo_name)

        fun bind(item: Repository) {
            repoName.text = item.name
            mainView.setOnClickListener { onItemClick(mainView, item) }
        }
    }
}