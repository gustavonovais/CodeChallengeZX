package gustavon.com.br.codechallengezx

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.products.ProductsPresenter
import org.junit.Test
import org.mockito.Mock

/**
 * Created by gustavon on 02/04/18.
 */
class ProductPresenterTest {

    @Mock
    var productPresenter: ProductsPresenter? = null

    @Mock
    lateinit var response: Response<PocCategorySearchQuery.Data>

    @Mock
    lateinit var apolloException: ApolloException


    @Test
    fun onSuccessEmpty() {
        productPresenter?.onSuccess(response)
    }


    @Test
    fun onErrorNull() {
        productPresenter?.onError(apolloException)


    }

    @Test
    fun testPocSearchValueZero(){
        productPresenter?.pocCagegorySearchMethod("", "12333aaa")
    }
}