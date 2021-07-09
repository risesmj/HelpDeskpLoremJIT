package com.loremjit.centraldechamados.model.Chamado;

import com.loremjit.centraldechamados.model.Login.Empresa;

import java.util.ArrayList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Area extends RealmObject {
    @PrimaryKey
    private String codigo;
    private String nome;
    @Ignore
    private ArrayList<Empresa> empresasDeAcesso;
}
