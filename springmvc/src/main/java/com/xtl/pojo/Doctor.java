package com.xtl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 31925
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor implements Serializable {
    private Integer id;
    private String username;
    private Integer age;
    private Date date;
}
