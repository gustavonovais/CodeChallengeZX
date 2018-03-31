package gustavon.com.br.codechallengezx.home

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.PocSearchMethodQuery

interface HomeView {
    fun searchMethodSuccess(ocSearchMethodQuery: Response<PocSearchMethodQuery.Data>)
    fun searchMethodError(e: ApolloException)
}