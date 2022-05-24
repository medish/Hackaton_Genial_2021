package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.CourseGroup;
import server.models.CourseGroupId;
import server.repositories.CourseGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseGroupService {
    @Autowired
    private CourseGroupRepository repository;

    /**
     * Gets all CourseGroups.
     * @return List of CourseGroups {@link CourseGroup}
     */
    public List<CourseGroup> getAll(){
        return repository.findAll();
    }

    /**
     * Get CourseGroup by ID
     * @param id CourseGroup's ID
     * @return {@link CourseGroup}
     */
    public Optional<CourseGroup> getById(CourseGroupId id){
        return repository.findById(id);
    }

    /**
     * Insert a CourseGroup record
     * @param CourseGroup {@link CourseGroup}
     */
    public void insert(CourseGroup CourseGroup){
        repository.saveAndFlush(CourseGroup);
    }

    /**
     * Insert multiple CourseGroup records
     * @param CourseGroups {@link CourseGroup}
     */
    public void insert(List<CourseGroup> CourseGroups){
        repository.saveAllAndFlush(CourseGroups);
    }

    /**
     * Delete a CourseGroup record
     * @param id CourseGroup's ID
     */
    public void delete(CourseGroupId id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple CourseGroup records.
     * @param ids List of IDs.
     */
    public void delete(List<CourseGroupId> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a CourseGroup record
     * @param CourseGroup {@link CourseGroup}
     * @return The new record of {@link CourseGroup}
     */
    public CourseGroup update(CourseGroup CourseGroup){
        return repository.saveAndFlush(CourseGroup);
    }

}
