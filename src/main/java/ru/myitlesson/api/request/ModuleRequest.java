package ru.myitlesson.api.request;

import com.google.gson.JsonObject;
import retrofit2.Response;
import ru.myitlesson.api.MyItLessonClient;
import ru.myitlesson.api.Utils;
import ru.myitlesson.api.entity.CourseEntity;
import ru.myitlesson.api.entity.ModuleEntity;
import ru.myitlesson.api.service.ModuleService;

import java.io.IOException;
import java.util.Objects;

public class ModuleRequest extends Request<ModuleService> {

    public ModuleRequest(MyItLessonClient client, ModuleService service) {
        super(client, service);
    }

    public ModuleEntity get(int id) throws IOException {
        Response<ModuleEntity> response = service.get(client.getBasic(), id).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public int add(ModuleEntity module, CourseEntity course) throws IOException {
        Response<JsonObject> response = service.add(client.getBasic(), module.getTitle(), module.getContent(), course.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));

        if(client.getError() != null) {
            return -1;
        }

        int id = Objects.requireNonNull(response.body()).get("id").getAsInt();
        course.getModules().add(id);
        module.setCourse(course.getId());

        return id;
    }

    public void delete(ModuleEntity module) throws IOException {
        Response<JsonObject> response = service.delete(client.getBasic(), module.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
    }
}
