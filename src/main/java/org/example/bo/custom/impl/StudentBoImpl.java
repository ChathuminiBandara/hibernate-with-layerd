package org.example.bo.custom.impl;


import org.example.bo.custom.StudentBo;
import org.example.dao.DAOFactory;
import org.example.dao.custom.StudentDAO;
import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.sql.SQLException;

public class StudentBoImpl implements StudentBo {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean save(StudentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(dto.getId(), dto.getName(), dto.getAddress()));
    }

    @Override
    public boolean update(StudentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getId(), dto.getName(), dto.getAddress()));
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }
}
