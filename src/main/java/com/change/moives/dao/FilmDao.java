package com.change.moives.dao;

import com.change.moives.entity.Film;

import java.util.List;

public interface FilmDao {
    List<Film>selectFilm();
    Film selectFilmById(String id);
}

