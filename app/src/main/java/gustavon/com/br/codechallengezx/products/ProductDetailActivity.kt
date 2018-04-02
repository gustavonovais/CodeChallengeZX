package gustavon.com.br.codechallengezx.products

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import gustavon.com.br.codechallengezx.R
import kotlinx.android.synthetic.main.product_item.*

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val PRICE = "PRICE"
        const val DESCRIPTION = "DESCRIPTION"
        const val TITLE = "TITLE"
        const val IMG = "IMG"
    }

    var price =  ""
    var description = ""
    var title = ""
    var img = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_item)
        title = getString(R.string.product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getArgs()
        configFields()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configFields(){
        Picasso.with(this).load(img)
                .placeholder(R.drawable.placeholder)
                .into(product_img)

        if (!description.isNullOrEmpty()){
            text_description.text = description
        }

        if (!title.isNullOrEmpty()){
            text_title.text = title
        }

        if (!price.isNullOrEmpty()){
            text_price.text = price
        }
    }


    private fun getArgs(){
        if (intent.extras.getString(PRICE) !=  null){
            price = intent.extras.getString(PRICE)
        }

        if (intent.extras.getString(DESCRIPTION)!=null){
            description = intent.extras.getString(DESCRIPTION)
        }

        if (intent.extras.getString(TITLE) != null){
            title = intent.extras.getString(TITLE)
        }

        if (intent.extras.getString(IMG) != null){
            img = intent.extras.getString(IMG)
        }
    }
}
