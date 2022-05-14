package com.pat.exam.repository;

import com.pat.exam.table.UserTable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserTable,Long> {
}
