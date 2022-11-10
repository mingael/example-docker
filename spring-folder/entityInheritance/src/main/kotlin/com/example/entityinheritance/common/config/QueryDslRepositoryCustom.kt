package com.example.entityinheritance.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.properties.Delegates

abstract class QueryDslRepositoryCustom(domainClass: Class<*>) : QuerydslRepositorySupport(domainClass) {

    protected var query: JPAQueryFactory by Delegates.notNull()

    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
        this.query = JPAQueryFactory(entityManager)
    }

}
