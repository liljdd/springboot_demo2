package com.ant.web;

import com.ant.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilj
 * @date 18/08/21
 */
@RestController
@RequestMapping("/teacher")
public class SpringJdbcController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * http://localhost:8080/dev/teacher
     * @return
     */
    @GetMapping
    public List<Teacher> queryTeachers() {
        // 查询所有用户
        String sql = "select * from teacher";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(Teacher.class));
    }

    /**
     * http://localhost:8080/dev/teacher/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        // 根据主键ID查询
        String sql = "select * from teacher where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class));
    }

    @DeleteMapping("/{id}")
    public int delTeacher(@PathVariable Long id) {
        // 根据主键ID删除用户信息
        String sql = "DELETE FROM teacher WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @PostMapping
    public int addTeacher(@RequestBody Teacher teacher) {
        // 添加用户
        String sql = "insert into teacher(name,del,age,entryTime, salary) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, teacher.getName(), teacher.getDel(), teacher.getAge(), teacher.getEntrytime(), teacher.getSalary());
    }


    @PutMapping("/{id}")
    public int editTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        // 根据主键ID修改用户信息
        String sql = "UPDATE teacher SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, teacher.getName(), id);
    }
}
