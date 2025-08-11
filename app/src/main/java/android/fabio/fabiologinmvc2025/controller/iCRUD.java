package android.fabio.fabiologinmvc2025.controller;

import java.util.List;

public interface iCRUD <T>{
    public boolean inserir(T t);
    public boolean alterar(T t);
    public boolean excluir(int id);
    public T buscar(int id);
    public List<T> listar();

}
