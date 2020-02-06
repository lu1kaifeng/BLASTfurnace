package org.henu.blastfurnace.master.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Slave {
    private String url;
    private OkHttpClient client;

    public Slave(String host, String path) {
        this.url = host + path;
        this.client = new OkHttpClient();
    }

    public List<AlignmentEntry> align(AlignmentRequest request) throws IOException {
        return new Gson().fromJson(Objects.requireNonNull(client.newCall(new Request.Builder()
                .url(url)
                .post(new RequestBody() {
                    @Nullable
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/json");
                    }

                    @Override
                    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                        bufferedSink.write(new Gson().toJson(request).getBytes());
                        bufferedSink.flush();
                        bufferedSink.close();
                    }
                }).build()).execute().body()).string(), new TypeToken<ArrayList<AlignmentEntry>>() {
        }.getType());
    }
}
