package giovanni.aindroid.instagram.model;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import giovanni.aindroid.instagram.helper.ConfigFirebase;

public class Usuario implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String caminhoFoto;
    private int seguidores = 0;
    private int seguindo = 0;
    private int postagens = 0;

    public Usuario() {
    }

    public void atualizarQtdPostagem(){

        DatabaseReference firebaseRef = ConfigFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(getId());
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("postagens", getPostagens());

        usuarioRef.updateChildren(dados);

    }

    public void atualizar(){

        DatabaseReference firebaseRef = ConfigFirebase.getFirebase();

        Map objeto = new HashMap();
        objeto.put("/usuarios" + getId() + "/nome", getNome());
        objeto.put("/usuarios" + getId() + "/caminhoFoto", getCaminhoFoto());

        firebaseRef.updateChildren(objeto);

    }

    public Map<String, Object> converterParaMap(){

        HashMap<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("email", getEmail());
        usuarioMap.put("nome", getNome());
        usuarioMap.put("id", getId());
        usuarioMap.put("caminhoFoto", getCaminhoFoto());
        usuarioMap.put("seguidores", getSeguidores());
        usuarioMap.put("seguindo", getSeguindo());
        usuarioMap.put("postagens", getPostagens());

        return usuarioMap;

    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfigFirebase.getFirebase();
        DatabaseReference usuariosRef = firebaseRef.child("usuarios").child(getId());
        usuariosRef.setValue(this);
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public int getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(int seguindo) {
        this.seguindo = seguindo;
    }

    public int getPostagens() {
        return postagens;
    }

    public void setPostagens(int postagens) {
        this.postagens = postagens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    @Exclude
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
