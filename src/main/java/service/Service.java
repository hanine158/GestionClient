package service;

import dao.Dao;
import ma.projet.beans.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connexion.Connexion;

public class Service implements Dao<Client> {

    @Override
    public boolean create(Client o) {
        try {
            Connection connection = Connexion.getConnection();
            String sql = "INSERT INTO client (nom, prenom) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client o) {
        try {
            Connection connection = Connexion.getConnection();
            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client o) {
        try {
            Connection connection = Connexion.getConnection();
            String sql = "UPDATE client SET nom = ?, prenom = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findById(int id) {
        try {
            Connection connection = Connexion.getConnection();
            String sql = "SELECT * FROM client WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getString("nom"), rs.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Connection connection = Connexion.getConnection();
            String sql = "SELECT * FROM client";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Client c = new Client(rs.getString("nom"), rs.getString("prenom"));
                c.setId(rs.getInt("id"));
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}