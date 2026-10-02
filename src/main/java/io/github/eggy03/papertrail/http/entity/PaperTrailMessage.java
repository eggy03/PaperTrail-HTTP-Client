package io.github.eggy03.papertrail.http.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public record PaperTrailMessage(@NonNull String messageId, @NonNull String messageContent, @NonNull String authorId) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaperTrailMessage(@JsonProperty("messageId") @NonNull String messageId,
                             @JsonProperty("messageContent") @NonNull String messageContent,
                             @JsonProperty("authorId") @NonNull String authorId
    ) {
        this.messageId = Objects.requireNonNull(messageId, "messageId cannot be null");
        this.messageContent = Objects.requireNonNull(messageContent, "messageContent cannot be null");
        this.authorId = Objects.requireNonNull(authorId, "authorId cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaperTrailMessage(String id, String content, String authorId1))) return false;
        return Objects.equals(messageId(), id) && Objects.equals(messageContent(), content) && Objects.equals(authorId(), authorId1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId(), messageContent(), authorId());
    }

    @Override
    public @NonNull String toString() {
        return "PaperTrailMessage{" +
                "messageId='" + messageId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
