package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.Rootuser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RootuserDao extends BaseDao<Rootuser> {

    public RootuserDao() {
        super("xiezuo");
    }

    @Override
    public List<Rootuser> listData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rootuser selectData(Rootuser data) {
        try {
            ResultSet rs = getDB().executeQuery("SELECT * FROM rootuser WHERE username='" + data.getUsername() + "';");
            if (rs.next()) {
                Rootuser rootuser = new Rootuser();
                rootuser.setUsername(rs.getString("username"));
                rootuser.setPassword(rs.getString("password"));
                rootuser.setId(rs.getInt("id"));
                rootuser.setStatus(rs.getInt("status"));
                return rootuser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Rootuser data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean add(Rootuser data) {
        try {
            Rootuser res = selectData(data);
            if (res != null) {
                return false;
            } else {
                getDB().execute("INSERT INTO rootuser (`username`, `password`, `status`) VALUES ('" + data.getUsername() + "', '" + data.getPassword() + "', 0);");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Rootuser data) {
        return false;
    }

    @Override
    public int countData() {
        // TODO Auto-generated method stub
        return 0;
    }


}
