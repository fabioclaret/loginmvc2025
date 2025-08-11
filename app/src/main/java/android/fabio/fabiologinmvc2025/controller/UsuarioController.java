package android.fabio.fabiologinmvc2025.controller;

import android.content.ContentValues;
import android.content.Context;
import android.fabio.fabiologinmvc2025.datamodel.UsuarioDataModel;
import android.fabio.fabiologinmvc2025.datasource.AppDataBase;
import android.fabio.fabiologinmvc2025.model.Usuario;

import java.util.Collections;
import java.util.List;

public class UsuarioController extends AppDataBase implements iCRUD<Usuario>{
    ContentValues dadosDoObjeto;
    public UsuarioController(Context context) {
        super(context);
    }

    @Override
    public boolean inserir(Usuario usuario) {
        dadosDoObjeto = new ContentValues();
        dadosDoObjeto.put("nome" , usuario.getNome());
        dadosDoObjeto.put("email", usuario.getEmail());
        dadosDoObjeto.put("senha", usuario.getSenha());

        return insert(UsuarioDataModel.TABELA, dadosDoObjeto);
    }

    @Override
    public boolean alterar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean excluir(int id) {
        return false;
    }

    @Override
    public Usuario buscar(int id) {
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return Collections.emptyList();
    }
    public boolean checkSenha(String email, String senha) {
        return checkUserPass(email, senha);
    }
    public boolean checkUsuario(String email) {
        return checkUser(email);
    }
}
