package com.ant.web;

import com.ant.pojo.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "value是干什么？", description = "SpringJdbcController相关的api", tags = "1.1")
public class SpringJdbcController {
    private static final Logger logger = LoggerFactory.getLogger(SpringJdbcController.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * http://localhost:8080/dev/teacher
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有用户信息", notes = "@ApiOperation：描述Controller类中的method接口")
    public List<Teacher> queryTeachers() {
        // 查询所有用户
        String sql = "select * from teacher";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(Teacher.class));
    }

    /**
     * http://localhost:8080/dev/teacher/1
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询（DONE）用户信息", notes = "@ApiOperation：描述Controller类中的method接口xxx")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Integer")
    public Teacher getTeacher(@PathVariable Long id) {
        // 根据主键ID查询
        String sql = "select * from teacher where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户（DONE）")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = "Integer", paramType = "path")
    public int delTeacher(@PathVariable Long id) {
        // 根据主键ID删除用户信息
        String sql = "DELETE FROM teacher WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * 如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam
     * @param teacher
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加用户（DONE）")
    public int addTeacher(@RequestBody Teacher teacher) {
        // 添加用户
        String sql = "insert into teacher(name,del,age,entryTime, salary) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, teacher.getName(), teacher.getDel(), teacher.getAge(), teacher.getEntrytime(), teacher.getSalary());
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键ID修改用户信息")
    public int editTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        logger.info("===========id="+id);
        logger.info("===========teacher="+teacher.toString());
        // 根据主键ID修改用户信息
        String sql = "UPDATE teacher SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, teacher.getName(), id);
    }
}
