package gustavon.com.br.codechallengezx.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import gustavon.com.br.codechallengezx.PocSearchMethodQuery
import gustavon.com.br.codechallengezx.R
import gustavon.com.br.codechallengezx.networking.ApiGraphQL
import gustavon.com.br.codechallengezx.category.CategoryActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), HomeView {

    private lateinit var presenter: HomePresenter
    private lateinit var apiGraphQL: ApiGraphQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        title = "Delivery address"

        apiGraphQL = ApiGraphQL()
        presenter = HomePresenter(apiGraphQL,this)

        configPlaceAutoComplete()
    }

    private fun configPlaceAutoComplete() {
        val autocompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                load.visibility = View.VISIBLE
                presenter.pocSearchMethod(place.latLng.latitude.toString(), place.latLng.longitude.toString())
            }

            override fun onError(status: Status) {
                Toast.makeText(this@HomeActivity, "Ocorreu um erro: $status", Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun searchMethodSuccess(pocSearchMethodQuery:  Response<PocSearchMethodQuery.Data>) {
        runOnUiThread{
            load.visibility = View.GONE

            var intent  = Intent(this@HomeActivity, CategoryActivity::class.java)
            //intent.putExtra("url_pull", item?.pulls_url.substringBefore("{"))
            startActivity(intent)
        }
    }

    override fun searchMethodError(e: ApolloException) {
        runOnUiThread{
            load.visibility = View.GONE
            Toast.makeText(this@HomeActivity, e.toString(), Toast.LENGTH_LONG).show()
        }
    }


}
