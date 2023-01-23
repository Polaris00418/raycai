package com.raycai.fluffie.http.response

import java.security.Key

class ProductReviewsResponse(status: Boolean) : BaseResponse(status) {
    var data: MutableList<ProductReview>? = null

    class ProductReview {
        var _id = ""
        var id = ""
        var review_id = ""
        var prod_id = ""
        var title = ""
        var name = ""
        var desc = 0
        var rating = ""
        var country = ""
        var sentiment = false
        var labels: MutableList<String>? = null
        var created_at = ""
        var updated_at = ""
   }
}