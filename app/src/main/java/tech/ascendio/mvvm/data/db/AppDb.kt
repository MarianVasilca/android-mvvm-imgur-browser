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

package tech.ascendio.mvvm.data.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import tech.ascendio.mvvm.data.vo.Image

/**
 * Main database description.
 */
@Database(
        entities = [
            Image::class
        ],
        version = 4,
        exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
