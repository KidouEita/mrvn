package tw.eita.mrvn.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.eita.mrvn.data.ApexApiService
import tw.eita.mrvn.data.MapObj
import tw.eita.mrvn.data.News

class HomeViewModel : ViewModel() {

    private val _news = MutableLiveData(listOf<News>())
    val news: LiveData<List<News>>
        get() = _news

    private val _map = MutableLiveData<MapObj>(null)
    val map: LiveData<MapObj>
        get() = _map

    init {
        fetchMaps()
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                ApexApiService.instance.fetchNews()
            }
            _news.postValue(result)
        }
    }

    private fun fetchMaps() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                ApexApiService.instance.fetchMapRotation()
            }
            _map.postValue(result)
        }
    }

}