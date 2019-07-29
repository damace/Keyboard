package co.damace.mykeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonGuardar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuardar = findViewById(R.id.buttonGuardar);
        editText = findViewById(R.id.editText);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrir();
            }
        });

    }

    private void abrir() {
        String dato = editText.getText().toString();
        Toast.makeText(MainActivity.this, dato, Toast.LENGTH_LONG).show();
    }



}
