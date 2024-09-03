package br.com.aula.caixatexto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

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

        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimpar = findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(this::calcular);
        btnLimpar.setOnClickListener(this::limpar);
    }

    public void calcular(View view) {
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        String nome = campoNome.getText().toString();
        String pesoStr = campoPeso.getText().toString();
        String alturaStr = campoAltura.getText().toString();

        if (nome.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty()) {
            resultado1.setText("");
            resultado2.setText("Entrada inválida");
            return;
        }

        try {
            Double numPeso = Double.parseDouble(pesoStr);
            Double numAltura = Double.parseDouble(alturaStr);

            if (numPeso <= 0 || numAltura <= 0) {
                resultado1.setText("");
                resultado2.setText("Entrada inválida");
                return;
            }

            Double numImc = numPeso / (numAltura * numAltura);

            DecimalFormat df = new DecimalFormat("##.##");
            String imcFormatado = df.format(numImc);
            String classificacao;

            if (numImc < 18.5) {
                classificacao = "Baixo peso";
            } else if (numImc <= 24.9) {
                classificacao = "Peso normal";
            } else if (numImc <= 29.9) {
                classificacao = "Sobrepeso";
            } else if (numImc <= 34.9) {
                classificacao = "Obesidade grau 1";
            } else if (numImc <= 39.9) {
                classificacao = "Obesidade grau 2";
            } else {
                classificacao = "Obesidade extrema";
            }

            resultado1.setText("IMC = " + imcFormatado);
            resultado2.setText(classificacao);

        } catch (NumberFormatException e) {
            resultado1.setText("");
            resultado2.setText("Entrada inválida");
        }
    }

    public void limpar(View view) {
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        campoNome.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
        resultado1.setText("-");
        resultado2.setText("-");
    }
}
