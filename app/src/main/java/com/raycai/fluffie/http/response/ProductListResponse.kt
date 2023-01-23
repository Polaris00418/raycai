package com.raycai.fluffie.http.response

class ProductListResponse(status: Boolean) : BaseResponse(status) {
    var data: MutableList<ProductDetail>? = null

    class ProductDetail {
        var _id = ""
        var id = ""
        var title = ""
        var slug = ""
        var prod_link = ""
        var price = 0
        var img = ""
        var details = ""
        var category: MutableList<String>? = null
        var brand = ""
        var refined_category = ""
        var key_benefits: MutableList<String>? = null
        var ingredient = ""
        var prod_claims: MutableList<String>? = null
        var created_at = ""
        var updated_at = ""
        var updatedAt = ""
    }
}