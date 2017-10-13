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
public class Movimientos {
    private int id;
    private String fecha;
    private Double cantidad, saldoRes;
    private String tipo;
    private String concepto;
    private int id_usuario;

    public static final String TABLE = "movimientos";

    public Movimientos(){

    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    

    public Double getSaldoRes() {
        return saldoRes;
    }

    public void setSaldoRes(Double saldoRes) {
        this.saldoRes = saldoRes;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getID_usuario() {
        return id_usuario;
    }

    public void setID_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public static ArrayList<Movimientos> buscarMovimientos(int id_user) throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM mov WHERE id_usuario = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id_user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Movimientos> movimientos = new ArrayList<Movimientos>();
        while(rs.next()){
            Movimientos mov = new Movimientos();
            //mov.setId(rs.getInt("id"));
            mov.setFecha(rs.getString("fecha"));
            mov.setCantidad(rs.getDouble("cantidad"));
            mov.setTipo(rs.getString("tipo_operacion"));
            mov.setSaldoRes(rs.getDouble("saldo_resultante"));
            mov.setID_usuario(rs.getInt("id_usuario"));
            mov.setConcepto(rs.getString("concepto"));

            movimientos.add(mov);
        }
        conn.close();
        return movimientos;
    }

    public boolean realizarMovimiento() throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO " + Movimientos.TABLE + " (fecha, cantidad, tipo_operacion,saldo_resultante, id_usuario,concepto) "
                + "VALUES (NOW(),?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, this.getCantidad());
        ps.setString(2, this.getTipo());
        ps.setDouble(3, this.getSaldoRes());
        ps.setInt(4, this.getID_usuario());
        ps.setString(5, this.getConcepto());

        int result = ps.executeUpdate();
        conn.close();
        return result > 0;
    }
    
    public static ArrayList<Movimientos> filtrarMovimientos(int id_user, String tipo) throws SQLException{
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM mov WHERE id_usuario = ? AND tipo_operacion = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id_user);
        ps.setString(2, tipo);
        ResultSet rs = ps.executeQuery();
        ArrayList<Movimientos> movimientos = new ArrayList<Movimientos>();
        while(rs.next()){
            Movimientos mov = new Movimientos();
            //mov.setId(rs.getInt("id"));
            mov.setFecha(rs.getString("fecha"));
            mov.setCantidad(rs.getDouble("cantidad"));
            mov.setTipo(rs.getString("tipo_operacion"));
            mov.setSaldoRes(rs.getDouble("saldo_resultante"));
            mov.setID_usuario(rs.getInt("id_usuario"));
            mov.setConcepto(rs.getString("concepto"));

            movimientos.add(mov);
        }
        conn.close();
        return movimientos;
    }
}
