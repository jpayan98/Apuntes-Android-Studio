package com.example.examen2dejuanpayan;

public class Libro {
    String titulo;
    String autor;
    String imagen;

    public Libro (String titulo,String autor, String imagen){
        this.titulo=titulo;
        this.autor=autor;
        this.imagen=imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
