package com.topevents.presentation


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.topevents.MainActivity
import com.topevents.R
import com.topevents.data.model.Event
import com.topevents.domain.EVENT_PLAY_LABEL
import com.topevents.util.parseIsoDate
import com.topevents.util.toStringCurrencyFormat
import com.topevents.util.toStringDateFormat
import kotlinx.android.synthetic.main.item_event.view.*

/**
 * [RecyclerView.Adapter] that can display a [Event] and makes a call to the
 * specified [MainActivity.OnListEventInteractionListener].
 */
class EventRecyclerViewAdapter(
        private val mValues: List<Event>,
        private val mListener: MainActivity.OnListEventInteractionListener?
) : RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Event
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onEventClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mNameView.text = item.name
        holder.mVenueView.text = item.venue

        holder.mPriceView.text = item.price.toStringCurrencyFormat()
        holder.mDateView.text = item.date.parseIsoDate().toStringDateFormat()
        holder.mLabelsView.text = item.labels.joinToString(transform = { it.capitalize() })

        holder.mAvailableSeatsView.text = holder.itemView.context.resources
                .getQuantityString(R.plurals.seats_available, item.available_seats, item.available_seats)

        if (item.labels.contains(EVENT_PLAY_LABEL)) {
            @Suppress("DEPRECATION")
            holder.mLabelsView.setTextColor(holder.itemView.context.resources.getColor(R.color.colorAccent))
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameView: TextView = mView.nameView
        val mDateView: TextView = mView.dateView
        val mAvailableSeatsView: TextView = mView.availableSeatsView
        val mPriceView: TextView = mView.priceView
        val mVenueView: TextView = mView.venueView
        val mLabelsView: TextView = mView.labelsView

        override fun toString(): String {
            return super.toString() + " '" + mNameView.text + "'"
        }
    }
}