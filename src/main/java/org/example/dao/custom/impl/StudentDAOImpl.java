package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Student s SET s.id = :id , s.name = :name , s.address = :address WHERE s.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",dto.getId());
        query.setParameter("name",dto.getName());
        query.setParameter("address",dto.getAddress());
        int executed = query.executeUpdate();
        transaction.commit();
        session.close();
        return executed > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Student s WHERE s.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int executed = query.executeUpdate();
        transaction.commit();
        session.close();
        return executed > 0;

    }

}
