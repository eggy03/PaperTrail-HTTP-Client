package io.github.eggy03.papertrail.sdk.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public record PaperTrailGuild(@NonNull String guildId, @Nullable String guildEventChannelId,
                              @Nullable String memberEventChannelId, @Nullable String messageEventChannelId) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaperTrailGuild(@JsonProperty("guildId") @NonNull String guildId,
                           @JsonProperty("guildEventChannelId") @Nullable String guildEventChannelId,
                           @JsonProperty("memberEventChannelId") @Nullable String memberEventChannelId,
                           @JsonProperty("messageEventChannelId") @Nullable String messageEventChannelId
    ) {

        this.guildId = Objects.requireNonNull(guildId, "guildId cannot be null");
        this.guildEventChannelId = guildEventChannelId;
        this.memberEventChannelId = memberEventChannelId;
        this.messageEventChannelId = messageEventChannelId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaperTrailGuild(
                String id, String eventChannelId, String channelId, String messageEventChannelId1
        ))) return false;
        return Objects.equals(guildId(), id) && Objects.equals(guildEventChannelId(), eventChannelId) && Objects.equals(memberEventChannelId(), channelId) && Objects.equals(messageEventChannelId(), messageEventChannelId1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guildId(), guildEventChannelId(), memberEventChannelId(), messageEventChannelId());
    }

    @Override
    public @NonNull String toString() {
        return "PaperTrailGuild{" +
                "guildId='" + guildId + '\'' +
                ", guildEventChannelId='" + guildEventChannelId + '\'' +
                ", memberEventChannelId='" + memberEventChannelId + '\'' +
                ", messageEventChannelId='" + messageEventChannelId + '\'' +
                '}';
    }
}