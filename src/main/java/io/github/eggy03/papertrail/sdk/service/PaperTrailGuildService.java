package io.github.eggy03.papertrail.sdk.service;

import io.github.eggy03.papertrail.sdk.entity.PaperTrailGuild;
import org.jspecify.annotations.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaperTrailGuildService {

    @POST("api/v2/guild")
    Call<Void> saveGuild(@Body PaperTrailGuild requestBody);

    @GET("api/v2/guild/{guildId}")
    Call<PaperTrailGuild> getGuild(@Path("guildId") @NonNull String guildId);

    @PATCH("api/v2/guild}")
    Call<Void> updateGuild(@Body PaperTrailGuild requestBody);

    @DELETE("api/v2/guild/{guildId}")
    Call<Void> deleteGuild(@Path("guildId") @NonNull String guildId);
}
