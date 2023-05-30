package com.example.semana9_android01.entities;

public class Anime {
   public String NombreAnime;
   public String DescripAnime;
   public String LinkImaAnime;
   public Boolean Estrella;

    public Anime() {
    }

    public Anime(String nombreAnime, String descripAnime, String linkImaAnime, Boolean estrella) {
        this.NombreAnime = nombreAnime;
        this.DescripAnime = descripAnime;
        this.LinkImaAnime = linkImaAnime;
        this.Estrella = estrella;
    }

    public String getNombreAnime() {
        return NombreAnime;
    }

    public void setNombreAnime(String nombreAnime) {
        this.NombreAnime = nombreAnime;
    }

    public String getDescripAnime() {
        return DescripAnime;
    }

    public void setDescripAnime(String descripAnime) {
        this.DescripAnime = descripAnime;
    }

    public String getLinkImaAnime() {
        return LinkImaAnime;
    }

    public void setLinkImaAnime(String linkImaAnime) {
        this.LinkImaAnime = linkImaAnime;
    }

    public Boolean getEstrella() {
        return Estrella;
    }

    public void setEstrella(Boolean estrella) {
        this.Estrella = estrella;
    }
}
