package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidQueryBlankException
import com.sungjae.portfolio.domain.exception.InvalidQueryException
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Single

class GetContentUseCase(
    private val repository: Repository
) : SingleInputUseCase<ContentEntity, Pair<String, String?>>() {

    override fun buildUseCaseInputSingle(params: Pair<String, String?>): Single<ContentEntity>? {
        val tabName = params.first
        val query = params.second

        return when {
            tabName.isEmpty() -> Single.error(InvalidTabTypeException())
            query == null -> Single.error(InvalidQueryException())
            query.isBlank() -> Single.error(InvalidQueryBlankException())
            else -> repository.getContents(type = tabName, query = query)
        }
    }
}