package com.rest_example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity// 1. This is a "black mark" for Spring: "Make a table from this class in the DB!"
@Data   // 2. Lombok magic: it will create getters (to get data) and setters (to change data)
@NoArgsConstructor // 3. Empty constructor (needed by Spring for "under the hood" operations)
@AllArgsConstructor // 4. Constructor with all fields (to conveniently create a book in code)
public class Book {
    @Id // 5. Each record in the database must have a unique "passport" (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 6. The database itself will assign IDs (1, 2, 3...)
    private Integer id;

    @NotBlank(message = "Title cannot be empty") // 7. Validation: do not allow saving empty
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Min(value = 1450, message = "Books were not printed then!") // 8. Validation: year check
    private int publicationYear;
}

