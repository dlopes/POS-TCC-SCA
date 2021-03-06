package com.organizacao.sca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "endereco")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    private String cep;
    //private Cliente cliente;
    //private Funcionario funcionario;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank // Não pode estar em branco
    @Size(max = 150) //Tamanho máximo de 100 caracteres
    @Column(nullable = false, length = 150)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    @NotNull
    @Size(max = 20) //Tamanho máximo de 20 caracteres
    @Column(nullable = false, length = 20)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Size(max = 150) //Tamanho máximo de 150 caracteres
    @Column(length = 150)
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    @NotNull
    @Size(max = 60) //Tamanho máximo de 100 caracteres
    @Column(nullable = false, length = 60)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @NotNull
    @Size(max = 60) //Tamanho máximo de 100 caracteres
    @Column(nullable = false, length = 60)
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @NotNull
    @Size(max = 9) //Tamanho máximo de 100 caracteres
    @Column(nullable = false, length = 9)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

   /* @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public Cliente getFuncionario() {
        return cliente;
    }

    public void setFuncionario(Cliente cliente) {
        this.cliente = cliente;
    }

    //@NotNull
    @ManyToOne
    @JoinColumn(name = "funcioanrio_id", nullable = false)
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Endereco other = (Endereco) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}