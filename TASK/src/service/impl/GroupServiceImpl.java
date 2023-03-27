package service.impl;

import db.Database;
import model.Group;
import service.GroupsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupServiceImpl implements GroupsService {
    private List<Database>databases;
    List<Group>groups;

    public GroupServiceImpl() {
        groups = new ArrayList<>();
    }

    @Override
    public String addNewGroup(Group group) {
        groups.add(group);
        return "Группа ийгиликтүү кошулду!";
    }

    @Override
    public String getGroupById(int id) {
        Group group = groups.stream().filter(g -> g.getId() == id).findFirst().orElse(null);
        return group != null ? group.toString() : "Группа табылган жок";
    }

    @Override
    public List<Group> getAllGroups() {
        return groups.stream().collect(Collectors.toList());
    }

    @Override
    public String updateGroupName(int id, String groupName) {
        Group group = groups.stream().filter(g -> g.getId() == id).findFirst().orElse(null);
        if (group != null) {
            group.setName(groupName);
            return "Группанын аталышы ийгиликтүү жаңыртылды!";
        }
        return "Группа табылган жок";
    }

    @Override
    public List<Group> filterByYear(int year, String ascDesc) {
        Comparator<Group> comparator = Comparator.comparing(Group::getYear);
        if (ascDesc.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }
        return groups.stream().filter(g -> g.getYear() == year).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Group> sortGroupByYear(String ascDesc) {
        Comparator<Group> comparator = Comparator.comparing(Group::getYear);
        if (ascDesc.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }
        return groups.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public void deleteGroupById(int id) {
        groups.removeIf(g -> g.getId() == id);

    }
}
