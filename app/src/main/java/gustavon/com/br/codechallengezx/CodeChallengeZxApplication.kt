package gustavon.com.br.codechallengezx

import android.app.Application
import com.apollographql.apollo.ApolloClient
import gustavon.com.br.codechallengezx.type.CustomType
import gustavon.com.br.codechallengezx.utils.ISO8601Adapter
import okhttp3.OkHttpClient

/**
 * Created by gustavon on 31/03/18.
 */
open class CodeChallengeZxApplication : Application(){

    companion object {
        lateinit var apolloClient : ApolloClient
    }

    override fun onCreate() {
        super.onCreate()

        configApolloClient()
    }

    private fun configApolloClient() {
        val httpClient = OkHttpClient.Builder().build()

        apolloClient = ApolloClient.builder()
                .serverUrl(BuildConfig.BASEURL)
                .addCustomTypeAdapter(CustomType.DATETIME, ISO8601Adapter())
                .okHttpClient(httpClient)
                .build()
    }

}

