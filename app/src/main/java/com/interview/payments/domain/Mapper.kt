package com.interview.payments.domain

interface Mapper<IN, OUT> {
    fun map(response: IN): OUT
}