package tw.eita.mrvn.ui.craft

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.eita.mrvn.data.ApexApiService
import tw.eita.mrvn.data.CraftBundle
import tw.eita.mrvn.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CraftViewModel @Inject constructor(private val apiService: ApexApiService) : BaseViewModel() {

    private val _craft = MutableLiveData<List<CraftBundle>>(listOf())
    val craft: LiveData<List<CraftBundle>>
        get() = _craft

    init {
        fetchCraftList()
    }

    private fun fetchCraftList() {
        viewModelScope.launch(exceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                apiService.fetchCraft()
            }
            _craft.postValue(result)
        }
    }


}