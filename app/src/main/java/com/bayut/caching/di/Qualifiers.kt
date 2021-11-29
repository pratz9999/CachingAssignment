package com.bayut.caching.di

import javax.inject.Qualifier

/**
 * custom annotation
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ServerApi


