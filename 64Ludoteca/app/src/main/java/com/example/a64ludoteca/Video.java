package com.example.a64ludoteca;

public class Video {
    private String referencia;
    private String titulo;
    private String autor;

    public Video(String referencia, String titulo, String autor) {
        this.referencia = referencia;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Ref: " + referencia + ", Título: " + titulo + ", Autor: " + autor;
    }
}
