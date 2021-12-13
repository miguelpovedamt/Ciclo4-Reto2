package com.example.tiresportapp;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


//metodo principal de nuestra actividad
public class productos extends AppCompatActivity {

    //declara un boton para comprar
    private Button btnComprar1;

    //declara un boton para comprar
    private Button btnComprar2;

    //declara un boton para buscar
    private Button btnBuscar;

    //declara un visor de texto
    private TextView txtLLanta;

    private ConstraintLayout padre;

    private Llanta llanta;

    ArrayList<Llanta> listaLlantas;

    ArrayList<Llanta> listaCarrito;

    @Override
    //sobrescribe el metodo cuando se crea la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //declara una barra de accion
        ActionBar barraMenu = getSupportActionBar();

        //muestra la barra de acciones en la pantalla
        barraMenu.setDisplayShowHomeEnabled(true);

        //determina un logo previamente guardado
        barraMenu.setLogo(R.mipmap.ic_launcher);

        //muestra un titulo en la barra de acciones
        barraMenu.setTitle("Productos");

        //muestra un subtitulo en la barra de acciones
        barraMenu.setSubtitle("Elige un producto");

        //muestra un logo previamente determinado
        barraMenu.setDisplayUseLogoEnabled(true);

        //cambiar el color de la barra para que siempre sea el mismo
        barraMenu.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313d8c")));

        listaLlantas = new ArrayList<>();

        listaLlantas.add(new Llanta(1,"LLanta goodyear R13",250000,100,R.drawable.wheel_blanco));
        listaLlantas.add(new Llanta(2,"LLanta Michelin R14",250000,100,R.drawable._9765));
        listaLlantas.add(new Llanta(3,"LLanta goodyear R15",250000,100,R.drawable.llanta_bf_));

        listaCarrito = new ArrayList<>();

        int azulPrincipal = getResources().getColor(R.color.azulPrincipal);
        int naranjaPrincipal = getResources().getColor(R.color.naranjaPrincipal);
        int blanco = getResources().getColor(R.color.white);

        padre = (ConstraintLayout) findViewById(R.id.padre);
        padre.setBackground(new ColorDrawable(Color.parseColor("#fef1e5")));

        ScrollView scroll = new ScrollView(this);
        int anchoMatch = ScrollView.LayoutParams.MATCH_PARENT;
        int altowrap = ScrollView.LayoutParams.WRAP_CONTENT;
        scroll.setLayoutParams(new ConstraintLayout.LayoutParams(anchoMatch,altowrap));
        padre.addView(scroll);

        TableLayout tabla = new TableLayout(this);
        tabla.setLayoutParams(new TableLayout.LayoutParams(anchoMatch,altowrap));
        scroll.addView(tabla);

        for (Llanta llanta : listaLlantas)
        {
            TableRow fila1 = new TableRow(this);
            fila1.setBackground(new ColorDrawable(blanco));
            tabla.addView(fila1);

            ImageView imagen = new ImageView(this);
            imagen.setImageResource(llanta.getImagen());
            TableRow.LayoutParams imageParams = new TableRow.LayoutParams(anchoMatch,500,1);
            imagen.setLayoutParams(imageParams);
            fila1.addView(imagen);

            TableRow fila2 = new TableRow(this);
            tabla.addView(fila2);


            TextView textNombre = new TextView(this);
            TableRow.LayoutParams textParam1 = new TableRow.LayoutParams(anchoMatch,altowrap,1);
            textParam1.setMargins(50,35,50,25);
            textNombre.setTextSize(20);
            textNombre.setTextColor(azulPrincipal);
            textNombre.setTypeface(null, Typeface.BOLD);
            textNombre.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            textNombre.setText(llanta.getNombre());
            textNombre.setLayoutParams(textParam1);

            TextView textPrecio = new TextView(this);
            textPrecio.setTextSize(25);
            textPrecio.setTextColor(naranjaPrincipal);
            textPrecio.setTypeface(null, Typeface.BOLD);
            textPrecio.setLines(1);
            TableRow.LayoutParams textParam2 = new TableRow.LayoutParams();
            textParam2.setMargins(50,35,50,5);
            textPrecio.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            DecimalFormatSymbols punto = new DecimalFormatSymbols();
            punto.setGroupingSeparator('.');
            DecimalFormat formatoNumero= new DecimalFormat("###,###,###",punto);
            textPrecio.setText("$"+formatoNumero.format(llanta.getPrecio()*1.0));
            textPrecio.setLayoutParams(textParam2);
            fila2.addView(textNombre);
            fila2.addView(textPrecio);

            TableRow fila3 = new TableRow(this);
            fila3.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altowrap,1));
            tabla.addView(fila3);

            Button btnAgrCarrito = new Button(this);
            btnAgrCarrito.setText("Agregar al carrito");
            TableRow.LayoutParams btnParam2 = new TableRow.LayoutParams(anchoMatch,altowrap,1);
            btnParam2.setMargins(50,15,50,50);
            btnAgrCarrito.setLayoutParams(btnParam2);
            btnAgrCarrito.setTextColor(blanco);
            btnAgrCarrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listaCarrito.size()==0){
                        listaCarrito.add(llanta);
                        Toast.makeText(getApplicationContext(), "Usted agrego: "+
                                llanta.getNombre(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(!verificar(llanta)){
                        listaCarrito.add(llanta);
                            Toast.makeText(getApplicationContext(), "Usted agrego: "+
                                    llanta.getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            aumentar(llanta);
                            Toast.makeText(getApplicationContext(), "Usted aumento en 1: "+
                                    llanta.getNombre(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            fila3.addView(btnAgrCarrito);

            LinearLayout divisor = new LinearLayout(this);
            divisor.setOrientation(LinearLayout.HORIZONTAL);
            divisor.setLayoutParams(new TableRow.LayoutParams(anchoMatch,4));
            divisor.setBackground(new ColorDrawable(azulPrincipal));
            tabla.addView(divisor);

        }
    }


    private boolean verificar(Llanta llanta){

                for(Llanta l : listaCarrito){
                    if(l.getId()==llanta.getId()){
                        return true;
                    }
                }
                return false;
        }

    private void aumentar(Llanta llanta){

        for(Llanta l : listaCarrito){
            if(l.getId()==llanta.getId()) {
                llanta.setCantidad(llanta.getCantidad()+1);
            }
        }
    }

    @Override
    //sobrescribe el metodo de creacion de menu
    public boolean onCreateOptionsMenu(Menu menuPrincipal) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimnu,menuPrincipal);
        MenuItem productos = menuPrincipal.findItem(R.id.botonProductos);
        productos.setVisible(false);
        return true;
    }
    @Override
    //determina que accion se va a ejecutar dependiendo de eleccion en el menu
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

                Intent intent = new Intent(this, Carrito.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("carrito", listaCarrito);
                intent.putExtras(bundle);
                startActivity(intent);

            default:
                return true;

        }


    }


}