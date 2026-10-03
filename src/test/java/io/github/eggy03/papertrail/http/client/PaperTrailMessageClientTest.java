package io.github.eggy03.papertrail.http.client;

import io.github.eggy03.papertrail.http.entity.PaperTrailMessage;
import io.github.eggy03.papertrail.http.exception.PaperTrailFatalException;
import io.github.eggy03.papertrail.http.service.PaperTrailMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaperTrailMessageClientTest {

    private final String messageId = "111111111111111";
    private final String messageContent = "test";
    private final String authorId = "222222222222222";

    private final PaperTrailMessage dummyEntity = new PaperTrailMessage(messageId, messageContent, authorId);

    @Mock
    PaperTrailMessageService service;

    PaperTrailMessageClient client;

    @BeforeEach
    void setClient() {
        client = new PaperTrailMessageClient(service);
    }

    @Test
    void saveMessage_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.saveMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.saveMessage(messageId, messageContent, authorId)).isTrue();

        verify(service).saveMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void saveMessage_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.saveMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.saveMessage(messageId, messageContent, authorId)).isFalse();

        verify(service).saveMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);

    }

    @Test
    void saveMessage_throwsIOException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.saveMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.saveMessage(messageId, messageContent, authorId));

        verify(service).saveMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getMessage_success_returnsOptional() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailMessage> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<PaperTrailMessage> mockedSuccessResponse = mock(Response.class);

        when(service.getMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);
        when(mockedSuccessResponse.body()).thenReturn(dummyEntity);

        Optional<PaperTrailMessage> result = client.getMessage(anyString());
        assertThat(result).contains(dummyEntity);

        verify(service).getMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getMessage_error_returnsEmptyOptional() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailMessage> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<PaperTrailMessage> mockedErrorResponse = mock(Response.class);

        when(service.getMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        Optional<PaperTrailMessage> result = client.getMessage(anyString());
        assertThat(result).isEmpty();

        verify(service).getMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getMessage_throwsIOException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailMessage> mockedCall = mock(Call.class);

        when(service.getMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.getMessage(messageId));

        verify(service).getMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateMessage_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.updateMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.updateMessage(messageId, messageContent, authorId)).isTrue();

        verify(service).updateMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateMessage_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.updateMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.updateMessage(messageId, messageContent, authorId)).isFalse();

        verify(service).updateMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateMessage_throwsIOException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.updateMessage(any(PaperTrailMessage.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.updateMessage(messageId, messageContent, authorId));

        verify(service).updateMessage(any(PaperTrailMessage.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteMessage_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.deleteMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.deleteMessage(messageId)).isTrue();

        verify(service).deleteMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteMessage_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.deleteMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.deleteMessage(messageId)).isFalse();

        verify(service).deleteMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteMessage_throwsIOException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.deleteMessage(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.deleteMessage(messageId));

        verify(service).deleteMessage(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }
}
