package gustavon.com.br.codechallengezx.products

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.PocCategorySearchQuery
import gustavon.com.br.codechallengezx.networking.ApiGraphQL

/**
 * Created by gustavon on 01/04/18.
 */
class ProductsPresenter (private var apiGraphQL: ApiGraphQL, private var view: ProductsView) : ApiGraphQL.CallbackPocCategorySearchMethod{

    override fun onSuccess(pocCategorySearchMethod: Response<PocCategorySearchQuery.Data>) {
       view.pocCagegorySearchMethodSuccess(pocCategorySearchMethod)
    }

    override fun onError(e: ApolloException) {
        view.pocCagegorySearchMethodError(e)
    }

    fun pocCagegorySearchMethod(id : String, categorySearch : String){
        apiGraphQL.pocCategorySearchMethod(this, id, categorySearch)
    }

}