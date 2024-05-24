package com.example.labmanage.domain.query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String studentId;
    private String username;
    private String major;
    private String mentor;
}
