package gustavon.com.br.codechallengezx

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.category.CategoryPresenter
import org.junit.Test
import org.mockito.Mock

/**
 * Created by gustavon on 02/04/18.
 */
class CategoryPresenterTest {

    @Mock
    lateinit var response: Response<AllCategoriesSearchQuery.Data>

    @Mock
    lateinit var apolloException: ApolloException

    @Mock
    var categoryPresenter: CategoryPresenter? = null

    @Test
    fun onSuccessNull() {
        categoryPresenter?.onSuccess(response)
    }

    @Test
    fun onErrorNull() {
        categoryPresenter?.onError(apolloException)
    }

}