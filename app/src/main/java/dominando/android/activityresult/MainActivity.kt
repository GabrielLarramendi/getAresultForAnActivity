package dominando.android.activityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var state: String? = null
    private lateinit var buttonSlct: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSlct = findViewById<Button>(R.id.buttonSelectState)

        buttonSlct.setOnClickListener {
            val intent = Intent(this, ActivityStatesList::class.java)

            intent.putExtra(ActivityStatesList.EXTRA_STATE, state)
            startActivityForResult(intent, REQUEST_STATE)
        }

        if (savedInstanceState != null) {
            state = savedInstanceState.getString(EXTRA_STATE)

            if (state != null) {
                buttonSlct.text = state
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_STATE) {
            state = data?.getStringExtra(ActivityStatesList.EXTRA_RESULT)
            buttonSlct.text = state
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ActivityStatesList.EXTRA_STATE, state)
    }

    companion object {
        private const val REQUEST_STATE = 1
        private const val EXTRA_STATE = "estado"
    }

}