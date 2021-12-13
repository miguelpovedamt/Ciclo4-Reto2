package com.example.tiresportapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
/*En esta iteracion se agrego el carrito de compras y la posibilidad de agregar
    varios productos al mismo, ademas que todas las vistas se crean dinamicamente
*/
//metodo principal de nuestra actividad
public class MainActivity extends AppCompatActivity {

    @Override
    //sobrescribe el metodo cuando se crea la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, productos.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);

        getSupportActionBar().hide();
    }
}
