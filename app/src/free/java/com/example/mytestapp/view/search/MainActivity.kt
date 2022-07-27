package com.example.mytestapp.view.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.mytestapp.R
import com.example.mytestapp.databinding.ActivityMainBinding
import com.example.mytestapp.presenter.search.PresenterSearchContract
import com.example.mytestapp.presenter.search.SearchPresenter
import com.example.mytestapp.repository.FakeGitHubRepository
import com.example.mytestapp.repository.RepositoryContract
import com.example.mytestapp.view.details.DetailsActivity
import java.util.*

class MainActivity : AppCompatActivity(), ViewSearchContract {

    private lateinit var binding: ActivityMainBinding
    private val adapter = SearchResultAdapter()
    private val fakeRepository = FakeGitHubRepository()
    private val presenter: PresenterSearchContract = SearchPresenter(this, fakeRepository)
    private var totalCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        binding.toDetailsActivityButton.setOnClickListener {
            startActivity(DetailsActivity.getIntent(this, totalCount))
        }
        setQueryListener()
        setRecyclerView()
    }

    private fun setRecyclerView() {
       binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun setQueryListener() {
       binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEditText.text.toString()
                if (query.isNotBlank()) {
                    presenter.searchGitHub(query)
                    return@OnEditorActionListener true
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.enter_search_word),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnEditorActionListener false
                }
            }
            false
        })
    }

    private fun createRepository(): RepositoryContract {
        return FakeGitHubRepository()
    }


    override fun displaySearchResults(
        searchResults: List<com.example.mytestapp.model.SearchResult>,
        totalCount: Int
    ) {
        with(binding.totalCountTextView) {
            visibility = View.VISIBLE
            text =
                String.format(
                    Locale.getDefault(), getString(R.string.results_count),
                    totalCount
                )
        }
        this.totalCount = totalCount
        adapter.updateResults(searchResults)
    }


    override fun displayError() {
        Toast.makeText(this, getString(R.string.undefined_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}
