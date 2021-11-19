package com.naijab.demo.jabshop

import kotlin.native.concurrent.SharedImmutable
import kotlinx.coroutines.CoroutineDispatcher

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
