package hu.unideb.inf.f1uptodate.utils

import androidx.recyclerview.widget.DiffUtil
import hu.unideb.inf.f1uptodate.model.raceresult.RaceResult

class RaceDiffutil(
    private val oldList: List<RaceResult>,
    private val newList: List<RaceResult>
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