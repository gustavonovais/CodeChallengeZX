package gustavon.com.br.codechallengezx.products

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import gustavon.com.br.codechallengezx.PocCategorySearchQuery
import gustavon.com.br.codechallengezx.R
import gustavon.com.br.codechallengezx.products.ProductDetailActivity.Companion.DESCRIPTION
import gustavon.com.br.codechallengezx.products.ProductDetailActivity.Companion.IMG
import gustavon.com.br.codechallengezx.products.ProductDetailActivity.Companion.PRICE
import gustavon.com.br.codechallengezx.products.ProductDetailActivity.Companion.TITLE
import kotlinx.android.synthetic.main.product_item.view.*

/**
 * Created by gustavon on 01/04/18.
 */
class ProductAdaper(private var productList : MutableList<PocCategorySearchQuery.Product>) : RecyclerView.Adapter<ProductAdaper.ViewHolder>(){

    lateinit var activity : Activity

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.product_item, parent, false)
        activity = parent?.context as AppCompatActivity
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = productList.get(position)

        holder?.let {
           setValueFields(item, holder)

            holder.card_item.setOnClickListener {
                callDetail(item)
            }
        }
    }

    private fun setValueFields(item: PocCategorySearchQuery.Product, holder: ViewHolder){
        Picasso.with(activity).load(item.productVariants()?.get(0)?.imageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.productImg)

        if (item.productVariants()?.get(0)?.price() == null){
            holder.price.text = "0.00"
        } else {
            holder.price.text = item.productVariants()?.get(0)?.price().toString()
        }
        if (!item.productVariants()?.get(0)?.description().isNullOrEmpty()){
            holder.description.text = item.productVariants()?.get(0)?.description()
        }
        if (!item.productVariants()?.get(0)?.title().isNullOrEmpty()){
            holder.title.text = item.productVariants()?.get(0)?.title()
        }
    }

    private fun callDetail(item : PocCategorySearchQuery.Product){
        var intent  = Intent(activity, ProductDetailActivity::class.java)

        if (!item.productVariants()?.get(0)?.price().toString().isNullOrEmpty()){
            intent.putExtra(PRICE, item.productVariants()?.get(0)?.price().toString())
        }
        if (!item.productVariants()?.get(0)?.description().isNullOrEmpty()){
            intent.putExtra(DESCRIPTION, item.productVariants()?.get(0)?.description())
        }
        if (!item.productVariants()?.get(0)?.title().isNullOrEmpty()){
            intent.putExtra(TITLE, item.productVariants()?.get(0)?.title())
        }
        if (!item.productVariants()?.get(0)?.imageUrl().isNullOrEmpty()){
            intent.putExtra(IMG, item.productVariants()?.get(0)?.imageUrl())
        }

        activity.startActivity(intent)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImg = itemView.product_img
        val price = itemView.text_price
        val description = itemView.text_description
        val title = itemView.text_title
        val card_item = itemView.card_item
    }
}