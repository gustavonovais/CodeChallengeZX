package gustavon.com.br.codechallengezx.networking

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.*
import gustavon.com.br.codechallengezx.utils.fromISO8601UTC
import gustavon.com.br.codechallengezx.utils.getDateNow

/**
 * Created by gustavon on 31/03/18.
 */
open class ApiGraphQL {

    fun pocCategorySearchMethod(callbackPocCategorySearchMethod: CallbackPocCategorySearchMethod, id : String, categoryId : String){
        CodeChallengeZxApplication.apolloClient.query(PocCategorySearchQuery.builder()
                .id(id)
                .categoryId(categoryId.toLong())
                .search("")
                .build()).enqueue(object : ApolloCall.Callback<PocCategorySearchQuery.Data>(){

            override fun onResponse(response: Response<PocCategorySearchQuery.Data>) {
                callbackPocCategorySearchMethod.onSuccess(response)
            }

            override fun onFailure(e: ApolloException) {
                callbackPocCategorySearchMethod.onError(e)
            }
        })
    }

    fun pocSearchMethod(callbackPocSearchMethod: CallbackPocSearchMethod, lat : String, long : String){
        CodeChallengeZxApplication.apolloClient.query(PocSearchMethodQuery.builder()
                .lat(lat)
                .long1(long)
                .algorithm(BuildConfig.ALGORITHM)
                .now(fromISO8601UTC(getDateNow())).build()).enqueue(object : ApolloCall.Callback<PocSearchMethodQuery.Data>() {


            override fun onResponse(response: Response<PocSearchMethodQuery.Data>) {
                callbackPocSearchMethod.onSuccess(response)
            }

            override fun onFailure(e: ApolloException) {
                callbackPocSearchMethod.onError(e)
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

    interface CallbackPocSearchMethod {
        fun onSuccess(pocSearchMethodQuery: Response<PocSearchMethodQuery.Data>)
        fun onError(e: ApolloException)
    }

    interface CallbackPocCategorySearchMethod {
        fun onSuccess(pocCategorySearchMethod: Response<PocCategorySearchQuery.Data>)
        fun onError(e: ApolloException)
    }

}