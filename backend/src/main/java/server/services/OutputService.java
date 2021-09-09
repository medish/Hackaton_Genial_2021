package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Output;
import server.repositories.OutputRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService{
    private final OutputRepository repository;

    @Autowired
    public OutputService(OutputRepository repository) {
        this.repository = repository;
    }


    /**
     * Gets all outputs.
     * @return List of outputs {@link Output}
     */
    public List<Output> getAll(){
        return repository.findAll();
    }

    /**
     * Get output by ID
     * @param id Output's ID
     * @return {@link Output}
     */
    public Optional<Output> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a output record
     * @param output {@link Output}
     */
    public void insert(Output output){
        repository.saveAndFlush(output);
    }

    /**
     * Insert multiple output records
     * @param outputs {@link Output}
     */
    public void insert(List<Output> outputs){
        repository.saveAllAndFlush(outputs);
    }

    /**
     * Delete a output record
     * @param id Output's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple output records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a output record
     * @param output {@link Output}
     * @return The new record of {@link Output}
     */
    public Output update(Output output){
        return repository.saveAndFlush(output);
    }


}
