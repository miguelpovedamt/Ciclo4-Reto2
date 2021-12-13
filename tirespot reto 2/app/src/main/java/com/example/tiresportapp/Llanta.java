package com.example.tiresportapp;


import android.os.Parcel;
import android.os.Parcelable;

public class Llanta implements Parcelable {

    private int id;
    private String nombre;
    private int precio;
    private int inventario;
    private int cantidad = 1;
    private int imagen;


    public Llanta(int id, String nombre, int precio, int inventario, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public Llanta(String nombre, int precio, int inventario) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.cantidad = 1;
    }

    public Llanta(String nombre, int precio, int inventario, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.cantidad = 1;
        this.imagen = imagen;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<Llanta> CREATOR = new Parcelable.Creator<Llanta>() {
        @Override
        public Llanta createFromParcel(Parcel in) {
            return new Llanta(in);
        }

        @Override
        public Llanta[] newArray(int size) {
            return new Llanta[size];
        }
    };
    protected Llanta(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        precio = in.readInt();
        inventario = in.readInt();
        cantidad = in.readInt();
        imagen = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeInt(precio);
        dest.writeInt(inventario);
        dest.writeInt(cantidad);
        dest.writeInt(imagen);
    }
}
