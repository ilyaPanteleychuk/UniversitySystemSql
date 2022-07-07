package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupDaoImpl implements CommonDao<Group>, GroupDao {

    private final ConnectionProvider connectionProvider;

    public GroupDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(Group group) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university." +
                "students(group_id, group_name) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group loadById(int id) {
        Group group = null;
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select * from university.groups where group_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int groupId = resultSet.getInt("group_id");
                String name = resultSet.getString("group_name");
                group = new Group(groupId, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    @Override
    public List<Group> loadAll() {
        List<Group> groups = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "select * from university.groups";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int groupId = resultSet.getInt("group_id");
                String name = resultSet.getString("group_name");
                Group group = new Group(groupId, name);
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public void deleteById(int id) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "delete from university.groups where group_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getGroupOfStudent(int studentId){
        int groupId = 0;
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select group_id from university.students where student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                groupId= resultSet.getInt("group_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupId;
    }

    @Override
    public List<Integer> findAllGroupWithLessOrEqualsStudentCount(int count) {
        List<Integer> groupsId = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "select group_id as ln " +
                "FROM(SELECT group_id, count(*) as Counter " +
                "FROM university.students GROUP BY group_id) " +
                "as group_id where Counter <= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                groupsId.add(resultSet.getInt("ln"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupsId;
    }
}
