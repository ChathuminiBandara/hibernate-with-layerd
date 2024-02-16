package org.example.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends  SuperDAO {

    boolean save (T dto)throws SQLException,ClassNotFoundException;

    boolean update (T dto) throws SQLException,ClassNotFoundException;

    boolean delete (int id) throws SQLException,ClassNotFoundException;
}
