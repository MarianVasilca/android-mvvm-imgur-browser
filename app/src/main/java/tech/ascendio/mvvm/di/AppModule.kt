/*
 * Copyright (C) 2018 Marian Vasilca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.ascendio.mvvm.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import tech.ascendio.mvvm.data.api.AppService
import tech.ascendio.mvvm.data.api.ImgurApi
import tech.ascendio.mvvm.data.db.AppDb
import tech.ascendio.mvvm.data.db.ImageDao
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideAppService(): AppService {
        return ImgurApi.instance.mImgurService
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDb {
        return Room
                .databaseBuilder(app, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideImageDao(db: AppDb): ImageDao {
        return db.imageDao()
    }

}
