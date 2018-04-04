package gustavon.com.br.codechallengezx.category

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.AllCategoriesSearchQuery
import gustavon.com.br.codechallengezx.networking.ApiGraphQL

/**
 * Created by gustavon on 31/03/18.
 */
class CategoryPresenter (private var apiGraphQL: ApiGraphQL, private var view: CategoryView) : ApiGraphQL.CallbackAllCategories{

    override fun onSuccess(allCategoriesSearchQuery: Response<AllCategoriesSearchQuery.Data>) {
        view.allCategoriesSearchSuccess(allCategoriesSearchQuery)
    }

    override fun onError(e: ApolloException) {
        view.allCategoriesSearchError(e)
    }

    fun allCategoriesSearch(){
        apiGraphQL.allCategoriesSearch(this)
    }

}