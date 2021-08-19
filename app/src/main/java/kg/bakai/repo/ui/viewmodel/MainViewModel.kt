package kg.bakai.repo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.bakai.repo.domain.model.Repository
import kg.bakai.repo.domain.repository.DataRepository
import kg.bakai.repo.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataRepository: DataRepository
): ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _repositories = MutableLiveData<Resource<List<Repository>>>()
    val repositories: LiveData<Resource<List<Repository>>> = _repositories

    fun getRepo(user: String) {
        scope.launch {
            _repositories.postValue(Resource.loading())
            val result = dataRepository.fetchData(user)
            _repositories.postValue(result)
        }
    }
}