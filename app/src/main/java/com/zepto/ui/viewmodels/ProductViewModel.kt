package com.zepto.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zepto.data.models.fakeApi.Product
import com.zepto.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private val _error= MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val products : StateFlow<List<Product>> = productRepository.getProduct()
        .catch {e ->
            _error.value = e.message ?: "Unknown error occurred"
            emit(emptyList())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch {
            _loading.value = true
            try{
                _error.value = null
            }catch (e: Exception){
                _error.value = e.message ?: "Unknown error occured"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getProductByID(id:String): Product?{
        val idInt = id.toIntOrNull()
        return idInt?.let {idAsInt ->
            products.value.find { it.id == idAsInt }
        }
    }

    fun refreshProducts(){
        loadProducts()
    }
}