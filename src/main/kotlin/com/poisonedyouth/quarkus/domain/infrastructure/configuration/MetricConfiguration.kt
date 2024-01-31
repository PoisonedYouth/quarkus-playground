package com.poisonedyouth.quarkus.domain.infrastructure.configuration

import io.agroal.api.AgroalDataSource
import io.micrometer.core.instrument.binder.db.PostgreSQLDatabaseMetrics
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics
import jakarta.enterprise.inject.Produces

class MetricConfiguration {

    @Produces
    fun jvmMemoryMetrics(): ClassLoaderMetrics {
        return ClassLoaderMetrics()
    }

    @Produces
    fun dbMetrics(dataSource: AgroalDataSource): PostgreSQLDatabaseMetrics {
        return PostgreSQLDatabaseMetrics(dataSource, "db")
    }

}