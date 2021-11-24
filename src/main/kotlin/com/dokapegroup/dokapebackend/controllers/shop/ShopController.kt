package com.dokapegroup.dokapebackend.controllers.shop

import com.dokapegroup.dokapebackend.controllers.mapper.DBShopToShopRes
import com.dokapegroup.dokapebackend.extensions.removeBearer
import com.dokapegroup.dokapebackend.jwt.JwtTokenProvider
import com.dokapegroup.dokapebackend.models.DBProduct
import com.dokapegroup.dokapebackend.models.DBShop
import com.dokapegroup.dokapebackend.models.shop.CreateShopRequest
import com.dokapegroup.dokapebackend.models.shop.ShopRes
import com.dokapegroup.dokapebackend.services.ProductService
import com.dokapegroup.dokapebackend.services.ShopService
import com.dokapegroup.dokapebackend.services.UserService
import com.dokapegroup.dokapebackend.utils.AuthToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/shops")
class ShopController @Autowired constructor(
    private val productService: ProductService,
    private val shopService: ShopService,
    private val tokenProvider: JwtTokenProvider,
    private val userService: UserService,
    private val dbShopToShopRes: DBShopToShopRes
) {
    @GetMapping("/{shopId}")
    fun getShopById(@PathVariable shopId: Long) = shopService.getShopById(shopId)

    @GetMapping("/{shopId}/products")
    fun getProductsByShopId(@PathVariable shopId: Long): List<DBProduct> =
        emptyList()

    @PostMapping("/create-shop")
    fun createShop(
        @RequestHeader("Authorization") token: AuthToken,
        @Valid @RequestBody createShopRequest: CreateShopRequest
    ): ShopRes {
        val userId = tokenProvider.getUserIdFromJWT(token.removeBearer())

        return try {
            shopService.getShopById(userId).also { it.user?.shop = null }
        } catch (rse: ResponseStatusException) {
            DBShop(
                id = userId,
                name = createShopRequest.name,
                description = createShopRequest.description,
            ).also {
                val user = userService.getUserById(userId)
                user?.apply {
                    this.shop = it
                }
                if (user != null) {
                    it.user = user
                    userService.updateUser(user)
                }
                shopService.addShop(it)
            }
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }.let { dbShopToShopRes(it) }
    }
}
