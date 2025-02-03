package it.programmazionemobile.olympicgames2024.validation


class ValidationRule(
    private val validationErrorMessage: String,
    private val validationFunction: (String?) -> Boolean
) {

    // Esegue la funzione di validazione associata alla regola di validazione
    fun validate(inputText: String?): Boolean {
        return validationFunction(inputText)
    }

    // Ritorna il messaggio di errore associato alla regola di validazione
    fun getErrorMessage(): String {
        return validationErrorMessage
    }

    /* Oggetto companion per le regole di validazione predefinite. Ogni funzione definisce un
    * proprio messaggio di errore e una propria regola di validazione, che vengono usati per
    * creare un oggetto ValidationRule, il quale è infine ritornato. */
    @Suppress("FunctionName")
    companion object {

        @JvmStatic
        fun Required(customErrorMessage: String? = null): ValidationRule {
            val defaultErrorMessage = "Questo campo è richiesto"

            fun validationFunction(inputText: String?): Boolean {
                return !inputText.isNullOrEmpty()
            }

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

        @JvmStatic
        fun MinLength(minLength: Int, customErrorMessage: String? = null): ValidationRule {
            val defaultErrorMessage = StringBuilder()
                .append("Il testo deve essere lungo almeno ")
                .append(minLength)
                .append(" caratteri").toString()

            fun validationFunction(inputText: String?): Boolean {
                return if (inputText.isNullOrEmpty()) return minLength == 0
                else inputText.length >= minLength
            }

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

        @JvmStatic
        fun MaxLength(maxLength: Int, customErrorMessage: String? = null): ValidationRule {
            val defaultErrorMessage = StringBuilder()
                .append("Il testo deve essere lungo non più di ")
                .append(maxLength)
                .append(" caratteri").toString()

            fun validationFunction(inputText: String?): Boolean {
                return if (inputText.isNullOrEmpty()) return true
                else inputText.length <= maxLength
            }

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

        @JvmStatic
        fun MinValue(minValue: Int, customErrorMessage: String? = null): ValidationRule {
            val defaultErrorMessage = StringBuilder()
                .append("Il valore minimo è ")
                .append(minValue).toString()

            fun validationFunction(inputText: String?): Boolean {
                return if (inputText.isNullOrEmpty()) return minValue == 0
                else inputText.toInt() >= minValue
            }

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

        @JvmStatic
        fun MaxValue(maxValue: Int, customErrorMessage: String? = null): ValidationRule {
            val defaultErrorMessage = StringBuilder()
                .append("Il valore massimo è ")
                .append(maxValue).toString()

            fun validationFunction(inputText: String?): Boolean {
                return if (inputText.isNullOrEmpty()) return true
                else inputText.toInt() <= maxValue
            }

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

        @JvmStatic
        fun Custom(
            customErrorMessage: String? = null,
            validationFunction: (String?) -> Boolean
        ): ValidationRule {
            val defaultErrorMessage = "Input errato"

            return ValidationRule(customErrorMessage ?: defaultErrorMessage) { inputText ->
                validationFunction(inputText)
            }
        }

    }

}
