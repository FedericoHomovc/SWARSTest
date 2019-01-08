package com.fhomovc.arrkandroidexercise.activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fhomovc.arrkandroidexercise.R
import com.fhomovc.arrkandroidexercise.adapters.PeopleAdapter
import com.fhomovc.arrkandroidexercise.models.Person
import com.fhomovc.arrkandroidexercise.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityPresenter.MainView {
    private var mPresenter: MainActivityPresenter = MainActivityPresenter(this)
    private var mAdapter: PeopleAdapter? = null
    private var mLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        mPresenter.loadPeople()
    }

    private fun setUpRecyclerView() {
        mAdapter = PeopleAdapter(ArrayList<Person>()) {
            val split = it.url.split("/")
            val id = split[split.size - 2]
            startActivity(PersonDetailActivity.newIntent(this, id))
        }
        people_list_recycler_view.layoutManager = mLayoutManager
        people_list_recycler_view.adapter = mAdapter
        people_list_recycler_view.addOnScrollListener(FinishScrollListener())
    }

    private inner class FinishScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition() + 1
            val modelsCount = mAdapter?.itemCount

            if (lastVisibleItemPosition == modelsCount) {
                mPresenter.loadPeople()
            }
        }
    }

    override fun hideProgress(isFirstPage: Boolean) {
        people_list_recycler_view.visibility = View.VISIBLE
        if (isFirstPage)
            people_list_spinner.visibility = View.GONE
        else
            people_list_bottom_spinner.visibility = View.GONE
    }

    override fun showProgress(isFirstPage: Boolean) {
        if (isFirstPage) {
            people_list_spinner.visibility = View.VISIBLE
            people_list_recycler_view.visibility = View.GONE
        } else
            people_list_bottom_spinner.visibility = View.VISIBLE

    }

    override fun setData(people: List<Person>) {
        mAdapter?.addPeople(people)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showError() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.error_dialog_title))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.error_dialog_retry)) { _, _ -> mPresenter.loadPeople() }
        val alert = builder.create()
        alert.show()
    }
}
