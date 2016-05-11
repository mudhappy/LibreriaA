
package pe.mud.model;

public class PokemonModel {
    private int id;
    private String nombre;
    private String img;

    public PokemonModel() {
    }

    public PokemonModel(int id, String nombre, String img) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
}
