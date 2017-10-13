package Model;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author krawz
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String fechaN;
    private String calle1;
    private String calle2;
    private String colonia;
    private String correo;
    private String contrasena;
    private double saldoActual;
    public static final String TABLE = "usuarios";

    public Usuario() {

    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public static Usuario buscarPorEmail(String email) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM " + Usuario.TABLE + " WHERE correo=?";
        PreparedStatement preparedStm = conn.prepareStatement(query);
        preparedStm.setString(1, email);

        ResultSet rs = preparedStm.executeQuery();
        Usuario user = null;
        if (rs.next()) {
            user = new Usuario();
            user.setId(rs.getInt("id"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidoP(rs.getString("apellido_paterno"));
            user.setApellidoM(rs.getString("apellido_materno"));
            user.setFechaN(rs.getString("fecha_nacimiento"));
            user.setCalle1(rs.getString("calle1"));
            user.setCalle2(rs.getString("calle2"));
            user.setColonia(rs.getString("colonia"));
            user.setCorreo(rs.getString("correo"));
            user.setContrasena(rs.getString("contrasena"));
            user.setSaldoActual(rs.getDouble("saldo_actual"));
        }
        conn.close();
        return user;
    }

    public void cerrarSesion(){
        Usuario user = new Usuario();
            user.setId(0);
            user.setNombre("");
            user.setApellidoP("");
            user.setApellidoM("");
            user.setFechaN("");
            user.setCalle1("");
            user.setCalle2("");
            user.setColonia("");
            user.setCorreo("");
            user.setContrasena("");
            user.setSaldoActual(0);
    }



    public boolean crearUsuario() throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO " + Usuario.TABLE + " (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, calle1, calle2, colonia, correo, contrasena) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, this.getNombre());
        ps.setString(2, this.getApellidoP());
        ps.setString(3, this.getApellidoM());
        ps.setString(4, this.getFechaN());
        ps.setString(5, this.getCalle1());
        ps.setString(6, this.getCalle2());
        ps.setString(7, this.getColonia());
        ps.setString(8, this.getCorreo());
        ps.setString(9, this.getContrasena());

        int result = ps.executeUpdate();
        conn.close();
        return result > 0;
    }

    public double obtenerSaldo() throws SQLException{
        double saldo = 0.0;
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT saldo_actual FROM " + Usuario.TABLE + " WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, this.getId());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            saldo = rs.getDouble("saldo_actual");
        }
        conn.close();
        return saldo;
    }

    public boolean actualizarSaldo() throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "UPDATE " + Usuario.TABLE + " SET saldo_actual = ? "
                + "WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, this.getSaldoActual());
        ps.setInt(2, this.getId());
        int result = ps.executeUpdate();
        conn.close();
        return result > 0;
    }

    public boolean delete() throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "DELETE FROM " + Usuario.TABLE + " WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, this.getId());
        int result = ps.executeUpdate();
        conn.close();
        return result > 0;
    }
}
