package gustavon.com.br.codechallengezx.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import gustavon.com.br.codechallengezx.AllCategoriesSearchQuery
import gustavon.com.br.codechallengezx.products.ProductsFragment

/**
 * Created by gustavon on 31/03/18.
 */
class CategoryPageAdapter(fragmentManager: FragmentManager, var list: AllCategoriesSearchQuery.Data?) :
        FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return ProductsFragment.newInstance("","")
    }

    override fun getCount(): Int {
        return list?.allCategory()?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list?.allCategory()?.get(position)?.title().toString()
    }
}