package com.fhomovc.arrkandroidexercise.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.fhomovc.arrkandroidexercise.R
import com.fhomovc.arrkandroidexercise.models.Person
import com.fhomovc.arrkandroidexercise.presenters.PersonDetailPresenter
import kotlinx.android.synthetic.main.activity_person_detail.*


class PersonDetailActivity : AppCompatActivity(), PersonDetailPresenter.PersonDetailView {

    var presenter: PersonDetailPresenter = PersonDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        val bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadPerson(intent.getStringExtra(PERSON_ID))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PERSON_ID = "PERSON_ID"

        fun newIntent(context: Context, id: String): Intent =
            Intent(context, PersonDetailActivity::class.java).apply {
                putExtra(PERSON_ID, id)
            }
    }

    override fun hideProgress() {
        person_detail_layout.visibility = View.VISIBLE
        persons_detail_spinner.visibility = View.GONE
    }

    override fun showProgress() {
        person_detail_layout.visibility = View.GONE
        persons_detail_spinner.visibility = View.VISIBLE
    }

    override fun setData(person: Person) {
        with(person) {
            person_detail_name.text = formatTextColor(getString(R.string.person_name), name)
            person_detail_height.text = formatTextColor(getString(R.string.person_height), formatHeight())
            person_detail_mass.text = formatTextColor(getString(R.string.person_mass), mass)
            person_detail_created_at.text = formatTextColor(getString(R.string.person_created_at), formatDate())
        }
    }

    private fun formatTextColor(format: String, value: String): SpannableString {
        val spanString = SpannableString(String.format(format, value))
        val formatLength = spanString.length - value.length
        spanString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAttributeValueText)),
            formatLength,
            spanString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spanString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAttributeLabelText)),
            0,
            formatLength,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spanString
    }

    override fun showError() {
        Toast.makeText(this@PersonDetailActivity, R.string.network_error_msg, Toast.LENGTH_LONG).show()
    }

}
