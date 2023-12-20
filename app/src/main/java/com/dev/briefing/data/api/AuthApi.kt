package com.dev.briefing.data.api

import com.dev.briefing.data.model.response.common.CommonResponse
import com.dev.briefing.data.model.GoogleRequest
import com.dev.briefing.data.model.GoogleSocialResponse
import com.dev.briefing.data.model.SingoutResponse
import com.dev.briefing.data.model.TokenRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("/members/auth/google")
    suspend fun getLoginToken(
        @Body identityToken : GoogleRequest,
    ): CommonResponse<GoogleSocialResponse>

    @DELETE("/members/{memberId}")
    suspend fun signOut(
        @Path("memberId") memberId : Int,
    ): CommonResponse<SingoutResponse>

    @POST("/members/auth/token")
    suspend fun getAccessToken(
        @Body refreshToken : TokenRequest,
    ): CommonResponse<GoogleSocialResponse>
}