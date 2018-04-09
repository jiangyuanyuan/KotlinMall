package com.kotlin.usercenter.di.module

import com.kotlin.usercenter.service.UploadService
import dagger.Module
import dagger.Provides

@Module
class UploadModule {
    @Provides
    fun provideUploadService(uploadService: UploadService):UploadService{
        return uploadService
    }
}