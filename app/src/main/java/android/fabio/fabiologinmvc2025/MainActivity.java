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
    UsuarioController controller;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entrar.setOnClickListener(view -> {
            if( validaCampos() ) {
                usuario = new Usuario();
                String nome = nomeUsuario.getText().toString();
                String email = emailUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                controller = new UsuarioController(this);

                boolean isCheckUser = controller.checkUsuario( email );
                boolean isCheckPass = controller.checkSenha( email, senha );
                if( isCheckUser && isCheckPass ) {
                    Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        cadastrar.setOnClickListener(view -> {
            if (validaCampos()) {
                usuario = new Usuario();
                controller = new UsuarioController(this);

                String nome  = nomeUsuario.getText().toString();
                String email = emailUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                boolean isCheckUser = controller.checkUsuario( email );
                if( isCheckUser ) {
                    Toast.makeText(this, "Usuário já cadastrado", Toast.LENGTH_SHORT).show();
                } else {
                    controller.inserir(usuario);
                    Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
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