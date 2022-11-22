package rest.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.api.Model.Trainer;
import rest.api.Repo.TrainerRepo;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private TrainerRepo trainerRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "API to see which trainer possesses which pokémons.<br><br>"+
                "To get a list of all the trainers, go to /trainers<br>"+
                "To add a new trainer, use /savetrainer while giving the data for the trainer in the requestbody<br>"+
                "To update a trainer, use /update/{id} while giving the data for the trainer in the requestbody<br>"+
                "To delete a trainer, use /delete/{id}<br>";
    }

    @GetMapping(value = "/trainers")
    public List<Trainer> getTrainers(){
        return trainerRepo.findAll();
    }

    @PostMapping(value = "/savetrainer")
    public String saveTrainer(@RequestBody Trainer trainer){
        trainerRepo.save(trainer);
        return "Succesfully saved new trainer";
    }

    @PutMapping(value = "/update/{id}")
    public String updateTrainer(@PathVariable long id, @RequestBody Trainer trainer){
        Trainer updatedTrainer = trainerRepo.findById(id).get();
        updatedTrainer.setFirstName(trainer.getFirstName());
        updatedTrainer.setLastName(trainer.getLastName());
        updatedTrainer.setAge(trainer.getAge());
        updatedTrainer.setPokemons(trainer.getPokemons());
        trainerRepo.save(updatedTrainer);
        return "Updated trainer with id: "+id;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTrainer(@PathVariable long id){
        Trainer deletedTrainer = trainerRepo.findById(id).get();
        trainerRepo.delete(deletedTrainer);
        return "Deleted trainer with id: " + id;
    }
}
