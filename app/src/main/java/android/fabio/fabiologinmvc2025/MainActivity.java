package android.fabio.fabiologinmvc2025;

import android.fabio.fabiologinmvc2025.controller.UsuarioController;
import android.fabio.fabiologinmvc2025.model.Usuario;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nomeUsuario, emailUsuario, senhaUsuario;
    Button cadastrar, entrar;
    UsuarioController usuarioController;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        cadastrar.setOnClickListener(view -> {
                    if (validaCampos()) {
                        usuario = new Usuario();
                        usuario.setNome(nomeUsuario.getText().toString());
                        usuario.setEmail(emailUsuario.getText().toString());
                        usuario.setSenha(senhaUsuario.getText().toString());

                        boolean isCheckUser = usuarioController.checkUser(usuario.getEmail());
                        if( isCheckUser ) {
                            Toast.makeText(this, "Usuário já cadastrado", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean isCheckUserPass = usuarioController.checkUserPass(usuario.getEmail(), usuario.getSenha());
                            if( isCheckUserPass ) {
                                usuarioController.inserir(usuario);
                                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        entrar.setOnClickListener(view -> {
            if( validaCampos() ) {
                usuario = new Usuario();
                usuario.setNome(nomeUsuario.getText().toString());
                usuario.setEmail(emailUsuario.getText().toString());
                usuario.setSenha(senhaUsuario.getText().toString());
                usuarioController = new UsuarioController(this);

                boolean isCheckUser = usuarioController.checkUser(usuario.getEmail());
                boolean isCheckPass = usuarioController.checkUserPass(usuario.getEmail(), usuario.getSenha());
                if( isCheckUser && isCheckPass ) {
                    Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validaCampos() {
        boolean camposValidados = false;
        if( nomeUsuario.getText().toString().isEmpty() ||
            emailUsuario.getText().toString().isEmpty() ||
            senhaUsuario.getText().toString().isEmpty() ) {
            camposValidados = false;
        } else {
            camposValidados = true;
        }
        return camposValidados;
    }

    private void initComponents() {
        nomeUsuario  = findViewById(R.id.nome);
        emailUsuario = findViewById(R.id.email);
        senhaUsuario = findViewById(R.id.senha);
        cadastrar    = findViewById(R.id.cadastrar);
        entrar       = findViewById(R.id.entrar);
    }
}