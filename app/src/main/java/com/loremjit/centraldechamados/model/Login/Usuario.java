package com.loremjit.centraldechamados.model.Login;

import com.loremjit.centraldechamados.model.Chamado.Area;

import java.util.ArrayList;

public class Usuario{
    private int codigo;
    private String login;
    private String nomeCompleto;
    private String departamento;
    private String email;
    private String superior;
    private ArrayList<Empresa> empresasDeAcesso;
    private ArrayList<Area> areasResponsavel;

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSuperior() { return superior; }
    public void setSuperior(String superior) { this.superior = superior; }
    public ArrayList<Empresa> getEmpresasDeAcesso() { return empresasDeAcesso; }
    public void setEmpresasDeAcesso(ArrayList<Empresa> empresasDeAcesso) { this.empresasDeAcesso = empresasDeAcesso; }
    public ArrayList<Area> getAreasResponsavel() { return areasResponsavel; }
    public void setAreasResponsavel(ArrayList<Area> areasResponsavel) { this.areasResponsavel = areasResponsavel; }
}
