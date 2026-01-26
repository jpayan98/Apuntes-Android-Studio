package com.example.appbibliotecajuanpayan;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private boolean prestado;
    private String portada;

    public Libro(String isbn, String titulo, String autor, String genero, boolean prestado, String portada) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;          // ← FALTABA ESTO
        this.prestado = prestado;       // ← FALTABA ESTO
        this.portada = portada;         // ← FALTABA ESTO
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getGenero() {
        return genero;
    }
    public boolean isPrestado() {
        return prestado;
    }
    public String getPortada() {
        return portada;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }
    public void setPortada(String portada) {
        this.portada = portada;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Título: " + titulo + ", Autor: " + autor + ", Género: " + genero + ", Prestado:" + prestado + ", Portada:" + portada;
    }
}