package ca.thapamanish.manish_midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    // Views
    private lateinit var spinner: Spinner
    private lateinit var valueEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var outputValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        valueEditText = findViewById(R.id.value)
        convertButton = findViewById(R.id.convert_button)
        outputValue = findViewById(R.id.output_value)
        val unitsarray = arrayOf("Kilogram","Gram","Milligram","Pound","Ounce","Tonne","Stone")
        // Set up the spinner
        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitsarray)
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = unitAdapter

        // Set up the button click listener
        convertButton.setOnClickListener {
            // Get the selected unit from the spinner
            val selectedUnit = spinner.selectedItem.toString()

            // Convert the value and set the output
            if (valueEditText.text.toString().isNotEmpty()) {
                val inputValue = valueEditText.text.toString().toDouble()
                val output = convertValue(selectedUnit, inputValue)
                outputValue.text = output.toString()
            } else {
                outputValue.text = "Please enter a value."
            }
        }
    }

    // Convert the value based on the selected unit
    private fun convertValue(unit: String, value: Double): Double {
        return when (unit) {
            "Kilogram" -> value * 1000.0
            "Gram" -> value
            "Milligram" -> value / 1000.0
            "Pound" -> value * 453.592
            "Ounce" -> value * 28.3495
            "Tonne" -> value * 0.000001
            "Stone" -> value * 0.0001574
            else -> value
        }
    }
}