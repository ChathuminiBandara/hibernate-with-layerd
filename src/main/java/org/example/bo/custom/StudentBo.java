package org.example.bo.custom;

import org.example.bo.SuperBo;
import org.example.dto.StudentDto;

import java.sql.SQLException;

public interface StudentBo extends SuperBo {
    boolean save(StudentDto dto)throws SQLException,ClassNotFoundException;

    boolean update(StudentDto dto) throws SQLException,ClassNotFoundException;

    boolean delete(int id) throws SQLException,ClassNotFoundException;
}
