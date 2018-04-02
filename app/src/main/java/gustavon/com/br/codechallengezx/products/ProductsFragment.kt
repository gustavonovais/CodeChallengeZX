package gustavon.com.br.codechallengezx.products

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.PocCategorySearchQuery
import gustavon.com.br.codechallengezx.R
import gustavon.com.br.codechallengezx.category.CategoryActivity.Companion.ID
import gustavon.com.br.codechallengezx.networking.ApiGraphQL
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(), ProductsView {
    lateinit var id: String
    lateinit var categoryId: String

    lateinit var apiGraphQL: ApiGraphQL
    lateinit var presenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            id = arguments.getString(ID)
            categoryId = arguments.getString(CATEGORY_ID)
        }

        apiGraphQL = ApiGraphQL()
        presenter = ProductsPresenter(apiGraphQL, this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load.visibility = View.VISIBLE
        presenter.pocCagegorySearchMethod(id, categoryId)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_products, container, false)
    }

    override fun pocCagegorySearchMethodSuccess(pocCategorySearchQuery: Response<PocCategorySearchQuery.Data>) {
        activity.runOnUiThread {
            load.visibility = View.GONE

            if (pocCategorySearchQuery.data()?.poc()?.products()!!.size > 0){
                recycler_view.layoutManager = LinearLayoutManager(activity)
                recycler_view.adapter = ProductAdaper(pocCategorySearchQuery.data()?.poc()?.products() as MutableList<PocCategorySearchQuery.Product>)
            } else {
                text_error.visibility = View.VISIBLE
                text_error.text = getString(R.string.empty_products)
            }
        }
    }

    override fun pocCagegorySearchMethodError(e: ApolloException) {
        activity.runOnUiThread {
            load.visibility = View.GONE
            Toast.makeText(activity, getString(R.string.error_consult_products), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val CATEGORY_ID = "CATEGORY_ID"

        fun newInstance(id: String, categoryId: String): ProductsFragment {
            val fragment = ProductsFragment()
            val args = Bundle()
            args.putString(ID, id)
            args.putString(CATEGORY_ID, categoryId)
            fragment.arguments = args
            return fragment
        }
    }
}