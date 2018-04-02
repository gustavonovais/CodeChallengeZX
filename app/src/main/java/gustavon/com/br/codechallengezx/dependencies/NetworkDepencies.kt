package gustavon.com.br.codechallengezx.dependencies

import dagger.Component
import gustavon.com.br.codechallengezx.networking.ApiGraphQL
import gustavon.com.br.codechallengezx.networking.NetworkModule
import javax.inject.Singleton

/**
 * Created by gustavon on 02/04/18.
 */

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkDependencies {
    fun inject(apiGraphQL: ApiGraphQL)
}
