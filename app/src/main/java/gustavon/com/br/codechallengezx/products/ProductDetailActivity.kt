package gustavon.com.br.codechallengezx.products

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    private var price =  ""
    private var description = ""
    private var title_text = ""
    private var img = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_item)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        getArgs()
        configFields()
        title = getString(R.string.product)
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

        if (!title_text.isNullOrEmpty()){
            text_title.text = title_text
        }

        if (price != "null"){
            text_price.text = price.format(2)
        } else {
            text_price.text = getString(R.string.value_zero)
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
            title_text = intent.extras.getString(TITLE)
        }

        if (intent.extras.getString(IMG) != null){
            img = intent.extras.getString(IMG)
        }
    }
}
