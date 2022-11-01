package com.kartik.grevocab.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kartik.grevocab.R
import com.kartik.grevocab.base.ResProvider
import com.kartik.grevocab.utility.validators.Item
import com.kartik.grevocab.utility.validators.LengthRule
import com.kartik.grevocab.utility.validators.Validator
import org.koin.core.component.KoinComponent

class FragmentGuestDetailsViewModel(private val resProvider: ResProvider) : ViewModel(), KoinComponent {

    val name = ObservableField<String>()
    val notes = ObservableField<String>()

    val nameError = ObservableField("")

    fun validate(): Boolean {

        val nameItem = Item(name.get(), LengthRule(resProvider.getString(R.string.enter_name), minLength = 1)) {
            nameError.set(it)
        }

        val validator = Validator(nameItem)
        return if (validator.validateAll()) {
            true
        } else {
            validator.showErrorForItems()
            false
        }
    }
}
