package com.onix.internship.backgroundanimationtask.ui.fragment.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.onix.internship.backgroundanimationtask.BR

data class MainModel(
    private var _translationOne: Float = 0F,
    private var _translationTwo: Float = 0F,
) : BaseObservable() {

    @get:Bindable
    var translationOne: Float = _translationOne
        set(value) {
            _translationOne = value
            field = value
            notifyPropertyChanged(BR.translationOne)
        }

    @get:Bindable
    var translationTwo: Float = _translationTwo
        set(value) {
            _translationTwo = value
            field = value
            notifyPropertyChanged(BR.translationTwo)
        }

}

