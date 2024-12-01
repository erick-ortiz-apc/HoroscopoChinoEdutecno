package com.edutecno.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.ConexionDB;

public class UsuarioDAO {

    public Usuario iniciarSesion(String username, String clave) throws Exception {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("ID_USU"),
                            rs.getString("NOMBRE"),
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"),
                            rs.getString("EMAIL"),
                            rs.getDate("FECHA_NACIMIENTO"),
                            rs.getString("ANIMAL")
                    );
                }
            }
        }
        return usuario;
    }

    public boolean crearUsuario(Usuario usuario) throws Exception {
        String sql = "INSERT INTO USUARIOS (NOMBRE, USERNAME, PASSWORD, EMAIL, FECHA_NACIMIENTO, ANIMAL) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getCorreo());
            ps.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(6, usuario.getAnimal());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean modificarUsuario(Usuario usuario) throws Exception {
        String sql = "UPDATE USUARIOS SET NOMBRE = ?, USERNAME = ?, PASSWORD = ?, EMAIL = ?, FECHA_NACIMIENTO = ?, ANIMAL = ? " +
                     "WHERE ID_USU = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getCorreo());
            ps.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(6, usuario.getAnimal());
            ps.setInt(7, usuario.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarUsuario(int id) throws Exception {
        String sql = "DELETE FROM USUARIOS WHERE ID_USU = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Usuario> buscarUsuarios(String filtro) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS WHERE NOMBRE LIKE ? OR USERNAME LIKE ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(new Usuario(
                            rs.getInt("ID_USU"),
                            rs.getString("NOMBRE"),
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"),
                            rs.getString("EMAIL"),
                            rs.getDate("FECHA_NACIMIENTO"),
                            rs.getString("ANIMAL")
                    ));
                }
            }
        }
        return usuarios;
    }
    
    public boolean existeUsuario(String username) throws Exception {
        String sql = "SELECT COUNT(*) FROM USUARIOS WHERE USERNAME = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public boolean actualizarAnimal(int id, String animal) throws Exception {
        String sql = "UPDATE USUARIOS SET ANIMAL = ? WHERE ID_USU = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, animal);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }
}
