package com.dokapegroup.dokapebackend.controllers.mapper

import com.dokapegroup.dokapebackend.models.DBShop
import com.dokapegroup.dokapebackend.models.shop.ShopRes
import org.springframework.stereotype.Component

@Component
class DBShopToShopRes : Mapper<DBShop, ShopRes> {
    override fun invoke(input: DBShop): ShopRes {
        return ShopRes(
            id = input.id,
            name = input.name,
            description = input.description,
            totalProducts = input.totalProducts,
            numberOfFollowers = input.numberOfFollowers,
            user = input.user?.id,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt,
        )
    }
}
