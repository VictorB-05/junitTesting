package org.example;

import java.sql.*;

public class Database {

    private Connection connection;


    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
            crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void crearTabla() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "dni TEXT PRIMARY KEY, " +
                    "saldo REAL);";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean autenticarUsuario(String dni) {
        try {
            String query = "SELECT * FROM usuarios WHERE dni = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void registrarUsuario(String dni) {
        try {
            String query = "INSERT INTO usuarios (dni, saldo) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dni);
            pstmt.setDouble(2, 0.0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public double obtenerSaldo(String dni) {
        double saldo = 0.0;
        try {
            String query = "SELECT saldo FROM usuarios WHERE dni = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }


    public void actualizarSaldo(String dni, double nuevoSaldo) {
        try {
            String query = "UPDATE usuarios SET saldo = ? WHERE dni = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setString(2, dni);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
