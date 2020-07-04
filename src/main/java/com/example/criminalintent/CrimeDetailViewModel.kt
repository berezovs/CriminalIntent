package com.example.criminalintent

import android.service.autofill.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.*

class CrimeDetailViewModel(): ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeId = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeId) { crimeId ->
        crimeRepository.getCrime(crimeId)

    }

    fun loadCrime(newCrimeId:  UUID){
        crimeId.value = newCrimeId
    }
    fun saveCrime(crime: Crime){
        crimeRepository.updateCrime((crime))
    }

    fun getPhotoFile(crime:Crime): File {
         return crimeRepository.getPhotoFile(crime)
    }

}