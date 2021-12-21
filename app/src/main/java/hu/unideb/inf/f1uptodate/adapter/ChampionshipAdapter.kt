package hu.unideb.inf.f1uptodate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.model.championship.ChampionshipResult
import kotlinx.android.synthetic.main.row_items_champ.view.*

class ChampionshipAdapter(
    val context : Context, val standingsList: List<ChampionshipResult>) :
    RecyclerView.Adapter<ChampionshipAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var position: TextView = itemView.position
        var driverName: TextView = itemView.driverName
        var points: TextView = itemView.points
        var constructorName: TextView = itemView.constructor

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items_champ, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.position.text = standingsList[position].position
        holder.driverName.text = standingsList[position].name
        holder.points.text = standingsList[position].points
        holder.constructorName.text = "Constructor: " + standingsList[position].constructor
    }

    override fun getItemCount(): Int {
        return standingsList.size
    }
}