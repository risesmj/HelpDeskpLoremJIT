package com.loremjit.centraldechamados.model.Login;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Empresa extends RealmObject {
    @PrimaryKey
    private int id;
    private int codigoEmpresa;
    private int codigoFilial;
    private String razaoSocial;
    private String nomeFantasia;
    private String cpnj;
    private String uf;
    private String municipio;
    private String bairro;
    private String endereco;
    private String cep;
}
