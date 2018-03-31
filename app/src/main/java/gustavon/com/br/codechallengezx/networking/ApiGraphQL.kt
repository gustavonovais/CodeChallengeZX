package gustavon.com.br.codechallengezx.networking

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.AllCategoriesSearchQuery
import gustavon.com.br.codechallengezx.BuildConfig
import gustavon.com.br.codechallengezx.CodeChallengeZxApplication
import gustavon.com.br.codechallengezx.PocSearchMethodQuery
import gustavon.com.br.codechallengezx.utils.fromISO8601UTC
import gustavon.com.br.codechallengezx.utils.getDateNow

/**
 * Created by gustavon on 31/03/18.
 */
class ApiGraphQL {

    fun pocSearchMethod(callbackPocSearchMethodQuery: CallbackPocSearchMethodQuery, lat : String, long : String){
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

    fun allCategoriesSearch(callbackAllCategories: CallbackAllCategories){
        CodeChallengeZxApplication.apolloClient.query(AllCategoriesSearchQuery.builder().build()).enqueue(object : ApolloCall.Callback<AllCategoriesSearchQuery.Data>(){

            override fun onResponse(response: Response<AllCategoriesSearchQuery.Data>) {
                callbackAllCategories.onSuccess(response)
            }

            override fun onFailure(e: ApolloException) {
                callbackAllCategories.onError(e)
            }
        })
    }

    interface CallbackAllCategories {
        fun onSuccess(allCategoriesSearchQuery: Response<AllCategoriesSearchQuery.Data>)
        fun onError(e: ApolloException)
    }

    interface CallbackPocSearchMethodQuery {
        fun onSuccess(pocSearchMethodQuery: Response<PocSearchMethodQuery.Data>)
        fun onError(e: ApolloException)
    }

}