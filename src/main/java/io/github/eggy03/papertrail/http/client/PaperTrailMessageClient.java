package io.github.eggy03.papertrail.http.client;

import io.github.eggy03.papertrail.http.entity.PaperTrailMessage;
import io.github.eggy03.papertrail.http.exception.PaperTrailFatalException;
import io.github.eggy03.papertrail.http.service.PaperTrailMessageService;
import okhttp3.ResponseBody;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S1192")
public final class PaperTrailMessageClient {

    private static final Logger log = LoggerFactory.getLogger(PaperTrailMessageClient.class);
    private final @NonNull PaperTrailMessageService service;


    public PaperTrailMessageClient(@NonNull Retrofit retrofit) {
        this(retrofit.create(PaperTrailMessageService.class));
    }

    PaperTrailMessageClient(@NonNull PaperTrailMessageService service) {
        this.service = Objects.requireNonNull(service, "service cannot be null");
    }

    public boolean saveMessage(@NonNull String messageId, @NonNull String messageContent, @NonNull String authorId) {

        try {
            Response<Void> response = service.saveMessage(new PaperTrailMessage(messageId, messageContent, authorId)).execute();
            if (response.isSuccessful())
                return true;
            else {
                try (ResponseBody errorBody = response.errorBody()) {
                    log.debug("Failed to save message [Guild ID={}]\nError Response: {}", messageId, errorBody == null ? null : errorBody.string());
                    return false;
                }
            }
        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public @NonNull Optional<PaperTrailMessage> getMessage(@NonNull String messageId) {

        Objects.requireNonNull(messageId, "messageId cannot be null");

        try {
            Response<PaperTrailMessage> response = service.getMessage(messageId).execute();
            if (response.isSuccessful())
                return Optional.ofNullable(response.body());
            else {
                try (ResponseBody errorBody = response.errorBody()) {
                    log.debug("Failed to get message [Guild ID={}]\nError Response: {}", messageId, errorBody == null ? null : errorBody.string());
                    return Optional.empty();
                }
            }
        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public boolean updateMessage(@NonNull String messageId, @NonNull String messageContent, @NonNull String authorId) {

        try {
            Response<Void> response = service.updateMessage(new PaperTrailMessage(messageId, messageContent, authorId)).execute();
            if (response.isSuccessful())
                return true;
            else {
                try (ResponseBody errorBody = response.errorBody()) {
                    log.debug("Failed to update message [Guild ID={}]\nError Response: {}", messageId, errorBody == null ? null : errorBody.string());
                    return false;
                }
            }
        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public boolean deleteMessage(@NonNull String messageId) {

        Objects.requireNonNull(messageId, "messageId cannot be null");

        try {
            Response<Void> response = service.deleteMessage(messageId).execute();
            if (response.isSuccessful())
                return true;
            else {
                try (ResponseBody errorBody = response.errorBody()) {
                    log.debug("Failed to delete message [Guild ID={}]\nError Response: {}", messageId, errorBody == null ? null : errorBody.string());
                    return false;
                }
            }
        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }
}
