package com.itb.inf3bn.pizzariacurso25.model.entity;
 
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
 
@Entity
@Table(name = "item_pedido")
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private int quantidadeItem;
 
    @Column(nullable = false, precision = 10, scale = 2) // Para valores decimais
    private BigDecimal precoUnitario;
 
    @Column(nullable = false)
    private boolean codStatus;
}
    