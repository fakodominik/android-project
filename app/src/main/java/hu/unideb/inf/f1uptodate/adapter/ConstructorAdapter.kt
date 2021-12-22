package hu.unideb.inf.f1uptodate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.model.constructor.ConstructorResult
import hu.unideb.inf.f1uptodate.utils.ConstructorDiffUtil
import kotlinx.android.synthetic.main.row_items_const.view.*


class ConstructorAdapter(
    val context : Context,private var standingsList: List<ConstructorResult>) :
    RecyclerView.Adapter<ConstructorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var position: TextView = itemView.position
        var points: TextView = itemView.points
        var constructorName: TextView = itemView.constName
        var wins: TextView = itemView.wins

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items_const, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.position.text = standingsList[position].position
        holder.points.text = standingsList[position].points + " points"
        holder.constructorName.text = standingsList[position].name
        holder.wins.text = "Won races: " + standingsList[position].wins
    }

    override fun getItemCount(): Int {
        return standingsList.size
    }

    fun setData(newList : List<ConstructorResult>) {
        val diffutil = ConstructorDiffUtil(standingsList,newList)
        val diffResults = DiffUtil.calculateDiff(diffutil)
        standingsList = newList
        diffResults.dispatchUpdatesTo(this)

    }
}