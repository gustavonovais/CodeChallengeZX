package gustavon.com.br.codechallengezx.home

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.PocSearchMethodQuery
import gustavon.com.br.codechallengezx.networking.ApiGraphQL

/**
 * Created by gustavon on 31/03/18.
 */
class HomePresenter (private var apiGraphQL: ApiGraphQL, private var view: HomeView) : ApiGraphQL.CallbackPocSearchMethod {

    override fun onSuccess(pocSearchMethodQuery: Response<PocSearchMethodQuery.Data>) {
        view.searchMethodSuccess(pocSearchMethodQuery)
    }

    override fun onError(e: ApolloException) {
        view.searchMethodError(e)
    }

    fun pocSearchMethod(lat : String, long : String){
        apiGraphQL.pocSearchMethod(this, lat, long)
    }

}