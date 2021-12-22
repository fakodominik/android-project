package hu.unideb.inf.f1uptodate.utils

import androidx.recyclerview.widget.DiffUtil
import hu.unideb.inf.f1uptodate.model.championship.ChampionshipResult

class ChampionDiffutil(
    private val oldList: List<ChampionshipResult>,
    private val newList: List<ChampionshipResult>
) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }


}