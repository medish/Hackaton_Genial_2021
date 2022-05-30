package server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.models.CourseSlot;

public abstract class AbstractService<T extends KeyID<ID>, ID> {

    public abstract JpaRepository<T, ID> getRepository();

    /**
     * Gets all CourseSlots.
     * 
     * @return List of CourseSlots {@link CourseSlot}
     */
    public List<T> getAll() {
        return getRepository().findAll();
    }

    /**
     * Get CourseSlot by ID
     * 
     * @param id CourseSlot's ID
     * @return {@link CourseSlot}
     */
    public Optional<T> getById(ID id) {
        return getRepository().findById(id);
    }

    /**
     * Insert a CourseSlot record
     * 
     * @param CourseSlot {@link CourseSlot}
     */
    public T insert(T courseSlot) {
        if (getById(courseSlot.getId()).isPresent()) {
            return null;
        }
        return getRepository().saveAndFlush(courseSlot);
    }

    /**
     * Insert multiple CourseSlot records
     * 
     * @param CourseSlots {@link CourseSlot}
     */
    public List<T> insert(List<T> courseSlots) {
        if (courseSlots.stream().anyMatch(courseSlot -> getById(courseSlot.getId()).isPresent())) {
            return List.of();
        }
        return getRepository().saveAllAndFlush(courseSlots);
    }

    /**
     * Delete a CourseSlot record
     * 
     * @param id CourseSlot's ID
     */
    public boolean delete(ID id) {
        if (getById(id).isEmpty()) {
            return false;
        }
        getRepository().deleteById(id);
        return true;
    }

    /**
     * Delete multiple CourseSlot records.
     * 
     * @param ids List of IDs.
     */
    public boolean delete(List<ID> ids) {
        if (ids.stream().anyMatch(id -> getById(id).isEmpty())) {
            return false;
        }
        getRepository().deleteAllById(ids);
        return true;
    }

    /**
     * Update a CourseSlot record
     * 
     * @param CourseSlot {@link CourseSlot}
     * @return The new record of {@link CourseSlot}
     */
    public T update(T courseSlot) {
        if (getById(courseSlot.getId()).isEmpty()) {
            return null;
        }
        return getRepository().saveAndFlush(courseSlot);
    }

}
