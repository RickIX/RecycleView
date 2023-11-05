package br.edu.up.listas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataset = mutableListOf(Aluno("Joao",20,"Medicina"),
            Aluno("Maria",25,"engenharia"),
            Aluno("Lucas",50,"direito") ,
            Aluno("Jose",40,"Medicina"),
            Aluno("Joana",40,"Medicina"),
            Aluno("Joaquim",19,"Medicina"),
            Aluno("Gabriel",20,"Analise de Sistemas"),)
        val recicle: RecyclerView = findViewById(R.id.reciclerView)
        var customAdapter = CustomAdapter(dataset)
        recicle.layoutManager = LinearLayoutManager(this)
        recicle.adapter = customAdapter

        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                dataset.removeAt(position)
                customAdapter.notifyItemRemoved(position)
            }
        }
        )
        itemTouchHelper.attachToRecyclerView(recicle)
    }

    class CustomAdapter(private val dataSet: MutableList<Aluno>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val nomeTextView: TextView
            val cursoTextView: TextView
            val idadeTextView: TextView
            init {
                nomeTextView = view.findViewById(R.id.nomeTextView)
                cursoTextView = view.findViewById(R.id.cursoTextView)
                idadeTextView = view.findViewById(R.id.idadeTextView)
            }
        }
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CustomAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
            return ViewHolder(view)
        }
        override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
            val aluno = dataSet[position]
            holder.nomeTextView.text = aluno.nome
            holder.idadeTextView.text = aluno.idade.toString()
            holder.cursoTextView.text = aluno.curso
            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, DetalhesAlunoActivity::class.java )
                intent.putExtra("nome", aluno.nome)
                intent.putExtra("idade", aluno.idade)
                intent.putExtra("curso", aluno.curso)
                it.context.startActivity(intent)
            }
        }
        override fun getItemCount(): Int {
            return dataSet.size
        }

    }
}