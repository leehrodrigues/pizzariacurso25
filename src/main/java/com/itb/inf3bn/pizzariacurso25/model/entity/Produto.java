package com.itb.inf3bn.pizzariacurso25.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 100)
    private String nome;
    @Column (nullable = true, length = 255)
    private String descriçao;
    @Column(nullable = true, length = 45)
    private String tipo;
    @Column(nullable = true)
    private int quantidadeEstoque;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoVenda;
    @Column (nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoCompra;
    private boolean codStatus;

    // Relacionamento entre entidades

    //@manyToOne: Muitos para Um (Muitos produtos para Um Categoria)
    //CascadeType: Define como as operações de persistência (INSERT, UPDATE, DELETE) Serão propagadas de uma entidade pai para uma entidade filha no banco de dados
    //MERGE: propaga operações de atualizaçâo da entidade pai para filhas, o MERGE é usado para atualizar uma entidade desaclopada no banco de dados, ou seja, se a entidade não existe no banco, o MERGE a insere se ja existe, o MERGE atualiza os dados como novos valores além do MERGE temos: ALL, PERSIST, REMOVE, REFRESH E DATACH.
    //fetch: define como os dados relacionados serão carregados no banco de dados quando a entidade for consultada temos duas: EAGER e LAZY.
    //LAZY: os dados relacionados só serão carregados quando forem acessados explicitamente no código.
    //EAGER: os dados relacionados serão carregados automaticament, ou seja, os dados da entidade filha será carregado juntamente os dados da entidade pai.
    //@JoinColumn: Representa as informações da Chave Estrangeira (FK) (nome e referencia)

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = true)
    private Categoria categoria;

    // Atributos de apoio

    @Transient    //Anotação para atributos QUE NÃO REPRESENTAM COLUNAS NO BANCO DE DADOS
    private String mensagemErro = "";
    @Transient
    private boolean isValid = true;

    public boolean validarProduto() {

        if(nome == null || nome.isEmpty()){
            mensagemErro += "O nome do produto é obrigatório:";
            isValid = false;
        }
        if(precoCompra < 0) {
            precoCompra = 0;
            mensagemErro += "O preço de compra do produto deve ser maior que zero:";
            isValid = false;
        }
        if(precoVenda < 0) {
            precoVenda = 0;
            mensagemErro += "O preço de venda do produto deve ser maior que zero:";
            isValid = false;
        }
        return isValid;
    }


}
