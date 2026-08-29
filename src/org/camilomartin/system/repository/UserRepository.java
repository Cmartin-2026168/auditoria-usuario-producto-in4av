package org.camilomartin.system.repository;

import org.camilomartin.system.model.User;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.camilomartin.system.config.ConexionDB;

public class UserRepository implements UserInterface {

    private CallableStatement callSP;
    private PreparedStatement statement;
    private ResultSet result;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection()
                    .prepareCall("{call sp_create_users (?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                if (callSP != null) {
                    callSP.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public User findByUser(String user) {
        User userFound = null;
        try {
            statement = conexionDB.getConnection()
                    .prepareStatement("SELECT name, lastname, email, user, password, id_user FROM Users WHERE user = ?");
            statement.setString(1, user);
            result = statement.executeQuery();

            if (result.next()) {
                userFound = new User();
                userFound.setName(result.getString("name"));
                userFound.setLastname(result.getString("lastname"));
                userFound.setEmail(result.getString("email"));
                userFound.setUser(result.getString("user"));
                userFound.setPassword(result.getString("password"));
                userFound.setIdUser(result.getString("id_user"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userFound;
    }
}
