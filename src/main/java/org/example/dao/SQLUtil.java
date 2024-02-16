package org.example.dao;

import org.example.db.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

    public static <T> T executeQuery(String hql, Object... args) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try (PreparedStatement pstm = connection.prepareStatement(hql)) {
            for (int i = 0; i < args.length; i++) {
                pstm.setObject(i + 1, args[i]);
            }
            if(hql.startsWith("SELECT")){
                return (T) pstm.executeQuery();
            }else {
                return (T) (Boolean)(pstm.executeUpdate()>0);
            }

            }

    }
}

