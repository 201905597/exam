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
@Table("USERS")
public class UserTable {
    private @Column("USER_ID") @Id Long userId;
    private @Column("USER") String user;
    private @Column("COMMENT") String comment;
}
