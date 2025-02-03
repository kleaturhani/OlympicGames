package it.programmazionemobile.olympicgames2024.validation

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.switchmaterial.SwitchMaterial
import it.programmazionemobile.olympicgames2024.utility.tagToString
import it.programmazionemobile.olympicgames2024.utility.textToString

class InputField<T> {

    /* Classe che rappresenta un campo di input di una form. Ha come proprietà:
    * inputView: campo di input con cui l'utente può interagire;
    * initialData: il valore presente inizialmente nel campo di input;
    * getInputData: funzione che ritorna il contenuto del campo di input */
    class InputField<T : View>(private val inputView: T) {
        private var initialData: Any? = null
        private var getInputData: () -> Any = { ("").toString() }

        init {
            initGetInputData()
            initialData = getInputData.invoke()
        }

        // Ritorna l'id dell'inputView
        fun getInputViewTag(): String {
            return inputView.tagToString()
        }

        // Ritorna il valore inserito nel campo di input
        fun getInputData(): Any {
            return getInputData.invoke()
        }

        // Ritorna inputView
        fun getInputView(): T {
            return inputView
        }

        // Controlla se il valore iniziale differisce da quello attuale
        fun hasDataChanged(): Boolean {
            return initialData != getInputData.invoke()
        }

        // Inizializza la funzione getInputData() in base al tipo di InputField
        private fun initGetInputData() = when (inputView::class) {
            SwitchMaterial::class -> initSwitch()
            AppCompatEditText::class -> initEditText()
            else -> {}
        }

        // Inizializza la funzione getInputData() per Switch
        private fun initSwitch() {
            val switch = inputView as SwitchMaterial
            getInputData = { switch.isChecked }
        }

        // Inizializza la funzione getInputData() per EditText
        private fun initEditText() {
            val editText = inputView as EditText
            getInputData = { editText.textToString() }
        }

    }
}