/*
 * Copyright (C) 2018 Marian Vasilca from Ascendio TechVision
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

package tech.ascendio.mvvm.data.api

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path
import tech.ascendio.mvvm.data.vo.Gallery
import tech.ascendio.mvvm.data.vo.Image

/**
 * REST API access points
 */
interface AppService {

    @GET("gallery/{section}/{sort}/{window}/{page}")
    fun getGalleries(
            @Path("section") section: String,
            @Path("sort") sort: String,
            @Path("window") window: String,
            @Path("page") page: Int
    ): LiveData<ApiResponse<List<Gallery>>>

    @GET("gallery/r/{subreddit}/{sort}/{window}/{page}")
    fun getSubredditGalleries(
            @Path("subreddit") subreddit: String,
            @Path("sort") sort: String,
            @Path("window") window: String,
            @Path("page") page: Int
    ): LiveData<ApiResponse<Response<List<Image>>>>

}
