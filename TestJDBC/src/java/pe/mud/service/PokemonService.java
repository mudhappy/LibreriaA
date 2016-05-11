/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mud.service;

import pe.mud.dao.PokemonDao;

public class PokemonService {
    
    private PokemonDao dao;
    
    public PokemonService()
    {
        dao = new PokemonDao();
    }
            
    public void addPokemon(String nombre, String img)
    {
        dao.AddPokemon(nombre, img);
    }
    
}
