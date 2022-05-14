package com.pat.exam.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("DOCS")
public class DocTable {
    private @Column("DOC_ID") @Id Long docId;
    private @Column("DOC") String doc;
    private @Column("USER_ID") Long userId;
}
