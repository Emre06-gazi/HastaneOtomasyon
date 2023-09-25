/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author MTxMCT
 */
import java.sql.*;

public class Query {
    
    private String host = "jdbc:oracle:thin://@localhost:1521/XEPDB1";
    private String user = "SYSTEM";
    private String pass = "8976";
    private Connection con;
    
    public Query(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            this.con = DriverManager.getConnection(host,user,pass);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public Connection  getCon(){
        return con;
    }
 
    public void close() throws SQLException{
        con.close();
    }
}
