package com.fhomovc.arrkandroidexercise.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fhomovc.arrkandroidexercise.R
import com.fhomovc.arrkandroidexercise.models.Person
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_person.*

class PeopleAdapter(
    private val people: MutableList<Person>,
    private val onClick: (Person) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_person, viewGroup, false).let {
                ViewHolder(it, onClick)
            }
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(people[position])
    }

    fun addPeople(people: List<Person>) {
        this.people.addAll(people)
    }

    class ViewHolder(override val containerView: View, private val onClick: (Person) -> Unit) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(person: Person) {
            with(person) {
                person_name.text = name
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }
}