package hu.unideb.inf.f1uptodate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.model.raceresult.RaceResult
import kotlinx.android.synthetic.main.row_items.view.*

class RaceAdapter(
    val context : Context, val raceList: List<RaceResult>) :
    RecyclerView.Adapter<RaceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var round: TextView = itemView.round
        var name: TextView = itemView.raceName
        var date: TextView = itemView.date
        var winner: TextView = itemView.winner

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.round.text = raceList[position].round
        holder.name.text = raceList[position].name
        holder.date.text = raceList[position].winner
        holder.winner.text = "Winner: " + raceList[position].date
    }

    override fun getItemCount(): Int {
        return raceList.size
    }
}