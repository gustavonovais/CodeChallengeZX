package gustavon.com.br.codechallengezx.products

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.PocCategorySearchQuery

/**
 * Created by gustavon on 01/04/18.
 */
interface ProductsView {
    fun pocCagegorySearchMethodSuccess(pocCategorySearchQuery: Response<PocCategorySearchQuery.Data>)
    fun pocCagegorySearchMethodError(e: ApolloException)
}