package com.sam.cleanarchitecturekickstart.domain.usecase

import com.sam.cleanarchitecturekickstart.domain.repository.Repository
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
) {
}