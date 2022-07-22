package tw.eita.mrvn.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import tw.eita.mrvn.data.ApexApiService
import tw.eita.mrvn.data.MapObj
import tw.eita.mrvn.data.News
import tw.eita.mrvn.ui.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _news = MutableLiveData(listOf<News>())
    val news: LiveData<List<News>>
        get() = _news

    private val _map = MutableLiveData<MapObj>(null)
    val map: LiveData<MapObj>
        get() = _map

    private val _isRefresh = MutableLiveData(false)
    val isRefresh: LiveData<Boolean>
        get() = _isRefresh

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(exceptionHandler) {
            _isRefresh.postValue(true)

            val newsFetched = async { ApexApiService.instance.fetchNews() }
            val mapFetched = async { ApexApiService.instance.fetchMapRotation() }

            _news.postValue(newsFetched.await())
            _map.postValue(mapFetched.await())

            _isRefresh.postValue(false)
        }
    }

}