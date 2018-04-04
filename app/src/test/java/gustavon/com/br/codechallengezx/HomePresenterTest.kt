package gustavon.com.br.codechallengezx

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import gustavon.com.br.codechallengezx.home.HomePresenter
import gustavon.com.br.codechallengezx.products.ProductsPresenter
import org.junit.Test
import org.mockito.Mock

/**
 * Created by gustavon on 02/04/18.
 */
class HomePresenterTest {

    @Mock
    private var homePresenter: HomePresenter? = null

    @Mock
    lateinit var response: Response<PocSearchMethodQuery.Data>

    @Mock
    lateinit var apolloException: ApolloException


    @Test
    fun onSuccessNull() {
        homePresenter?.onSuccess(response)
    }

    @Test
    fun onErrorNull() {
        homePresenter?.onError(apolloException)
    }

    @Test
    fun testPocSearchValueZero(){
        homePresenter?.pocSearchMethod("999", "0")
    }

    @Test
    fun testPocSearchValueEmpty(){
        homePresenter?.pocSearchMethod("", "")
    }
}