package com.ceodev18.listdata.ui.hitdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ceodev18.listdata.data.entities.Hit
import com.ceodev18.listdata.data.repository.HitRepository
import com.ceodev18.listdata.utils.Resource


class HitDetailViewModel @ViewModelInject constructor(
    private val repository: HitRepository
) : ViewModel() {

}
