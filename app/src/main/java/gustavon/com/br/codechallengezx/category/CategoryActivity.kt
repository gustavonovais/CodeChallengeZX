package gustavon.com.br.codechallengezx.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.AllCategoriesSearchQuery
import gustavon.com.br.codechallengezx.R
import gustavon.com.br.codechallengezx.networking.ApiGraphQL
import kotlinx.android.synthetic.main.activity_produtcs.*

class CategoryActivity : AppCompatActivity(), CategoryView{
    private lateinit var presenter: CategoryPresenter
    private lateinit var apiGraphQL: ApiGraphQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtcs)
        title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        apiGraphQL = ApiGraphQL()
        presenter = CategoryPresenter(apiGraphQL, this)

        load.visibility = View.VISIBLE
        presenter.allCategoriesSearch()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun allCategoriesSearchSuccess(allCategoriesSearchQuery: Response<AllCategoriesSearchQuery.Data>) {
        runOnUiThread {
            load.visibility = View.GONE
            layout.visibility = View.VISIBLE
            viewPager.adapter = CategoryPageAdapter(supportFragmentManager, allCategoriesSearchQuery.data())
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun allCategoriesSearchError(e: ApolloException) {
        load.visibility = View.GONE
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }
}
