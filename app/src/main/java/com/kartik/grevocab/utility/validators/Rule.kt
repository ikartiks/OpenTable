package com.kartik.grevocab.utility.validators

import java.lang.StringBuilder

abstract class Rule(val error: String) {
    abstract fun executeRule(propertyValue: Any?): Boolean
}

// open class Item(private val property: KProperty<Any>
// val value = property.getter

class RequiredRule(error: String) : Rule(error) {
    override fun executeRule(propertyValue: Any?): Boolean {
        return propertyValue?.toString()?.trim()?.isNotEmpty() == true
    }
}

class LengthRule(error: String, private val minLength: Int = 0, private val maxLength: Int = Int.MAX_VALUE) : Rule(error) {
    override fun executeRule(propertyValue: Any?): Boolean {
        val length = propertyValue?.toString()?.length
        return length in (minLength) until maxLength
    }
}

open class Item(private val property: String?, private vararg val rules: Rule, private val callback: ((error: String) -> Unit)? = null) {

    fun getError() {
        for (rule in rules) {
            if (!rule.executeRule(property)) {
                callback?.invoke(rule.error)
                break
            } else {
                callback?.invoke("")
            }
        }
    }

    fun getAllErrors() {
        val sb = StringBuilder()
        for (rule in rules) {
            if (!rule.executeRule(property)) {
                sb.append(rule.error)
                sb.append("\n")
            }
        }
        callback?.invoke(sb.toString().trim())
    }

    fun isValid(): Boolean {
        for (rule in rules) {
            if (!rule.executeRule(property)) {
                return false
            }
        }
        return true
    }
}

open class Validator(private vararg val items: Item) {

    fun validateAll(): Boolean {
        var result = true
        for (item in items) {
            result = item.isValid() && result
        }
        return result
    }

    fun showErrorForItems() {
        items.forEach {
            it.getError()
        }
    }
    fun showAllErrorsForItems() {
        items.forEach {
            it.getAllErrors()
        }
    }
}
