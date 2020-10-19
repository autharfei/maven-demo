package com.change.moives.dao.Impl;

import com.change.moives.dao.CateLogDao;
import com.change.moives.entity.CateLog;
import com.change.moives.utils.DbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CateLogImpl implements CateLogDao {

    @Override
    public List<CateLog> selectCateLog() {
        String sql = "SELECT * FROM t_catelog";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<CateLog> list = null;
        try {
            conn = DbManager.getInstance().getConn();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                int isUser = resultSet.getInt(2);
                String name = resultSet.getString(3);
                CateLog cateLog = new CateLog();
                cateLog.setId(id);
                cateLog.setIsUse(isUser);
                cateLog.setName(name);
                list.add(cateLog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            DbManager.getInstance().close(resultSet,ps,conn);
        }
        return list;
    }
}
