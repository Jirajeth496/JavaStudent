package ku.cs.services;

import ku.cs.models.Student;
import ku.cs.models.StudentList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StudentListFileDatasource implements Datasource<StudentList> {
    private final String directoryName;
    private final Path filePath;

    public StudentListFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.filePath = Path.of(directoryName, fileName);
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        Path dirPath = Path.of(directoryName);

        try {
            if (Files.notExists(dirPath)) {
                System.out.println("directory not exists");
                Files.createDirectories(dirPath);
            } else {
                System.out.println("directory exists");
            }

            if (Files.notExists(filePath)) {
                System.out.println("file not exists");
                Files.createFile(filePath);
            } else {
                System.out.println("file exists");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public StudentList readData() {
        StudentList list = new StudentList();

        try {
            BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                double score = Double.parseDouble(data[2].trim());
                list.addNewStudent(id, name, score);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void writeData(StudentList data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                    filePath, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            for (Student student : data.getStudents()) {
                writer.write(student.getId() + "," + student.getName() + ", " + student.getScore());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}