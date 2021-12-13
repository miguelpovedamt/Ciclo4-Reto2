package com.example.tiresportapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sucursales extends AppCompatActivity {

    //declara un boton para contacto
    private Button contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);
        ActionBar barraMenu= getSupportActionBar();

        barraMenu.setDisplayShowHomeEnabled(true);
        barraMenu.setLogo(R.mipmap.ic_launcher);
        barraMenu.setTitle("Sucursales");
        barraMenu.setSubtitle("Elige una Sucursal");
        barraMenu.setDisplayUseLogoEnabled(true);
        barraMenu.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313d8c")));


        //busca por id el boton de servicio3
        contacto= (Button) findViewById(R.id.btnContacto);

        //crea un action listener para el boton servicio3
        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "El boton de contacto se habilitara pronto ",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuPrincipal) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimnu,menuPrincipal);
        MenuItem carrito = menuPrincipal.findItem(R.id.carrito);
        carrito.setVisible(false);
        MenuItem buscar = menuPrincipal.findItem(R.id.buscar);
        buscar.setVisible(false);
        MenuItem productos = menuPrincipal.findItem(R.id.botonProductos);
        productos.setVisible(false);
        MenuItem servicios = menuPrincipal.findItem(R.id.botonServicios);
        servicios.setVisible(false);
        MenuItem sucursales = menuPrincipal.findItem(R.id.botonSucursales);
        sucursales.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem menuItem){
        switch (menuItem.getItemId())
        {
            case R.id.botonProductos:
                Intent principal = new Intent(getApplicationContext(),MainActivity.class );
                startActivity(principal);
                return true;

            case R.id.botonServicios:
                Intent servicios = new Intent(getApplicationContext(), servicios.class);
                startActivity(servicios);
                return true;

            case R.id.botonSucursales:
                Intent sucursales = new Intent(getApplicationContext(), sucursales.class);
                startActivity(sucursales);
                return true;

            case R.id.carrito:
                Toast.makeText(getApplicationContext(),
                        "El carrito se habilitara pronto",Toast.LENGTH_LONG).show();
                return true;

            case R.id.botonSalir:
                finish();
                return true;

            default:
                return true;

        }

    }

}

