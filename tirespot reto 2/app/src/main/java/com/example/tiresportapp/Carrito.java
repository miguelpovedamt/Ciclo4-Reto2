package com.example.tiresportapp;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Carrito extends AppCompatActivity {

    ArrayList<Llanta> listaCarrito;
    private Double precioTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        int azulPrincipal = getResources().getColor(R.color.azulPrincipal);
        int naranjaPrincipal = getResources().getColor(R.color.naranjaPrincipal);
        int blanco = getResources().getColor(R.color.white);

        Intent intentCarrito = getIntent();
        ArrayList<Llanta> listaCarrito = intentCarrito.getParcelableArrayListExtra("carrito");

        ActionBar barraMenu= getSupportActionBar();

        barraMenu.setDisplayShowHomeEnabled(true);
        barraMenu.setLogo(R.mipmap.ic_launcher);
        barraMenu.setTitle("Carrito");
        barraMenu.setSubtitle("Mi Compra");
        barraMenu.setDisplayUseLogoEnabled(true);
        barraMenu.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313d8c")));

        ConstraintLayout principalCarrito = (ConstraintLayout) findViewById(R.id.principalCarrito);
        principalCarrito.setBackground(new ColorDrawable(blanco));


        int anchoMatch = ScrollView.LayoutParams.MATCH_PARENT;
        int altoWrap = ScrollView.LayoutParams.WRAP_CONTENT;
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new ConstraintLayout.LayoutParams(anchoMatch,altoWrap));
        scroll.setBackground(new ColorDrawable(blanco));
        scroll.setId(View.generateViewId());

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(principalCarrito);
        constraintSet.connect(scroll.getId(), ConstraintSet.LEFT,principalCarrito.getId(),ConstraintSet.LEFT);
        constraintSet.connect(scroll.getId(),ConstraintSet.RIGHT,principalCarrito.getId(),ConstraintSet.RIGHT);
        constraintSet.connect(scroll.getId(),ConstraintSet.TOP,principalCarrito.getId(),ConstraintSet.TOP);
        constraintSet.connect(scroll.getId(),ConstraintSet.BOTTOM,principalCarrito.getId(),ConstraintSet.BOTTOM);

        principalCarrito.addView(scroll);



        if(listaCarrito.size()==0){

            LinearLayout layoutVacio = new LinearLayout(this);
            layoutVacio.setOrientation(LinearLayout.VERTICAL);
            layoutVacio.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams layoutVacioParams = new LinearLayout.LayoutParams(anchoMatch,altoWrap);
            layoutVacioParams.setMargins(0,0,0,0);
            layoutVacio.setLayoutParams(layoutVacioParams);
            principalCarrito.addView(layoutVacio);


            ImageView triste = new ImageView(this);
            triste.setImageResource(R.drawable.carritovacio);
            LinearLayout.LayoutParams parametrosTriste = new LinearLayout.LayoutParams(anchoMatch,anchoMatch);
            triste.setLayoutParams(parametrosTriste);
            layoutVacio.addView(triste);
            
        }else {
            TableLayout tablaCarrito = new TableLayout(this);
            TableLayout.LayoutParams tablaParams = new TableLayout.LayoutParams(anchoMatch,altoWrap);
            tablaCarrito.setLayoutParams(tablaParams);
            scroll.addView(tablaCarrito);
            tablaCarrito.setBackground(new ColorDrawable(blanco));

            TableRow titulos = new TableRow(this);

            titulos.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap));

            LinearLayout layoutProducto = new LinearLayout(this);
            layoutProducto.setOrientation(LinearLayout.VERTICAL);
            layoutProducto.setGravity(Gravity.CENTER);
            TableRow.LayoutParams parametros = new TableRow.LayoutParams(anchoMatch,100,1);
            parametros.setMargins(0,10,0,0);
            layoutProducto.setLayoutParams(parametros);

            TextView textoProducto = new TextView(this);
            textoProducto.setText("Producto");
            TableRow.LayoutParams parametrosText = new TableRow.LayoutParams(anchoMatch,altoWrap,1);
            textoProducto.setTextSize(17);
            textoProducto.setGravity(Gravity.CENTER);
            textoProducto.setMaxLines(1);
            textoProducto.setTypeface(null, Typeface.BOLD);
            textoProducto.setTextColor(azulPrincipal);
            titulos.addView(layoutProducto);
            textoProducto.setLayoutParams(parametrosText);
            layoutProducto.addView(textoProducto);

            LinearLayout layoutPrecio = new LinearLayout(this);
            layoutPrecio.setOrientation(LinearLayout.VERTICAL);
            layoutPrecio.setGravity(Gravity.CENTER);
            layoutPrecio.setLayoutParams(parametros);

            TextView textoPrecio = new TextView(this);
            textoPrecio.setText("Precio/u");
            textoPrecio.setTypeface(null, Typeface.BOLD);
            textoPrecio.setTextSize(17);
            textoPrecio.setGravity(Gravity.CENTER);
            textoPrecio.setMaxLines(1);
            textoPrecio.setLayoutParams(parametrosText);
            textoPrecio.setTextColor(azulPrincipal);
            titulos.addView(layoutPrecio);
            layoutPrecio.addView(textoPrecio);

            LinearLayout layoutCantidadTit = new LinearLayout(this);
            layoutCantidadTit.setOrientation(LinearLayout.VERTICAL);
            layoutCantidadTit.setGravity(Gravity.CENTER);
            layoutCantidadTit.setLayoutParams(parametros);

            TextView textoCantidad = new TextView(this);
            textoCantidad.setText("Cantidad");
            textoCantidad.setTypeface(null, Typeface.BOLD);
            textoCantidad.setTextSize(17);
            textoCantidad.setGravity(Gravity.CENTER);
            textoCantidad.setLines(1);
            textoCantidad.setLayoutParams(parametrosText);
            textoCantidad.setTextColor(azulPrincipal);
            titulos.addView(layoutCantidadTit);
            layoutCantidadTit.addView(textoCantidad);

            LinearLayout layoutSubtotal = new LinearLayout(this);
            layoutSubtotal.setOrientation(LinearLayout.VERTICAL);
            layoutSubtotal.setGravity(Gravity.CENTER);
            layoutSubtotal.setLayoutParams(parametros);

            TextView textoSubtotal = new TextView(this);
            textoSubtotal.setText("Subtotal");
            textoSubtotal.setTypeface(null, Typeface.BOLD);
            textoSubtotal.setTextSize(17);
            textoSubtotal.setGravity(Gravity.CENTER);
            textoSubtotal.setMaxLines(1);
            textoSubtotal.setLayoutParams(parametrosText);
            textoSubtotal.setTextColor(azulPrincipal);
            titulos.addView(layoutSubtotal);
            layoutSubtotal.addView(textoSubtotal);

            tablaCarrito.addView(titulos);

            LinearLayout divisor = new LinearLayout(this);
            divisor.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams diviParams = new TableRow.LayoutParams(anchoMatch,8);
            diviParams.setMargins(0,20,0,10);
            divisor.setLayoutParams(diviParams);
            divisor.setBackground(new ColorDrawable(naranjaPrincipal));

            tablaCarrito.addView(divisor);

            DecimalFormatSymbols punto = new DecimalFormatSymbols();
            punto.setGroupingSeparator('.');
            DecimalFormat formatoNumero= new DecimalFormat("###,###,###",punto);



            TextView precioTotalView = new TextView(this);

            for (Llanta l : listaCarrito) {


                TableRow productoRow = new TableRow(this);
                productoRow.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap));
                tablaCarrito.addView(productoRow);
                productoRow.setGravity(Gravity.CENTER);

                LinearLayout layoutImage = new LinearLayout(this);
                layoutImage.setOrientation(LinearLayout.VERTICAL);
                layoutImage.setLayoutParams(new TableRow.LayoutParams(altoWrap,altoWrap,1));

                productoRow.addView(layoutImage);

                ImageView imagenProducto = new ImageView(this);
                imagenProducto.setImageResource(l.getImagen());
                TableRow.LayoutParams imagenPrParams =  new TableRow.LayoutParams(150,150);
                imagenPrParams.setMargins(30,0,0,0);
                imagenProducto.setLayoutParams(imagenPrParams);

                layoutImage.addView(imagenProducto);

                LinearLayout layoutPrecioPr = new LinearLayout(this);
                layoutPrecioPr.setOrientation(LinearLayout.VERTICAL);
                layoutPrecioPr.setGravity(Gravity.CENTER);
                layoutPrecioPr.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap));

                productoRow.addView(layoutPrecioPr);

                TextView precioProducto = new TextView(this);
                precioProducto.setText("$"+formatoNumero.format(l.getPrecio()*1.0));
                TextViewCompat.setAutoSizeTextTypeWithDefaults(precioProducto,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                precioProducto.setTypeface(null, Typeface.BOLD);
                precioProducto.setTextSize(17f);
                precioProducto.setGravity(Gravity.CENTER);
                precioProducto.setMaxLines(1);
                LinearLayout.LayoutParams text2Params =  new TableRow.LayoutParams(anchoMatch,altoWrap,1);
                text2Params.setMargins(20,0,30,0);
                precioProducto.setLayoutParams(text2Params);
                precioProducto.setTextColor(azulPrincipal);

                layoutPrecioPr.addView(precioProducto);

                LinearLayout layoutCantidadPr = new LinearLayout(this);
                layoutCantidadPr.setOrientation(LinearLayout.HORIZONTAL);
                layoutCantidadPr.setGravity(Gravity.CENTER);
                layoutCantidadPr.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap,1));

                productoRow.addView(layoutCantidadPr);

                TextView cantProducto = new TextView(this);
                TextView precioSubTotal = new TextView(this);

                Button btnBajar = new Button(this);
                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(50, 50);
                btnParams.setMargins(10,10,0,15);
                btnBajar.setLayoutParams( btnParams);
                btnBajar.setTextColor(azulPrincipal);
                btnBajar.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                btnBajar.setPadding(0,0,0,0);
                btnBajar.setTypeface(null, Typeface.BOLD);
                btnBajar.setText("<");
                btnBajar.setTextSize(20f);
                btnBajar.setBackgroundTintList(ColorStateList.valueOf(blanco));


                btnBajar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l.getCantidad()>1) {
                            l.setCantidad(l.getCantidad() - 1);
                                cantProducto.setText("" + l.getCantidad());
                                precioSubTotal.setText("$"+formatoNumero.format(l.getPrecio()*1.0*l.getCantidad()));
                                precioTotalView.setText(String.valueOf("Total:    $ "+formatoNumero.format(calcularTotal(listaCarrito))));
                        }
                    }
                });

                layoutCantidadPr.addView(btnBajar);

                cantProducto.setText(""+l.getCantidad());
                TextViewCompat.setAutoSizeTextTypeWithDefaults(cantProducto,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                cantProducto.setTypeface(null, Typeface.BOLD);
                cantProducto.setGravity(Gravity.CENTER);
                cantProducto.setMaxLines(1);
                LinearLayout.LayoutParams text3Params = new LinearLayout.LayoutParams(  anchoMatch,altoWrap,2);
                text3Params.setMargins(10,0,10,0);
                cantProducto.setLayoutParams(text3Params);
                cantProducto.setTextColor(azulPrincipal);

                layoutCantidadPr.addView(cantProducto);

                Button btnSubir = new Button(this);
                LinearLayout.LayoutParams btnParams2 = new LinearLayout.LayoutParams(50, 50);
                btnParams2.setMargins(0,10,20,15);
                btnSubir.setLayoutParams(btnParams2);
                btnSubir.setTextColor(azulPrincipal);
                btnSubir.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                btnSubir.setPadding(0,0,0,0);
                btnSubir.setTypeface(null, Typeface.BOLD);
                btnSubir.setText(">");
                btnSubir.setTextSize(20f);
                btnSubir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l.getCantidad()<39) {
                        l.setCantidad(l.getCantidad()+1);
                            cantProducto.setText(""+l.getCantidad());
                            precioSubTotal.setText("$"+formatoNumero.format(l.getPrecio()*1.0*l.getCantidad()));
                            precioTotalView.setText(String.valueOf("Total:    $ "+formatoNumero.format(calcularTotal(listaCarrito))));

                        }
                    }
                });
                btnSubir.setBackgroundTintList(ColorStateList.valueOf(blanco));

                layoutCantidadPr.addView(btnSubir);

                LinearLayout layoutprecioSubTotal = new LinearLayout(this);
                layoutprecioSubTotal.setOrientation(LinearLayout.VERTICAL);
                layoutprecioSubTotal.setGravity(Gravity.CENTER);
                layoutprecioSubTotal.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap));

                productoRow.addView(layoutprecioSubTotal);

                precioSubTotal.setText("$"+formatoNumero.format(l.getPrecio()*1.0*l.getCantidad()));
                TextViewCompat.setAutoSizeTextTypeWithDefaults(precioSubTotal,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                precioSubTotal.setTypeface(null, Typeface.BOLD);
                precioSubTotal.setGravity(Gravity.CENTER);
                precioSubTotal.setMaxLines(1);
                LinearLayout.LayoutParams precioSubTotalParam =  new LinearLayout.LayoutParams(anchoMatch,altoWrap);
                precioSubTotalParam .setMargins(20,0,30,0);
                precioSubTotal.setLayoutParams(precioSubTotalParam);
                precioSubTotal.setTextColor(azulPrincipal);

                layoutprecioSubTotal.addView(precioSubTotal);

                LinearLayout divisor2 = new LinearLayout(this);
                divisor2.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams diviParams2 = new LinearLayout.LayoutParams(anchoMatch,4);
                divisor2 .setLayoutParams(diviParams2);
                divisor2 .setBackground(new ColorDrawable(naranjaPrincipal));


                tablaCarrito.addView(divisor2);

            }

            LinearLayout barraInferior = new LinearLayout(this);
            barraInferior.setOrientation(LinearLayout.VERTICAL);

            ConstraintLayout.LayoutParams parametros2 = new ConstraintLayout.LayoutParams(anchoMatch,400);
            parametros2.verticalBias = 1f;
            barraInferior.setLayoutParams(parametros2);
            barraInferior.setBackground(new ColorDrawable(blanco));

            LinearLayout divisor2 = new LinearLayout(this);
            divisor2 .setOrientation(LinearLayout.HORIZONTAL);
            divisor2 .setLayoutParams(diviParams);
            divisor2 .setBackground(new ColorDrawable(naranjaPrincipal));

            barraInferior.addView(divisor2);

            LinearLayout layoutprecioTotal = new LinearLayout(this);
            layoutprecioTotal.setOrientation(LinearLayout.HORIZONTAL);
            layoutprecioTotal.setGravity(Gravity.CENTER);
            layoutprecioTotal.setLayoutParams(new TableRow.LayoutParams(anchoMatch,altoWrap));

            barraInferior.addView(layoutprecioTotal);


            precioTotalView.setText(String.valueOf("Total:    $ "+formatoNumero.format(calcularTotal(listaCarrito))));
            precioTotalView.setTypeface(null, Typeface.BOLD);
            precioTotalView.setGravity(Gravity.RIGHT);
            precioTotalView.setMaxLines(1);
            precioTotalView.setTextSize(20);
            LinearLayout.LayoutParams precioTotalParam =  new LinearLayout.LayoutParams(anchoMatch,altoWrap);
            precioTotalParam .setMargins(30,30,30,30);
            precioTotalView.setLayoutParams(precioTotalParam);
            precioTotalView.setTextColor(azulPrincipal);

            layoutprecioTotal.addView(precioTotalView);


            Button btbComprarCarrito = new Button(this);
            LinearLayout.LayoutParams btnCompraParams =  new LinearLayout.LayoutParams(anchoMatch,altoWrap);
            btnCompraParams.setMargins(30,30,30,30);
            precioTotalView.setLayoutParams(btnCompraParams);
            barraInferior.addView(btbComprarCarrito);
            btbComprarCarrito.setText("Continuar con el pago");
            btbComprarCarrito.setTextSize(20);
            btbComprarCarrito.setTextColor(blanco);
            btbComprarCarrito.setTypeface(null, Typeface.BOLD);
            btbComprarCarrito.setBackgroundTintList(ColorStateList.valueOf(naranjaPrincipal));

            barraInferior.setId(View.generateViewId());

            principalCarrito.addView(barraInferior);
            constraintSet.clone(principalCarrito);
            constraintSet.connect(barraInferior.getId(), ConstraintSet.LEFT,principalCarrito.getId(),ConstraintSet.LEFT);
            constraintSet.connect(barraInferior.getId(),ConstraintSet.RIGHT,principalCarrito.getId(),ConstraintSet.RIGHT);
            constraintSet.connect(barraInferior.getId(),ConstraintSet.TOP,principalCarrito.getId(),ConstraintSet.TOP);
            constraintSet.connect(barraInferior.getId(),ConstraintSet.BOTTOM,principalCarrito.getId(),ConstraintSet.BOTTOM);

            constraintSet.applyTo(principalCarrito);


        }



    }

    private Double calcularTotal(ArrayList<Llanta> listaCar){
        Double precioTotal = 0.0;
        for (Llanta l : listaCar
             ) {
                precioTotal += l.getCantidad()*l.getPrecio();
        }
        return precioTotal;
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

            case R.id.botonSalir:
                        finish();
                return true;
            default:
                return true;

        }


    }
}