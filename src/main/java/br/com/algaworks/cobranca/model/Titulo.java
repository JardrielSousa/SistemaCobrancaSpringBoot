package br.com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Titulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo ;
	
	@NotNull(message = "Descrição é obrigatória")
	@NotEmpty(message = "Descrição é obrigatória")
	@Size(max = 60 ,  min = 3, message = "Descrição não pode contém , mais que 60 Caracteries")
	private String descricao;
	
	@NotNull(message = "Data de Nascimento é obrigatória")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataVencimento;
	
	@NotNull(message = "Valor é obrigatória ")
	@DecimalMin(value = "0.01",message = "Valor não pode ser menor que 0.01")
	@DecimalMax(value = "9999999.99",message = "Valor não pode ser maior que 9.999.999.999.99")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
	}
	
}
