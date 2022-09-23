package ua.gorobetso.module.utils;

import ua.gorobetso.module.dao.*;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private static final StudentGroupDao STUDENT_GROUP_DAO = new StudentGroupDao();
    private static final StudentDao STUDENT_DAO = new StudentDao();
    private static final GradeDao GRADE_DAO = new GradeDao();
    private static final TeacherDao TEACHER_DAO = new TeacherDao();
    private static final SubjectDao SUBJECT_DAO = new SubjectDao();
    private static final FlywayMigration FLYWAY_MIGRATION = new FlywayMigration();

    public List<String> getCommandList() {
        List<String> command = new ArrayList<>();
        command.add("Migrate database");
        command.add("Search groups by title");
        command.add("Get number of students in each group");
        command.add("Average grade of each group");
        command.add("Search teacher by name or surname");
        command.add("Get subjects with the worst and best performance");
        command.add("Get students whose grade is higher than the given");
        command.add("Exit");
        return command;
    }

    public void doCommand() {
        while (true) {
            int userInput = UserInputUtil.getUserInput("What do you want to do?", getCommandList());
            if (userInput == 1) {
                FLYWAY_MIGRATION.migrateDatabase();
            } else if (userInput == 2) {
                STUDENT_GROUP_DAO.searchByTitle(UserInputUtil.getUserInputString("Enter a group title:"));
            } else if (userInput == 3) {
                STUDENT_DAO.countStudentsInStudentGroups();
            } else if (userInput == 4) {
                GRADE_DAO.avgGradeByStudentGroup();
            } else if (userInput == 5) {
                TEACHER_DAO.searchByNameOrSurname(UserInputUtil.getUserInputString("Enter name or surname:"));
            } else if (userInput == 6) {
                SUBJECT_DAO.getSubjectWithWorstAndBestPerformance();
            } else if (userInput == 7) {
                STUDENT_DAO.getStudentsWhoseAverageGradeExpensiveThan(UserInputUtil.getUserInputChangeInteger("Enter limit value:"));
            } else if (userInput == 8) {
                FLYWAY_MIGRATION.cleanDatabase();
                break;
            }
        }
    }
}
