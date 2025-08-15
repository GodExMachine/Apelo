package modelo.entidade.animal;

public class Animal {

    private Long id;
    private String especie;
    private String raca;
    private String cor;
    private String porte;

    public Animal() {
    }

    public Animal(Long id, String especie, String raca, String cor, String porte) {
        setId(id);
        setEspecie(especie);
        setRaca(raca);
        setCor(cor);
        setPorte(porte);
    }

    public Animal(String especie, String raca, String cor, String porte) {
        setEspecie(especie);
        setRaca(raca);
        setCor(cor);
        setPorte(porte);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
