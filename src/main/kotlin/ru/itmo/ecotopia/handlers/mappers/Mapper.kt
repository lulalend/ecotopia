package ru.itmo.ecotopia.handlers.mappers

interface Mapper<JPAE, E, Req, Resp> {
    fun toEntity(domain: Req): JPAE
    fun fromEntity(entity: E): Resp
}