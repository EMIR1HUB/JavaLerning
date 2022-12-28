package com.suleimanov.spring.dao;

import com.suleimanov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person;", new PersonMapper()); // можно использовать new BeanPropertyRowMapper<>(Person.class)
    }

    public Person show(int id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        String SQL = "INSERT INTO person(name, age, email) VALUES (?, ?, ?);";
        jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        String SQL = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?;";
        jdbcTemplate.update(SQL, updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?;", id);
    }

//        private static final String URL = "jdbc:postgresql://localhost:5432/db_for_spring";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "3696";
//    private static final Connection connection;
//
//    static {
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Person> index() {
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//                people.add(person);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return people;
//    }
//
//    public Person show(int id) {
//        Person person = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return person;
//    }
//
//    public void save(Person person) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO person(name, age, email) VALUES (?, ?, ?);");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void update(int id, Person updatePerson) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?;");
//            preparedStatement.setString(1, updatePerson.getName());
//            preparedStatement.setInt(2, updatePerson.getAge());
//            preparedStatement.setString(3, updatePerson.getEmail());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void delete(int id) {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(
//                    "DELETE FROM person WHERE id = ?;");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
