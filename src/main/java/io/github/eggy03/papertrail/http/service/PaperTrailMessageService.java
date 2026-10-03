package io.github.eggy03.papertrail.http.service;

import io.github.eggy03.papertrail.http.entity.PaperTrailMessage;
import org.jspecify.annotations.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaperTrailMessageService {

    @POST("api/v2/message")
    Call<Void> saveMessage(@Body PaperTrailMessage requestBody);

    @GET("api/v2/message/{messageId}")
    Call<PaperTrailMessage> getMessage(@Path("messageId") @NonNull String messageId);

    @PATCH("api/v2/message")
    Call<Void> updateMessage(@Body PaperTrailMessage requestBody);

    @DELETE("api/v2/message/{messageId}")
    Call<Void> deleteMessage(@Path("messageId") @NonNull String messageId);
}
