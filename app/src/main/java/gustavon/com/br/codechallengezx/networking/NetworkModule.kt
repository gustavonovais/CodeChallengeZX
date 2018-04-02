package gustavon.com.br.codechallengezx.networking

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import gustavon.com.br.codechallengezx.BuildConfig
import gustavon.com.br.codechallengezx.type.CustomType
import gustavon.com.br.codechallengezx.utils.ISO8601Adapter
import okhttp3.OkHttpClient

/**
 * Created by gustavon on 02/04/18.
 */
@Module
class NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        val httpClient = OkHttpClient.Builder().build()

        return ApolloClient.builder()
                .serverUrl(BuildConfig.BASEURL)
                .addCustomTypeAdapter(CustomType.DATETIME, ISO8601Adapter())
                .okHttpClient(httpClient)
                .build()
    }

}
