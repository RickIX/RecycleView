package br.edu.up.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class DetalhesAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_aluno)
        val editTextNome: EditText = findViewById(R.id.editTextNome)
        val nomeAluno = intent.getStringExtra("nome")
        editTextNome.setText(nomeAluno)
        val editTextCurso: EditText = findViewById(R.id.editTextCurso)
        val cursoAluno = intent.getStringExtra("curso")
        editTextCurso.setText(cursoAluno)
        val editTextIdade: EditText = findViewById(R.id.editTextIdade)
        val idadeAluno = intent.getIntExtra("idade", 0).toString()
        editTextIdade.setText(idadeAluno)
    }
    fun voltarLista(view: View){
        finish()
    }
}

