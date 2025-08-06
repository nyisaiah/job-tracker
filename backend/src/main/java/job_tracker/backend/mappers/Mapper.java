package job_tracker.backend.mappers;

public interface Mapper<Entity, Dto> {
    Dto mapToDto(Entity entity);

    Entity mapToEntity(Dto dto);

}
