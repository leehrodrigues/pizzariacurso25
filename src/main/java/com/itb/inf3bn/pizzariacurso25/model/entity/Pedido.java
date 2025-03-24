package com.itb.inf3bn.pizzariacurso25.model.entity;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
 
@Entity
@Table(name = "pedidos")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private LocalDateTime dataHoraCompra;
 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
 
    @Column(nullable = false, length = 50)
    private String status;
 
    @Column(nullable = false)
    private boolean codStatus;
 
    @Column(nullable = false)
    private LocalDateTime dataHoraEntrega;
 
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
 
    @Column(nullable = false, length = 20)
    private String numeroPedido;
 
    @Transient
    private String mensagemErro = "";
 
    @Transient
    private boolean isValid = true;
 
    public boolean validarPedido() {
        return isValid;
    }
}
