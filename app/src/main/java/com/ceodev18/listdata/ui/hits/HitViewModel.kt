package com.ceodev18.listdata.ui.hits

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ceodev18.listdata.data.entities.Hit
import com.ceodev18.listdata.data.repository.HitRepository

class HitViewModel @ViewModelInject constructor(
    private val repository: HitRepository
) : ViewModel() {
    val hits = repository.getHits()
    val hitsUpdate = repository.getHits()

    fun deleteHit(hit: Hit) {
        repository.deleteHit(hit)
    }
    fun remove(id: Int) {
        repository.softDelete(id)
    }


}
