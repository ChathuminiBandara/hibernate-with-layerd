package org.example.dao;

import org.example.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
     private static DAOFactory daoFactory;

     private DAOFactory (){
     }
     public static DAOFactory getDaoFactory(){
         return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
     }
     public enum  DAOTypes{
         STUDENT
     }
     public SuperDAO getDAO(DAOTypes daoTypes){
         switch (daoTypes){
             case STUDENT:
                 return new StudentDAOImpl();
             default:
                 return null;
         }
     }

}
