package com.edutecno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.ConexionDB;

public class HoroscopoDAO {

    // Método para consultar todos los horóscopos
    public List<Horoscopo> obtenerTodosHoroscopos() throws Exception {
        List<Horoscopo> listaHoroscopos = new ArrayList<>();
        String sql = "SELECT * FROM HOROSCOPO";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Horoscopo horoscopo = new Horoscopo(
                        rs.getString("ANIMAL"),
                        rs.getDate("FECHA_INICIO"),
                        rs.getDate("FECHA_FIN")
                );
                listaHoroscopos.add(horoscopo);
            }
        }
        System.out.println(listaHoroscopos);
        return listaHoroscopos;
    }
}
