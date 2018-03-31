package gustavon.com.br.codechallengezx.networking

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.BuildConfig
import gustavon.com.br.codechallengezx.CodeChallengeZxApplication
import gustavon.com.br.codechallengezx.PocSearchMethodQuery
import gustavon.com.br.codechallengezx.utils.fromISO8601UTC
import gustavon.com.br.codechallengezx.utils.getDateNow

/**
 * Created by gustavon on 31/03/18.
 */
class ApiGraphQL {

    fun callPocSearchMethod(callbackPocSearchMethodQuery: CallbackPocSearchMethodQuery, lat : String, long : String){
        CodeChallengeZxApplication.apolloClient.query(PocSearchMethodQuery.builder()
                .lat(lat)
                .long1(long)
                .algorithm(BuildConfig.ALGORITHM)
                .now(fromISO8601UTC(getDateNow())).build()).enqueue(object : ApolloCall.Callback<PocSearchMethodQuery.Data>() {


            override fun onResponse(response: Response<PocSearchMethodQuery.Data>) {
                callbackPocSearchMethodQuery.onSuccess(response)
            }

            override fun onFailure(e: ApolloException) {
                callbackPocSearchMethodQuery.onError(e)
            }
        })
    }

    interface CallbackPocSearchMethodQuery {
        fun onSuccess(pocSearchMethodQuery: Response<PocSearchMethodQuery.Data>)
        fun onError(e: ApolloException)
    }

}