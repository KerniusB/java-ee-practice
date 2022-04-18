package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private Integer id;
    private String name;
    private Integer teamId;
}