package es.jfechevarria.domain.common.repositories

abstract class RepositoryRequest(
    var page: Int = 1,
    var total: Int = 10
)