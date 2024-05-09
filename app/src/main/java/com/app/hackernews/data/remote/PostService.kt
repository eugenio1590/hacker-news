package com.app.hackernews.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for interacting with the remote Hacker News API.
 *
 * This interface defines methods for retrieving post data from the remote service.
 */
interface PostService {

    /**
     * Retrieves a paginated list of post from the remote service.
     *
     * @param page The page number indicating the set of post to retrieve.
     * @return A [Response] containing the paginated list of [PagedListDTO] representing post data.
     */
    @GET("api/v1/search_by_date")
    suspend fun fetchPosts(
        @Query("page") page: Int,
        @Query("hitsPerPage") limit: Int,
        @Query("query") query: String = "mobile"
    ) : Response<PagedListDTO>
}
