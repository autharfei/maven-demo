package com.change.moives.dao.Impl;

import com.change.moives.dao.FilmDao;
import com.change.moives.entity.Film;
import com.change.moives.utils.DbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {

    @Override
    public List<Film> selectFilm() {
        String sql = "SELECT id,image,name,on_decade,type_name FROM t_film where cate_log_name='电影'limit 0,12";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List list = null;
        try {
            conn = DbManager.getInstance().getConn();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            list = new ArrayList();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String image = resultSet.getString(2);
                String name = resultSet.getString(3);
                String onDecade = resultSet.getString(4);
                String typeName = resultSet.getString(5);
                Film film = new Film();
                film.setId(id);
                film.setImage(image);
                film.setName(name);
                film.setOnDecade(onDecade);
                film.setTypeName(typeName);
                list.add(film);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            DbManager.getInstance().close(resultSet,ps,conn);
        }
        return list;
    }

    @Override
    public Film selectFilmById(String id) {
        String sql = "SELECT id, actor, cate_log_name, cate_log_id," +
                " evaluation, image, isUse, loc_name, loc_id, " +
                "name, on_decade, plot, resolution, status, " +
                "sub_class_name, sub_class_id, type_name, " +
                "type_id FROM t_film  where id=?";

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        Film film = null;
        try {
            conn = DbManager.getInstance().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                film = new Film();
                film.setId(resultSet.getString(1));
                film.setActor(resultSet.getString(2));
                film.setCateLogName(resultSet.getString(3));
                film.setCateLogId(resultSet.getString(4));
                film.setEvaluation(resultSet.getDouble(5));
                film.setImage(resultSet.getString(6));
                film.setIsUse(resultSet.getInt(7));
                film.setLocName(resultSet.getString(8));
                film.setLocId(resultSet.getString(9));
                film.setName(resultSet.getString(10));
                film.setOnDecade(resultSet.getString(11));
                film.setPlot(resultSet.getString(12));
                film.setResolution(resultSet.getString(13));
                film.setStatus(resultSet.getString(14));
                film.setSubClassName(resultSet.getString(15));
                film.setSubClassId(resultSet.getString(16));
                film.setTypeName(resultSet.getString(17));
                film.setTypeId(resultSet.getString(18));
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            DbManager.getInstance().close(resultSet,ps,conn);
        }

        return film;
    }
}
