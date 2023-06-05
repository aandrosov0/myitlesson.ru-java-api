package ru.myitlesson.api.request;

import com.google.gson.JsonObject;
import retrofit2.Response;
import ru.myitlesson.api.MyItLessonClient;
import ru.myitlesson.api.Utils;
import ru.myitlesson.api.entity.ModuleEntity;
import ru.myitlesson.api.entity.LessonEntity;
import ru.myitlesson.api.service.LessonService;

import java.io.IOException;
import java.util.Objects;

public class LessonRequest extends Request<LessonService> {

    public LessonRequest(MyItLessonClient client, LessonService service) {
        super(client, service);
    }

    public LessonEntity get(int id) throws IOException {
        Response<LessonEntity> response = service.get(client.getBasic(), id).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public int add(LessonEntity lesson, ModuleEntity module) throws IOException {
        Response<JsonObject> response = service.add(client.getBasic(), lesson.getTitle(), lesson.getContent(), module.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));

        if(client.getError() != null) {
            return -1;
        }

        int id = Objects.requireNonNull(response.body()).get("id").getAsInt();
        module.getLessons().add(id);
        lesson.setModule(module.getId());

        return id;
    }

    public void delete(LessonEntity lesson) throws IOException {
        Response<JsonObject> response = service.delete(client.getBasic(), lesson.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
    }
}
