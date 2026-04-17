public class Perfil {
    private int id;
    private String codigo;
    private String tamanho;
    private int quantidade;
    private String linhaFornecedor;

    // Construtor vazio
    public Perfil() {
    }

    // Construtor com parâmetros (sem id, pois é gerado pelo banco)
    public Perfil(String codigo, String tamanho, int quantidade, String linhaFornecedor) {
        this.codigo = codigo;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.linhaFornecedor = linhaFornecedor;
    }

    // Construtor completo (com id)
    public Perfil(int id, String codigo, String tamanho, int quantidade, String linhaFornecedor) {
        this.id = id;
        this.codigo = codigo;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.linhaFornecedor = linhaFornecedor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getLinhaFornecedor() {
        return linhaFornecedor;
    }

    public void setLinhaFornecedor(String linhaFornecedor) {
        this.linhaFornecedor = linhaFornecedor;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", quantidade=" + quantidade +
                ", linhaFornecedor='" + linhaFornecedor + '\'' +
                '}';
    }
}
