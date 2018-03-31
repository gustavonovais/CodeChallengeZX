package gustavon.com.br.codechallengezx.category

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.AllCategoriesSearchQuery

/**
 * Created by gustavon on 31/03/18.
 */
interface CategoryView {
    fun allCategoriesSearchSuccess(allCategoriesSearchQuery: Response<AllCategoriesSearchQuery.Data>)
    fun allCategoriesSearchError(e: ApolloException)
}