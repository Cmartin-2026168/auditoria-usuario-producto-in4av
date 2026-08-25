package org.camilomartin.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instanciaConexionDB;
    private static Connection connection;

    private ConexionDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + Enviroment.LOCATION_SERVICE + "/"
                    + Enviroment.DATA_BASE,
                    Enviroment.USER,
                    Enviroment.PASSWORD);
        } catch (ClassNotFoundException classNotFound) {
            System.out.println("Error clase no encontrada");
        } catch (SQLException sqlException) {
            System.out.println("Error de conexion a db");
        } catch (Exception e) {
            System.out.println("Error padre " + e.getMessage());
        }
    }

    public static ConexionDB getInstanciaConexionDB() throws SQLException {
        if (instanciaConexionDB == null) {
            instanciaConexionDB = new ConexionDB();
        }
        return instanciaConexionDB;
    }

    public static class getInstanciaConexionDB {

        public getInstanciaConexionDB() {
        }
    }

}
