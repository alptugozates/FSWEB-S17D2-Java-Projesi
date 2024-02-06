package rest;

import com.workintech.spring.SpringProject2.model.*;
import com.workintech.spring.SpringProject2.tax.DeveloperTax;
import com.workintech.spring.SpringProject2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/developers")
public class DeveloperController {
   private Map<Integer, Developer> developers;
    private Taxable taxCalculator;

    public DeveloperController(Taxable taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @PostConstruct
    public void addDevelopers(){
        developers = new HashMap<>();
        developers.put(1, new Developer(1,"Ahmet", 40000, Experience.JUNIOR));
        developers.put(2, new Developer(2,"Murat", 60000, Experience.MID));
        developers.put(3, new Developer(3,"Ali", 80000, Experience.SENIOR));
    }
    @GetMapping
    public List<Developer> getAllDevelopers(){
        return new ArrayList<>(developers.values());
    }
    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id){
    return developers.get(id);
    }
    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer request) {
        Developer existingDeveloper = developers.get(id);

        if (existingDeveloper != null) {
            existingDeveloper.setName(request.getName());
            existingDeveloper.setSalary(request.getSalary());
            existingDeveloper.setExperience(request.getExperience());
        }

        return existingDeveloper;
    }
    @DeleteMapping("/{id}")
    public Developer deleteDeveloper(@PathVariable int id) {
        return developers.remove(id);
    }

    @PostMapping
    public Developer addDeveloper(@RequestBody Developer request) {
        int id = developers.size() + 1;
        String name = request.getName();
        double salary = request.getSalary();
        Experience experience = request.getExperience();

        Developer newDeveloper;
        switch (experience) {
            case JUNIOR:
                newDeveloper = new JuniorDeveloper(id, name, salary - salary * taxCalculator.getSimpleTaxRate());
                break;
            case MID:
                newDeveloper = new MidDeveloper(id, name, salary - salary * taxCalculator.getMiddleTaxRate());
                break;
            case SENIOR:
                newDeveloper = new SeniorDeveloper(id, name, salary - salary * taxCalculator.getUpperTaxRate());
                break;
            default:
                throw new IllegalArgumentException("Invalid experience type");
        }

        developers.put(id, newDeveloper);
        return newDeveloper;
    }

}
