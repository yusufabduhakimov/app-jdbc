package entity;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    private Integer id;
    private String name;
    private boolean active;

}
