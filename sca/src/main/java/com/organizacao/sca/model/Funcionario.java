/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Anjo_Grabiel
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private String orgaorg;
    private byte[] foto;
    //private String bairro;
    private String cargo;
    private String celular;
    private String telefone;
    private BigDecimal comissao;
    private BigDecimal salario;
    //private Integer departamento_codigo;
    private Date posse;
    private String auditoria;
    private List<EnderecoFuncionario> enderecos = new ArrayList<>();
    //private Set<Departamento> departamento = new HashSet<>();
    private Set<Profissao> profissoes = new HashSet<>();
    private Set<Departamento> departamentos = new HashSet<>();
    private List<Usuario> usuarios;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotBlank // Não pode estar em branco
    @Size(max = 100) //Tamanho máximo de 50 caracteres
    @Column(nullable = false, length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    @NotNull //Não pode ser nulo
    @Column(name = "cpf", nullable = false, length = 14)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Email
    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 255)
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @NotNull //Não pode ser nulo
    @Column(nullable = false, length = 20)
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaorg() {
        return orgaorg;
    }

    public void setOrgaorg(String orgaorg) {
        this.orgaorg = orgaorg;
    }

    @Lob
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    @NotNull //Não pode ser nulo
    @Column(nullable = false, length = 60)
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @NotNull //Não pode ser nulo
    @Column(name = "celular", nullable = false, length = 12)
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    @NotBlank
    @NotNull //Não pode ser nulo
    @Column(name = "tel_fixo", nullable = false, length = 12)
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Column(name = "comissao", precision = 10, scale = 2)
    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    @Column(name = "salario", nullable = false, precision = 10, scale = 2)
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getPosse() {
        return posse;
    }

    public void setPosse(Date posse) {
        this.posse = posse;
    }

    public String getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }
     
    //orphanRemoval= true para deletar endereço
   //@NotNull //Não pode ser nulo
   @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval= true)// relacionamento um para muitos(chave extrangeira fica na tabela "Endereco")
   public List<EnderecoFuncionario> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoFuncionario> enderecos) {
        this.enderecos = enderecos;
    }
    
    @ManyToMany
    @JoinTable(name = "funcionario_departamentos",
    joinColumns = @JoinColumn(name = "funcionario_id"),
    inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    public Set<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    
    @ManyToMany
    @JoinTable(name = "funcionario_profissoes",
    joinColumns = @JoinColumn(name = "funcionario_id"),
    inverseJoinColumns = @JoinColumn(name = "profissao_id"))
    public Set<Profissao> getProfissoes() {
        return profissoes;
    }

    public void setProfissoes(Set<Profissao> profissoes) {
        this.profissoes = profissoes;
    }
    
    @OneToMany(mappedBy = "funcionario")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    

}
