package com.pat.exam.repository;

import com.pat.exam.table.DocTable;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<DocTable,Long> {
}
