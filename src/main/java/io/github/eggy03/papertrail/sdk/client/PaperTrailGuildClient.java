package io.github.eggy03.papertrail.sdk.client;

import io.github.eggy03.papertrail.sdk.entity.PaperTrailGuild;
import io.github.eggy03.papertrail.sdk.exception.PaperTrailFatalException;
import io.github.eggy03.papertrail.sdk.service.PaperTrailGuildService;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S1192")
public final class PaperTrailGuildClient {

    private static final Logger log = LoggerFactory.getLogger(PaperTrailGuildClient.class);
    private final @NonNull PaperTrailGuildService service;

    public PaperTrailGuildClient(@NonNull Retrofit retrofit) {
        this(retrofit.create(PaperTrailGuildService.class));
    }

    PaperTrailGuildClient(@NonNull PaperTrailGuildService service) {
        this.service = Objects.requireNonNull(service, "service cannot be null");
    }

    public boolean saveGuild(
            @NonNull String guildId,
            @Nullable String guildEventChannelId,
            @Nullable String memberEventChannelId,
            @Nullable String messageEventChannelId
    ) {

        try {
            Response<Void> response = service.saveGuild(new PaperTrailGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).execute();
            if (response.isSuccessful())
                return true;
            else {
                log.debug("Failed to save guild [Guild ID={}]\nError Response: {}", guildId, response.errorBody());
                return false;
            }

        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public @NonNull Optional<PaperTrailGuild> getGuild(@NonNull String guildId) {

        Objects.requireNonNull(guildId, "guildId cannot be null");

        try {
            Response<PaperTrailGuild> response = service.getGuild(guildId).execute();
            if (response.isSuccessful())
                return Optional.ofNullable(response.body());
            else {
                log.debug("Failed to get guild [Guild ID={}]\nError Response: {}", guildId, response.errorBody());
                return Optional.empty();
            }

        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public boolean updateGuild(
            @NonNull String guildId,
            @Nullable String guildEventChannelId,
            @Nullable String memberEventChannelId,
            @Nullable String messageEventChannelId
    ) {

        try {
            Response<Void> response = service.updateGuild(new PaperTrailGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).execute();
            if (response.isSuccessful())
                return true;
            else {
                log.debug("Failed to update guild [Guild ID={}]\nError Response: {}", guildId, response.errorBody());
                return false;
            }

        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }
    }

    public boolean deleteGuild(@NonNull String guildId) {

        Objects.requireNonNull(guildId, "guildId cannot be null");

        try {
            Response<Void> response = service.deleteGuild(guildId).execute();
            if (response.isSuccessful())
                return true;
            else {
                log.debug("Failed to delete guild [Guild ID={}]\nError Response: {}", guildId, response.errorBody());
                return false;
            }

        } catch (IOException e) {
            throw new PaperTrailFatalException("Failed to talk to the API", e);
        }

    }
}
