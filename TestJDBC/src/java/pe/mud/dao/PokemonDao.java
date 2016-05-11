package pe.mud.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.mud.db.AccesoDB;
import pe.mud.model.PokemonModel;

public class PokemonDao {
    
    
    public void AddPokemon(String nombre,String img)
    {
        Connection con = null;
        
        try
        {
           con = AccesoDB.getConexion();
           String sql = "INSERT INTO tablaPokemon(nombre,img) VALUES(?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nombre);
            pstm.setString(2, img);
            pstm.executeUpdate();
            pstm.close();
        }catch(Exception e)
        {
            
        }
    }
    
    public List<PokemonModel> listar() throws SQLException {
        Connection cn = null;
        List<PokemonModel> lista = new ArrayList<PokemonModel>();

        cn = AccesoDB.getConexion();
        String sql = "SELECT * FROM tablaPokemon";
        CallableStatement cstm = cn.prepareCall(sql);
        ResultSet rs = cstm.executeQuery();
        while (rs.next()) {
            PokemonModel pokemon = new PokemonModel();
            pokemon.setId(rs.getInt("id"));
            pokemon.setNombre(rs.getString("nombre"));
            pokemon.setImg(rs.getString("img"));
            lista.add(pokemon);
        }
        cstm.close();

        return lista;
    }
}
