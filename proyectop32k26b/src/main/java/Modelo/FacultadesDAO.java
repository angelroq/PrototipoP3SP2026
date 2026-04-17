/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Controlador.clsFacultades;
import Controlador.clsBitacora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Angel R
 */
public class FacultadesDAO {

    
    private static final String SQL_SELECT =
            "SELECT codigo_facultad, nombre_facultad, status_facultad FROM facultades";

    private static final String SQL_INSERT =
            "INSERT INTO facultades (nombre_facultad, status_facultad) VALUES(?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE facultades SET nombre_facultad=?, status_facultad=? WHERE codigo_facultad=?";

    private static final String SQL_DELETE =
            "DELETE FROM facultades WHERE codigo_facultad=?";

    private static final String SQL_SELECT_ID =
            "SELECT codigo_facultad, nombre_facultad, status_facultad FROM facultades WHERE codigo_facultad=?";


    private static final String SQL_INSERT_BITACORA =
            "INSERT INTO bitacora(usuid, aplcodigo, bitfecha, bitip, bitequipo, bitaccion) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BITACORA =
            "SELECT bitcodigo, usuid, aplcodigo, bitfecha, bitip, bitequipo, bitaccion FROM bitacora";

    private static final String SQL_UPDATE_BITACORA =
            "UPDATE bitacora SET usuid=?, aplcodigo=?, bitfecha=?, bitip=?, bitequipo=?, bitaccion=? WHERE bitcodigo=?";

    private static final String SQL_DELETE_BITACORA =
            "DELETE FROM bitacora WHERE bitcodigo=?";


    
    public List<clsFacultades> obtenerFacultades(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<clsFacultades> lista = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                clsFacultades p = new clsFacultades();
                p.setCodigo_facultad(rs.getString("codigo_facultad"));
                p.setNombre_facultad(rs.getString("nombre_facultad"));
                p.setStatus_facultad(rs.getString("status_facultad"));
                lista.add(p);
            }

            //bitacora.setBitaccion("SELECT perfiles");
            //insertarBitacora(bitacora);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return lista;
    }

    public int insertarFacultades(clsFacultades facultades, clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, facultades.getNombre_facultad());
            stmt.setString(2, facultades.getStatus_facultad());

            rows = stmt.executeUpdate();

            bitacora.setBitaccion("INSERT facultades " + facultades.getNombre_facultad());
            insertarBitacora(bitacora);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int actualizarFacultades(clsFacultades facultades, clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, facultades.getNombre_facultad());
            stmt.setString(2, facultades.getStatus_facultad());
            stmt.setString(3, facultades.getCodigo_facultad());

            rows = stmt.executeUpdate();

            bitacora.setBitaccion("UPDATE facultades " + facultades.getCodigo_facultad());
            insertarBitacora(bitacora);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int eliminarFacultades(clsFacultades facultades, clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setString(1, facultades.getCodigo_facultad());

            rows = stmt.executeUpdate();

            bitacora.setBitaccion("DELETE facultades " + facultades.getCodigo_facultad());
            insertarBitacora(bitacora);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public clsFacultades obtenerFacultadesPorId(int id, clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsFacultades facultades = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                facultades = new clsFacultades();
                facultades.setCodigo_facultad(rs.getString("codigo_facultad"));
                facultades.setNombre_facultad(rs.getString("nombre_facultad"));
                facultades.setStatus_facultad(rs.getString("status_facultad"));
            }

            //bitacora.setBitaccion("SELECT perfil ID " + id);
            //insertarBitacora(bitacora);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return facultades;
    }


    
    public int insertarBitacora(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_BITACORA);

            stmt.setInt(1, bitacora.getUsucodigo());
            stmt.setInt(2, bitacora.getAplcodigo());
            stmt.setString(3, bitacora.getBitfecha());
            stmt.setString(4, bitacora.getBitip());
            stmt.setString(5, bitacora.getBitequipo());
            stmt.setString(6, bitacora.getBitaccion());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<clsBitacora> obtenerBitacora() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<clsBitacora> lista = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BITACORA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                clsBitacora b = new clsBitacora();
                b.setBitcodigo(rs.getInt("bitcodigo"));
                b.setUsucodigo(rs.getInt("usucodigo"));
                b.setAplcodigo(rs.getInt("aplcodigo"));
                b.setBitfecha(rs.getString("bitfecha"));
                b.setBitip(rs.getString("bitip"));
                b.setBitequipo(rs.getString("bitequipo"));
                b.setBitaccion(rs.getString("bitaccion"));
                lista.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return lista;
    }

    public int actualizarBitacora(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_BITACORA);

            stmt.setInt(1, bitacora.getUsucodigo());
            stmt.setInt(2, bitacora.getAplcodigo());
            stmt.setString(3, bitacora.getBitfecha());
            stmt.setString(4, bitacora.getBitip());
            stmt.setString(5, bitacora.getBitequipo());
            stmt.setString(6, bitacora.getBitaccion());
            stmt.setInt(7, bitacora.getBitcodigo());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int eliminarBitacora(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BITACORA);

            stmt.setInt(1, bitacora.getBitcodigo());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
}
