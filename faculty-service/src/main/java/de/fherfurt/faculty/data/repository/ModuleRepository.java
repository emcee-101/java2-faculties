package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.ArrayList;
import java.util.List;

public class ModuleRepository {
    private final Functions<Module> moduleFunctions = new Functions<Module>();
    private final List<Module> modules = new ArrayList<Module>();

    public void save(Module module) {
        moduleFunctions.save(module, modules);
    }

    public void findByName(String name) {
        moduleFunctions.findByName(name, modules);
    }

    public void delete(String name) {
        moduleFunctions.delete(name, modules);
    }

    public List<Module> getList() {
        return modules;
    }
}

