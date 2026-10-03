package io.github.eggy03.papertrail.http.client;

import io.github.eggy03.papertrail.http.entity.PaperTrailGuild;
import io.github.eggy03.papertrail.http.exception.PaperTrailFatalException;
import io.github.eggy03.papertrail.http.service.PaperTrailGuildService;
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
class PaperTrailGuildClientTest {

    private final String guildId = "111111111111111";
    private final String guildEventChannelId = "222222222222222";
    private final String memberEventChannelId = "333333333333333";
    private final String messageEventChannelId = "444444444444444";

    private final PaperTrailGuild dummyEntity = new PaperTrailGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId);

    @Mock
    PaperTrailGuildService service;

    PaperTrailGuildClient client;

    @BeforeEach
    void setClient() {
        client = new PaperTrailGuildClient(service);
    }

    @Test
    void saveGuild_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.saveGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.saveGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).isTrue();

        verify(service).saveGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void saveGuild_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.saveGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.saveGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).isFalse();

        verify(service).saveGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void saveGuild_throwsException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.saveGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.saveGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId));

        verify(service).saveGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getGuild_success_returnsOptional() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailGuild> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<PaperTrailGuild> mockedSuccessResponse = mock(Response.class);

        when(service.getGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);
        when(mockedSuccessResponse.body()).thenReturn(dummyEntity);

        Optional<PaperTrailGuild> result = client.getGuild(anyString());
        assertThat(result).contains(dummyEntity);

        verify(service).getGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getGuild_error_returnsEmptyOptional() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailGuild> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<PaperTrailGuild> mockedErrorResponse = mock(Response.class);

        when(service.getGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(true);
        when(mockedErrorResponse.body()).thenReturn(null);

        Optional<PaperTrailGuild> result = client.getGuild(anyString());
        assertThat(result).isEmpty();

        verify(service).getGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void getGuild_throwsException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<PaperTrailGuild> mockedCall = mock(Call.class);

        when(service.getGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.getGuild(guildId));

        verify(service).getGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateGuild_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.updateGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.updateGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).isTrue();

        verify(service).updateGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateGuild_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.updateGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.updateGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId)).isFalse();

        verify(service).updateGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void updateGuild_throwsException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.updateGuild(any(PaperTrailGuild.class))).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.updateGuild(guildId, guildEventChannelId, memberEventChannelId, messageEventChannelId));

        verify(service).updateGuild(any(PaperTrailGuild.class));
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteGuild_success_returnsTrue() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedSuccessResponse = mock(Response.class);

        when(service.deleteGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedSuccessResponse);
        when(mockedSuccessResponse.isSuccessful()).thenReturn(true);

        assertThat(client.deleteGuild(anyString())).isTrue();

        verify(service).deleteGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteGuild_error_returnsFalse() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);
        @SuppressWarnings("unchecked")
        Response<Void> mockedErrorResponse = mock(Response.class);

        when(service.deleteGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenReturn(mockedErrorResponse);
        when(mockedErrorResponse.isSuccessful()).thenReturn(false);

        assertThat(client.deleteGuild(anyString())).isFalse();

        verify(service).deleteGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }

    @Test
    void deleteGuild_throwsException() throws IOException {

        @SuppressWarnings("unchecked")
        Call<Void> mockedCall = mock(Call.class);

        when(service.deleteGuild(anyString())).thenReturn(mockedCall);
        when(mockedCall.execute()).thenThrow(IOException.class);

        assertThrows(PaperTrailFatalException.class, () -> client.deleteGuild(guildId));

        verify(service).deleteGuild(anyString());
        verifyNoMoreInteractions(mockedCall, service);
    }
}
