package com.dokapegroup.dokapebackend.controllers.mapper

interface Mapper<I, O> : (I) -> O {
    override fun invoke(input: I): O
}
