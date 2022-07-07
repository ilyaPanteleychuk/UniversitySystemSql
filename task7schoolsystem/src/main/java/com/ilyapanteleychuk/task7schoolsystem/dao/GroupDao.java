package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupDao {

    private final ConnectionProvider connectionProvider;

    public GroupDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Group getGroupById(int id) {
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
