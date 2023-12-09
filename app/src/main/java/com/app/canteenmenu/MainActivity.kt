package com.app.canteenmenu

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		window.statusBarColor = ContextCompat.getColor(this, R.color.color_secondary)
		val orderBtn: Button = findViewById(R.id.orderBtn)
		val itemIncBtns: Array<Button> = arrayOf(
			findViewById<Button>(R.id.incItemBtn1),
			findViewById<Button>(R.id.incItemBtn2),
			findViewById<Button>(R.id.incItemBtn3),
			findViewById<Button>(R.id.incItemBtn4),
			findViewById<Button>(R.id.incItemBtn5)
		)
		val itemDecBtns: Array<Button> = arrayOf(
			findViewById<Button>(R.id.decItemBtn1),
			findViewById<Button>(R.id.decItemBtn2),
			findViewById<Button>(R.id.decItemBtn3),
			findViewById<Button>(R.id.decItemBtn4),
			findViewById<Button>(R.id.decItemBtn5)
		)
		val itemCountText: Array<TextView> = arrayOf(
			findViewById<TextView>(R.id.itemCount1),
			findViewById<TextView>(R.id.itemCount2),
			findViewById<TextView>(R.id.itemCount3),
			findViewById<TextView>(R.id.itemCount4),
			findViewById<TextView>(R.id.itemCount5)
		)
		val items: Array<String> = arrayOf(
			"Macroni",
			"Noodles",
			"Chicken biriyani",
			"Lemon Juice",
			"Meals"
		)
		val prices: Array<Double> = arrayOf(
			90.0,
			50.0,
			120.0,
			20.0,
			50.0
		)

		val itemCounts: Array<Int> = arrayOf(0, 0, 0, 0, 0)
		for (i in 0..4) {
			itemIncBtns[i].setOnClickListener {
				itemCounts[i]++
				itemCountText[i].text = "${itemCounts[i]}"
			}

			itemDecBtns[i].setOnClickListener {
				if (itemCounts[i] > 0) itemCounts[i]--
				itemCountText[i].text = "${itemCounts[i]}"
			}
		}

		orderBtn.setOnClickListener {
			//set message for alert dialog
			var itemList = ""
			var netCost = 0.0
			for (i in 0..4) {
				if (itemCounts[i] > 0) {
					val cost = prices[i] * itemCounts[i]
					itemList += "\n${items[i]} - ${itemCounts[i]} (₹$cost)"
					netCost += cost
				}
			}
			if (itemList.isNotEmpty()) {
				val builder = AlertDialog.Builder(this);
				builder.setTitle("Order following items? ")
				val msg = "$itemList\n\nTotal  - ₹$netCost"
				builder.setMessage(msg)
				//performing positive action
				builder.setPositiveButton("Confirm") { dialogInterface, which ->
					Toast.makeText(applicationContext, "Items Ordered!", Toast.LENGTH_LONG).show()
				}
				//performing cancel action
				builder.setNeutralButton("Cancel") { dialogInterface, which ->
				}
				// Create the AlertDialog
				val alertDialog: AlertDialog = builder.create()
				// Set other dialog properties
				alertDialog.setCancelable(false)
				alertDialog.show()
			} else {
				Toast.makeText(applicationContext, "Please select some food", Toast.LENGTH_LONG).show()
			}
		}
	}
}