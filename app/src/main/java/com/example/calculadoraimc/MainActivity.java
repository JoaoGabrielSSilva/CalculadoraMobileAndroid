package com.example.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText campoAltura;
    private EditText campoPeso;
    private Button botaoCalcular;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoAltura = findViewById(R.id.campoAltura);
        campoPeso = findViewById(R.id.campoPeso);
        botaoCalcular = findViewById(R.id.botaoCalcular);
        textoResultado = findViewById(R.id.textoResultado);

        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC(){
        String pesoStr = campoPeso.getText().toString();
        String alturaStr = campoAltura.getText().toString();

        if (!pesoStr.isEmpty() && !alturaStr.isEmpty()){
            float peso = Float.parseFloat(pesoStr);
            float altura = Float.parseFloat(alturaStr);

            if (altura != 0){
                float imc = peso / (altura * altura);

                if(imc < 18.5){
                    textoResultado.setText(String.format("Seu IMC é: %.2f e você está abaixo do peso.", imc));
                }
                else if (imc >= 18.5 && imc < 24.99) {
                    textoResultado.setText(String.format("Seu IMC é: %.2f e você está com o peso normal.", imc));
                }
                else if (imc >= 25 && imc < 29.99) {
                    textoResultado.setText(String.format("Seu IMC é: %.2f e você está com sobrepeso.", imc));
                }else{
                    textoResultado.setText(String.format("Seu IMC é: %.2f e você está obeso.", imc));
                }
            } else{
                textoResultado.setText("A altura não pode ser zero.");
            }
        }else {
            textoResultado.setText("Por favor preencha todos os campos.");
        }
    }
}